package connectfour;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A Korong osztály tesztelésére szolgáló osztály.
 */
public class KorongTest {

    @Test
    public void testKorongConstructorAndGetSzin() {
        Korong korong = new Korong('S');
        assertEquals('S', korong.getSzin());
    }

}
