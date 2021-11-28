package think.concurrent;

public class Agent1 extends Thread {
    
    private final MySimpleSynchronizer sync;
    
    public Agent1(final MySimpleSynchronizer sync) {
        this.sync = sync;
    }
    
    public void run() {
        this.a1();
        this.sync.signalArrived();
        this.a2();
        this.a3();
    }
    
    public void a1() {log("a1"); delay(5000);}
    public void a2() {log("a2"); delay(2000);}
    public void a3() {log("a3"); delay(1000);}

    private void log(final String string) {
        System.out.println("[Thread-01] : " + string);
    }
    
    public void delay(final long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
