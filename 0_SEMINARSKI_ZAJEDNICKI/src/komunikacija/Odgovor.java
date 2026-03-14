/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class Odgovor implements Serializable {
    
    private Object odgovor;
    private Exception exc;
    
    public Odgovor() {
    }

    public Odgovor(Object odgovor, Exception exc) {
        this.odgovor = odgovor;
        this.exc = exc;
    }

    public Odgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
    public Exception getExc() {
        return exc;
    }

    public void setExc(Exception exc) {
        this.exc = exc;
    }
}
