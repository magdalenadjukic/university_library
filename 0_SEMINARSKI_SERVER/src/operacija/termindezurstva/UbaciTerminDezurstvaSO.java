/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.termindezurstva;

import domen.TerminDezurstva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author DELL
 */
public class UbaciTerminDezurstvaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object obj) throws Exception {
        if (obj == null || !(obj instanceof TerminDezurstva)) {
        throw new Exception("Sistem ne može da zapamti termin dežurstva.");
        }

        TerminDezurstva td = (TerminDezurstva) obj;

        if (td.getVremeOd() == null || td.getVremeDo() == null) {
            throw new Exception("Sistem ne može da zapamti termin dežurstva.");
        }

        if (!td.getVremeOd().before(td.getVremeDo())) {
            throw new Exception("Sistem ne može da zapamti termin dežurstva.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj, String kljuc) throws Exception {
        broker.add((TerminDezurstva) obj);
    }
    
}
