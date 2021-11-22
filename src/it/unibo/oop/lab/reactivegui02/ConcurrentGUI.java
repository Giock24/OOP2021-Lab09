package it.unibo.oop.lab.reactivegui02;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 * 
 * My Personal reactive GUI.
 *
 */
public class ConcurrentGUI extends JFrame {
    
    /**
     * 
     */
    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.1;
    private static final long serialVersionUID = 1L;
    
    private final JLabel display = new JLabel();
    private final JButton up = new JButton("up");
    private final JButton down = new JButton("down");
    private final JButton stop = new JButton("stop");
    
    public void setPanel() {
        
        final JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        /* components p1 */
        //display.setText(Integer.toString(this.counter));
        p1.add(display);
        p1.add(up);
        p1.add(down);
        p1.add(stop);
        
        /* Set On Visible all components */
        this.getContentPane().add(p1);
        
        /* Create the counter agent and start it */
        final Agent agent = new Agent();
        new Thread(agent).start();
        /*
         * Register a listener that stops it
         */
        stop.addActionListener(new ActionListener() {
            /**
             * event handler associated to action event on button stop.
             * 
             * @param e
             *            the action event that will be handled by this listener
             */
            @Override
            public void actionPerformed(final ActionEvent e) {
                // Agent should be final
                agent.stopCounting();
            }
        });
        
        up.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                agent.dirUp();
                
            }

        });
        
        down.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                agent.dirDown();
                
            }

        });
    }
    
    public void view() {
        
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setVisible(true);
        
    }
    
    private class Agent implements Runnable {

        private volatile boolean stop;
        private volatile boolean direction;
        private int counter;
        
        @Override
        public void run() {
            while(!this.stop) {
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            // This will happen in the EDT: since i'm reading counter it needs to be volatile.
                            ConcurrentGUI.this.display.setText(Integer.toString(Agent.this.counter));
                        }
                    });
                    if (!this.direction) {
                        this.increment();
                    } else {
                        this.decrement();
                    }
                    Thread.sleep(100);
                } catch (InvocationTargetException | InterruptedException ex) {
                    /*
                     * This is just a stack trace print, in a real program there
                     * should be some logging and decent error reporting
                     */
                    ex.printStackTrace();
                }
            }
            
        }
        /**
         * External command to stop counting.
         */
        public void stopCounting() {
            this.stop = true;
        }
        
        public void dirUp() {
            this.direction = false;
        }
        
        public void dirDown() {
            this.direction = true;
        }
        
        public synchronized void increment() {
            this.counter++;
        }
        
        public synchronized void decrement() {
            this.counter--;
        }
        
    }
}
