package connect4;

// A Jatekos osztály a játékosokat reprezentálja, nevüket és győzelmeik számát tárolja
public class Jatekos {
    // A játékos nevét tároló mező
    private final String nev;
    // A játékos győzelmeinek számát tároló mező
    private int gyozelmek;

    // Konstruktor, amely beállítja a játékos nevét és győzelmeinek számát
    public Jatekos(String nev, int gyozelmek) {
        this.nev = nev;
        this.gyozelmek = gyozelmek;
    }

    // Getter metódus a játékos nevének lekérdezéséhez
    public String getNev() {
        return nev;
    }

    // Getter metódus a játékos győzelmeinek számának lekérdezéséhez
    public int getGyozelmek() {
        return gyozelmek;
    }

    // Metódus a játékos győzelmeinek számának növeléséhez
    public void novelGyozelmek() {
        this.gyozelmek++;
    }

    // A játékos adatait string formátumban visszaadó metódus
    @Override
    public String toString() {
        return nev + ":" + gyozelmek;
    }

    // Statikus metódus, amely egy string alapján létrehoz egy Jatekos objektumot
    public static Jatekos fromString(String str) {
        String[] parts = str.split(":");
        return new Jatekos(parts[0], Integer.parseInt(parts[1]));
    }
}

