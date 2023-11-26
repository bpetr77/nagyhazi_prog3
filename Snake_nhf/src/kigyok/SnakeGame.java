package kigyok;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import kigyok.SnakeGame.MyKeyAdapter;

/**
 * A Snake játékot reprezentáló osztály.
 */
public class SnakeGame extends JPanel implements ActionListener{

	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static final int UNIT_SIZE = 25;
	boolean fruitEaten = true;
	boolean gameOver = false;
	private Walls walls;
	private Fruit fruit;
	private Snake snake;
	private Player player;
	private JFrame frame;
	private int speed = 100;
	private Timer timer;
	private boolean titelOver = false;
	
    /**
     * Konstruktor, inicializálja a játék elemeit, példányosítja a kígyót, falakat és gyümölcsöt.
     * Beállítja a játék sebességét, és elindítja a timert.
     *
     * @param frame   A játékot tartalmazó JFrame.
     * @param player1 Az aktuális játékos adatait tartalmazó Player objektum.
     */
    public SnakeGame(JFrame frame, Player player1) {
    	this.frame = frame;
    	walls = new Walls();
    	snake = new Snake();
    	fruit = new Apple();
    	this.player = player1;
        spawnFruit();
        
        addKeyListener(new MyKeyAdapter());

        timer = new Timer(speed, this);
        timer.start();
    }
    /**
     * Az ütközéseket ellenőrző függvény. Ellenőrzi, hogy a kígyó feje ütközik-e a falakkal, önmagával,
     * vagy elfogyaszt-e gyümölcsöt. Az ütközés esetén a játéknak vége.
     */
    public void checkCollision() {
    	Point head = snake.getbodyidx(0);
    	if(head != null) {
    		
    		// Ellenőrzi, hogy a kígyó feje érinti-e a gyümölcs pozícióját
    		if (head.equals(fruit.getLocation())) {
    			
    			// Gyümölcs típusa alapján végrehajtja a megfelelő műveleteket		
    			switch (fruit.gettype()) {
    			
    			// Alma esetén növeli a kígyót, frissíti a pontszámot,és új gyümölcsöt spawnol
	    		    case 1:
	    		        snake.grow();
	    		        spawnFruit();
	    		        player.setScore(player.getScore() + 1);
	    		        fruitEaten = true;
	    		        speed = 100;
	    		        break;
    		    
	                    // Barack esetén kétszer növeli a kígyót, frissíti a pontszámot,
	                    // és új gyümölcsöt spawnol
	    		    case 2:
	    		        snake.grow();
	    		        snake.grow();
	    		        spawnFruit();
	    		        player.setScore(player.getScore() + 2);
	    		        fruitEaten = true;
	    		        speed = 100;
	    		        break;
    		    
	    		        // citrom esetén a kígyó hosszát felezi, ha páratlan akkor eredeti hossz + 1 / 2,
	                    // frissíti a pontszámot, és új gyümölcsöt spawnol
	    		    case 3:
	    		        int newLength;
	    		        if (snake.getLength() % 2 == 0) {
	    		            newLength = snake.getLength() / 2;
	    		        } else {
	    		            newLength = (snake.getLength() + 1) / 2;
	    		        }
	    		        
	    		        for (int i = snake.getLength() - 1; i >= newLength; i--) {
	    		            snake.getBody().remove(snake.getbodyidx(i));
	    		        }
	    		        snake.setLength(newLength);
	    		        
	    		        spawnFruit();
	    		        player.setScore(player.getScore() + 1);
	    		        fruitEaten = true;
	    		        speed = 100;
	    		        break;
	    		    
	                    // Narancs esetén növeli a kígyót, frissíti a pontszámot,
	                    // és növeli a kígyó sebességét
	    		    case 4:
	    		        snake.grow();
	    		        spawnFruit();
	    		        player.setScore(player.getScore() + 1);
	    		        fruitEaten = true;
	    		        speed = 70;
	    		        break;
	    		    
	    		    default:
	    		        break;
    			}
        	}
    		
    		// Ellenőrzi, hogy a kígyó feje elérte-e a játékteret határoló falakat
    		if(head.x == SCREEN_WIDTH || head.y == SCREEN_HEIGHT || head.x < 0 || head.y < 0) {
    			gameOver = true; 
    		}
    		
    		// Ellenőrzi, hogy a kígyó feje ütközik-e a saját testével
    		for(int i = 1; i < snake.getBody().size(); i++) {
    			Point bodyPart = snake.getbodyidx(i);
    			if(head.equals(bodyPart))
    				gameOver = true; 
    				//System.out.println("Collision detected! Head: " + head + ", Body Part: " + bodyPart);
    			
    		}
    		// Ellenőrzi, hogy a kígyó feje ütközik-e a falakkal
    		for(int i = 0; i < walls.getWalls().size(); i++) {
    			Point wall = walls.getwallidx(i);
    			if(head.equals(wall))
    				gameOver = true; 
    	}
    }
    }
    
