package kigyok;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Walls osztály reprezentálja a játékban szereplő falakat.
 */
public class Walls {
	private ArrayList<Point> walls;
	
    /**
     * Walls osztály konstruktora, inicializálja a falak listáját, majd létrehozza és elhelyezi a falakat.
     */
	public Walls() {
		walls = new ArrayList<>();
		initwalls();
	}
	
    /**
     * Visszaadja a falak listáját.
     *
     * @return A falak listája.
     */
	public ArrayList<Point> getWalls() {
		return walls;
	}
	
	/**
	 * Falak inicializálása véletlenszerűen a kígyó fejének elkerülésével.
	 * A falak 0.01 valószínűséggel jönnek létre minden mezőnél, kivéve a kígyó kezdeti pozícióját.
	 */
	public void initwalls() {
	        Random random = new Random();
	        for (int row = 0; row < SnakeGame.SCREEN_HEIGHT / SnakeGame.UNIT_SIZE; row++) {
	            for (int col = 0; col < SnakeGame.SCREEN_WIDTH / SnakeGame.UNIT_SIZE; col++) {
	            	// A kígyó kezdeti pozíciójánál nincs fal
	            	if(col != SnakeGame.SCREEN_WIDTH / SnakeGame.UNIT_SIZE / 2 || row != SnakeGame.SCREEN_HEIGHT / SnakeGame.UNIT_SIZE / 2) {
	            		if (random.nextDouble() < 0.01) {
	            			Point point = new Point((col * SnakeGame.UNIT_SIZE), (row * SnakeGame.UNIT_SIZE));
	            			walls.add(point);
                	}
            	}
            }
        }
    }

    /**
     * Ellenőrzi, hogy a megadott pont (gyümölcs) ütközik-e valamely falponttal.
     *
     * @param point Az ellenőrizni kívánt pont.
     * @return Igaz, ha ütközik falponttal, különben hamis.
     */
	public boolean Fruitcollide(Point point) {
        for (Point wall : walls) {
            if (wall.equals(point)) {
                return true;
            }
        }
        return false;
    }
	
    /**
     * Kirajzolja a falakat a megadott Graphics objektum segítségével.
     *
     * @param g A Graphics objektum, amellyel rajzolni lehet.
     */
	public void draw(Graphics g) {
		for (Point p : walls) {
				g.setColor(new Color(61, 35, 0));
				g.fillRect(p.x, p.y, SnakeGame.UNIT_SIZE, SnakeGame.UNIT_SIZE);
		}
	}
	
    /**
     * Visszaadja a falak listájában az adott indexen található falat.
     *
     * @param idx Az index, amelyen található falat vissza szeretnénk kapni.
     * @return A falpont az adott indexen, vagy null, ha az index érvénytelen.
     */
	public Point getwallidx(int idx) {
		if (idx < walls.size()) {
			return walls.get(idx);
		} else {
			return null;
		}
	}
}
