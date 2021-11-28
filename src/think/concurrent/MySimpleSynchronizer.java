package think.concurrent;

public class MySimpleSynchronizer {
    
    private boolean signalArrived;
    
    public MySimpleSynchronizer() {
        signalArrived = false;
    }
    
    public synchronized void waitForSignal() throws InterruptedException {
        while (!signalArrived) {
            wait();
        }
    }
    
    public synchronized void signalArrived() {
        signalArrived = true;
        notifyAll(); // meglio usare notifiAll al posto della notify normale
    }

}
