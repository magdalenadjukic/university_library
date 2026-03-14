/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.knjiga;

import domen.Knjiga;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author DELL
 */
public class UcitajKnjigeSO extends ApstraktnaGenerickaOperacija {
    List<Knjiga> lista;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        lista=broker.getAll(new Knjiga(), "");
    }

    public List<Knjiga> getLista() {
        return lista;
    }

    public void setLista(List<Knjiga> lista) {
        this.lista = lista;
    }
    
}
