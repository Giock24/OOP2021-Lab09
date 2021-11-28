package think.concurrent;

public class Ping extends Thread {
    
    private final MySimpleSynchronizer sync, otherSynch;
    
    public Ping(final MySimpleSynchronizer sync, final MySimpleSynchronizer otherSynch) {
        this.sync = sync;
        this.otherSynch = otherSynch;
    }
    
    public void run() {
        while (true) {
            try {
                sync.waitForSignal();
                log("Ping!");
                otherSynch.signalArrived();
                delay(1000);
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
