/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.TerminDezurstva;
import forme.UbaciTerminDezurstvaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
/**
 *
 * @author DELL
 */
public class UbaciTerminDezurstvaKontroler {
    
    private final UbaciTerminDezurstvaForma utdf;

    public UbaciTerminDezurstvaKontroler(UbaciTerminDezurstvaForma utdf) {
        this.utdf = utdf;
        addActionListener();
    }

    public void otvoriFormu() {
        utdf.setVisible(true);
    }

    private void addActionListener() {
        utdf.ubaciTDAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vremeOdStr = utdf.getjTextFieldvremeOD().getText().trim();
                String vremeDoStr = utdf.getjTextFieldvremeDO().getText().trim();

                Time vremeOd=null;
                Time vremeDo=null;
                try {
                    vremeOd = Time.valueOf(vremeOdStr); 
                    vremeDo = Time.valueOf(vremeDoStr);
                } catch (IllegalArgumentException exc) {
                    JOptionPane.showMessageDialog(utdf, "Sistem ne može da zapamti termin dežurstva.","Greška",JOptionPane.ERROR_MESSAGE);
                    return;
                }
 
                TerminDezurstva td = new TerminDezurstva(-1, vremeOd, vremeDo);

                try {
                    Komunikacija.getInstance().ubaciTerminDezurstva(td);
                    JOptionPane.showMessageDialog(utdf, "Sistem je zapamtio termin dežurstva.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    utdf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(utdf, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
                
                
            }
        });
    }
    
}
