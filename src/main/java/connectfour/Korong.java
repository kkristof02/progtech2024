package connectfour;

/**
 * A Korong osztály a játékban használt korongokat reprezentálja.
 */
public final class Korong {
    /**
     * A korong színét tároló mező.
     */
    private final char korongSzin;

    /**
     * Konstruktor, amely beállítja a korong színét.
     * @param szin a korong színe
     */
    public Korong(final char szin) {
        this.korongSzin = szin;
    }

    /**
     * Getter metódus a korong színének lekérdezéséhez.
     * @return a korong színe
     */
    public char getSzin() {
        return korongSzin;
    }
}
