package kigyok_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kigyok.Player;

public class Player_test {


    private Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals("", player.getName());
        assertEquals(0, player.getScore());
    }

    @Test
    public void testSetName() {
        player.setName("John");
        assertEquals("John", player.getName());
    }

    @Test
    public void testSetScore() {
        player.setScore(100);
        assertEquals(100, player.getScore());
    }




}
