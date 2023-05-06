package com.gb.hw3;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PingPongThread implements Runnable {
    private final Object MONITOR;
    private final String NAME;
    private final int ROUNDS;

    @Override
    public void run() {
        synchronized (MONITOR) {
            for (int i = 0; i < ROUNDS; i++) {
                System.out.printf("%s (round %d)\n", NAME, i + 1);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MONITOR.notify();

                try {
                    MONITOR.wait(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
