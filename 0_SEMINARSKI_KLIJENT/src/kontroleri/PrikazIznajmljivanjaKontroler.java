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
import forme.PrikazIznajmljivanjaForma;
import forme.mod.FormaMOD;
import forme.model.ModelTabeleIznajmljivanje;
import forme.model.ModelTabeleStavkaIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author DELL
 */
public class PrikazIznajmljivanjaKontroler {
    
    private final PrikazIznajmljivanjaForma pif;

    public PrikazIznajmljivanjaKontroler(PrikazIznajmljivanjaForma pif) {
        this.pif = pif;
        addActionListener();
    }
    public void otvoriFormu() {
        try {
            pripremiFormu();
        } catch (Exception ex) {
            Logger.getLogger(PrikazIznajmljivanjaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        pif.setVisible(true);
        
    }
    private void addActionListener() {
        pif.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Bibliotekar b = (Bibliotekar) pif.getjComboBox1bibliotekar().getSelectedItem();
               Student s = (Student) pif.getjComboBox1student().getSelectedItem();
               String datumIzn = pif.getjTextField1datumIZn().getText();
               Date datum = null;
               if (!datumIzn.isEmpty()) {
                try {
                    datum = new SimpleDateFormat("dd.MM.yyyy").parse(datumIzn);
                } catch (ParseException ex) {
                    System.out.println("Neispravan unos datuma");
                    JOptionPane.showMessageDialog(pif, "Datum uneti u formatu: dd.MM.yyyy");
                    return;
                }
                }

                Iznajmljivanje kriterijum = new Iznajmljivanje();
                kriterijum.setBibliotekar(b);
                kriterijum.setStudent(s);
                kriterijum.setDatumIznajmljivanja(datum);
                try {
                    List<Iznajmljivanje> rezultat = Komunikacija.getInstance().pretraziIznajmljivanja(kriterijum);
                    ModelTabeleIznajmljivanje mti = new ModelTabeleIznajmljivanje(rezultat);
                    pif.getjTable1Iznajmljivanja().setModel(mti);

                    if (rezultat.isEmpty()) {
                        JOptionPane.showMessageDialog(pif, "Sistem ne može da nađe iznajmljivanja po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(pif, "Sistem je našao iznajmljivanja po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    }   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pif, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

                }
                
                    
        }
        });
        pif.addBtnResetActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pif.getjTextField1datumIZn().setText("");
                try {
                    pripremiFormu();
                } catch (Exception ex) {
                    Logger.getLogger(PrikazIznajmljivanjaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        pif.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //otvara se forma sa vec popunjenim podacima za izmenu
                int red = pif.getjTable1Iznajmljivanja().getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(pif, "Sistem ne može da nađe iznajmljivanje", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getjTable1Iznajmljivanja().getModel();
                    Iznajmljivanje izn = mti.getLista().get(red);
                    
                    List<StavkaIznajmljivanja> stavke = izn.getListaStavki();
                    izn.setListaStavki((ArrayList<StavkaIznajmljivanja>) stavke);
                    
                    koordinator.Koordinator.getInstance().dodajParam("iznajmljivanjePromena", izn);
                    koordinator.Koordinator.getInstance().otvoriKreirajIznajmljivanjeFormu(FormaMOD.DETALJI);
                }
            }
        });
    }

    public void pripremiFormu() throws Exception {
        //iznajmljivanja
        List<Iznajmljivanje> listaIzn=Komunikacija.getInstance().ucitajIznajmljivanja();
        
        //za ukupan dug
        for (Iznajmljivanje izn : listaIzn) {
        double ukupanDug = 0;
        if (izn.getListaStavki() != null) {
            for (StavkaIznajmljivanja si : izn.getListaStavki()) {
                if (si.getRokVracanja() != null && si.getKnjiga() != null) {
                    java.util.Date krajnji;
                    if (si.getDatumVracanja() != null) {
                        krajnji = si.getDatumVracanja();
                    } else {
                        krajnji = new java.util.Date();
                    }
                    long diffMs = krajnji.getTime() - si.getRokVracanja().getTime();
                    long danaKasnjenja = diffMs / (1000L * 60 * 60 * 24);
                    if (danaKasnjenja > 0) {
                        double penali = danaKasnjenja * 0.10 * si.getKnjiga().getCena();
                        si.setPenali(Math.min(penali, si.getKnjiga().getCena()));
                    }
                }
                ukupanDug += si.getPenali();
            }
        }
        izn.setUkupanDug(ukupanDug);
        //Komunikacija.getInstance().promeniIznajmljivanje(izn);
        }
        
        ModelTabeleIznajmljivanje mti=new ModelTabeleIznajmljivanje(listaIzn);
        pif.getjTable1Iznajmljivanja().setModel(mti);

        //combo box student 
        List<Student> studenti = Komunikacija.getInstance().ucitajStudente();
        pif.getjComboBox1student().removeAllItems();
        pif.getjComboBox1student().addItem(null);
        for (Student s : studenti) {
            pif.getjComboBox1student().addItem(s);
        }
        pif.getjComboBox1student().setSelectedItem(null);
        //bibliotekar
        List<Bibliotekar> bibliotekari = Komunikacija.getInstance().ucitajBibliotekare();
        
        pif.getjComboBox1bibliotekar().removeAllItems();
        pif.getjComboBox1bibliotekar().addItem(null);
        for (Bibliotekar b : bibliotekari) {
            pif.getjComboBox1bibliotekar().addItem(b);
        }
        pif.getjComboBox1bibliotekar().setSelectedItem(null); 
        
    }


    
}
