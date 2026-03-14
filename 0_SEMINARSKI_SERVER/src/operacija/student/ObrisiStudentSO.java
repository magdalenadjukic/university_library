/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.student;

import domen.Iznajmljivanje;
import domen.Student;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author DELL
 */
public class ObrisiStudentSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object obj) throws Exception {
        
        if (obj == null || !(obj instanceof Student)) {
            throw new Exception("Sistem ne može da obriše studenta.");
        }
        
        Student student = (Student) obj;
        
        if (student.getIme() == null || student.getPrezime() == null) {
            throw new Exception("Sistem ne može da obriše studenta.");
        }
        if (student.getIme().isEmpty() || student.getPrezime().isEmpty()) {
            throw new Exception("Sistem ne može da obriše studenta.");
        }
        
        String uslov = " JOIN student ON iznajmljivanje.idStudent = student.idStudent"
        + " JOIN nivostudija ON student.idNivoStudija = nivostudija.idNivoStudija"
        + " JOIN bibliotekar ON iznajmljivanje.idBibliotekar = bibliotekar.idBibliotekar"
        + " WHERE student.idStudent = " + student.getIdStudent();

        List<Iznajmljivanje> iznajmljivanja = broker.getAll(new Iznajmljivanje(), uslov);
        if (iznajmljivanja != null && !iznajmljivanja.isEmpty()) {
            throw new Exception("Sistem ne može da obriše studenta.");
        }
    
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        broker.delete((Student)obj);
    }
    
}
