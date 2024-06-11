package com.dalixinc.javagames.render;

import com.dalixinc.javagames.util.FrameRate;

import java.awt.*;
import javax.swing.*;


public class HelloWorldApp extends JFrame {

    private final FrameRate frameRate;

    private static  long count = 0;

    public HelloWorldApp() {
        frameRate = new FrameRate();
    }

    protected void createAndShowGUI() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.setBackground( Color.WHITE );
        gamePanel.setPreferredSize( new Dimension( 320, 240 ) );
        getContentPane().add( gamePanel );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setTitle( "Hello World!" );
        pack();
        frameRate.initialize();
        setVisible( true );
    }

    private class GamePanel extends JPanel {
        public void paint( Graphics g ) {
            super.paint( g );
            onPaint( g );
            System.out.println(count++ );
            g = null;
        }
    }

    protected void onPaint( Graphics g ) {
        frameRate.calculate();
        g.setColor( Color.BLACK );
        g.drawString( frameRate.getFrameRate(), 30, 30 );
        repaint();
    }

    public static void main( String[] args ) {
        final HelloWorldApp app = new HelloWorldApp();
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                app.createAndShowGUI();
            }
        });
    }
}
