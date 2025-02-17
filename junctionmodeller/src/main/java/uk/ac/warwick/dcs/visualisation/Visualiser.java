package uk.ac.warwick.dcs.visualisation;

/*
 * REMEMBER TO TALK ABOUT THIS IN THE DESIGN DOC:
 * 
 * Decided against FXGL in favour of Swing because FXGL was too powerful.
 * Swing is in-built and actually has all I need 
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Visualiser {
    private static final int screenSizeX = 400;
    private static final int screenSizeY = 400;

    public Visualiser(){

    }

    public void Run(){
        JFrame scr = new JFrame();

        // configure window
        scr.setTitle("Hello World");
        scr.setSize(screenSizeX, screenSizeY);
        scr.setVisible(true);
        scr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawStatics();
    }

    private void drawStatics(){
        JPanel background = new JPanel();

        
    }
}
