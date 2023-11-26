package kigyok;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * A kígyót reprezentáló osztály. Tartalmazza a kígyó testének koordinátáit, hosszát, és irányát.
 */
public class Snake {
	private ArrayList<Point> body; // Kígyó testének koordinátái
	private int length; // Kígyó hossza
	private char direction; // Kígyó fejének iránya ('U' - fel, 'D' - le, 'L' - balra, 'R' - jobbra)

    /**
     * Az osztály konstruktora, inicializálja a kígyó testét, hosszát és kezdeti irányát.
     */
	public Snake() {
		body = new ArrayList<>();
		length = 3;
		direction = 'U'; // Kezdetben a kígyó felfelé néz
		initBody();
	}
	
    /**
     * Visszaadja a kígyó fejének irányát.
     *
     * @return A kígyó fejének iránya.
     */
	public char getDirection() {
		return direction;
	}
	
    /**
     * Visszaadja a kígyó egy adott testrészét a megadott index alapján.
     *
     * @param idx Az index, amely alapján lekérjük a testrészt.
     * @return A kígyó testrésze a megadott indexen vagy null, ha az index érvénytelen.
     */
	public Point getbodyidx(int idx) {
		if (idx < body.size()) {
			return body.get(idx);
		} else {
			return null;
		}
	}

    /**
     * Visszaadja a kígyó teljes testét.
     *
     * @return A kígyó testét tartalmazó lista.
     */
	public ArrayList<Point> getBody() {
		return body;
	}
	
    /**
     * Inicializálja a kígyó testét kezdetben a képernyő közepén lesz, és 3 testrésszel
     */
	public void initBody() {
		int x = SnakeGame.SCREEN_WIDTH / (2 * SnakeGame.UNIT_SIZE) * SnakeGame.UNIT_SIZE;
		int y = SnakeGame.SCREEN_HEIGHT / (2 * SnakeGame.UNIT_SIZE) * SnakeGame.UNIT_SIZE;

		for (int i = 0; i < length; i++) {
			Point point = new Point(x, y + i * SnakeGame.UNIT_SIZE);
			body.add(point);
		}
	}
	

    /**
     * A kígyó mozgása a jelenlegi irányba, ahol az új fej a régi fej + 1 blokk a jelenlegi irányba, és az utolsó testrész törlődik
     */
	public void move() {
		Point head = body.get(0);
		Point newHead = new Point(head);

		switch (direction) {
		case 'U':
			newHead.y -= SnakeGame.UNIT_SIZE;
			break;
		case 'D':
			newHead.y += SnakeGame.UNIT_SIZE;
			break;
		case 'L':
			newHead.x -= SnakeGame.UNIT_SIZE;
			break;
		case 'R':
			newHead.x += SnakeGame.UNIT_SIZE;
			break;
		}

		// Hozzáadjuk az új fejet a kígyóhoz
		body.add(0, newHead);

		// Ha elértük a kígyó testének hosszát, eltávolítjuk a farok végét
		if (body.size() > length) {
			body.remove(length);
		}
	}

    /**
     * Növeli a kígyó hosszát.
     */
	public void grow() {
		length++;
	}

    /**
     * Kirajzolja a kígyót, a fej és a testrészek eltérő árnyalatú zölddel.
     *
     * @param g A Graphics objektum, amelyre rajzolunk.
     */
	public void draw(Graphics g) {
		Point head = body.get(0);
		for (Point p : body) {
			if (p.equals(head)) {
				g.setColor(new Color(57, 219, 73));
				g.fillRect(p.x, p.y, SnakeGame.UNIT_SIZE, SnakeGame.UNIT_SIZE);
			} else {
				g.setColor(new Color(54, 202, 69));
				g.fillRect(p.x, p.y, SnakeGame.UNIT_SIZE, SnakeGame.UNIT_SIZE);
			}
		}
	}

	 /**
     * Beállítja a kígyó irányát. Nem engedi meg az ellenkező irányba való változtatást (180 fokos változást).
     *
     * @param newDirection Az új irány, amelybe a kígyót állítjuk.
     */
	public void setDirection(char newDirection) {
		if (newDirection == 'U' && direction != 'D')
			direction = newDirection;
		else if (newDirection == 'D' && direction != 'U')
			direction = newDirection;
		else if (newDirection == 'L' && direction != 'R')
			direction = newDirection;
		else if (newDirection == 'R' && direction != 'L')
			direction = newDirection;
	}

    /**
     * Visszaadja a kígyó hosszát.
     *
     * @return A kígyó hossza.
     */
	public int getLength() {
		return length;
	}

    /**
     * Beállítja a kígyó hosszát.
     *
     * @param length Az új kígyó hossza.
     */
	public void setLength(int length) {
		this.length = length;
	}

}
