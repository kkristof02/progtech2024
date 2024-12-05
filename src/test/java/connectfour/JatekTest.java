package connectfour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * A JatekTest osztály a Jatek osztály tesztelésére szolgál.
 */
public class JatekTest {

    private Jatek jatek;

    /**
     * Inicializálja a tesztkörnyezetet minden egyes teszt előtt.
     */
    @BeforeEach
    public void setUp() {
        jatek = new Jatek(6, 7);
    }

    /**
     * Teszteljük, hogy a játék döntetlennel zárul-e, ha a tábla tele van.
     */
    @Test
    public void testDontetlen() {
        Jatek jatek = new Jatek(2, 2);
        jatek.getTabla().korongElhelyezese(new Korong('S'), 0);
        jatek.getTabla().korongElhelyezese(new Korong('P'), 1);
        jatek.getTabla().korongElhelyezese(new Korong('S'), 0);
        jatek.getTabla().korongElhelyezese(new Korong('P'), 1);
        assertTrue(jatek.getTabla().teleVan());
    }

    /**
     * Teszteljük a győzelem ellenőrzését különböző irányokban.
     */
    @Test
    public void testGyozelemEllenorzes() {
        Tabla tabla = jatek.getTabla();

        // Vízszintes győzelem ellenőrzése
        tabla.korongElhelyezese(new Korong('S'), 0);
        tabla.korongElhelyezese(new Korong('S'), 1);
        tabla.korongElhelyezese(new Korong('S'), 2);
        tabla.korongElhelyezese(new Korong('S'), 3);
        assertTrue(tabla.gyozelemEllenorzes(new Korong('S')));

        // Függőleges győzelem ellenőrzése
        tabla = new Tabla(6, 7);
        tabla.korongElhelyezese(new Korong('P'), 0);
        tabla.korongElhelyezese(new Korong('P'), 0);
        tabla.korongElhelyezese(new Korong('P'), 0);
        tabla.korongElhelyezese(new Korong('P'), 0);
        assertTrue(tabla.gyozelemEllenorzes(new Korong('P')));

        // Átlós győzelem ellenőrzése (balról jobbra)
        tabla = new Tabla(6, 7);
        tabla.korongElhelyezese(new Korong('S'), 0);
        tabla.korongElhelyezese(new Korong('P'), 1);
        tabla.korongElhelyezese(new Korong('S'), 1);
        tabla.korongElhelyezese(new Korong('P'), 2);
        tabla.korongElhelyezese(new Korong('P'), 2);
        tabla.korongElhelyezese(new Korong('S'), 2);
        tabla.korongElhelyezese(new Korong('P'), 3);
        tabla.korongElhelyezese(new Korong('P'), 3);
        tabla.korongElhelyezese(new Korong('P'), 3);
        tabla.korongElhelyezese(new Korong('S'), 3);
        assertTrue(tabla.gyozelemEllenorzes(new Korong('S')));

        // Átlós győzelem ellenőrzése (jobbról balra)
        tabla = new Tabla(6, 7);
        tabla.korongElhelyezese(new Korong('S'), 3);
        tabla.korongElhelyezese(new Korong('P'), 2);
        tabla.korongElhelyezese(new Korong('S'), 2);
        tabla.korongElhelyezese(new Korong('P'), 1);
        tabla.korongElhelyezese(new Korong('P'), 1);
        tabla.korongElhelyezese(new Korong('S'), 1);
        tabla.korongElhelyezese(new Korong('P'), 0);
        tabla.korongElhelyezese(new Korong('P'), 0);
        tabla.korongElhelyezese(new Korong('P'), 0);
        tabla.korongElhelyezese(new Korong('S'), 0);
        assertTrue(tabla.gyozelemEllenorzes(new Korong('S')));
    }

    /**
     * Teszteljük, hogy a tábla tele van-e.
     */
    @Test
    public void testTeleVan() {
        Jatek jatek = new Jatek(2, 2);

        // Ellenőrizzük, hogy a tábla kezdetben üres
        assertFalse(jatek.getTabla().teleVan());

        // Elhelyezünk korongokat a táblán
        jatek.getTabla().korongElhelyezese(new Korong('S'), 0);
        jatek.getTabla().korongElhelyezese(new Korong('P'), 1);
        jatek.getTabla().korongElhelyezese(new Korong('S'), 0);
        jatek.getTabla().korongElhelyezese(new Korong('P'), 1);

        // Ellenőrizzük, hogy a tábla tele van-e
        assertTrue(jatek.getTabla().teleVan());
    }

    @Test
    public void testJatekInicializalasa() {
        assertNotNull(jatek.getTabla(), "A tábla nem lehet null.");
    }

    // AdatbazisKezelo osztály tesztjei
    @Test
    public void testAddOrUpdateJatekos() {
        AdatbazisKezelo.initializeDatabase();
        AdatbazisKezelo.addOrUpdateJatekos("Teszt Jatekos", 5);
        Jatekos jatekos = AdatbazisKezelo.getJatekosByNev("Teszt Jatekos");
        assertNotNull(jatekos, "A játékos nem lehet null.");
        assertEquals(5, jatekos.getGyozelmek(), "A győzelmek száma nem megfelelő.");
    }

    @Test
    public void testGetHighScore() {
        AdatbazisKezelo.initializeDatabase();
        AdatbazisKezelo.addOrUpdateJatekos("Teszt Jatekos", 5);
        String highScore = AdatbazisKezelo.getHighScore();
        assertNotNull(highScore, "A győzelmi rangsor nem lehet null.");
        assertTrue(highScore.contains("Teszt Jatekos"), "A győzelmi rangsor nem tartalmazza a játékost.");
    }


        @Test
        public void testJatekosConstructorAndGetters() {
            Jatekos jatekos = new Jatekos("TesztJatekos", 3);
            assertEquals("TesztJatekos", jatekos.getNev());
            assertEquals(3, jatekos.getGyozelmek());
        }

        /**
         * Teszteli a novelGyozelmek metódust.
         */
        @Test
        public void testNovelGyozelmek() {
            Jatekos jatekos = new Jatekos("TesztJatekos", 3);
            jatekos.novelGyozelmek();
            assertEquals(4, jatekos.getGyozelmek());
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

        /**
         * Teszteli a konstruktor és getter metódusokat.
         */
        @Test
        public void testPozicioConstructorAndGetters() {
            Pozicio pozicio = new Pozicio(3, 5);
            assertEquals(3, pozicio.getSor());
            assertEquals(5, pozicio.getOszlop());
        }

        /**
         * Teszteli az equals metódust különböző esetekre.
         */
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

        /**
         * Teszteli a hashCode metódust.
         */
        @Test
        public void testHashCode() {
            Pozicio pozicio1 = new Pozicio(2, 4);
            Pozicio pozicio2 = new Pozicio(2, 4);
            Pozicio pozicio3 = new Pozicio(3, 5);

            assertEquals(pozicio1.hashCode(), pozicio2.hashCode());
            assertNotEquals(pozicio1.hashCode(), pozicio3.hashCode());
        }

        /**
         * Teszteli a toString metódust.
         */
        @Test
        public void testToString() {
            Pozicio pozicio = new Pozicio(3, 5);
            String expected = "Pozicio{sor=3, oszlop=5}";
            assertEquals(expected, pozicio.toString());
        }
    }








