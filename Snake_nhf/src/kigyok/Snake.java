package kigyok;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	private ArrayList<Point> body; // Kígyó testének koordinátái
	private int length; // Kígyó hossza
	private char direction; // Kígyó fejének iránya ('U' - fel, 'D' - le, 'L' - balra, 'R' - jobbra)

	public Snake() {
		body = new ArrayList<>();
		length = 3;
		direction = 'U'; // Kezdetben a kígyó felfelé néz
		initBody();
	}

	public Point getbodyidx(int idx) {
		if (idx < body.size()) {
			return body.get(idx);
		} else {
			return null;
		}
	}

	public ArrayList<Point> getBody() {
		return body;
	}

	public void initBody() {
		// Kezdetben a kígyó a képernyő közepén lesz
		int x = SnakeGame.SCREEN_WIDTH / (2 * SnakeGame.UNIT_SIZE) * SnakeGame.UNIT_SIZE;
		int y = SnakeGame.SCREEN_HEIGHT / (2 * SnakeGame.UNIT_SIZE) * SnakeGame.UNIT_SIZE;

		// 3 testrész inicializálása
		for (int i = 0; i < length; i++) {
			Point point = new Point(x, y + i * SnakeGame.UNIT_SIZE);
			body.add(point);
		}
	}

	public void move() {
		// A kígyó mozgása a jelenlegi irányba
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

	public void grow() {
		length++;
	}

	public void draw(Graphics g) {
		// Rajzoljuk meg a kígyót
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

	public void setDirection(char newDirection) {
		// Irány beállítása, de ne engedjük meg az ellenkező irányba való változtatást
		if (newDirection == 'U' && direction != 'D')
			direction = newDirection;
		else if (newDirection == 'D' && direction != 'U')
			direction = newDirection;
		else if (newDirection == 'L' && direction != 'R')
			direction = newDirection;
		else if (newDirection == 'R' && direction != 'L')
			direction = newDirection;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
