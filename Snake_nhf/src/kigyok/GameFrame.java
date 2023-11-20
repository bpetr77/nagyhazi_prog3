package kigyok;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	GameFrame() {
		Dimension screenSize = new Dimension(617, 637);
		JFrame frame = new JFrame();
		Player player = new Player();
	    frame.add(new MenuPanel(frame, player));
	    frame.setTitle("Snake");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setResizable(false);
	    frame.setPreferredSize(screenSize);
	    frame.pack();
	    frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

}
