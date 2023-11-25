package kigyok;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	// Az osztály konstruktora, ami beállítja az ablak alapvető tulajdonságait
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
