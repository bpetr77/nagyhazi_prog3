package kigyok;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Walls {
	private ArrayList<Point> walls;
	
	public Walls() {
		walls = new ArrayList<>();
		initwalls();
	}
	public ArrayList<Point> getWalls() {
		return walls;
	}
	
	public void initwalls() {
	        Random random = new Random();
	        for (int row = 0; row < SnakeGame.SCREEN_HEIGHT / SnakeGame.UNIT_SIZE; row++) {
	            for (int col = 0; col < SnakeGame.SCREEN_WIDTH / SnakeGame.UNIT_SIZE; col++) {
	            	if(col != SnakeGame.SCREEN_WIDTH / SnakeGame.UNIT_SIZE / 2 || row != SnakeGame.SCREEN_HEIGHT / SnakeGame.UNIT_SIZE / 2) {
	                if (random.nextDouble() < 0.01) {
                		Point point = new Point((col * SnakeGame.UNIT_SIZE), (row * SnakeGame.UNIT_SIZE));
                		walls.add(point);
                	}
            	}
            }
        }
    }
	
	public boolean Fruitcollide(Point point) {
        for (Point wall : walls) {
            if (wall.equals(point)) {
                return true;
            }
        }
        return false;
    }
	
	public void draw(Graphics g) {
		for (Point p : walls) {
				g.setColor(new Color(61, 35, 0));
				g.fillRect(p.x, p.y, SnakeGame.UNIT_SIZE, SnakeGame.UNIT_SIZE);
		}
	}
	
	public Point getwallidx(int idx) {
		if (idx < walls.size()) {
			return walls.get(idx);
		} else {
			return null;
		}
	}
	
	
}
