package concurrencyfun;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class StateDuringThreadSleepingTest {
    @Test
    void testState() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " :: in State :: " + Thread.currentThread().getState());

        var t = new Thread(() -> {
            final Thread thisThread = Thread.currentThread();
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        System.out.println(t.getName() + " :: in State :: " + t.getState());
        t.start();
        System.out.println(t.getName() + " :: in State :: " + t.getState());
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println(t.getName() + " :: in State :: " + t.getState());
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println(t.getName() + " :: in State :: " + t.getState());
    }
}
