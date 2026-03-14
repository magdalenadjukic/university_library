/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class BibliotekarTermin implements ApstraktniDomenskiObjekat{
    private Bibliotekar bibliotekar;
    private TerminDezurstva terminDezurstva;
    private Date datumDezurstva;

    public BibliotekarTermin() {
    }

    public BibliotekarTermin(Bibliotekar bibliotekar, TerminDezurstva terminDezurstva, Date datumDezurstva) {
        this.bibliotekar = bibliotekar;
        this.terminDezurstva = terminDezurstva;
        this.datumDezurstva = datumDezurstva;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    public TerminDezurstva getTerminDezurstva() {
        return terminDezurstva;
    }

    public void setTerminDezurstva(TerminDezurstva terminDezurstva) {
        this.terminDezurstva = terminDezurstva;
    }

    public Date getDatumDezurstva() {
        return datumDezurstva;
    }

    public void setDatumDezurstva(Date datumDezurstva) {
        this.datumDezurstva = datumDezurstva;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final BibliotekarTermin other = (BibliotekarTermin) obj;
        if (!Objects.equals(this.bibliotekar, other.bibliotekar)) {
            return false;
        }
        if (!Objects.equals(this.terminDezurstva, other.terminDezurstva)) {
            return false;
        }
        return Objects.equals(this.datumDezurstva, other.datumDezurstva);
    }

    @Override
    public String toString() {
        return  "bibliotekar=" + bibliotekar + ", terminDezurstva=" + terminDezurstva + ", datumDezurstva=" + datumDezurstva + ' ';
    }

    @Override
    public String vratiNazivTabele() {
        return "bibliotekartermin";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idBibliotekar,idTerminDezurstva,datumDezurstva";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return bibliotekar.getIdBibliotekar()+","+terminDezurstva.getIdTerminDezurstva()+",'"+datumDezurstva+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "bibliotekartermin.idBibliotekar="+bibliotekar.getIdBibliotekar()+" AND bibliotekartermin.idTerminDezurstva="+terminDezurstva.getIdTerminDezurstva();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "idBibliotekar="+bibliotekar.getIdBibliotekar()+",idTerminDezurstva="+terminDezurstva.getIdTerminDezurstva()+",datumDezurstva='"+datumDezurstva+"'";
                
    }
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
