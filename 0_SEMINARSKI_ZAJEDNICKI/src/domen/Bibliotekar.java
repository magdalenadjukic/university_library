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
public class Bibliotekar implements ApstraktniDomenskiObjekat{
    private int idBibliotekar;
    private String ime;
    private String prezime;
    private String korisnickoIme; 
    private String email;
    private String lozinka;

    public Bibliotekar() {
    }

    public Bibliotekar(int idBibliotekar, String ime, String prezime, String korisnickoIme, String email, String lozinka) 
    {
        this.idBibliotekar = idBibliotekar;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme=korisnickoIme;
        this.email = email;
        this.lozinka = lozinka;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public int getIdBibliotekar() {
        return idBibliotekar;
    }

    public void setIdBibliotekar(int idBibliotekar) {
        this.idBibliotekar = idBibliotekar;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Bibliotekar other = (Bibliotekar) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.lozinka, other.lozinka);
    }

    
    
    

    @Override
    public String toString() {
        return ime;
    }

    @Override
    public String vratiNazivTabele() {
        return "bibliotekar";
    }


    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,korisnickoIme,email,lozinka";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+korisnickoIme+"','"+email+"','"+lozinka+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "bibliotekar.idBibliotekar="+idBibliotekar;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        
        return "idBibliotekar='"+idBibliotekar+"',ime='"+ime+"',prezime='"+prezime+"',korisnickoIme='"+korisnickoIme+"',email='"+email+"',lozinka='"+lozinka+"'";
        
    }
     @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        
        while(rs.next()){
            int idBibliotekar=rs.getInt("bibliotekar.idBibliotekar");
            String ime=rs.getString("bibliotekar.ime");
            String prezime=rs.getString("bibliotekar.prezime");
            String korisnickoIme=rs.getString("bibliotekar.korisnickoIme");
            String email=rs.getString("bibliotekar.email");
            String lozinka=rs.getString("bibliotekar.lozinka");
            Bibliotekar b=new Bibliotekar(idBibliotekar, ime, prezime,korisnickoIme, email, lozinka);
            lista.add(b);
        }
        
        return lista;
    }
    
      @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
