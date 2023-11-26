package kigyok;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A Top5 osztály kezeli a legjobb 5 játékos adatait, beleértve az olvasást és írást is a fájlba.
 */
public class Top5 implements Serializable {
	private static String TOP5_FILENAME = "valami.dat";
	
    /**
     * Beállítja a top 5 játékosokat tartalmazó fájl nevét.
     *
     * @param name A fájl neve.
     */
	public static void setfilename(String name) {
		TOP5_FILENAME = name;
	}
	

    /**
     * Frissíti a top 5 játékost a megadott játékossal. A top 5 listát beolvassa, hozzáadja a jelenlegi játékost,
     * majd a játékosok pontszámának csökkenő sorrendjében rendezve elmenti a frissített listát.
     *
     * @param currentPlayer Az aktuális játékos, akit hozzáadunk a top 5 listához.
     */
    public static void updateTop5(Player currentPlayer) {
        List<Player> top5 = readTop5FromFile();
        top5.add(currentPlayer);
        Collections.sort(top5, Comparator.comparingInt(Player::getScore).reversed());
        
        // Csak az első 5 elemet tartjuk meg
        top5 = top5.subList(0, Math.min(5, top5.size()));
        
        writeTop5ToFile(top5);
    }
    
    /**
     * Beolvassa a top 5 játékost tartalmazó fájlt és visszaadja a listát.
     *
     * @return Az olvasott top 5 játékosok listája vagy üres lista, ha a beolvasás során hiba történik.
     */
    public static ArrayList<Player> readTop5FromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(TOP5_FILENAME))) {
            // Cast-oljuk vissza az ArrayList-re
            return new ArrayList<>((List<Player>) inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
    
    /**
     * Az aktuális top 5 játékost tartalmazó listát írja ki a TOP5_FILENAME fájlba.
     *
     * @param top5 A top 5 játékosok listája.
     */
    public static void writeTop5ToFile(List<Player> top5) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(TOP5_FILENAME))) {
            // ArrayList-et használunk helyette
            outputStream.writeObject(new ArrayList<>(top5));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
