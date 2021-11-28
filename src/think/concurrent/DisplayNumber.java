package think.concurrent;

public class DisplayNumber {
    
    private int currentThread;
    private final int maxThread;
    
    public DisplayNumber(final int maxThread) {
        this.currentThread = 1;
        this.maxThread = maxThread;
    }
    
    public synchronized void inc() {
        if (this.currentThread == this.maxThread) {
            this.currentThread = 1;
        } else {
            this.currentThread++;
        }
    }
    
    public synchronized int getCurrentThread() {
        return this.currentThread;
    }

}
