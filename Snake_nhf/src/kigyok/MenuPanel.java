package kigyok;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MenuPanel extends JPanel {
	private JTextField nameField = new JTextField(20);
	
	public MenuPanel(JFrame frame, Player player) {
		 setBackground(new Color(170, 255, 228));
    //construct components
		JButton startButton = new JButton ("Start Game");
		JButton Top5 = new JButton ("Top5");
		JButton exit = new JButton ("Exit");
		//JTextField nameField = new JTextField(20);
		JLabel TextLabel = new JLabel ("Add meg a neved: ");
		JLabel Caption = new JLabel ("SNAKE");
		
		Font buttonFont = new Font(Font.SERIF, Font.BOLD, 21);
        startButton.setFont(buttonFont);
        Top5.setFont(buttonFont);
        exit.setFont(buttonFont);
        
        Caption.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
        //Caption.setFont(CaptionFont);
        nameField.setFont(new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 20));
        
        Font TextFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 20);
        TextLabel.setFont(TextFont);

        // Szövegszín beállítása
        startButton.setForeground(Color.BLACK);
        Top5.setForeground(Color.BLACK);
        exit.setForeground(Color.BLACK);
        TextLabel.setForeground(Color.black);

        // Háttérszín beállítása
        startButton.setBackground(new Color(1, 205, 96));
        Top5.setBackground(new Color(1, 205, 96));
        exit.setBackground(new Color(150,20,20));
        nameField.setBackground(new Color(212, 255, 241));

        // Adj hozzá akciókezelőt a "Start Game" gombhoz
        startButton.addActionListener(e -> startGame(frame, player));
        exit.addActionListener(e -> System.exit(0));

    //adjust size and set layout
    setLayout (null);

    //add components
    add (startButton);
    add (Top5);
    add (exit);
    add (nameField);
    add (TextLabel);
    add (Caption);

    //set component bounds (only needed by Absolute Positioning)
    startButton.setBounds (120, 130, 160, 50);
    exit.setBounds (465, 475, 90, 60);
    Top5.setBounds (335, 130, 160, 50);
    nameField.setBounds  (260, 255, 285, 40);
    TextLabel.setBounds (35, 260, 220, 30);
    Caption.setBounds (210, 30, 235, 65);
	}
    private void startGame(JFrame frame, Player player) {
    	player.setName(nameField.getText());
        SnakeGame snakeGame = new SnakeGame(frame,player);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(snakeGame);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        //snakeGame.setFocusable(true);
        snakeGame.requestFocusInWindow();
        
    }
}
