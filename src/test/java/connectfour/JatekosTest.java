package connectfour;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A Jatekos osztály tesztelésére szolgáló osztály.
 */
public class JatekosTest {

    @Test
    public void testJatekosConstructorAndGetters() {
        Jatekos jatekos = new Jatekos("TesztJatekos", 3);
        assertEquals("TesztJatekos", jatekos.getNev());
        assertEquals(3, jatekos.getGyozelmek());
    }

    @Test
    public void testNovelGyozelmek() {
        Jatekos jatekos = new Jatekos("TesztJatekos", 3);
        jatekos.novelGyozelmek();
        assertEquals(4, jatekos.getGyozelmek());
    }

    @Test
    public void testAddOrUpdateJatekos() {
        Jatekos jatekos = new Jatekos("TesztJatekos", 3);
        AdatbazisKezelo.addOrUpdateJatekos(jatekos.getNev(), jatekos.getGyozelmek());
        Jatekos lekertJatekos = AdatbazisKezelo.getJatekosByNev("TesztJatekos");
        assertEquals(jatekos.getNev(), lekertJatekos.getNev());
        assertEquals(jatekos.getGyozelmek(), lekertJatekos.getGyozelmek());
    }

    @Test
    public void testToString() {
        Jatekos jatekos = new Jatekos("TesztJatekos", 3);
        String expected = "TesztJatekos:3";
        assertEquals(expected, jatekos.toString());
    }

    @Test
    public void testFromString() {
        Jatekos jatekos = Jatekos.fromString("TesztJatekos:3");
        assertEquals("TesztJatekos", jatekos.getNev());
        assertEquals(3, jatekos.getGyozelmek());
    }
}
