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
import forme.GlavnaForma;
import forme.mod.FormaMOD;
import forme.model.ModelTabeleStavkaIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class GlavnaFormaKontroler {
    private final GlavnaForma gf;

    public GlavnaFormaKontroler(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
        gf.addJMenuKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student noviStudent=new Student();
                int idStudent=komunikacija.Komunikacija.getInstance().kreirajStudent(noviStudent);
                if(idStudent==-1){
                    JOptionPane.showMessageDialog(gf, "Sistem ne može da kreira studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    noviStudent.setIdStudent(idStudent);
                    System.out.println("rezervisani ID studenta: "+idStudent);
                    JOptionPane.showMessageDialog(gf, "Sistem je kreirao studenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    //kreira se hes mapa za novog studenta koji ima samo id za sad
                    koordinator.Koordinator.getInstance().dodajParam("novistudent", noviStudent);
                    koordinator.Koordinator.getInstance().otvoriDetaljiFormu(FormaMOD.KREIRAJ);
                }
            }
        });
        gf.addJMenuKreirajIZNActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iznajmljivanje i=new Iznajmljivanje();
                int idIznajmljivanje=komunikacija.Komunikacija.getInstance().kreirajIznajmljivanje(i);
                if(idIznajmljivanje==-1){
                    JOptionPane.showMessageDialog(gf, "Sistem ne može da kreira iznajmljivanje.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    //setujem novi rezervisani id
                    i.setIdIznajmljivanje(idIznajmljivanje);
                    System.out.println("rezervisani ID iznajmljivanja: "+idIznajmljivanje);
                    JOptionPane.showMessageDialog(gf, "Sistem je kreirao iznajmljivanje.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    koordinator.Koordinator.getInstance().dodajParam("novoizn", i);
                    koordinator.Koordinator.getInstance().otvoriKreirajIznajmljivanjeFormu(FormaMOD.KREIRAJ);
                }
            }
        });
        
        
        
    }

    public void otvoriFormu() {
        gf.setVisible(true);
        Bibliotekar ulogovani=koordinator.Koordinator.getInstance().getUlogovaniBibl();
        gf.getjLabel2ULOGOVANI().setText("Dobar dan, "+ulogovani.getIme()+" !");
        
    }

    
    
}
