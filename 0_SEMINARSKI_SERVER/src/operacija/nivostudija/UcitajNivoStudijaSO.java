/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nivostudija;

import domen.NivoStudija;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author DELL
 */
public class UcitajNivoStudijaSO extends ApstraktnaGenerickaOperacija{
    List<NivoStudija> lista;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        lista = broker.getAll(new NivoStudija(), "");
    }

    public List<NivoStudija> getLista() {
        return lista;
    }

    public void setLista(List<NivoStudija> lista) {
        this.lista = lista;
    }

   
    
}
