/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
public class StavkaIznajmljivanja implements ApstraktniDomenskiObjekat{
    private int rb;
    private Iznajmljivanje iznajmljivanje;
    private Knjiga knjiga; 
    private Date rokVracanja;
    private Date datumVracanja;
    private double penali;
    
    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(int rb, Iznajmljivanje iznajmljivanje, Knjiga knjiga, Date rokVracanja, Date datumVracanja, double penali) {
        this.rb = rb;
        this.iznajmljivanje = iznajmljivanje;
        this.knjiga = knjiga;
        this.rokVracanja = rokVracanja;
        this.datumVracanja = datumVracanja;
        this.penali = penali;
    }
    
    public Date getRokVracanja() {
        return rokVracanja;
    }

    public void setRokVracanja(Date rokVracanja) {
        this.rokVracanja = rokVracanja;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public double getPenali() {
        return penali;
    }

    public void setPenali(double penali) {
        this.penali = penali;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
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
        final StavkaIznajmljivanja other = (StavkaIznajmljivanja) obj;
        return this.rb == other.rb;
    }

    @Override
    public String toString() {
        return "Stavka iznajmljivanja: " + rb;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaiznajmljivanja";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            StavkaIznajmljivanja si = new StavkaIznajmljivanja();
            si.setRb(rs.getInt("stavkaiznajmljivanja.rb"));
            si.setRokVracanja(rs.getDate("stavkaiznajmljivanja.rokVracanja"));
            si.setDatumVracanja(rs.getDate("stavkaiznajmljivanja.datumVracanja"));
            si.setPenali(rs.getDouble("stavkaiznajmljivanja.penali"));
            Knjiga knjiga = new Knjiga();
            knjiga.setIdKnjiga(rs.getInt("knjiga.idKnjiga"));
            knjiga.setNazivKnjige(rs.getString("knjiga.nazivKnjige"));
            knjiga.setAutor(rs.getString("knjiga.autor"));
            knjiga.setZanr(Zanr.valueOf(rs.getString("knjiga.zanr")));
            knjiga.setCena(rs.getDouble("knjiga.cena"));
            si.setKnjiga(knjiga);
            
            
            lista.add(si);
        }
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb,idIznajmljivanje,rokVracanja,datumVracanja,penali,idKnjiga";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String rokVrac = (rokVracanja != null) ? "'"+sdf.format(rokVracanja)+"'" : "NULL";
        String datumVrac = (datumVracanja != null) ? "'"+sdf.format(datumVracanja)+"'" : "NULL";

        return rb+","
            +iznajmljivanje.getIdIznajmljivanje()+","
            +rokVrac+","
            +datumVrac+","
            +penali+","
            +knjiga.getIdKnjiga();
    }

    @Override
    public String vratiPrimarniKljuc(){
        return "stavkaiznajmljivanja.rb=" + rb + " AND stavkaiznajmljivanja.idIznajmljivanje=" + iznajmljivanje.getIdIznajmljivanje();
        
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String rokVrac = (rokVracanja != null) ? "'"+sdf.format(rokVracanja)+"'" : "NULL";
        String datumVrac = (datumVracanja != null) ? "'"+sdf.format(datumVracanja)+"'" : "NULL";
        System.out.println("vratiVrednostiZaIzmenu - datumVracanja field: " + datumVracanja);
        System.out.println("vratiVrednostiZaIzmenu - datumVrac string: " + datumVrac);

        return "rb="+rb
            +",idIznajmljivanje="+iznajmljivanje.getIdIznajmljivanje()
            +",rokVracanja="+rokVrac
            +",datumVracanja="+datumVrac
            +",penali="+penali
            +",idKnjiga="+knjiga.getIdKnjiga();
    }
    
  
    
}
