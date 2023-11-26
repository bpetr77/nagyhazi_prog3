package kigyok;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * Az egyes játékosokat reprezentáló osztály. A játékosnak van neve és pontszáma.
 */
public class Player implements Serializable{
	private String name;
	private int score;
	
    /**
     * Az osztály konstruktora, amely inicializálja a játékost nevével és pontszámával.
     */
	public Player() {
		name = "";
		score = 0;
	}
	
    /**
     * Visszaadja a játékos nevét.
     *
     * @return A játékos neve.
     */
	public String getName() {
		return name;
	}
	
    /**
     * Beállítja a játékos nevét.
     *
     * @param name A beállítandó név.
     */
	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * Visszaadja a játékos pontszámát.
     *
     * @return A játékos pontszáma.
     */
	public int getScore() {
		return score;
	}
	
    /**
     * Beállítja a játékos pontszámát.
     *
     * @param score A beállítandó pontszám.
     */
	public void setScore(int score) {
		this.score = score;
	}
	
    /**
     * Új játékost inicializál, nullára állítva a nevet és a pontszámot.
     */
	public void newPlayer(){
		name = "";
		score = 0;
	}
	
}