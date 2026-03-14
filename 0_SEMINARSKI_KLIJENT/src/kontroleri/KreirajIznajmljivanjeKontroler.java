/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Bibliotekar;
import domen.Iznajmljivanje;
import domen.Knjiga;
import domen.StavkaIznajmljivanja;
import domen.Student;
import forme.KreirajPromeniIznajmljivanjeForma;
import forme.mod.FormaMOD;
import forme.model.ModelTabeleStavkaIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
/**
 *
 * @author DELL
 */
public class KreirajIznajmljivanjeKontroler {
    private final KreirajPromeniIznajmljivanjeForma kif;
    LocalDate danas=null;
    private int idIznajmljivanje=-1;
    
    public KreirajIznajmljivanjeKontroler(KreirajPromeniIznajmljivanjeForma kif) {
        this.kif = kif;
        addActionListener();
    }


    private void pripremiFormuKreiraj() {//kreiranje iznajmljivanja
        Bibliotekar ulogovani=koordinator.Koordinator.getInstance().getUlogovaniBibl();
        popuniCB();
        //stavljam rezervisani id u idIznajmljivanje
        Iznajmljivanje izn=(Iznajmljivanje) koordinator.Koordinator.getInstance().vratiParam("novoizn");
        idIznajmljivanje=izn.getIdIznajmljivanje();
        //tabela sa stavkama
        ModelTabeleStavkaIznajmljivanja mtsi = new ModelTabeleStavkaIznajmljivanja();
        kif.getjTable1stavke().setModel(mtsi);
        
    }

