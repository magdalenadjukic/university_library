/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.bibliotekar;

import domen.Bibliotekar;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author DELL
 */
public class UcitajBibliotekarSO extends ApstraktnaGenerickaOperacija {
    List<Bibliotekar> lista;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        lista=broker.getAll(new Bibliotekar(), "");
    }

    public List<Bibliotekar> getLista() {
        return lista;
    }

    public void setLista(List<Bibliotekar> lista) {
        this.lista = lista;
    }
    
}
