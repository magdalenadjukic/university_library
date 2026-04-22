/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package operacija.bibliotekar;


import domen.Bibliotekar;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author DELL
 */
public class PrijaviBibliotekarSOTest {
    private PrijaviBibliotekarSO so;

    @Before
    public void setUp() {
        so = new PrijaviBibliotekarSO();
        // ocisti listu prijavljenih pre svakog testa
        kontroler.Kontroler.getInstance().getPrijavljeniBibl().clear();
    }

    @After
    public void tearDown() {
        kontroler.Kontroler.getInstance().getPrijavljeniBibl().clear();
    }

    // 1. null objekat
    @Test
    public void shouldThrowWhenObjectIsNull() throws Exception {
        try {
            so.preduslovi(null);
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Korisničko ime i šifra nisu ispravni.", e.getMessage());
        }
    }

    // 2. objekat nije Bibliotekar
    @Test
    public void shouldThrowWhenObjectIsNotBibliotekar() throws Exception {
        try {
            so.preduslovi("nekiString");
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Korisničko ime i šifra nisu ispravni.", e.getMessage());
        }
    }

    // 3. korisnicko ime je null
    @Test
    public void shouldThrowWhenKorisnickoImeIsNull() throws Exception {
        Bibliotekar b = new Bibliotekar();
        b.setKorisnickoIme(null);
        b.setLozinka("sifra123");

        try {
            so.preduslovi(b);
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Korisničko ime i šifra nisu ispravni.", e.getMessage());
        }
    }

    // 4. korisnicko ime je prazno
    @Test
    public void shouldThrowWhenKorisnickoImeIsEmpty() throws Exception {
        Bibliotekar b = new Bibliotekar();
        b.setKorisnickoIme("");
        b.setLozinka("sifra123");

        try {
            so.preduslovi(b);
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Korisničko ime i šifra nisu ispravni.", e.getMessage());
        }
    }

    // 5. lozinka je null
    @Test
    public void shouldThrowWhenLozinkaIsNull() throws Exception {
        Bibliotekar b = new Bibliotekar();
        b.setKorisnickoIme("admin");
        b.setLozinka(null);

        try {
            so.preduslovi(b);
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Korisničko ime i šifra nisu ispravni.", e.getMessage());
        }
    }

    // 6. bibliotekar vec prijavljen
    @Test
    public void shouldThrowWhenBibliotekarAlreadyLoggedIn() throws Exception {
        Bibliotekar b = new Bibliotekar();
        b.setKorisnickoIme("admin");
        b.setLozinka("sifra123");

        // dodaj ga u listu prijavljenih
        kontroler.Kontroler.getInstance().getPrijavljeniBibl().add(b);

        try {
            so.preduslovi(b);
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Ne može da se otvori glavna forma i meni.", e.getMessage());
        }
    }

    // 7. validni podaci - ne sme da baci exception
    @Test
    public void shouldNotThrowWhenDataIsValid() throws Exception {
        Bibliotekar b = new Bibliotekar();
        b.setKorisnickoIme("admin");
        b.setLozinka("sifra123");

        try {
            so.preduslovi(b);
        } catch (Exception e) {
            fail("Nije ocekivan exception: " + e.getMessage());
        }
    }
    
}
