package kigyok_test;

import static org.junit.Assert.*;


import org.junit.Test;

import kigyok.Orange;

public class Orange_test {

	 @Test
	    public void spawnFruit_test() {
	        Orange orange = new Orange();
	        orange.spawnFruit();

	        assertTrue(orange.getLocation().getX() >= 0 && orange.getLocation().getX() < 600);
	        assertTrue(orange.getLocation().getY() >= 0 && orange.getLocation().getY() < 600);
	    }

	    @Test
	    public void getType_test() {
	    	Orange orange = new Orange();
	        assertEquals(4, orange.gettype());
	    }
	}

