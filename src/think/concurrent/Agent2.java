package think.concurrent;

public class Agent2 extends Thread {
    
    private final MySimpleSynchronizer sync;
    
    public Agent2(final MySimpleSynchronizer sync) {
        this.sync = sync;
    }
    
    public void run() {
        this.b1();
        try {
            this.sync.waitForSignal();
            this.b2();
            this.b3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void b1() {log("b1");}
    public void b2() {log("b2");}
    public void b3() {log("b3");}

    private void log(final String string) {
        System.out.println("[Thread-02] : " + string);
    }
    
    public void delay(final long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
