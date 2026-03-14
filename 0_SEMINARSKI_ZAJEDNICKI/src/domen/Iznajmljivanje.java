/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
/**
 *
 * @author DELL
 */
public class Iznajmljivanje implements ApstraktniDomenskiObjekat{
    private int idIznajmljivanje;
    private Date datumIznajmljivanja;
    private Bibliotekar bibliotekar;
    private Student student;
    private List<StavkaIznajmljivanja> listaStavki;
    private double ukupanDug; 
    
    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje, Date datumIznajmljivanja,  Bibliotekar bibliotekar, Student student,List<StavkaIznajmljivanja> listaStavki,double ukupanDug) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.bibliotekar = bibliotekar;
        this.student = student;
        this.listaStavki=listaStavki;
        this.ukupanDug=ukupanDug;
    }

    public List<StavkaIznajmljivanja> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaIznajmljivanja> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public Date getDatumIznajmljivanja() {
        return datumIznajmljivanja;
    }

    public void setDatumIznajmljivanja(Date datumIznajmljivanja) {
        this.datumIznajmljivanja = datumIznajmljivanja;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getUkupanDug() {
        return ukupanDug;
    }

    public void setUkupanDug(double ukupanDug) {
        this.ukupanDug = ukupanDug;
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        return this.idIznajmljivanje == other.idIznajmljivanje;
    }

    

    @Override
    public String toString() {
        return "listaStavki=" + listaStavki;
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
           Iznajmljivanje i=new Iznajmljivanje();
           i.setIdIznajmljivanje(rs.getInt("iznajmljivanje.idIznajmljivanje"));
           i.setDatumIznajmljivanja(new Date(rs.getDate("iznajmljivanje.datumIznajmljivanja").getTime()));
           i.setUkupanDug(rs.getDouble("iznajmljivanje.ukupanDug"));
           Bibliotekar b=new Bibliotekar();
           b.setIdBibliotekar(rs.getInt("bibliotekar.idBibliotekar"));
           b.setIme(rs.getString("bibliotekar.ime"));
           b.setPrezime(rs.getString("bibliotekar.prezime"));
           b.setKorisnickoIme(rs.getString("bibliotekar.korisnickoIme"));
           b.setEmail(rs.getString("bibliotekar.email"));
           b.setLozinka(rs.getString("bibliotekar.lozinka")); 
           NivoStudija ns=new NivoStudija();
           ns.setIdNivoStudija(rs.getInt("nivostudija.idNivoStudija"));
           ns.setNazivNivoa(rs.getString("nivostudija.nazivNivoa"));
           Student s=new Student();
           s.setIdStudent(rs.getInt("student.idStudent"));
           s.setIme(rs.getString("student.ime"));
           s.setPrezime(rs.getString("student.prezime"));
           s.setEmail(rs.getString("student.email"));
           s.setNivoStudija(ns);
           
           i.setBibliotekar(b);
           i.setStudent(s);
           lista.add(i);
        }
            
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumIznajmljivanja,datumVracanja,ukupanDug,idBibliotekar,idStudent";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {//IZMENA
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "'"+sdf.format(datumIznajmljivanja)+"',"+ukupanDug+","+bibliotekar.getIdBibliotekar()+","+student.getIdStudent(); 
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje.idIznajmljivanje="+idIznajmljivanje;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("vratiobj iz rs."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {    
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return "idIznajmljivanje="+idIznajmljivanje+",datumIznajmljivanja='"+sdf.format(datumIznajmljivanja)+"',ukupanDug="+ukupanDug+",idBibliotekar="+bibliotekar.getIdBibliotekar()+",idStudent="+student.getIdStudent();
    
    }

    
    
}
