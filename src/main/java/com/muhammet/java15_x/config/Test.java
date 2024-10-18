package com.muhammet.java15_x.config;

public class Test {
    public static void main(String[] args) {
        Long currentTime = System.currentTimeMillis();
        for (long i = 0; i < 10_000_000_000L; i++) {

        }
        Long endTime = System.currentTimeMillis();
        System.out.println("Geçen Süre...: "+ (endTime-currentTime));
    }
}
