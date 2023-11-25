package kigyok_test;

import static org.junit.Assert.*;


import org.junit.Test;

import kigyok.Apple;

public class Apple_test {

	 @Test
	    public void spawnFruit_test() {
	        Apple apple = new Apple();
	        apple.spawnFruit();

	        assertTrue(apple.getLocation().getX() >= 0 && apple.getLocation().getX() < 600);
	        assertTrue(apple.getLocation().getY() >= 0 && apple.getLocation().getY() < 600);
	    }


	    @Test
	    public void getType_test() {
	        Apple apple = new Apple();
	        assertEquals(1, apple.gettype());
	    }
}