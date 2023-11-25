package kigyok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;


public abstract class Fruit {
    protected Point location;

    public Fruit() {
        location = new Point();
    }

    public Point getLocation() {
        return location;
    }
    //Az gyümölcs véletlenszerű pozícióját beállító metódus.
    //A pozíció a SnakeGame ablak méretéhez és a játék egység méretéhez van igazítva.
    public abstract void spawnFruit();

    //Gyümölcs kirajzolása
    public abstract void draw(Graphics g);
    
    //Gyümölcs típúsa
    public abstract int gettype();
}
