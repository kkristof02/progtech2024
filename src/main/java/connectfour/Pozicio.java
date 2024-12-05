package connectfour;

import java.util.Objects;

/**
 * A Pozicio osztály a játéktáblán lévő pozíciókat reprezentálja.
 */
public final class Pozicio {
    /**
     * A sor indexét tároló mező.
     */
    private final int pozicioSor;

    /**
     * Az oszlop indexét tároló mező.
     */
    private final int pozicioOszlop;

    /**
     * Konstruktor, amely beállítja a pozíció sor és oszlop értékét.
     * @param sor a sor értéke
     * @param oszlop az oszlop értéke
     */
    public Pozicio(final int sor, final int oszlop) {
        this.pozicioSor = sor;
        this.pozicioOszlop = oszlop;
    }

    /**
     * Getter metódus a sor értékének lekérdezéséhez.
     * @return a sor értéke
     */
    public int getSor() {
        return pozicioSor;
    }

    /**
     * Getter metódus az oszlop értékének lekérdezéséhez.
     * @return az oszlop értéke
     */
    public int getOszlop() {
        return pozicioOszlop;
    }

    /**
     * Két pozíció egyenlőségét ellenőrző metódus.
     * @param obj az összehasonlítandó objektum
     * @return igaz, ha a két pozíció egyenlő, egyébként hamis
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pozicio pozicio = (Pozicio) obj;
        return pozicioSor == pozicio.pozicioSor
                && pozicioOszlop == pozicio.pozicioOszlop;
    }

    /**
     * A pozíció hash kódjának létrehozása.
     * @return a pozíció hash kódja
     */
    @Override
    public int hashCode() {
        return Objects.hash(pozicioSor, pozicioOszlop);
    }

    /**
     * A pozíció string reprezentációja.
     * @return a pozíció string reprezentációja
     */
    @Override
    public String toString() {
        return "Pozicio{"
                + "sor=" + pozicioSor
                + ", oszlop=" + pozicioOszlop
                + '}';
    }
}
