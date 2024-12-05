package connectfour;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Az adatbázis kezelését végző osztály.
 */
public final class AdatbazisKezelo {
    /**
     * Az adatbázis URL-je.
     */
    private static final String DB_URL = "jdbc:sqlite:connectfour.db";

    // Privát konstruktor, hogy megakadályozzuk az osztály példányosítását
    private AdatbazisKezelo() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Inicializálja az adatbázist és létrehozza
     * a szükséges táblákat, ha nem léteznek.
     */
    public static void initializeDatabase() {
        try (Connection kapcsolat = DriverManager.getConnection(DB_URL)) {
            if (kapcsolat != null) {
                Statement allitas = kapcsolat.createStatement();
                String letrehozasTabla = "CREATE TABLE IF NOT EXISTS jatekos ("
                        + "nev TEXT PRIMARY KEY, "
                        + "gyozelmek INTEGER NOT NULL)";
                allitas.execute(letrehozasTabla);

                String letrehozasTablaAllas =
                        "CREATE TABLE IF NOT EXISTS allas ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "tabla TEXT NOT NULL)";
                allitas.execute(letrehozasTablaAllas);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Új játékos hozzáadása vagy győzelmeinek frissítése az adatbázisban.
     * @param nev a játékos neve
     * @param gyozelmek a játékos győzelmeinek száma
     */
    public static void addOrUpdateJatekos(final String nev,
                                          final int gyozelmek) {
        String sqlInsert = "INSERT INTO jatekos(nev, gyozelmek) VALUES(?, ?)"
                + "ON CONFLICT(nev) DO UPDATE SET gyozelmek=excluded.gyozelmek";
        try (Connection kapcsolat = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = kapcsolat.prepareStatement(sqlInsert)) {
            pstmt.setString(1, nev);
            pstmt.setInt(2, gyozelmek);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Játékos lekérdezése az adatbázisból név alapján.
     * @param nev a játékos neve
     * @return a lekérdezett játékos
     */
    public static Jatekos getJatekosByNev(final String nev) {
        String sql = "SELECT nev, gyozelmek FROM jatekos WHERE nev = ?";
        try (Connection kapcsolat = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = kapcsolat.prepareStatement(sql)) {
            pstmt.setString(1, nev);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Jatekos(rs.getString("nev"), rs.getInt("gyozelmek"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * A játékosok győzelmi rangsorának lekérdezése.
     * @return a játékosok győzelmi rangsora
     */
    public static String getHighScore() {
        StringBuilder eredmeny = new StringBuilder();
        String sql = "SELECT nev, gyozelmek FROM jatekos "
                + "ORDER BY gyozelmek DESC";
        try (Connection kapcsolat = DriverManager.getConnection(DB_URL);
             Statement allitas = kapcsolat.createStatement();
             ResultSet rs = allitas.executeQuery(sql)) {
            while (rs.next()) {
                eredmeny.append(rs.getString("nev")).append(": ")
                        .append(rs.getInt("gyozelmek")).append(" győzelem\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eredmeny.toString();
    }

    /**
     * Játékállás mentése az adatbázisba.
     * @param tabla a tábla állapota
     */
    public static void saveJatekAllas(final String tabla) {
        String sqlInsert = "INSERT INTO allas(tabla) VALUES(?)";
        try (Connection kapcsolat = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = kapcsolat.prepareStatement(sqlInsert)) {
            pstmt.setString(1, tabla);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Játékállás lekérdezése az adatbázisból.
     * @return a játékállás
     */
    public static String getJatekAllas() {
        String sql = "SELECT tabla FROM allas ORDER BY id DESC LIMIT 1";
        try (Connection kapcsolat = DriverManager.getConnection(DB_URL);
             Statement allitas = kapcsolat.createStatement();
             ResultSet rs = allitas.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getString("tabla");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