    private void addActionListener() {
        kif.addBtnDodajKnjiguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Knjiga k=(Knjiga) kif.getjComboBox1knjige().getSelectedItem();
                StavkaIznajmljivanja si=new StavkaIznajmljivanja();
                si.setKnjiga(k);
                
                LocalDate localDate = LocalDate.parse(kif.getjTextField1datumIzn().getText(),DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                //ROK VRACANJA
                si.setRokVracanja(Date.valueOf(localDate.plusDays(15)));   
                   
                ModelTabeleStavkaIznajmljivanja mtsi=(ModelTabeleStavkaIznajmljivanja) kif.getjTable1stavke().getModel();
                mtsi.dodajStavku(si);
            }
        });
        kif.addBtnUkloniKnjiguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = kif.getjTable1stavke().getSelectedRow();
                if(red != -1) {
                    ModelTabeleStavkaIznajmljivanja mtsi =  (ModelTabeleStavkaIznajmljivanja) kif.getjTable1stavke().getModel();
                    mtsi.obrisiStavku(red);
                }
                else{
                    JOptionPane.showMessageDialog(kif, "Selektuj knjigu za brisanje.");
                }
            }
        });
        kif.addBtnOMOGUCIizmenuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            kif.getjButton1promeni().setEnabled(true);
            kif.getjTextField1datumIzn().setEnabled(true);
            kif.getjComboBox1student().setEnabled(true);
            kif.getjComboBox1knjige().setEnabled(true);
            
            kif.getjButton1dodajStavku().setEnabled(true);
            kif.getjButton1obrisiStavku().setEnabled(true);
            kif.getjButton1unesiDatVr().setEnabled(true);
            }
        });
        kif.addBtnKreirajIznActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //vec imam id
                //pokupi podatke,kreiraj izn,zapamtiizn
                kreirajIzn(e);
            }

            private void kreirajIzn(ActionEvent e) {
                try {
                    Iznajmljivanje izn=new Iznajmljivanje();
                    izn.setIdIznajmljivanje(idIznajmljivanje);
                    if(idIznajmljivanje==-1){
                        JOptionPane.showMessageDialog(kif, "Sistem ne može da zapamti iznajmljivanje.");
                        return;
                    }
                    String datumIznString = kif.getjTextField1datumIzn().getText();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    if (datumIznString.trim().isEmpty()) {
                        izn.setDatumIznajmljivanja(null);
                    } else {
                        java.util.Date datum = sdf.parse(datumIznString);
                        izn.setDatumIznajmljivanja(datum);
                    }
                    izn.setBibliotekar(koordinator.Koordinator.getInstance().getUlogovaniBibl());
                    izn.setStudent((Student) kif.getjComboBox1student().getSelectedItem());                    
                    ModelTabeleStavkaIznajmljivanja mti = (ModelTabeleStavkaIznajmljivanja) kif.getjTable1stavke().getModel();
                    List<StavkaIznajmljivanja> stavke = mti.getLista();
                    
                    for (StavkaIznajmljivanja si : stavke) {
                        //za svaku stavku stavljam da odgovara ovom iznajmljivanju
                        si.setIznajmljivanje(izn);
                    }
                    //za iznajmljivanje postavljam listu stavki
                    izn.setListaStavki((ArrayList<StavkaIznajmljivanja>) stavke);
                    izn.setUkupanDug(0);
                    komunikacija.Komunikacija.getInstance().zapamtiIznajmljivanje(izn);
                    JOptionPane.showMessageDialog(kif, "Sistem je zapamtio iznajmljivanje.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    kif.dispose();
                } catch (ParseException ex) {
                    System.out.println("Parsiranje greška");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(kif, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        kif.addBtnUnesiDatVrActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = kif.getjTable1stavke().getSelectedRow();
                if (selektovaniRed == -1) {
                JOptionPane.showMessageDialog(kif, "Selektuj stavku iz tabele.");
                return;
                }
                ModelTabeleStavkaIznajmljivanja mtsi = 
               (ModelTabeleStavkaIznajmljivanja) kif.getjTable1stavke().getModel();
                mtsi.setEditabilniRed(selektovaniRed);
            }
        });
        kif.addBtnPromeniIznActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    promeniIznajmljivanje(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(kif, ex.getMessage(), "Greska",JOptionPane.ERROR_MESSAGE);
                }
            }

            private void promeniIznajmljivanje(ActionEvent e) throws Exception {
                try {
                    Iznajmljivanje izn=new Iznajmljivanje();
                    String datumString = kif.getjTextField1datumIzn().getText();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    if (datumString.trim().isEmpty()) {
                        izn.setDatumIznajmljivanja(null);
                    } else {
                        java.util.Date datum = sdf.parse(datumString);
                        izn.setDatumIznajmljivanja(datum);
                    } 
                    izn.setIdIznajmljivanje(Integer.parseInt(kif.getjTextField1idIznajmljivanja().getText()));
                    izn.setBibliotekar(koordinator.Koordinator.getInstance().getUlogovaniBibl());
                    izn.setStudent((Student) kif.getjComboBox1student().getSelectedItem());
                    
                    ModelTabeleStavkaIznajmljivanja mtsi=(ModelTabeleStavkaIznajmljivanja) kif.getjTable1stavke().getModel();
                    List<StavkaIznajmljivanja> stavke=mtsi.getLista();
                   
                    double ukupanDug=0;
                    for (StavkaIznajmljivanja si : stavke) {
                        ukupanDug+=si.getPenali();
                        
                    }
                    izn.setUkupanDug(ukupanDug);
                    
                    for (StavkaIznajmljivanja si : stavke) {
                        si.setIznajmljivanje(izn);
                    }
                    izn.setListaStavki((ArrayList<StavkaIznajmljivanja>) stavke);
       
                    komunikacija.Komunikacija.getInstance().promeniIznajmljivanje(izn);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio iznajmljivanje.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    kif.dispose();
                
                }
                catch (ParseException exc) {
                    System.out.println("Parsiranje greška");
                }
                
            }
        });
    }

    private void popuniCB() {
        List<Student> studenti=komunikacija.Komunikacija.getInstance().ucitajStudente();
        kif.getjComboBox1student().removeAllItems();
        for (Student s : studenti) {
            System.out.println(s.getIme());
            kif.getjComboBox1student().addItem(s);
        }
        
        List<Knjiga> knjige=komunikacija.Komunikacija.getInstance().ucitajKnjige();
        kif.getjComboBox1knjige().removeAllItems();
        for (Knjiga k : knjige) {
            kif.getjComboBox1knjige().addItem(k);
        }
    }

    public void otvoriFormu(FormaMOD formaMOD) {
        
        if(formaMOD==formaMOD.DETALJI){
            //prvobitno onemoguceno za izmenu dok se ne klikne dugme
            kif.getjButton1kreirajIzn().setVisible(false);
            kif.getjTextField1datumIzn().setEnabled(false);
            kif.getjButton1unesiDatVr().setVisible(true);
            kif.getjTextField1idIznajmljivanja().setVisible(true);
            kif.getjLabel5id().setVisible(true);
            kif.getjButton1promeni().setEnabled(false);
            kif.getjTextField1datumIzn().setEnabled(false);
            kif.getjComboBox1student().setEnabled(false);
            kif.getjComboBox1knjige().setEnabled(false);
            kif.getjButton1dodajStavku().setEnabled(false);
            kif.getjButton1obrisiStavku().setEnabled(false);
            kif.getjButton1unesiDatVr().setEnabled(false);
            kif.setTitle("Detalji iznajmljivanja");
            
            //popunjavanje podataka
            popuniFormuZaIzmenu();
           
        }
        else{//KREIRANJE            
            
            danas = LocalDate.now();
            DateTimeFormatter datumform = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            kif.getjTextField1datumIzn().setText(danas.format(datumform));
            kif.getjTextField1idIznajmljivanja().setVisible(false);
            kif.getjLabel5id().setVisible(false);
            kif.getjButton1promeni().setVisible(false);
            kif.getjButton1unesiDatVr().setVisible(false);
            kif.getjButton1omoguci().setVisible(false);
            kif.setTitle("Kreiraj iznajmljivanje");
            
            pripremiFormuKreiraj();
        }
        kif.setVisible(true);
    }

    private void popuniFormuZaIzmenu() {
        Bibliotekar ulogovani=koordinator.Koordinator.getInstance().getUlogovaniBibl();
        Iznajmljivanje izn=(Iznajmljivanje) koordinator.Koordinator.getInstance().vratiParam("iznajmljivanjePromena");
        if(izn==null){
            JOptionPane.showMessageDialog(kif, "Sistem ne može da nađe iznajmljivanje");
            return;
        }
        
        JOptionPane.showMessageDialog(kif, "Sistem je našao iznajmljivanje.");
        
        kif.getjTextField1idIznajmljivanja().setText(izn.getIdIznajmljivanje()+"");
        ModelTabeleStavkaIznajmljivanja mtsi = new ModelTabeleStavkaIznajmljivanja();
        kif.getjTable1stavke().setModel(mtsi);
        mtsi.setLista(izn.getListaStavki());
        popuniCB();
        kif.getjComboBox1student().setSelectedItem(izn.getStudent());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datString = sdf.format(izn.getDatumIznajmljivanja());
        kif.getjTextField1datumIzn().setText(datString);
        
    }
    
    
}
