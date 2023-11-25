package kigyok_test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import kigyok.Player;
import kigyok.Top5;

public class Top5_test {


    private static final String TEST_FILENAME = "test_top5.dat";

    @Before
    public void setUp() {
        // Inicializálás előtt töröljük a tesztfájlt (ha létezik)
        deleteTestFile();
        Top5.setfilename(TEST_FILENAME);
    }

    @After
    public void tearDown() {
        // Tesztek után töröljük a tesztfájlt
        deleteTestFile();
    }

    @Test
    public void testWriteTop5() {
        Player player1 = new Player();
        player1.setName("Player1");
        player1.setScore(10);

        Player player2 = new Player();
        player2.setName("Player2");
        player2.setScore(20);
        
        List<Player> top5 = new ArrayList<>();
        top5.add(player1);
        top5.add(player2);
        Top5.writeTop5ToFile(top5);

        ArrayList<Player> top5_b = Top5.readTop5FromFile();
        Assert.assertEquals(2, top5_b.size());
    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void testupdateTop5() {
        Player player1 = new Player();
        player1.setName("Player1");
        player1.setScore(10);

        Player player2 = new Player();
        player2.setName("Player2");
        player2.setScore(20);
        
        Player player3 = new Player();
        player3.setName("Player3");
        player3.setScore(40);
        
        Player player4 = new Player();
        player4.setName("Player4");
        player4.setScore(30);
        
        Player player5 = new Player();
        player5.setName("Player5");
        player5.setScore(50);
        
        List<Player> top5 = new ArrayList<>();
        top5.add(player1);
        top5.add(player2);
        top5.add(player3);
        top5.add(player4);
        top5.add(player5);
        
        Top5.writeTop5ToFile(top5);
        
        Player player6 = new Player();
        player6.setName("Player6");
        player6.setScore(60);
        Top5.updateTop5(player6);
        
        ArrayList<Player> top5_b = Top5.readTop5FromFile();
        
        Assert.assertEquals(5, top5_b.size());
        
        Assert.assertEquals(60, top5_b.get(0).getScore());
        Assert.assertEquals(50, top5_b.get(1).getScore());
        Assert.assertEquals(40, top5_b.get(2).getScore());
        Assert.assertEquals(30, top5_b.get(3).getScore());
        Assert.assertEquals(20, top5_b.get(4).getScore());
        
        Assert.assertEquals("Player6", top5_b.get(0).getName());
        Assert.assertEquals("Player5", top5_b.get(1).getName());
        Assert.assertEquals("Player3", top5_b.get(2).getName());
        Assert.assertEquals("Player4", top5_b.get(3).getName());
        Assert.assertEquals("Player2", top5_b.get(4).getName());
        
    }


    private void deleteTestFile() {
        File testFile = new File(TEST_FILENAME);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

}
