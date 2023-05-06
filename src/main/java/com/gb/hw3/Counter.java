package com.gb.hw3;

import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private final ReentrantLock LOCK;
    @Getter
    private int counter;

    public Counter(int startNumber) {
        LOCK = new ReentrantLock();
        counter = startNumber;
    }

    public void count() {
        LOCK.lock();
        counter++;
        LOCK.unlock();
    }
}
