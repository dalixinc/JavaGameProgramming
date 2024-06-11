package com.dalixinc.javagames.render;

import java.awt.event.*;
import javax.swing.*;


public class RenderThreadExample extends JFrame implements Runnable {

    private volatile boolean running;
    private Thread gameThread;

    public RenderThreadExample() {

    }

    protected void createAndShowGUI() {

        setSize( 320, 240 );
        setTitle( "Render Thread" );
        setVisible( true );

        gameThread = new Thread( this );
        gameThread.start();
    }

    public void run() {
        running = true;
        while( running ) {
            System.out.println( "Game Loop" );
            sleep( 10 );
        }
    }

    private void sleep( long sleep ) {
        try {
            Thread.sleep( sleep );
        } catch( InterruptedException ex ) {
            ex.printStackTrace();   // Bad to do nothing in a catchg block
        }
    }

    protected void onWindowClosing() {
        try {
            long startTime = System.currentTimeMillis();
            System.out.println( "Stopping Thread..." );
            running = false;
            gameThread.join();
            long timeTaken = System.currentTimeMillis() - startTime;
            System.out.println( "Stopped!!! -  " + timeTaken + "ms  -  " + Thread.currentThread().getName() );
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }
        System.exit( 0 );
    }

    public static void main( String[] args ) {
        final RenderThreadExample app = new RenderThreadExample();
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
