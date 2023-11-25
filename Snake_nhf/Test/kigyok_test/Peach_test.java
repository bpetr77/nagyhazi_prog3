package kigyok_test;

import static org.junit.Assert.*;


import org.junit.Test;

import kigyok.Peach;

public class Peach_test {

	 @Test
	    public void spawnFruit_test() {
	        Peach peach = new Peach();
	        peach.spawnFruit();

	        assertTrue(peach.getLocation().getX() >= 0 && peach.getLocation().getX() < 600);
	        assertTrue(peach.getLocation().getY() >= 0 && peach.getLocation().getY() < 600);
	    }

	    @Test
	    public void getType_test() {
	    	Peach peach = new Peach();
	        assertEquals(2, peach.gettype());
	    }

}
