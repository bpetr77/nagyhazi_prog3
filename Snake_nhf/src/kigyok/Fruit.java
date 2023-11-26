package kigyok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;


/**
 * Az általános gyümölcs (pl. alma, barack, stb.) absztrakt osztály, amelyet minden gyümölcs
 * típus implementál. A gyümölcsök a kígyó által elfogyasztandó objektumokat jelentik a játékban.
 */
public abstract class Fruit {
	/** A gyümölcs pozíciója a játékteren. */
    protected Point location;

    /**
     * Az osztály konstruktora. Inicializálja a gyümölcs pozícióját.
     */
    public Fruit() {
        location = new Point();
    }

    /**
     * Az adott gyümölcs típus elhelyezését végző metódus a játékteren.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Az adott gyümölcs típus elhelyezését végző metódus a játékteren.
     * A pozíció a SnakeGame ablak méretéhez és a játék egység méretéhez van igazítva.
     */
    public abstract void spawnFruit();

    /**
     * Az adott gyümölcs típus rajzolását végző metódus a játékteren.
     *
     * @param g a Graphics objektum, amelyen a rajzolás történik
     */
    public abstract void draw(Graphics g);
    
    /**
     * Az adott gyümölcs típusát visszaadó metódus.
     *
     * @return a gyümölcs típusa
     */
    public abstract int gettype();
}
