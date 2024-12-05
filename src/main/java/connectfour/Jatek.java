package connectfour;

import java.util.Scanner;

/**
 * A Jatek osztály a játék logikáját és állapotát kezeli.
 */
public final class Jatek {
    /**
     * A játékhoz használt tábla.
     */
    private final Tabla tabla;
    /**
     * A felhasználói bemenetek olvasására szolgáló scanner.
     */
    private final Scanner beolvaso;

    /**
     * A játékos neve.
     */
    private String jatekosNev;

    /**
     * Alapértelmezett sorok száma a játékban.
     */
    private static final int ALAPERTELMEZETT_SOR = 6;

    /**
     * Alapértelmezett oszlopok száma a játékban.
     */
    private static final int ALAPERTELMEZETT_OSZLOP = 7;

    /**
     * Konstruktor, amely létrehozza a táblát és a scannert.
     * Inicializálja az adatbázist is.
     * @param sorok a tábla sorainak száma
     * @param oszlopok a tábla oszlopainak száma
     */
    public Jatek(final int sorok, final int oszlopok) {
        this(sorok, oszlopok, new Scanner(System.in));
    }

    /**
     * Konstruktor, amely létrehozza a táblát és a megadott scannert.
     * @param sorok a tábla sorainak száma
     * @param oszlopok a tábla oszlopainak száma
     * @param scanner a felhasználói bemenetek olvasására szolgáló scanner
     */
    public Jatek(final int sorok, final int oszlopok, final Scanner scanner) {
        this.tabla = new Tabla(sorok, oszlopok);
        this.beolvaso = scanner;
        AdatbazisKezelo.initializeDatabase();
    }

    /**
     * Getter metódus a tábla lekérdezéséhez.
     * @return a tábla
     */
    public Tabla getTabla() {
        return tabla;
    }

    /**
     * Megkezdi a játékot.
     */
    public void kezdes() {
        System.out.println("Üdvözöllek a Connect 4 játékban!");
        System.out.println("A játéktér mérete: " + tabla.getSorok()
                + " sor x " + tabla.getOszlopok() + " oszlop");
        System.out.print("Add meg a játékos neved: ");
        jatekosNev = beolvaso.nextLine();
        tabla.tablaNyomtatasa();

        Jatekos jatekos = AdatbazisKezelo.getJatekosByNev(jatekosNev);
        if (jatekos == null) {
            jatekos = new Jatekos(jatekosNev, 0);
        }

        boolean emberVanSoron = true; // Az emberi játékos kezd
        while (true) {
            if (emberVanSoron) {
                System.out.println("Add meg az oszlopot (1-"
                        + tabla.getOszlopok() + "): ");
                int oszlop = beolvaso.nextInt() - 1;
                if (oszlop < 0 || oszlop >= tabla.getOszlopok()) {
                    System.out.println("Érvénytelen oszlop, próbáld újra.");
                    continue;
                }
                if (!tabla.korongElhelyezese(new Korong('S'), oszlop)) {
                    System.out.println("Az oszlop tele van, próbáld újra.");
                    continue;
                }
                if (tabla.gyozelemEllenorzes(new Korong('S'))) {
                    tabla.tablaNyomtatasa();
                    System.out.println("Gratulálok, "
                            + jatekosNev + "! Nyertél!");
                    jatekos.novelGyozelmek();
                    AdatbazisKezelo.addOrUpdateJatekos(jatekos.getNev(),
                            jatekos.getGyozelmek());
                    break;
                }
            } else {
                int oszlop = (int) (Math.random() * tabla.getOszlopok());
                tabla.korongElhelyezese(new Korong('P'), oszlop);
                System.out.println("A gép lépett a "
                        + (oszlop + 1) + " oszlopba.");
                if (tabla.gyozelemEllenorzes(new Korong('P'))) {
                    tabla.tablaNyomtatasa();
                    System.out.println("A gép nyert!");
                    break;
                }
            }
            tabla.tablaNyomtatasa();
            if (tabla.teleVan()) {
                System.out.println("A játék döntetlennel zárult, "
                        + "a tábla megtelt!");
                break;
            }
            emberVanSoron = !emberVanSoron; // Vált a játékosok között
        }

        System.out.println("Győzelmi rangsor:");
        System.out.println(AdatbazisKezelo.getHighScore());

        ujJatekInditasa();
    }

    /**
     * Új játék indítása.
     */
    private void ujJatekInditasa() {
        System.out.println("Új játék indul!");
        Jatek ujJatek = new Jatek(ALAPERTELMEZETT_SOR, ALAPERTELMEZETT_OSZLOP);
        ujJatek.kezdes();
    }

    /**
     * Főprogram indítása.
     * @param args a parancssori argumentumok
     */
    public static void main(final String[] args) {
        Jatek jatek = new Jatek(ALAPERTELMEZETT_SOR, ALAPERTELMEZETT_OSZLOP);
        jatek.kezdes();
    }
}
