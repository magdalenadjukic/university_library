/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.NivoStudija;
import domen.Student;
import forme.DetaljiForma;
import forme.mod.FormaMOD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Action;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author DELL
 */
public class DetaljiStudentaKontroler {
    private final DetaljiForma df;
    private int idStudent=-1;
    
    public DetaljiStudentaKontroler(DetaljiForma df) {
        this.df = df;
        addActionListeners();
    }
    public void otvoriFormu(FormaMOD formaMOD) {
        if(formaMOD==formaMOD.KREIRAJ){
            df.getjButton1kreirajst().setVisible(true);
            df.getjButton1OBRISI().setVisible(false);
            df.getjButton1IZMENI().setVisible(false);
            df.getjButton1omoguciIzmenu().setVisible(false);
            df.getjTextField1ID().setVisible(false);
            df.getjLabel1ID().setVisible(false);
            
            pripremiFormuKreiranje();
        }
        else{
            df.getjButton1kreirajst().setVisible(false);
            pripremiFormuIzmena();

        }
        df.setVisible(true);
    }
    
    
    private void addActionListeners() {
        df.addBtnOmoguciIzmenuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                df.getjButton1OBRISI().setEnabled(true);
                df.getjButton1IZMENI().setEnabled(true);
                
                Student s = (Student) Koordinator.getInstance().vratiParam("student");
                if(s == null) {
                    JOptionPane.showMessageDialog(df, "Sistem ne moze da nađe studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                df.getjTextField1ID().setText(s.getIdStudent()+" ");
                df.getjTextFieldIME().setText(s.getIme());
                df.getjTextField2PREZIME().setText(s.getPrezime());
                df.getjTextField3EMAIL().setText(s.getEmail());
                df.getjComboBox1NIVOST().setSelectedItem(s.getNivoStudija());
                
                df.getjTextFieldIME().setEnabled(true);
                df.getjTextField2PREZIME().setEnabled(true);
                df.getjTextField3EMAIL().setEnabled(true);
                df.getjComboBox1NIVOST().setEnabled(true);
                
                
            }
            
        });
        df.addBtnPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idStudent = Integer.parseInt(df.getjTextField1ID().getText().trim());
                String ime = df.getjTextFieldIME().getText().trim();
                String prezime = df.getjTextField2PREZIME().getText().trim();
                String email=df.getjTextField3EMAIL().getText().trim();
                NivoStudija ns = (NivoStudija) df.getjComboBox1NIVOST().getSelectedItem();
                
                Student student = new Student(idStudent,ime,prezime,email,ns);
                
                try{
                    Komunikacija.getInstance().promeniStudent(student);
                    Koordinator.getInstance().osveziFormu();
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio studenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    df.dispose();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    df.dispose();
                }
                
            }
        });
        df.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idStudent = Integer.parseInt(df.getjTextField1ID().getText().trim());
                String ime = df.getjTextFieldIME().getText().trim();
                String prezime = df.getjTextField2PREZIME().getText().trim();
                String email=df.getjTextField3EMAIL().getText().trim();
                NivoStudija ns = (NivoStudija) df.getjComboBox1NIVOST().getSelectedItem();
                
                Student student = new Student(idStudent,ime,prezime,email,ns);
                
                try {
                    Komunikacija.getInstance().obrisiStudent(student);
                    Koordinator.getInstance().osveziFormu();
                    JOptionPane.showMessageDialog(df, "Sistem je obrisao studenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                   // pripremiFormu();
                    df.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(df, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    df.dispose();
                }
                
            }
        });
        df.addBtnKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {                    
                    
                    String ime=df.getjTextFieldIME().getText().trim();
                    String prezime=df.getjTextField2PREZIME().getText().trim();
                    String email=df.getjTextField3EMAIL().getText().trim();
                    NivoStudija ns=(NivoStudija) df.getjComboBox1NIVOST().getSelectedItem();
                    Student noviStudent=new Student(idStudent,ime,prezime,email,ns);
                    Komunikacija.getInstance().zapamtiStudenta(noviStudent);
                    
                    JOptionPane.showMessageDialog(df, "Sistem je zapamtio studenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    
                    df.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(df, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
    }

    private void pripremiFormuIzmena() {
        
        ucitajComboBoxStudije();
        
        df.getjTextField1ID().setEnabled(false);
        df.getjButton1IZMENI().setEnabled(false);
        
        
        Student student=(Student) koordinator.Koordinator.getInstance().vratiParam("student");
        if(student == null) {
            JOptionPane.showMessageDialog(df, "Sistem ne moze da nađe studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        df.getjTextField1ID().setText(student.getIdStudent()+" ");
        df.getjTextFieldIME().setText(student.getIme());
        df.getjTextField2PREZIME().setText(student.getPrezime());
        df.getjComboBox1NIVOST().setSelectedItem(student.getNivoStudija());
        df.getjTextField3EMAIL().setText(student.getEmail());
        
        df.getjTextFieldIME().setEnabled(false);
        df.getjTextField2PREZIME().setEnabled(false);
        df.getjComboBox1NIVOST().setEnabled(false);
        df.getjTextField3EMAIL().setEnabled(false);
    }

    private void ucitajComboBoxStudije() {
        List<NivoStudija> nivoi = Komunikacija.getInstance().ucitajNivoStudija();
        df.getjComboBox1NIVOST().removeAllItems();
        for (NivoStudija ns : nivoi) {
            df.getjComboBox1NIVOST().addItem(ns);
        }
    }

    private void pripremiFormuKreiranje() {
        //ako je kreiranje vrati novog studenta (ima samo ID za sad)
        Student student=(Student) koordinator.Koordinator.getInstance().vratiParam("novistudent");
        //i postavi taj id ovde
        idStudent=student.getIdStudent();
        
        List<NivoStudija> nivoStudija = Komunikacija.getInstance().ucitajNivoStudija();
        df.getjComboBox1NIVOST().removeAllItems();
        for (NivoStudija n : nivoStudija) {
            df.getjComboBox1NIVOST().addItem(n);
        }
    }

   
    
}
