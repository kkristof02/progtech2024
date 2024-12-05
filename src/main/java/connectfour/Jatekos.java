package connectfour;

/**
 * A Jatekos osztály a játékosokat reprezentálja,
 * nevüket és győzelmeik számát tárolja.
 */
public final class Jatekos {
    /**
     * A játékos nevét tároló mező.
     */
    private final String jatekosNev;

    /**
     * A játékos győzelmeinek számát tároló mező.
     */
    private int jatekosGyozelmek;

    /**
     * Konstruktor, amely beállítja a játékos nevét és győzelmeinek számát.
     * @param nev a játékos neve
     * @param gyozelmek a játékos győzelmeinek száma
     */
    public Jatekos(final String nev, final int gyozelmek) {
        this.jatekosNev = nev;
        this.jatekosGyozelmek = gyozelmek;
    }

    /**
     * Getter metódus a játékos nevének lekérdezéséhez.
     * @return a játékos neve
     */
    public String getNev() {
        return jatekosNev;
    }

    /**
     * Getter metódus a játékos győzelmeinek számának lekérdezéséhez.
     * @return a játékos győzelmeinek száma
     */
    public int getGyozelmek() {
        return jatekosGyozelmek;
    }

    /**
     * Metódus a játékos győzelmeinek számának növeléséhez.
     */
    public void novelGyozelmek() {
        this.jatekosGyozelmek++;
        AdatbazisKezelo.addOrUpdateJatekos(jatekosNev, jatekosGyozelmek);
    }

    /**
     * A játékos adatait string formátumban visszaadó metódus.
     * @return a játékos adatai string formátumban
     */
    @Override
    public String toString() {
        return jatekosNev + ":" + jatekosGyozelmek;
    }

    /**
     * Statikus metódus, amely egy string alapján
     * létrehoz egy Jatekos objektumot.
     * @param str a játékos adatait tartalmazó string
     * @return a létrehozott Jatekos objektum
     */
    public static Jatekos fromString(final String str) {
        String[] parts = str.split(":");
        return new Jatekos(parts[0], Integer.parseInt(parts[1]));
    }
}
