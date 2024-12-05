package connectfour;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Az AdatbazisKezelo osztály tesztelésére szolgáló osztály.
 */
public class AdatbazisKezeloTest {

    /**
     * Inicializálja az adatbázist a tesztek előtt.
     */
    @BeforeAll
    public static void initializeDatabase() {
        AdatbazisKezelo.initializeDatabase();
    }

    /**
     * Teszteli az addOrUpdateJatekos metódust.
     */
    @Test
    public void testAddOrUpdateJatekos() {
        AdatbazisKezelo.addOrUpdateJatekos("Teszt Jatekos", 5);
        Jatekos jatekos = AdatbazisKezelo.getJatekosByNev("Teszt Jatekos");
        assertNotNull(jatekos, "A játékos nem lehet null.");
        assertEquals(5, jatekos.getGyozelmek(), "A győzelmek száma nem megfelelő.");
    }

    /**
     * Teszteli a getHighScore metódust.
     */
    @Test
    public void testGetHighScore() {
        AdatbazisKezelo.addOrUpdateJatekos("Teszt Jatekos", 5);
        String highScore = AdatbazisKezelo.getHighScore();
        assertNotNull(highScore, "A győzelmi rangsor nem lehet null.");
        assertTrue(highScore.contains("Teszt Jatekos"), "A győzelmi rangsor nem tartalmazza a játékost.");
    }

    /**
     * Teszteli a saveJatekAllas metódust és a getJatekAllas metódust.
     */
    @Test
    public void testSaveAndGetJatekAllas() {
        String tablaAllas = "111000\n110000\n000000\n000000\n000000\n000000";
        AdatbazisKezelo.saveJatekAllas(tablaAllas);
        String retrievedAllas = AdatbazisKezelo.getJatekAllas();
        assertEquals(tablaAllas, retrievedAllas, "A mentett és a lekérdezett játékállás nem egyezik meg.");
    }
}
