package com.gb.hw3;

public class MainCounter {
    public static void main(String[] args) {
        int number = 350;
        Counter counter = new Counter(number);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3251; i++) {
                counter.count();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 3409; i++) {
                counter.count();
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 2990; i++) {
                counter.count();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter.getCounter());
        System.out.println();

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.count();
                System.out.println(counter.getCounter() + " thread 1");
            }
        });
        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                counter.count();
                System.out.println(counter.getCounter() + " thread 2");
            }
        });

        thread4.start();
        thread5.start();
        try {
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter.getCounter());
    }
}
