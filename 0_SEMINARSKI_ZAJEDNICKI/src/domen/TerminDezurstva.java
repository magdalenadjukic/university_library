/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.sql.Time;
/**
 *
 * @author DELL
 */
public class TerminDezurstva implements ApstraktniDomenskiObjekat{
    private int idTerminDezurstva;
    private Time vremeOd;
    private Time vremeDo;

    public TerminDezurstva() {
    }

    public TerminDezurstva(int idTerminDezurstva, Time vremeOd, Time vremeDo) {
        this.idTerminDezurstva = idTerminDezurstva;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
    }

    public int getIdTerminDezurstva() {
        return idTerminDezurstva;
    }

    public void setIdTerminDezurstva(int idTerminDezurstva) {
        this.idTerminDezurstva = idTerminDezurstva;
    }

    public Time getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(Time vremeOd) {
        this.vremeOd = vremeOd;
    }

    public Time getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(Time vremeDo) {
        this.vremeDo = vremeDo;
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
        final TerminDezurstva other = (TerminDezurstva) obj;
        if (!Objects.equals(this.vremeOd, other.vremeOd)) {
            return false;
        }
        return Objects.equals(this.vremeDo, other.vremeDo);
    }

    @Override
    public String toString() {
        return vremeOd + " - " + vremeDo;
    }

    @Override
    public String vratiNazivTabele() {
        return "termindezurstva";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        
         while (rs.next()) {
        int idTerminDezurstva = rs.getInt("termindezurstva.idTerminDezurstva");
        Time vremeOd = rs.getTime("termindezurstva.vremeOd");
        Time vremeDo = rs.getTime("termindezurstva.vremeDo");
        TerminDezurstva td = new TerminDezurstva(idTerminDezurstva, vremeOd, vremeDo);
        lista.add(td);
    }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "vremeOd,vremeDo";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+vremeOd+"','"+vremeDo+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "termindezurstva.idTerminDezurstva="+idTerminDezurstva;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "idTerminDezurstva="+idTerminDezurstva+",vremeOd='"+vremeOd+"',vremeDo='"+vremeDo+"'";
    }
    
    
}
