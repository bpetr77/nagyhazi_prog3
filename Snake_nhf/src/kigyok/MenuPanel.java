package kigyok;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A kezdőképernyőt megjelenítő panelt reprezentáló osztály. Tartalmaz gombokat a játék indításához,
 * a Top5 játékos megjelenítéséhez, valamint a játék bezárásához. A panelen a játékos nevét is meg kell adnia
 * a játék indítása előtt.
 */
public class MenuPanel extends JPanel {
	private JTextField nameField = new JTextField(20);
    /**
     * Az osztály konstruktora. Inicializálja a panel összes komponensét, gombokat, és beállítja azok megjelenését.
     * A gombokhoz ActionListener-eket rendel, amelyek meghívják a megfelelő metódusokat.
     *
     * @param frame A fő JFrame, amelyhez a panel hozzá lesz adva.
     * @param player A játékos objektum, amely tartalmazza a játékos nevét.
     */
	public MenuPanel(JFrame frame, Player player) {
		 setBackground(new Color(170, 255, 228));

		JButton startButton = new JButton ("Start Game");
		JButton Top5 = new JButton ("Top5");
		JButton exit = new JButton ("Exit");
		JLabel TextLabel = new JLabel ("Add meg a neved: ");
		JLabel Caption = new JLabel ("SNAKE");
		
		// Betűméret/stílus beállítása
		Font buttonFont = new Font(Font.SERIF, Font.BOLD, 21);
        startButton.setFont(buttonFont);
        Top5.setFont(buttonFont);
        exit.setFont(buttonFont);
        
        Caption.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
        
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

        //actionlistenerek a gombokhoz amik meghívják a megfelelő metódusokat
        startButton.addActionListener(e -> startGame(frame, player));
        exit.addActionListener(e -> System.exit(0));
        Top5.addActionListener(e -> showTop5(frame));

   
    setLayout (null);

    //komponensek hozzáadása
    add (startButton);
    add (Top5);
    add (exit);
    add (nameField);
    add (TextLabel);
    add (Caption);

    //komponensek pozíciójának és éretének precíz beállítása
    startButton.setBounds (120, 130, 160, 50);
    exit.setBounds (465, 475, 90, 60);
    Top5.setBounds (335, 130, 160, 50);
    nameField.setBounds  (260, 255, 285, 40);
    TextLabel.setBounds (35, 260, 220, 30);
    Caption.setBounds (210, 30, 235, 65);
	}
	
    /**
     * A játék indítását végző metódus. Beállítja a játékos nevét, létrehoz egy új SnakeGame-t,
     * majd hozzáadja a játékot a fő JFrame-hez.
     *
     * @param frame  A fő JFrame, amelyhez a játék hozzá lesz adva.
     * @param player A játékos objektum, amely tartalmazza a játékos nevét.
     */
    private void startGame(JFrame frame, Player player) {
    	player.setName(nameField.getText());
        SnakeGame snakeGame = new SnakeGame(frame,player);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(snakeGame);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        snakeGame.requestFocusInWindow();
    }
    
    /**
     * A Top5 játékosokat megjelenítő metódus. Létrehoz egy új Top5Panel-t,
     * majd hozzáadja azt a fő JFrame-hez.
     *
     * @param frame A fő JFrame, amelyhez a Top5 panel hozzá lesz adva.
     */
    private void showTop5(JFrame frame) {
        Top5panel top5Panel = new Top5panel();
        top5Panel.showTop5(frame);  // Az adatok megjelenítése a Top5Panel objektumban

        // A Top5Panel hozzáadása a meglévő JFrame-hez
        frame.getContentPane().removeAll();
        frame.getContentPane().add(top5Panel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
    

    }
