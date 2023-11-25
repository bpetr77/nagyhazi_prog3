package kigyok;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Top5panel extends JPanel {

	 public void showTop5(JFrame frame) {
	        ArrayList<Player> top5Players = Top5.readTop5FromFile();
	        setBackground(new Color(170, 255, 228));
	        
	        if(top5Players.size() == 5) {
	        JLabel Caption = new JLabel("High Scores: ");
	        JLabel first = new JLabel("1. " + top5Players.get(0).getName() + "  " + top5Players.get(0).getScore() + "p");
	        JLabel second = new JLabel("2. " + top5Players.get(1).getName() + "  " + top5Players.get(1).getScore() + "p");
	        JLabel third = new JLabel("3. " + top5Players.get(2).getName() + "  " + top5Players.get(2).getScore() + "p");
	        JLabel fourth = new JLabel("4. " + top5Players.get(3).getName() + "  " + top5Players.get(3).getScore() + "p");
	        JLabel fifth = new JLabel("5. " + top5Players.get(4).getName() + "  " + top5Players.get(4).getScore() + "p");
	        
	        JButton backButton = new JButton ("Back");
	        
	        // Betűméret/stílus beállítása
	        Font backbuttonFont = new Font(Font.SERIF, Font.BOLD, 21);
	        backButton.setBackground(new Color(1, 205, 96));
	        
	        Font ScoreFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 35);
	        first.setFont(ScoreFont);
	        second.setFont(ScoreFont);
	        third.setFont(ScoreFont);
	        fourth.setFont(ScoreFont);
	        fifth.setFont(ScoreFont);
	        
	        Font CaptionFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 50);
	        Caption.setFont(CaptionFont);
	        
	        //komponensek hozzáadása
	        add(Caption);
	        add(first);
	        add(second);
	        add(third);
	        add(fourth);
	        add(fifth);
	        add(backButton);
	        
	        setLayout (null);

	      //komponensek pozíciójának és éretének precíz beállítása
	        Caption.setBounds (25, 25, 415, 60);
	        first.setBounds (80, 90, 415, 60);
	        second.setBounds (80, 155, 415, 60);
	        third.setBounds (80, 220, 415, 60);
	        fourth.setBounds (80, 285, 415, 60);
	        fifth.setBounds (80, 350, 415, 60);
	        backButton.setBounds (465, 475, 90, 60);
	        
	        //actionlistener a gombokhoz amik meghívja a megfelelő metódust (ujra vissza a menupanelbe)
            backButton.addActionListener(e -> {
                // A backButton-ra kattintva visszatér a MenuPanel-re
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new MenuPanel(frame, new Player()));
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            });
            
	        }else {
	        	// ha még nincs 5 eredmény akkor nem jelenít meg egyet sem amíg nem lesz 5
	        	JLabel warning = new JLabel("There isn't enought result");
	        	add(warning);
	        }
	    }
	} 
