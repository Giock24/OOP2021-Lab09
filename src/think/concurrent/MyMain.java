package think.concurrent;

public class MyMain extends Thread{

    public static void main(final String... s) {
        
        final MyFirstClassConcurrent clock = new MyFirstClassConcurrent();
        clock.start();
        
        try {
            sleep(5);
            System.out.println("Se Funziona ci sono due Thread stampati ;)");
        } catch (Exception e) {
            
        }

    }

}
