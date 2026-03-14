/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class NivoStudija implements ApstraktniDomenskiObjekat{
    private int idNivoStudija;
    private String nazivNivoa;

    public NivoStudija() {
    }

    public NivoStudija(int idNivoStudija, String nazivNivoa) {
        this.idNivoStudija = idNivoStudija;
        this.nazivNivoa = nazivNivoa;
    }

    public int getIdNivoStudija() {
        return idNivoStudija;
    }

    public void setIdNivoStudija(int idNivoStudija) {
        this.idNivoStudija = idNivoStudija;
    }

    public String getNazivNivoa() {
        return nazivNivoa;
    }

    public void setNazivNivoa(String nazivNivoa) {
        this.nazivNivoa = nazivNivoa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NivoStudija other = (NivoStudija) obj;
        return Objects.equals(this.nazivNivoa, other.nazivNivoa);
    }

    @Override
    public String toString() {
        return nazivNivoa;
    }

    @Override
    public String vratiNazivTabele() {
        return "nivostudija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
         List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        
        while(rs.next()){
            int idNivoStudija=rs.getInt("nivostudija.idNivoStudija");
            String nazivNivoa=rs.getString("nivostudija.nazivNivoa");
            NivoStudija ns=new NivoStudija(idNivoStudija, nazivNivoa);
            lista.add(ns);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivNivoa";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivNivoa+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "nivostudija.idNivoStudija="+idNivoStudija;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "idNivoStudija="+idNivoStudija+",nazivNivoa='"+nazivNivoa+"'";
    }
    
}
