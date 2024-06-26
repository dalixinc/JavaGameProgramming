package com.dalixinc.javagames.render;

import com.dalixinc.javagames.util.FrameRate;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;


/**
 *  # 3
 *
 * This is a simple example of a Java Swing application that displays a frame, using active rendering.
 * We thus don't need to call repaint() in the game loop. - We are in control of the rendering.
 * We use a BufferStrategy to manage the rendering process.
 * We in fact actively tell Swing not to repaint the canvas by calling setIgnoreRepaint(true).
 */
public class ActiveRenderingExample extends JFrame implements Runnable {

    private FrameRate frameRate;
    private BufferStrategy bs;
    private volatile boolean running;
    private Thread gameThread;

    public ActiveRenderingExample() {
        frameRate = new FrameRate();
    }

    protected void createAndShowGUI() {

        Canvas canvas = new Canvas();
        canvas.setSize( 320, 240 );
        canvas.setBackground( Color.BLUE );
        canvas.setIgnoreRepaint( true );
        getContentPane().add( canvas );
        setTitle( "Active Rendering" );
        setIgnoreRepaint( true );
        pack();

        setVisible( true );
        canvas.createBufferStrategy( 2 );
        bs = canvas.getBufferStrategy();

        gameThread = new Thread( this );
        gameThread.setName( "Game Thread" );
        gameThread.start();
    }

    public void run() {
        running = true;
        frameRate.initialize();
        while( running ) {
            gameLoop();
        }
    }

    public void gameLoop() {
        do {
            do {
                Graphics g = null;
                try {
                    g = bs.getDrawGraphics();
                    g.clearRect( 0, 0, getWidth(), getHeight() );
                    render( g );
                } finally {
                    if( g != null ) {
                        g.dispose();
                    }
                }
            } while( bs.contentsRestored() );
            bs.show();
        } while( bs.contentsLost() );
    }

    private void render( Graphics g ) {
        frameRate.calculate();
        g.setColor( Color.RED );
        g.drawString( frameRate.getFrameRate(), 30, 30 );
    }

    protected void onWindowClosing() {
        try {
            long startTime = System.currentTimeMillis();
            System.out.println( "Stopping Thread..." );
            running = false;
            gameThread.join();
            long timeTaken = System.currentTimeMillis() - startTime;
            System.out.println("Stopped!!! - by closing Window in " + timeTaken + "ms  -  " + Thread.currentThread().getName() );
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }
        System.exit( 0 );
    }

    public static void main( String[] args ) {
        final ActiveRenderingExample app = new ActiveRenderingExample();
        app.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
                app.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                app.createAndShowGUI();
            }
        });
    }

}
