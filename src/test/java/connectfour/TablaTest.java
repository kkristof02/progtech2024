package connectfour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A Tabla osztály tesztelésére szolgáló osztály.
 */
public class TablaTest {

    private Tabla tabla;

    @BeforeEach
    public void setUp() {
        tabla = new Tabla(6, 7);
    }

    @Test
    public void testTablaInicializalasa() {
        assertNotNull(tabla, "A tábla nem lehet null.");
        assertEquals(6, tabla.getSorok());
        assertEquals(7, tabla.getOszlopok());
    }

    @Test
    public void testKorongElhelyezese() {
        Korong korong = new Korong('S');
        assertTrue(tabla.korongElhelyezese(korong, 0));
        assertEquals('S', tabla.getRacs()[5][0]);
    }

    @Test
    public void testTeleVan() {
        // Töltsük meg a táblát korongokkal
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tabla.korongElhelyezese(new Korong('S'), j);
            }
        }
        assertTrue(tabla.teleVan());
    }

    @Test
    public void testGyozelemEllenorzes() {
        Korong korong = new Korong('S');
        // Helyezzünk el négy egymás melletti korongot
        for (int i = 0; i < 4; i++) {
            tabla.korongElhelyezese(korong, i);
        }
        assertTrue(tabla.gyozelemEllenorzes(korong));
    }

    @Test
    public void testTablaNyomtatasa() {
        tabla.tablaNyomtatasa(); // Csak futtatjuk, hogy ne legyen kivétel
    }
}
