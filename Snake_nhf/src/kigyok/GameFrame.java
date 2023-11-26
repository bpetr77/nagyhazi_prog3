package kigyok;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * A Snake játék ablakát reprezentáló osztály. Inicializálja az ablak alapvető tulajdonságait,
 * létrehozza a játékos objektumot, és hozzáadja a kezdőképernyőt megjelenítő MenuPanel-t a JFrame-hez.
 */
public class GameFrame extends JFrame {

	/**
     * Az osztály konstruktora, inicializálja az ablak tulajdonságait, létrehozza a játékos objektumot,
     * és hozzáadja a kezdőképernyőt megjelenítő MenuPanel-t a JFrame-hez.
     */
	GameFrame() {
		Dimension screenSize = new Dimension(617, 637);
		JFrame frame = new JFrame();
		Player player = new Player();
		
		// Menüpanel hozzáadása a JFrame-hez, kezdőképernyőként
	    frame.add(new MenuPanel(frame, player));
	    
	    frame.setTitle("Snake");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false);
	    frame.setPreferredSize(screenSize);
	    frame.pack();
	    frame.setVisible(true);
	    
	    // Az ablak középre pozícionálása a képernyőn
		frame.setLocationRelativeTo(null);

	}

}
