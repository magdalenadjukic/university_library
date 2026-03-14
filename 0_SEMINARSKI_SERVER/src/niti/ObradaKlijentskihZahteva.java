/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Bibliotekar;
import domen.Iznajmljivanje;
import domen.Knjiga;
import domen.NivoStudija;
import domen.StavkaIznajmljivanja;
import domen.Student;
import domen.TerminDezurstva;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import komunikacija.Operacija;
import kontroler.Kontroler;

/**
 *
 * @author DELL
 */
public class ObradaKlijentskihZahteva extends Thread {
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj=false;
    
    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket=socket;
        posiljalac=new Posiljalac(socket);
        primalac=new Primalac(socket);
    }
    
    @Override
    public void run() {
        while(!kraj){
                Zahtev zahtev=(Zahtev) primalac.primi();
                Odgovor odgovor=new Odgovor();
                try{
                switch (zahtev.getOperacija()) {
                    case PRIJAVA:
                        try {
                            Bibliotekar b=(Bibliotekar) zahtev.getParametar();

                            b=kontroler.Kontroler.getInstance().prijaviBibliotekar(b);
                            odgovor.setOdgovor(b);
                        } catch (Exception e) {
                            odgovor.setExc(e);
                        }
                        break;
                    
                    case UCITAJ_STUDENTE:
                        try {
                           List<Student> studenti=kontroler.Kontroler.getInstance().ucitajStudente();
                           odgovor.setOdgovor(studenti);
                        } catch (Exception e) {
                           odgovor.setExc(e);
                        }
                        break;
                    case PRETRAZI_STUDENT:
                        try {
                            Student kriterijum = (Student) zahtev.getParametar();
                            List<Student> studentiRezultat = kontroler.Kontroler.getInstance().pretraziStudente(kriterijum);
                            odgovor.setOdgovor(studentiRezultat);
                        } catch (Exception e) {
                            odgovor.setExc(e);
                        }
                        
                        break;
                    case OBRISI_STUDENT:
                    try {
                        Student s=(Student) zahtev.getParametar();
                        kontroler.Kontroler.getInstance().obrisiStudent(s);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setExc(e);
                    }
                        break;
                    case UCITAJ_NIVOE:
                        List<NivoStudija> listaNivoa = Kontroler.getInstance().ucitajNivoStudija();
                        odgovor.setOdgovor(listaNivoa);
                        break;
                    case KREIRAJ_STUDENT:
                        Student s=(Student) zahtev.getParametar();
                        int id=Kontroler.getInstance().kreirajStudent(s);
                        odgovor.setOdgovor(id);
                        break;
                    case ZAPAMTI_STUDENT:
                        try {
                            Student novi=(Student) zahtev.getParametar();
                            Kontroler.getInstance().zapamtiStudent(novi);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setExc(e);
                        }
                        break;
                    case PROMENI_STUDENT:
                        Student studentIzmena = (Student) zahtev.getParametar();
                        Kontroler.getInstance().promeniStudent(studentIzmena);
                        odgovor.setOdgovor(null);
                        break;
                    case UCITAJ_IZNAJMLJIVANJA:
                        List<Iznajmljivanje> iznajmljivanja=kontroler.Kontroler.getInstance().ucitajIznajmljivanja();
                        
                        odgovor.setOdgovor(iznajmljivanja);
                        
                        break;
                    case PRETRAZI_IZNAJMLJIVANJE:
                        Iznajmljivanje kriterijumIzn = (Iznajmljivanje) zahtev.getParametar();
                        List<Iznajmljivanje> iznResult = kontroler.Kontroler.getInstance().pretraziIznajmljivanja(kriterijumIzn);
                        odgovor.setOdgovor(iznResult);
                        break;
                    case UCITAJ_BIBLIOTEKARE:
                        List<Bibliotekar> bibliotekari=kontroler.Kontroler.getInstance().ucitajBibliotekare();
                        odgovor.setOdgovor(bibliotekari);
                        break;
                    case UCITAJ_KNJIGE:
                        List<Knjiga> knjige=kontroler.Kontroler.getInstance().ucitajKnjige();
                        odgovor.setOdgovor(knjige);
                        break;
                    case KREIRAJ_IZNAJMLJIVANJE:
                        Iznajmljivanje i=(Iznajmljivanje) zahtev.getParametar();
                        int idIzn=Kontroler.getInstance().kreirajIznajmljivanje(i);
                        odgovor.setOdgovor(idIzn);
                        break;
                    case ZAPAMTI_IZNAJMLJIVANJE:
                        try {
                            Iznajmljivanje novoIzn=(Iznajmljivanje) zahtev.getParametar();
                            Kontroler.getInstance().zapamtiIznajmljivanje(novoIzn);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setExc(e);
                        }
                        break;
                    case PROMENI_IZNAJMLJIVANJE:
                        try {
                            Iznajmljivanje izn = (Iznajmljivanje) zahtev.getParametar();
                            Kontroler.getInstance().promeniIznajmljivanje(izn);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setExc(e);
                        }
                        break;
                    case UBACI_TD:
                        try {
                            TerminDezurstva td = (TerminDezurstva) zahtev.getParametar();
                            Kontroler.getInstance().ubaciTerminDezurstva(td);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setExc(e);
                        }
                        
                        break;
                    default:
                        System.out.println("operacija ne postoji");
                }
                }catch(Exception ex){
                     ex.printStackTrace();
                     odgovor.setExc(ex);
                }
                
                posiljalac.posalji(odgovor);
            
        }
    }
    
    public void prekini(){
        kraj=true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
    
}
