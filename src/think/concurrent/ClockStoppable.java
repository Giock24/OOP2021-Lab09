package think.concurrent;

/**
 * 
 *  This Clock can be stopped
 *
 */
public class ClockStoppable implements Runnable {
    
    private final long sleep;
    private volatile boolean stopped;
    
    public ClockStoppable (final long sleep) {
        this.sleep = sleep;
    }
    
    @Override
    public void run() {
        while(!stopped) {
            final double milliseconds = new java.util.Date().getTime();
            System.out.println("milleseconds: " + milliseconds);
            try {
                Thread.sleep(this.sleep);
            } catch (IllegalArgumentException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    
    public void forceStop() {
        this.stopped = true;
        Thread.interrupted();
    }

}
