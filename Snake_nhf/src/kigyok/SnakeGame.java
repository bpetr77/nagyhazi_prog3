package kigyok;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener{

	public static int SCREEN_WIDTH = 600;
	public static int SCREEN_HEIGHT = 600;
	public static int UNIT_SIZE = 25;
	boolean fruitEaten = true;
	boolean gameOver = false;
	Walls walls;
	Fruit fruit;
	Snake snake;
	Player player;
	JFrame frame;
	private int speed = 100;
	Timer timer;
	
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
    
    public void checkCollision() {
    	Point head = snake.getbodyidx(0);
    	if(head != null) {
    		if (head.equals(fruit.getLocation())) {
    			if(fruit.gettype() == 1) {
    				snake.grow();
    				spawnFruit();
    				player.setScore(player.getScore() + 1);
    				fruitEaten = true;  
    				speed = 100;// Gyümölcs evés esemény
    				//orangeeaten = false;
    				
    			}else if(fruit.gettype() == 2) {
    				snake.grow();
    				snake.grow();
    				spawnFruit();
    				player.setScore(player.getScore() + 2);
    				fruitEaten = true;
    				speed = 100;
    				//orangeeaten = false;
    				
    			}else if(fruit.gettype() == 3) {
    				if(snake.getLength() % 2 == 0) {
    				//snake.setLength(snake.getLength() / 2);
    		        int newLength = snake.getLength() / 2;
    		        for (int i = snake.getLength() - 1; i >= newLength; i--) {
    		            snake.getBody().remove(snake.getbodyidx(i));
    		        }
    		        snake.setLength(newLength);
    				spawnFruit();
    				player.setScore(player.getScore() + 1);
    				fruitEaten = true;
    				speed = 100;
    				//orangeeaten = false;
    				}else {
    			        int newLength = (snake.getLength() + 1) / 2;
    			        for (int i = snake.getLength() - 1; i >= newLength; i--) {
    			            snake.getBody().remove(snake.getbodyidx(i));
    			        }
    			        snake.setLength(newLength);
        				spawnFruit();
        				player.setScore(player.getScore() + 1);
        				fruitEaten = true;
        				speed = 100;
        				//orangeeaten = false;
    				}
    			}else if(fruit.gettype() == 4) {
    				snake.grow();
    				spawnFruit();
    				player.setScore(player.getScore() + 1);
    				fruitEaten = true;
    				speed = 70;
    				//orangeeaten = true;
    			}
        	}
    		
    		if(head.x == SCREEN_WIDTH || head.y == SCREEN_HEIGHT || head.x < 0 || head.y < 0) {
    			gameOver = true; 
    		}
    		
    		for(int i = 1; i < snake.getBody().size(); i++) {
    			Point bodyPart = snake.getbodyidx(i);
    			if(head.equals(bodyPart))
    				//System.exit(0);
    				gameOver = true; 
    				//System.out.println("Collision detected! Head: " + head + ", Body Part: " + bodyPart);
    			
    		}
    		
    		for(int i = 0; i < walls.getWalls().size(); i++) {
    			Point wall = walls.getwallidx(i);
    			if(head.equals(wall))
    				gameOver = true; 
    	}
    }
    }
    
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //setBackground(Color.BLACK);
        if(!gameOver) {
        fruit.draw(g); 
        snake.draw(g);
        walls.draw(g);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + player.getScore(), SCREEN_WIDTH - 100, 30);
        timer.setDelay(speed);
        
        }else {
        	g.setColor(Color.BLACK);
        	g.setFont(new Font("font", Font.BOLD, 60));
        	g.drawString("Game Over", SCREEN_WIDTH / 2 - 154, SCREEN_HEIGHT / 2);
        	
        	Timer timer = new Timer(700, new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        // Az időzítő lejártakor futó kód
        	        // Az új MenuPanel példány létrehozása
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
        	});

        	// Időzítő indítása
        	timer.setRepeats(false); // Csak egyszer fut le
        	timer.start();
        
    	/*for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
    		g.setColor(Color.black);
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
			g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
		}*/
  
    }
    }
    
    
    public class MyKeyAdapter extends KeyAdapter{
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
