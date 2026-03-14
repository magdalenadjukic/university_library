/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import com.sun.java.accessibility.util.AWTEventMonitor;
import domen.Bibliotekar;
import forme.GlavnaForma;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class LoginKontroler {
    private final LoginForma lf;

    public LoginKontroler(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }
    
    private void addActionListeners() {
        
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prijava(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(lf, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void prijava(ActionEvent e) throws Exception {
                String korisnickoIme=lf.getjTextField1korIme().getText().trim();
                String lozinka=String.valueOf(lf.getjPasswordField1().getPassword());
                komunikacija.Komunikacija.getInstance().konekcija();
                try {
                    Bibliotekar b= komunikacija.Komunikacija.getInstance().prijava(korisnickoIme,lozinka);
                    if(b==null){
                        JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                        koordinator.Koordinator.getInstance().setUlogovaniBibl(b);
                        JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        koordinator.Koordinator.getInstance().otvoriGlavnuFormu(); 
                        lf.dispose();
                    
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(lf, exc.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

                }
                
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
}
