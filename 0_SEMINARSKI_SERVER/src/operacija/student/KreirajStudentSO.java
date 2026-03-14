/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.student;

import domen.Student;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author DELL
 */
public class KreirajStudentSO extends ApstraktnaGenerickaOperacija {
    int id;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
        if(obj == null || !(obj instanceof Student)) {
            throw new Exception("Sistem ne može da kreira studenta.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        id=broker.create((Student)obj);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    

    
    
}
