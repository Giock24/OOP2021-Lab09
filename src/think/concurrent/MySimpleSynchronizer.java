package think.concurrent;

public class MySimpleSynchronizer {
    
    private boolean signalArrived;
    
    public MySimpleSynchronizer() {
        this.signalArrived = false;
    }
    
    public synchronized void waitForSignal() throws InterruptedException {
        while (!this.signalArrived) {
            this.wait();
        }
    }
    
    public synchronized void signaArrived() {
        this.signalArrived = true;
        this.notifyAll(); // ???
    }

}
