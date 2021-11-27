package think.concurrent;

/**
 * 
 *  My Second Version of Clock
 *
 */
public class ClockRunnable implements Runnable {

    private double millisecond;
    //Thread th = new Thread(new ClockRunnable());
    
    public ClockRunnable () {
        this.millisecond = new java.util.Date().getTime();
    }
        
    public void run() {
        while(true) {
            this.millisecond = new java.util.Date().getTime();
            System.out.println("millesecond : " + this.millisecond);
            try {
                Thread.sleep(1000);
            } catch (IllegalArgumentException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
