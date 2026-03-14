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
public class PromeniStudentSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj == null || !(obj instanceof Student)) {
            throw new Exception("Sistem ne moze da zapamti studenta.");
        }
        Student st=(Student) obj;
        if(st.getIdStudent()==-1){
            throw new Exception("Sistem ne moze da zapamti studenta.");
        }
        if(st.getIme().isEmpty() || st.getPrezime().isEmpty()){
            throw new Exception("Sistem ne moze da zapamti studenta.");
        }
        if(!st.getEmail().contains("@")){
             throw new Exception("Sistem ne moze da zapamti studenta.");
        }
    
        
        List<NivoStudija> ns = broker.getAll(st.getNivoStudija(), null);
        if (ns == null || ns.isEmpty()) {
            throw new Exception("Sistem ne moze da zapamti studenta.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        broker.edit((Student)obj);
    }
    
}
