package think.concurrent;

public class Ping extends Thread {
    
    private final MySimpleSynchronizer sync, otherSynch;
    private final DisplayNumber display;
    
    public Ping(final MySimpleSynchronizer sync, final MySimpleSynchronizer otherSynch
            , final DisplayNumber display) {
        this.sync = sync;
        this.otherSynch = otherSynch;
        this.display = display;
    }
    
    public void run() {
        final int nThread = 1;
        while (true) {
            try {
                while (nThread == display.getCurrentThread()) {
                    sync.waitForSignal();
                }
                log("Ping!");
                otherSynch.signalArrived();
                display.inc();
                delay(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    //public synchronized void ping() {log("ping"); delay(1000);}

    private void log(final String string) {
        synchronized (System.out) {
            System.out.println("[" + getName() + "] : " + string);
            delay(1000);
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
