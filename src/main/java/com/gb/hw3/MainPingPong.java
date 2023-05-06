package com.gb.hw3;

public class MainPingPong {
    public static void main(String[] args) {
        Object monitor = new Object();
        int rounds = 5;
        Thread ping = new Thread(new PingPongThread(monitor, "Ping", rounds));
        Thread pong = new Thread (new PingPongThread(monitor, "Pong", rounds));
        ping.start();
        pong.start();
    }
}