    /**
     * Gyümölcs spawnolását végző függvény. Ha a kígyó elfogyasztotta a gyümölcsöt,
     * véletlenszerűen választ egy új gyümölcs típust, majd elhelyezi azt a játéktéren.
     * Ellenőrzi, hogy a gyümölcs nem ütközik falakkal, és újat választ addig, amíg nem
     * helyezkedik el olyan területen, ahol nincs fal.
     */
    public void spawnFruit() {
    	if(fruitEaten) {
        Random rand = new Random();
        int randNum = rand.nextInt(100) + 1; // 1-100 közötti véletlenszám

        if (randNum <= 60) {
            fruit = new Apple();
        } else if(randNum > 60 && randNum <= 80){
            fruit = new Peach();
        }else if(randNum > 80 && randNum <= 90) {
        	fruit = new Lemon();
        }else if(randNum > 90 && randNum <= 100) {
        	fruit = new Orange();
        }

        // spawnol egy gyümölcs amíg az nem fallal egy egységbe esik
        do{
            fruit.spawnFruit();
        }while (walls.Fruitcollide(fruit.getLocation()));
        fruitEaten = false;
    	}
    	
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	spawnFruit();
    	snake.move();
    	checkCollision();
        repaint(); 
    }

    /**
     * A játékteret kirajzoló függvény, ami felülírja a JPanel osztály paintComponent metódusát.
     * Az if-else ágak alapján kezeli a játék aktuális állapotát: ha még nincs vége a játéknak, kirajzolja
     * a gyümölcsöt, a kígyót, a falakat és a játékos pontszámát. Ha vége a játéknak de még nem volt kirajzolva a game over felirat, akkor kirajzolja
     * a "Game Over" feliratot, majd frissíti a legjobb 5 játékos listát. Ha a game over feliratnak is vége akkor várunk 2 másodpercet és vissza térünk a menübe
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //setBackground(Color.BLACK);
        if(!gameOver) {
        fruit.draw(g); 
        snake.draw(g);
        walls.draw(g);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + player.getScore(), SCREEN_WIDTH - 100, 30);
        timer.setDelay(speed);
        
        }else if(gameOver && titelOver == false){
        	g.setColor(Color.BLACK);
        	g.setFont(new Font("Arial", Font.BOLD, 60));
        	g.drawString("Game Over", SCREEN_WIDTH / 2 - 154, SCREEN_HEIGHT / 2);
        	titelOver = true;
        	Top5.updateTop5(player);
        	/*ArrayList<Player> lista = Top5.readTop5FromFile();
        	for(int i = 0; i < lista.size(); i++)
        		System.out.println(lista.get(i).getName() + lista.get(i).getScore());*/

        	}else if(titelOver) {
            	try {
    				Thread.sleep(2000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		player.newPlayer();
    	        MenuPanel menuPanel = new MenuPanel(frame, player);

    	        // Az összes komponens eltávolítása a JFrame-ből
    	        frame.getContentPane().removeAll();

    	        // Az új MenuPanel hozzáadása a JFrame-hez
    	        frame.getContentPane().add(menuPanel);

    	        // A megjelenítés frissítése
    	        frame.getContentPane().revalidate();
    	        frame.getContentPane().repaint();

    	        // A fókusz beállítása a MenuPanel-re
    	        menuPanel.requestFocusInWindow();
        	}
        
  
    }
    
    /**
     * A billentyűzet eseményeit kezelő belső osztály, ami a KeyAdaptertől származik.
     */
    public class MyKeyAdapter extends KeyAdapter{
      /**
        * A lenyomott billentyűk kódjainak kezelése alapján beállítja a kígyó irányát.
        */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                snake.setDirection('U');
                break;
            case KeyEvent.VK_DOWN:
                snake.setDirection('D');
                break;
            case KeyEvent.VK_LEFT:
                snake.setDirection('L');
                break;
            case KeyEvent.VK_RIGHT:
                snake.setDirection('R');
                break;
        }
    }
    
    }
	
}
