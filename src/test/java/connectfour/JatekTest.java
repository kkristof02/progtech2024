package connectfour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A Jatek osztály tesztelésére szolgáló osztály.
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
}
