package concurrencyfun;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

class DaemonThreadTest {
    @Test
    void howTo() throws InterruptedException {
        var t = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("I'm forked daemon and will die in 2 seconds");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.setDaemon(true);
        t.start();

        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    void daemonThreadMagicTest() throws InterruptedException {
        var t = new Thread(() -> {

            var t2 = new Thread(() -> {
                while (true) {
                    try {
                        System.out.println("I'm forked daemon from forked daemon");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();


            while (true) {
                try {
                    System.out.println("I'm forked daemon and will die in 2 seconds");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.setDaemon(true);
        t.start();

        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    void innerForkedThreadTest() {
        new Thread(() -> {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println("I'm forked from forked thread");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true) {
                try {
                    System.out.println("I'm forked");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Test
    void immortalThread() {
        new Thread(()->{
            while(true){
                try {
                    System.out.println("I'm forked");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
