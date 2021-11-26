package think.concurrent;

import java.util.concurrent.*;

/**
 * 
 *  This is a Clock that use a Thread Class
 *
 */
public class MyFirstClassConcurrent extends Thread {
    
    private double millisecond;
    //final private java.util.Date d = new java.util.Date();
    
    public MyFirstClassConcurrent () {
        this.millisecond = new java.util.Date().getTime();
    }
    
    public void run() {
        while(true) {
            this.millisecond = new java.util.Date().getTime();
            System.out.println("millesecond : " + this.millisecond);
            try {
                sleep(1000);
            } catch (Exception e) {
                
            }
        }
    }

}
