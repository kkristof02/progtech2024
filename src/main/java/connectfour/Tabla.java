package connectfour;

/**
 * A Tabla osztály a játék tábláját reprezentálja.
 */
public final class Tabla {
    /**
     * A tábla sorainak száma.
     */
    private final int tablaSorok;

    /**
     * A tábla oszlopainak száma.
     */
    private final int tablaOszlopok;

    /**
     * A tábla rácsának 2D tömbje.
     */
    private final char[][] racs;

    /**
     * A győzelemhez szükséges hosszúság.
     */
    private static final int GYOZELEM_HOSSZ = 4;

    /**
     * A maximális ellenőrzési határ.
     */
    private static final int MAX_ELLENORZES = 3;

    /**
     * Konstruktor, amely inicializálja a táblát,
     * a megadott sorok és oszlopok száma alapján.
     * @param sorok a sorok száma
     * @param oszlopok az oszlopok száma
     */
    public Tabla(final int sorok, final int oszlopok) {
        this.tablaSorok = sorok;
        this.tablaOszlopok = oszlopok;
        this.racs = new char[sorok][oszlopok];
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                this.racs[i][j] = ' ';
            }
        }
    }

    /**
     * Getter metódus a sorok számának lekérdezéséhez.
     * @return a sorok száma.
     */
    public int getSorok() {
        return tablaSorok;
    }

    /**
     * Getter metódus az oszlopok számának lekérdezéséhez.
     * @return az oszlopok száma.
     */
    public int getOszlopok() {
        return tablaOszlopok;
    }

    /**
     * Getter metódus a rács 2D tömbjének lekérdezéséhez.
     * @return a rács 2D tömbje.
     */
    public char[][] getRacs() {
        return racs;
    }

    /**
     * Korong elhelyezése a megadott oszlopban.
     * @param korong a korong színe.
     * @param oszlop az oszlop száma.
     * @return igaz, ha sikeres az elhelyezés, egyébként hamis.
     */
    public boolean korongElhelyezese(final Korong korong, final int oszlop) {
        for (int i = tablaSorok - 1; i >= 0; i--) {
            if (racs[i][oszlop] == ' ') {
                racs[i][oszlop] = korong.getSzin();
                return true;
            }
        }
        return false;
    }

    /**
     * A tábla aktuális állapotának nyomtatása.
     */
    public void tablaNyomtatasa() {
        for (int i = 0; i < tablaSorok; i++) {
            for (int j = 0; j < tablaOszlopok; j++) {
                System.out.print("| " + racs[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println(new String(new char[tablaOszlopok]).replace(
                "\0", "---"));
    }

    /**
     * Győzelem ellenőrzése a megadott korong színe alapján.
     * @param korong a korong színe.
     * @return igaz, ha a korong négy egymás mellett van, egyébként hamis.
     */
    public boolean gyozelemEllenorzes(final Korong korong) {
        char szin = korong.getSzin();
        // Vízszintes ellenőrzés
        for (int i = 0; i < tablaSorok; i++) {
            for (int j = 0; j < tablaOszlopok - MAX_ELLENORZES; j++) {
                if (racs[i][j] == szin && racs[i][j + 1] == szin
                        && racs[i][j + 2] == szin
                        && racs[i][j + GYOZELEM_HOSSZ - 1] == szin) {
                    return true;
                }
            }
        }
        // Függőleges ellenőrzés
        for (int j = 0; j < tablaOszlopok; j++) {
            for (int i = 0; i < tablaSorok - MAX_ELLENORZES; i++) {
                if (racs[i][j] == szin && racs[i + 1][j] == szin
                        && racs[i + 2][j] == szin
                        && racs[i + GYOZELEM_HOSSZ - 1][j] == szin) {
                    return true;
                }
            }
        }
        // Átlós ellenőrzés (\)
        for (int i = 0; i < tablaSorok - MAX_ELLENORZES; i++) {
            for (int j = 0; j < tablaOszlopok - MAX_ELLENORZES; j++) {
                if (racs[i][j] == szin && racs[i + 1][j + 1] == szin
                        && racs[i + 2][j + 2] == szin
                        && racs[i + GYOZELEM_HOSSZ - 1]
                        [j + GYOZELEM_HOSSZ - 1] == szin) {
                    return true;
                }
            }
        }
        // Átlós ellenőrzés (/)
        for (int i = 0; i < tablaSorok - MAX_ELLENORZES; i++) {
            for (int j = GYOZELEM_HOSSZ - 1; j < tablaOszlopok; j++) {
                if (racs[i][j] == szin && racs[i + 1][j - 1] == szin
                        && racs[i + 2][j - 2] == szin
                        && racs[i + GYOZELEM_HOSSZ - 1]
                        [j - GYOZELEM_HOSSZ + 1] == szin) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ellenőrzés, hogy tele van-e a tábla.
     * @return igaz, ha tele van, egyébként hamis.
     */
    public boolean teleVan() {
        for (int j = 0; j < tablaOszlopok; j++) {
            if (racs[0][j] == ' ') {
                return false;
            }
        }
        return true;
    }
}
