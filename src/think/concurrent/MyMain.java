package think.concurrent;

public class MyMain extends Thread {

    public static void main(final String... s) {
        
        //final MyFirstClassConcurrent clock = new MyFirstClassConcurrent();
        //clock.start();
        
        final ClockRunnable clock2 = new ClockRunnable();
        new Thread(clock2).start();
        
        try {
            sleep(5000);
            System.out.println("Se Funziona ci sono due Thread stampati ;)");
        } catch (Exception e) {
            
        }

    }

}
