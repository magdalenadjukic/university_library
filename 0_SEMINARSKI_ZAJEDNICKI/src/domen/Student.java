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
public class Student implements ApstraktniDomenskiObjekat{
    private int idStudent;
    private String ime;
    private String prezime;
    private String email;
    private NivoStudija nivoStudija; 

    public Student() {
    }

    public Student(int idStudent, String ime, String prezime, String email, NivoStudija nivoStudija) {
        this.idStudent = idStudent;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.nivoStudija = nivoStudija;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
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

    public NivoStudija getNivoStudija() {
        return nivoStudija;
    }

    public void setNivoStudija(NivoStudija nivoStudija) {
        this.nivoStudija = nivoStudija;
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "student ";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            Student s=new Student();
            s.setIdStudent(rs.getInt("student.idStudent"));
            s.setIme(rs.getString("student.ime"));
            s.setPrezime(rs.getString("student.prezime"));
            s.setEmail(rs.getString("student.email"));
            NivoStudija ns=new NivoStudija();
            ns.setIdNivoStudija(rs.getInt("nivostudija.idNivoStudija"));
            ns.setNazivNivoa(rs.getString("nivostudija.nazivNivoa"));
            s.setNivoStudija(ns);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,idNivoStudija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+email+"',"+nivoStudija.getIdNivoStudija();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "student.idStudent="+idStudent;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        //idStudent, ime, prezime, email, idNivoStudija
        return "idStudent="+idStudent+",ime='"+ime+"',prezime='"+prezime+"',email='"+email+"',idNivoStudija="+nivoStudija.getIdNivoStudija();
    }
    
    
}
