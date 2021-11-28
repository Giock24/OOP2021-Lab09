package think.concurrent;

public class Pong extends Thread {

    private final MySimpleSynchronizer sync, otherSynch;
    
    public Pong(final MySimpleSynchronizer sync, final MySimpleSynchronizer otherSynch) {
        this.sync = sync;
        this.otherSynch = otherSynch;
    }
    
    public void run() {
        while (true) {
            try {
                sync.waitForSignal();
                log("Pong!");
                otherSynch.signalArrived();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    //public synchronized void pong() {log("pong"); delay(2000);}

    public synchronized void log(final String string) {
        synchronized (System.out) {
            System.out.println("[" + getName() + "] : " + string);
            delay(2000);
        }
    }
    
    public void delay(final long ms) {
        try {
            sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
