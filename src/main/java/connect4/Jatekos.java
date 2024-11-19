package connect4;

public class Jatekos {
    private final String nev;
    private int gyozelmek;

    public Jatekos(String nev, int gyozelmek) {
        this.nev = nev;
        this.gyozelmek = gyozelmek;
    }

    public String getNev() {
        return nev;
    }

    public int getGyozelmek() {
        return gyozelmek;
    }

    public void novelGyozelmek() {
        this.gyozelmek++;
    }

    @Override
    public String toString() {
        return nev + ":" + gyozelmek;
    }

    public static Jatekos fromString(String str) {
        String[] parts = str.split(":");
        return new Jatekos(parts[0], Integer.parseInt(parts[1]));
    }
}

