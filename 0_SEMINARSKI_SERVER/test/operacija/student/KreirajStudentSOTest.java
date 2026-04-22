/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package operacija.student;

import domen.Student;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KreirajStudentSOTest {

    private KreirajStudentSO so;

    @Before
    public void setUp() {
        so = new KreirajStudentSO();
    }

    @Test
    public void shouldThrowWhenObjectIsNull() throws Exception {
        try {
            so.preduslovi(null);
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Sistem ne može da kreira studenta.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowWhenObjectIsNotStudent() throws Exception {
        try {
            so.preduslovi("randomTekst");
            fail("Ocekivao se exception");
        } catch (Exception e) {
            assertEquals("Sistem ne može da kreira studenta.", e.getMessage());
        }
    }

    @Test
    public void shouldNotThrowWhenStudentIsValid() throws Exception {
        Student s = new Student();
        try {
            so.preduslovi(s);
        } catch (Exception e) {
            fail("Nije ocekivan exception: " + e.getMessage());
        }
    }
}