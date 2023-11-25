package kigyok_test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import kigyok.Snake;

public class Snake_test {

	private Snake snake;

    @Before
    public void setUp() {
        snake = new Snake();
    }

    @Test
    public void testInitBody() {
        assertEquals(3, snake.getBody().size());
        assertEquals(new Point(300, 300), snake.getbodyidx(0));
        assertEquals(new Point(300, 325), snake.getbodyidx(1));
        assertEquals(new Point(300, 350), snake.getbodyidx(2));
    }

    @Test
    public void testMove() {
        snake.move();
        assertEquals(new Point(300, 275), snake.getbodyidx(0));
        assertEquals(new Point(300, 300), snake.getbodyidx(1));
        assertEquals(new Point(300, 325), snake.getbodyidx(2));
    }

    @Test
    public void testGrow() {
        snake.grow();
        assertEquals(4, snake.getLength());
    }

    @Test
    public void testSetDirection() {
        snake.setDirection('R');
        assertEquals('R', snake.getDirection());
        snake.setDirection('L');
        assertEquals('R', snake.getDirection()); // Irány ne változzon vissza
    }

}
