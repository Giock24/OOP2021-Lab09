package think.concurrent;

public class MyMain extends Thread {
    
    private static final long TIME_TO_SLEEP = 1000;

    public static void main(final String... s) {
        
        //final MyFirstClassConcurrent clock = new MyFirstClassConcurrent();
        //clock.start();
        
        //final ClockRunnable clock2 = new ClockRunnable();
        //new Thread(clock2).start();
        
        final MyMain program1 = new MyMain();
        program1.programAutoClosable();
        
        try {
            sleep(5000);
            System.out.println("Se Funziona ci sono due Thread stampati ;)");
        } catch (IllegalArgumentException | InterruptedException e) {
            e.printStackTrace();
        }
        

    }
    
    public void programAutoClosable() {
        
        final ClockStoppable clock3 = new ClockStoppable(TIME_TO_SLEEP);
        final Thread th = new Thread(clock3);
        th.start();
        
        try {
            System.out.println("This Program will close in...");
            sleep(TIME_TO_SLEEP);
            System.out.println("3");
            sleep(TIME_TO_SLEEP);
            System.out.println("2");
            sleep(TIME_TO_SLEEP);
            System.out.println("1");
            sleep(TIME_TO_SLEEP);
        } catch (IllegalArgumentException | InterruptedException e) {
            e.printStackTrace();
        }
        
        clock3.forceStop();
        //th.interrupt();
        
        System.out.println("Program Closed with success!");
    }

}
