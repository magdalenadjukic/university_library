/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanje;

import domen.Bibliotekar;
import domen.Iznajmljivanje;
import domen.Knjiga;
import domen.StavkaIznajmljivanja;
import domen.Student;
import java.util.Date;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author DELL
 */
public class ZapamtiIznajmljivanjeSO  extends ApstraktnaGenerickaOperacija{
    @Override
    protected void preduslovi(Object obj) throws Exception {
       if (obj == null || !(obj instanceof Iznajmljivanje)) {
        throw new Exception("Sistem ne može da zapamti iznajmljivanje.");
        }
    
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) obj;

        if (iznajmljivanje.getDatumIznajmljivanja() == null || 
            iznajmljivanje.getDatumIznajmljivanja().after(new Date())) {
            throw new Exception("Sistem ne može da zapamti iznajmljivanje.");
        }

        List<Bibliotekar> bibliotekari = broker.getAll(iznajmljivanje.getBibliotekar(), null);
        if (bibliotekari == null || bibliotekari.isEmpty()) {
            throw new Exception("Sistem ne može da zapamti iznajmljivanje.");
        }
        String uslov = " JOIN nivostudija ON student.idNivoStudija = nivostudija.idNivoStudija";
        List<Student> studenti = broker.getAll(iznajmljivanje.getStudent(), uslov);
        if (studenti == null || studenti.isEmpty()) {
            throw new Exception("Sistem ne može da zapamti iznajmljivanje.");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        Iznajmljivanje izn=(Iznajmljivanje) obj;
        List<StavkaIznajmljivanja> stavke = izn.getListaStavki();
        for (StavkaIznajmljivanja si : stavke) {
            si.setIznajmljivanje(izn);
            broker.add(si);
        }
        broker.edit((Iznajmljivanje) obj);
       
        
        
    }
    
}
