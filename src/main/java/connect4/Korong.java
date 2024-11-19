package connect4;

// A Korong osztály a játékban használt korongokat reprezentálja
public class Korong {
    // A korong színét tároló mező
    private final char szin;

    // Konstruktor, amely beállítja a korong színét
    public Korong(char szin) {
        this.szin = szin;
    }

    // Getter metódus a korong színének lekérdezéséhez
    public char getSzin() {
        return szin;
    }
}


