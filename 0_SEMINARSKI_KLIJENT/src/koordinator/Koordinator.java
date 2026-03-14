/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import domen.Bibliotekar;
import domen.Student;
import forme.DetaljiForma;
import forme.GlavnaForma;
import forme.KreirajPromeniIznajmljivanjeForma;
import forme.LoginForma;
import forme.PrikazIznajmljivanjaForma;
import forme.PrikazStudenataForma;
import forme.UbaciTerminDezurstvaForma;
import forme.mod.FormaMOD;
import java.util.HashMap;
import kontroleri.DetaljiStudentaKontroler;
import kontroleri.GlavnaFormaKontroler;
import kontroleri.KreirajIznajmljivanjeKontroler;
import kontroleri.LoginKontroler;
import kontroleri.PrikazIznajmljivanjaKontroler;
import kontroleri.PrikazStudenataKontroler;
import kontroleri.UbaciTerminDezurstvaKontroler;

/**
 *
 * @author DELL
 */
public class Koordinator {
    //sadrzi sve ostale kontrolere, kao glavni kontroler
    private static Koordinator instance;
    private LoginKontroler loginKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    private Bibliotekar ulogovaniBibl;
    private PrikazStudenataKontroler psk;
    private DetaljiStudentaKontroler dsk;
    private PrikazIznajmljivanjaKontroler pik;
    private KreirajIznajmljivanjeKontroler kik;
    private UbaciTerminDezurstvaKontroler utdk;
    
    private HashMap<String,Object> parametri;
    
    private Koordinator() {
        parametri=new HashMap<>();
    }

    public static Koordinator getInstance() {
        if(instance==null)
            instance=new Koordinator();
        return instance;
    }

    public Bibliotekar getUlogovaniBibl() {
        return ulogovaniBibl;
    }

    public void setUlogovaniBibl(Bibliotekar ulogovaniBibl) {
        this.ulogovaniBibl = ulogovaniBibl;
    }

    public void otvoriLoginFormu() {
        loginKontroler=new LoginKontroler(new LoginForma());
        loginKontroler.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaKontroler=new GlavnaFormaKontroler(new GlavnaForma());
        glavnaFormaKontroler.otvoriFormu();
    }

    public void otvoriPrikazStudenataFormu() {
        psk=new PrikazStudenataKontroler(new PrikazStudenataForma());
        psk.otvoriFormu();
    }

    public void otvoriDetaljiFormu(FormaMOD formaMOD) {
        dsk=new DetaljiStudentaKontroler(new DetaljiForma());
        if(formaMOD==formaMOD.DETALJI)
         dsk.otvoriFormu(formaMOD.DETALJI);
        else
            dsk.otvoriFormu(formaMOD.KREIRAJ);
    }
     public void otvoriPrikazIznajmljivanjaFormu() {
        pik=new PrikazIznajmljivanjaKontroler(new PrikazIznajmljivanjaForma());
        pik.otvoriFormu();
    }
    
    //za parametre
    public void dodajParam(String s,Object o){
        parametri.put(s, o);
    }
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void osveziFormu() {
        psk.osveziFormu();
    }

    public void otvoriKreirajIznajmljivanjeFormu(FormaMOD formaMOD) {
        kik=new KreirajIznajmljivanjeKontroler(new KreirajPromeniIznajmljivanjeForma());
        if(formaMOD==formaMOD.DETALJI)
             kik.otvoriFormu(formaMOD.DETALJI);
        else
            kik.otvoriFormu(formaMOD.KREIRAJ);
    }

    public void osveziTabeluIznajmljivanja() throws Exception {
        
        pik=new PrikazIznajmljivanjaKontroler(new PrikazIznajmljivanjaForma());
        pik.pripremiFormu();
    }

    public void otvoriUbaciTDFormu() {
        utdk=new UbaciTerminDezurstvaKontroler(new UbaciTerminDezurstvaForma());
        utdk.otvoriFormu();
    }
}
