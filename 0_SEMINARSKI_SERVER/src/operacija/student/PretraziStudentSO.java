/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.student;

import domen.NivoStudija;
import domen.Student;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author DELL
 */
public class PretraziStudentSO extends ApstraktnaGenerickaOperacija{
    List<Student> listaStudenata;

    
    @Override
    protected void preduslovi(Object obj) throws Exception {
        String uslov = " JOIN nivostudija ON student.idNivoStudija = nivostudija.idNivoStudija";
        List<Student> studenti = broker.getAll(new Student(), uslov);
        if (studenti == null || studenti.isEmpty()) {
            throw new Exception("Sistem ne moze da nađe studente po zadatim kriterijumima.");
        }

        List<NivoStudija> nivoStudija = broker.getAll(new NivoStudija(), null);
        if (nivoStudija == null || nivoStudija.isEmpty()) {
            throw new Exception("Sistem ne moze da nađe studente po zadatim kriterijumima.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        
        String uslov = " JOIN nivostudija ON student.idNivoStudija=nivostudija.idNivoStudija WHERE 1=1 ";
        
        if(obj!=null){
            Student kriterijum = (Student) obj;
            
            if (kriterijum.getIme() != null && !kriterijum.getIme().isEmpty()) {
        uslov += " AND student.ime LIKE '%" + kriterijum.getIme() + "%'";
        }
        if (kriterijum.getPrezime() != null && !kriterijum.getPrezime().isEmpty()) {
        uslov += " AND student.prezime LIKE '%" + kriterijum.getPrezime() + "%'";
        }
        if (kriterijum.getNivoStudija() != null) {
        uslov += " AND student.idNivoStudija=" + kriterijum.getNivoStudija().getIdNivoStudija();
        }
        }
        
    
        listaStudenata = broker.getAll(new Student(), uslov);
    }
    public List<Student> getListaStudenata() {
        return listaStudenata;
    }

    public void setListaStudenata(List<Student> listaStudenata) {
        this.listaStudenata = listaStudenata;
    }
    
}
