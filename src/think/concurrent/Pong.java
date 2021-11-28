package think.concurrent;

public class Pong extends Thread {

    private final MySimpleSynchronizer sync, otherSynch;
    private final DisplayNumber display;
    
    public Pong(final MySimpleSynchronizer sync, final MySimpleSynchronizer otherSynch
            , final DisplayNumber display) {
        this.sync = sync;
        this.otherSynch = otherSynch;
        this.display = display;
    }
    
    public void run() {
        final int nThread = 2;
        while (true) {
            try {
                while (nThread == display.getCurrentThread()) {
                    sync.waitForSignal();
                }
                log("Pong!");
                otherSynch.signalArrived();
                display.inc();
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
