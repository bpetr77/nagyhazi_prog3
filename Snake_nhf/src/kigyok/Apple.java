package kigyok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Apple extends Fruit {
    public Apple() {
        super();
    }

    // Az alma elhelyezését végző metódus
    @Override
    public void spawnFruit() {
        Random rand = new Random();
        int x = rand.nextInt((SnakeGame.SCREEN_WIDTH / SnakeGame.UNIT_SIZE)) * SnakeGame.UNIT_SIZE;
        int y = rand.nextInt((SnakeGame.SCREEN_HEIGHT / SnakeGame.UNIT_SIZE)) * SnakeGame.UNIT_SIZE;
        location.setLocation(x, y);
    }

    // Az almát rajzoló metódus
	@Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(location.x, location.y, SnakeGame.UNIT_SIZE, SnakeGame.UNIT_SIZE);
        
    }
	
	// Az alma típusát visszaadó metódus
    @Override
    public int gettype() {
    	return 1;
    }
}
