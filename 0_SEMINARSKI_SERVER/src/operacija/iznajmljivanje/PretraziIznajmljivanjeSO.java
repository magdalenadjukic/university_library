/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanje;

import domen.Bibliotekar;
import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
    import domen.Student;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author DELL
 */
public class PretraziIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {
    List<Iznajmljivanje> iznajmljivanja;
    
    @Override
    protected void preduslovi(Object obj) throws Exception {
        List<Bibliotekar> bibliotekari = broker.getAll(new Bibliotekar(), null);
        if (bibliotekari == null || bibliotekari.isEmpty()) {
            throw new Exception("Sistem ne moze da nađe iznajmljivanja.");
        }

        String uslov = " JOIN nivostudija ON student.idNivoStudija = nivostudija.idNivoStudija";
        List<Student> studenti = broker.getAll(new Student(), uslov);
        if (studenti == null || studenti.isEmpty()) {
            throw new Exception("Sistem ne moze da nađe iznajmljivanja.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        String uslov = " JOIN bibliotekar ON iznajmljivanje.idBibliotekar=bibliotekar.idBibliotekar"
                 + " JOIN student ON iznajmljivanje.idStudent=student.idStudent"
                 + " JOIN nivostudija ON student.idNivoStudija=nivostudija.idNivoStudija"
                 + " WHERE 1=1";
    
        if (obj != null) {
            Iznajmljivanje kriterijum = (Iznajmljivanje) obj;
            if (kriterijum.getBibliotekar() != null) {
            uslov += " AND iznajmljivanje.idBibliotekar=" + kriterijum.getBibliotekar().getIdBibliotekar();
            }
            if (kriterijum.getStudent() != null) {
            uslov += " AND iznajmljivanje.idStudent=" + kriterijum.getStudent().getIdStudent();
            }
            if (kriterijum.getDatumIznajmljivanja() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            uslov += " AND iznajmljivanje.datumIznajmljivanja='" + sdf.format(kriterijum.getDatumIznajmljivanja()) + "'";
            }
        }
    
        iznajmljivanja = broker.getAll(new Iznajmljivanje(), uslov);
        for (Iznajmljivanje izn : iznajmljivanja) {
            String stavkaUslov = " JOIN knjiga ON stavkaiznajmljivanja.idKnjiga = knjiga.idKnjiga WHERE idIznajmljivanje = " + izn.getIdIznajmljivanje();
            List<StavkaIznajmljivanja> stavke = broker.getAll(new StavkaIznajmljivanja(), stavkaUslov);
            izn.setListaStavki((ArrayList<StavkaIznajmljivanja>) stavke);
        }
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }

    public void setIznajmljivanja(List<Iznajmljivanje> iznajmljivanja) {
        this.iznajmljivanja = iznajmljivanja;
    }
    
}
