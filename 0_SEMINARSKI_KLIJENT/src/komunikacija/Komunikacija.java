/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Bibliotekar;
import domen.Iznajmljivanje;
import domen.Knjiga;
import domen.NivoStudija;
import domen.StavkaIznajmljivanja;
import domen.Student;
import domen.TerminDezurstva;
//import forme.KreirajStudentForma;
import forme.PrikazStudenataForma;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import koordinator.Koordinator;

/**
 *
 * @author DELL
 */
public class Komunikacija {
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;
 
    
    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance==null)
            instance=new Komunikacija();
        return instance;
    }
    
    public void konekcija() {
        try {
            socket=new Socket("localhost", 9000);
            posiljalac=new Posiljalac(socket);
            primalac=new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("server nije povezan");
        }
    }

    public Bibliotekar prijava(String korisnickoIme, String lozinka) throws Exception{
        Bibliotekar b=new Bibliotekar();
        b.setKorisnickoIme(korisnickoIme);
        b.setLozinka(lozinka);
        
        Zahtev zahtev=new Zahtev(Operacija.PRIJAVA, b);
        posiljalac.posalji(zahtev);
        
        Odgovor odg=(Odgovor) primalac.primi();
        
        b=(Bibliotekar) odg.getOdgovor();
        if(odg.getExc()==null){
           System.out.println("USPEH");
           return b;
        }
        else{
          System.out.println("GRESKA");
          throw odg.getExc();
        }
    }

    public List<Student> ucitajStudente() {
        List<Student> lista=new ArrayList<>();
        
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_STUDENTE, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odg=(Odgovor) primalac.primi();
        lista=(List<Student>) odg.getOdgovor();
        
        return lista;
    }

    public void obrisiStudent(Student s) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_STUDENT, s);
        posiljalac.posalji(zahtev);
        
        Odgovor odg=(Odgovor) primalac.primi();
        if (odg.getExc() == null) {
            System.out.println("USPESNO BRISANJE STUDENTA");
        } else {
            System.out.println("GRESKA BRISANJE STUDENTA");
            throw odg.getExc();
        }
    }

    public List<NivoStudija> ucitajNivoStudija() {
        List<NivoStudija> lista=new ArrayList<>();
        
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_NIVOE, null);
        
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        lista = (List<NivoStudija>) odgovor.getOdgovor();
        
        return lista;        
    }

    public int kreirajStudent(Student noviStudent) {
        int id;
        
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_STUDENT,noviStudent);
       
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        
        id = (Integer) odgovor.getOdgovor();
        
        return id;
    }

    public void zapamtiStudenta(Student noviStudent) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.ZAPAMTI_STUDENT, noviStudent);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getExc()!=null){
            System.out.println("Nije moguce zapamtiti studenta.");
            throw odgovor.getExc();
        }
        else{
            System.out.println("zapamtiStudenta uspesno");
        }
    }

    public void promeniStudent(Student student) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_STUDENT, student);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getExc() == null) {
            System.out.println("USPEH PROMENI STUDENT");
            Koordinator.getInstance().osveziFormu();
        } else {
            System.out.println("GRESKA");
            throw odgovor.getExc();
        }
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() {
        List<Iznajmljivanje> lista=new ArrayList<>();
        
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_IZNAJMLJIVANJA, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odg=(Odgovor) primalac.primi();
        lista=(List<Iznajmljivanje>) odg.getOdgovor();
        
        return lista;
    }

    public List<Bibliotekar> ucitajBibliotekare() {
        List<Bibliotekar> lista=new ArrayList<>();
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_BIBLIOTEKARE, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odg=(Odgovor) primalac.primi();
        lista=(List<Bibliotekar>) odg.getOdgovor();
        
        return lista;
    }

    public List<Knjiga> ucitajKnjige() {
        List<Knjiga> lista=new ArrayList<>();
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_KNJIGE, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odg=(Odgovor) primalac.primi();
        lista=(List<Knjiga>) odg.getOdgovor();
        
        return lista;
    }

    public int kreirajIznajmljivanje(Iznajmljivanje i) {
        int id;
        
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_IZNAJMLJIVANJE,i);
       
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        
        id = (Integer) odgovor.getOdgovor();
        
        return id;
    }

    public void zapamtiIznajmljivanje(Iznajmljivanje izn) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.ZAPAMTI_IZNAJMLJIVANJE, izn);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getExc()!=null){
            System.out.println("Nije moguce zapamtiti iznajmljivanje. (komunikacija)");
            throw odgovor.getExc();
        }
        else{
            System.out.println("zapamti iznajmljivanje uspesno");
        }
    }

    public void promeniIznajmljivanje(Iznajmljivanje izn) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_IZNAJMLJIVANJE, izn);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getExc() == null) {
            System.out.println("USPEH PROMENI IZNAJMLJIVANJE");
            Koordinator.getInstance().osveziTabeluIznajmljivanja();
        } else {
            System.out.println("GRESKA PROMENI IZNAJMLJIVANJE");
            throw odgovor.getExc();
        }
    }

    public List<Student> pretraziStudente(Student kriterijum)  throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_STUDENT, kriterijum);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getExc()==null)
           return (List<Student>) odg.getOdgovor();
        else{
            throw odg.getExc();
        }
    }

    public List<Iznajmljivanje> pretraziIznajmljivanja(Iznajmljivanje kriterijum) {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_IZNAJMLJIVANJE, kriterijum);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<Iznajmljivanje>) odg.getOdgovor();
    }

    public void ubaciTerminDezurstva(TerminDezurstva td) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_TD, td);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getExc() == null) {
            System.out.println("Uspeh ubaci TD");
        } else {
            System.out.println("GRESKA ubaci TD");
            throw odgovor.getExc();
        }
    }


    
    
    
    
    
}
