/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanje;

import domen.Bibliotekar;
import domen.Iznajmljivanje;
import domen.Knjiga;
import domen.Student;
import java.util.Date;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author DELL
 */
public class KreirajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {
    int id;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
        //samo se kreira prazan red u bazi
        if(obj == null || !(obj instanceof Iznajmljivanje)) {
            throw new Exception("Sistem ne može da kreira iznajmljivanje.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        id=broker.create((Iznajmljivanje)obj);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
