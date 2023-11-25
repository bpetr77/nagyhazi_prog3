package kigyok_test;

import static org.junit.Assert.*;


import org.junit.Test;

import kigyok.Lemon;

public class Lemon_test {

	 @Test
	    public void spawnFruit_test() {
	        Lemon l = new Lemon();
	        l.spawnFruit();

	        assertTrue(l.getLocation().getX() >= 0 && l.getLocation().getX() < 600);
	        assertTrue(l.getLocation().getY() >= 0 && l.getLocation().getY() < 600);
	    }


	    @Test
	    public void getType_test() {
	    	Lemon l = new Lemon();
	        assertEquals(3, l.gettype());
	    }

}
