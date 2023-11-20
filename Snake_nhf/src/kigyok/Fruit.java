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

    public abstract void spawnFruit();

    public abstract void draw(Graphics g);
    
    public abstract int gettype();
}
