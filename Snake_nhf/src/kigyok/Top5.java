package kigyok;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Top5 implements Serializable {
	private static final String TOP5_FILENAME = "valami.dat";
    
    public static void updateTop5(Player currentPlayer) {
        List<Player> top5 = readTop5FromFile();
        top5.add(currentPlayer);
        Collections.sort(top5, Comparator.comparingInt(Player::getScore).reversed());
        
        // Csak az első 5 elemet tartjuk meg
        top5 = top5.subList(0, Math.min(5, top5.size()));
        
        writeTop5ToFile(top5);
    }

    public static ArrayList<Player> readTop5FromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(TOP5_FILENAME))) {
            // Cast-oljuk vissza az ArrayList-re
            return new ArrayList<>((List<Player>) inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static void writeTop5ToFile(List<Player> top5) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(TOP5_FILENAME))) {
            // ArrayList-et használunk helyette
            outputStream.writeObject(new ArrayList<>(top5));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
