/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package operacija.student;

import domen.Student;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObrisiStudentSOTest {

    private ObrisiStudentSO so;

    @Before
    public void setUp() {
        so = new ObrisiStudentSO();
    }

    // 1. null objekat
    @Test
    public void shouldThrowWhenObjectIsNull() throws Exception {
        try {
            so.preduslovi(null);//proverava preduslove kada se unese null
            fail("Ocekivao se exception");//ako se ovo izvrsi,nesto nije u redu
        } catch (Exception e) {
            //ispravno ponasanje bi bilo da dodje do exceptiona ako je prosledjen null
            assertEquals("Sistem ne može da obriše studenta.", e.getMessage());
        }
    }

    // 2. objekat nije Student
    @Test
    public void shouldThrowWhenObjectIsNotStudent() throws Exception {
        try {
            so.preduslovi("nekiString");
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Sistem ne može da obriše studenta.", e.getMessage());
        }
    }

    // 3. ime je null
    @Test
    public void shouldThrowWhenImeIsNull() throws Exception {
        Student s = new Student();
        s.setIme(null);
        s.setPrezime("Petrovic");

        try {
            so.preduslovi(s);
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Sistem ne može da obriše studenta.", e.getMessage());
        }
    }

    // 4. prezime je null
    @Test
    public void shouldThrowWhenPrezimeIsNull() throws Exception {
        Student s = new Student();
        s.setIme("Marko");
        s.setPrezime(null);

        try {
            so.preduslovi(s);
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Sistem ne može da obriše studenta.", e.getMessage());
        }
    }

    // 5. ime je prazno
    @Test
    public void shouldThrowWhenImeIsEmpty() throws Exception {
        Student s = new Student();
        s.setIme("");
        s.setPrezime("Petrovic");

        try {
            so.preduslovi(s);
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Sistem ne može da obriše studenta.", e.getMessage());
        }
    }

    // 6. prezime je prazno
    @Test
    public void shouldThrowWhenPrezimeIsEmpty() throws Exception {
        Student s = new Student();
        s.setIme("Marko");
        s.setPrezime("");

        try {
            so.preduslovi(s);
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Sistem ne može da obriše studenta.", e.getMessage());
        }
    }
}