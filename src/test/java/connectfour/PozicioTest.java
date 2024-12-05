package connectfour;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A Pozicio osztály tesztelésére szolgáló osztály.
 */
public class PozicioTest {

    @Test
    public void testPozicioConstructorAndGetters() {
        Pozicio pozicio = new Pozicio(3, 5);
        assertEquals(3, pozicio.getSor());
        assertEquals(5, pozicio.getOszlop());
    }

    @Test
    public void testEquals() {
        Pozicio pozicio1 = new Pozicio(2, 4);
        Pozicio pozicio2 = new Pozicio(2, 4);
        Pozicio pozicio3 = new Pozicio(3, 5);
        Pozicio pozicioNull = null;
        Object otherObject = new Object();

        // Ugyanaz az objektum
        assertTrue(pozicio1.equals(pozicio1));
        // Egyenlő objektumok
        assertTrue(pozicio1.equals(pozicio2));
        // Különböző objektumok
        assertFalse(pozicio1.equals(pozicio3));
        // Null objektum
        assertFalse(pozicio1.equals(pozicioNull));
        // Különböző típusú objektum
        assertFalse(pozicio1.equals(otherObject));
    }

    @Test
    public void testHashCode() {
        Pozicio pozicio1 = new Pozicio(2, 4);
        Pozicio pozicio2 = new Pozicio(2, 4);
        Pozicio pozicio3 = new Pozicio(3, 5);

        assertEquals(pozicio1.hashCode(), pozicio2.hashCode());
        assertNotEquals(pozicio1.hashCode(), pozicio3.hashCode());
    }

    @Test
    public void testToString() {
        Pozicio pozicio = new Pozicio(3, 5);
        String expected = "Pozicio{sor=3, oszlop=5}";
        assertEquals(expected, pozicio.toString());
    }
}
