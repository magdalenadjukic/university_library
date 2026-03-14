/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.NivoStudija;
import domen.Student;
import forme.DetaljiForma;
import forme.PrikazStudenataForma;
import forme.mod.FormaMOD;
import forme.model.ModelTabeleStudenti;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author DELL
 */
public class PrikazStudenataKontroler {
    private final PrikazStudenataForma psf;

    public PrikazStudenataKontroler(PrikazStudenataForma psf) {
        this.psf = psf;
        addActionListener();
    }
   

    public void otvoriFormu() {
        pripremiFormu();
        psf.setVisible(true);
        
    }

    private void pripremiFormu() {
        List<Student> listaStudenata=komunikacija.Komunikacija.getInstance().ucitajStudente();
        ModelTabeleStudenti mts=new ModelTabeleStudenti(listaStudenata);
        psf.getjTable1studenti().setModel(mts);
        
        List<NivoStudija> nivoStudija = Komunikacija.getInstance().ucitajNivoStudija();
        psf.getjComboBox1NivoSt().removeAllItems();
        for (NivoStudija n : nivoStudija) {
            psf.getjComboBox1NivoSt().addItem(n);
        }
        
    }

    private void addActionListener() {
       
        psf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ime,prezime,nivo studija
                String ime=psf.getjTextField1imeStudenta().getText().trim();
                String prezime=psf.getjTextField1prezimeStudenta().getText().trim();
                NivoStudija nivoStudija=(NivoStudija) psf.getjComboBox1NivoSt().getSelectedItem();
                
                Student kriterijum = new Student();
                kriterijum.setIme(ime);
                kriterijum.setPrezime(prezime);
                kriterijum.setNivoStudija(nivoStudija);

                List<Student> rezultat=new ArrayList<>();
                try {
                    rezultat = komunikacija.Komunikacija.getInstance().pretraziStudente(kriterijum);
                    ModelTabeleStudenti mts = new ModelTabeleStudenti(rezultat);
                    psf.getjTable1studenti().setModel(mts);
                    if(rezultat.isEmpty()){
                       JOptionPane.showMessageDialog(psf, "Sistem ne može da nađe studente po zadatim kriterijumima." , "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                       JOptionPane.showMessageDialog(psf, "Sistem je našao studente po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(psf, exc.getMessage(), "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                }
                
            }
        }); 
        psf.addBtnResetTableActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });
        psf.addBtnDetaljiTableActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //za izabranog stud iz tabele se prikazuju detalji u novoj formi
                int selektovaniRed=psf.getjTable1studenti().getSelectedRow();
                if(selektovaniRed==-1){
                    JOptionPane.showMessageDialog(psf, "Sistem ne može da nađe studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(psf, "Sistem je našao studenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleStudenti mts=(ModelTabeleStudenti) psf.getjTable1studenti().getModel();
                    Student student=mts.getLista().get(selektovaniRed);
                    Koordinator.getInstance().dodajParam("student", student);
                    koordinator.Koordinator.getInstance().otvoriDetaljiFormu(FormaMOD.DETALJI);
                    
                }
                
            }
        });
        
        
    }

    public void osveziFormu() {
        pripremiFormu();
    }

    
    
}
