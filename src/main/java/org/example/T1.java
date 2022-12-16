package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T1 extends Thread {
    int x;
    public T1(int x) {
        this.x = x;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (App.locker) {
                App.locker.notify();
                App.x++;
                try {
                    App.locker.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

class T2 extends Thread {
    int x;
    public T2(int x) {
        this.x = x;
    }
    @Override
    public synchronized void start() {
        while (true) {
            synchronized (App.locker) {
                try {
                    App.locker.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(App.x);
                App.locker.notify();
            }
        }
    }
}
