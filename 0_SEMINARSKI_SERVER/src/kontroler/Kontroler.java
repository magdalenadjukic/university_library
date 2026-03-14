/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Bibliotekar;
import domen.Iznajmljivanje;
import domen.Knjiga;
import domen.NivoStudija;
import domen.StavkaIznajmljivanja;
import domen.Student;
import domen.TerminDezurstva;
import java.util.ArrayList;
import java.util.List;
import operacija.bibliotekar.PrijaviBibliotekarSO;
import operacija.bibliotekar.UcitajBibliotekarSO;
import operacija.iznajmljivanje.KreirajIznajmljivanjeSO;
import operacija.iznajmljivanje.PretraziIznajmljivanjeSO;
import operacija.iznajmljivanje.PromeniIznajmljivanjeSO;
import operacija.iznajmljivanje.ZapamtiIznajmljivanjeSO;
import operacija.knjiga.UcitajKnjigeSO;
import operacija.nivostudija.UcitajNivoStudijaSO;
import operacija.student.KreirajStudentSO;
import operacija.student.ObrisiStudentSO;
import operacija.student.PretraziStudentSO;
import operacija.student.PromeniStudentSO;
import operacija.termindezurstva.UbaciTerminDezurstvaSO;

/**
 *
 * @author DELL
 */
public class Kontroler {
    
    private static Kontroler instance;
    private List<Bibliotekar> prijavljeniBibl=new ArrayList<>();
           
    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if(instance==null)
            instance=new Kontroler();
        return instance;
    }

    public List<Bibliotekar> getPrijavljeniBibl() {
        return prijavljeniBibl;
    }

    public void setPrijavljeniBibl(List<Bibliotekar> prijavljeniBibl) {
        this.prijavljeniBibl = prijavljeniBibl;
    }
    
    public Bibliotekar prijaviBibliotekar(Bibliotekar b) throws Exception {
        PrijaviBibliotekarSO operacija=new PrijaviBibliotekarSO();
        operacija.izvrsi(b, null);
        prijavljeniBibl.add(b);
       // System.out.println("klasa kontroler, prijavljivanje:"+operacija.getBibliotekar());
        return operacija.getBibliotekar();
    }
    public List<Bibliotekar> ucitajBibliotekare() throws Exception {
        
        UcitajBibliotekarSO operacija=new UcitajBibliotekarSO();
        operacija.izvrsi(null, null);
        //System.out.println("klasa kontroler ucitaj bibl:"+operacija.getLista());
        return operacija.getLista();
    }
    
    public List<Student> ucitajStudente() throws Exception {
        PretraziStudentSO operacija=new PretraziStudentSO();
        operacija.izvrsi(null, null);
        //System.out.println("klasa kontroler:"+operacija.getListaStudenata());
        return operacija.getListaStudenata();
    }

    public void obrisiStudent(Student s) throws Exception {
        ObrisiStudentSO operacija=new ObrisiStudentSO();
        operacija.izvrsi(s, null);
    }

    public List<NivoStudija> ucitajNivoStudija() throws Exception {
        UcitajNivoStudijaSO operacija = new UcitajNivoStudijaSO();
        operacija.izvrsi(null, null);
        
        return operacija.getLista();
    }

    public int kreirajStudent(Student noviStudent) throws Exception {
        KreirajStudentSO operacija=new KreirajStudentSO();
        operacija.izvrsi(noviStudent, null);
        return operacija.getId();
    }

    public void zapamtiStudent(Student novi) throws Exception {
        PromeniStudentSO operacija=new PromeniStudentSO();
        operacija.izvrsi(novi, null);
    }

    public void promeniStudent(Student studentIzmena) throws Exception {
        PromeniStudentSO operacija = new PromeniStudentSO();
        operacija.izvrsi(studentIzmena, null);
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() throws Exception {
        PretraziIznajmljivanjeSO operacija=new PretraziIznajmljivanjeSO();
        operacija.izvrsi(null, null);
       // System.out.println("klasa kontroler ucitaj:"+operacija.getIznajmljivanja());
        return operacija.getIznajmljivanja();
    }

    public List<Knjiga> ucitajKnjige() throws Exception {
        UcitajKnjigeSO operacija=new UcitajKnjigeSO();
        operacija.izvrsi(null, null);
        //System.out.println("klasa kontroler ucitaj knjige:"+operacija.getLista());
        return operacija.getLista();
    }

    public int kreirajIznajmljivanje(Iznajmljivanje izn) throws Exception {
        KreirajIznajmljivanjeSO operacija=new KreirajIznajmljivanjeSO();
        operacija.izvrsi(izn, null);
        return operacija.getId();
    }

    public void zapamtiIznajmljivanje(Iznajmljivanje novoIzn) throws Exception {
        ZapamtiIznajmljivanjeSO operacija=new ZapamtiIznajmljivanjeSO();
        operacija.izvrsi(novoIzn, null);
    }

    public void promeniIznajmljivanje(Iznajmljivanje izn) throws Exception {
        PromeniIznajmljivanjeSO operacija=new PromeniIznajmljivanjeSO();
        operacija.izvrsi(izn, null);
    }

    public List<Student> pretraziStudente(Student kriterijum) throws Exception {
        PretraziStudentSO operacija=new PretraziStudentSO();
        operacija.izvrsi(kriterijum, null);
        return operacija.getListaStudenata();
    }

    public List<Iznajmljivanje> pretraziIznajmljivanja(Iznajmljivanje kriterijumIzn) throws Exception {
        PretraziIznajmljivanjeSO operacija=new PretraziIznajmljivanjeSO();
        operacija.izvrsi(kriterijumIzn, null);
        return operacija.getIznajmljivanja();
    }

    public void ubaciTerminDezurstva(TerminDezurstva td) throws Exception {
        UbaciTerminDezurstvaSO operacija=new UbaciTerminDezurstvaSO();
        operacija.izvrsi(td, null);
    }

    

    
    
    
}
