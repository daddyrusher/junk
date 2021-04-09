package com.daddyrusher.patterns.observer;

import java.math.BigDecimal;

public class GrabStocks {
    public static void main(String[] args) {
        StockGrabber stockGrabber = new StockGrabber();
        StockObserver observerOne = new StockObserver(stockGrabber);
        stockGrabber.setIbmPrice(new BigDecimal("100.2"));
        stockGrabber.setAaplPrice(new BigDecimal("666.2"));
        stockGrabber.setGoogPrice(new BigDecimal("123.5"));

        StockObserver observerTwo = new StockObserver(stockGrabber);
        stockGrabber.unregister(observerOne);
        stockGrabber.setIbmPrice(new BigDecimal("424.75"));
        stockGrabber.setAaplPrice(new BigDecimal("230.16"));
        stockGrabber.setGoogPrice(new BigDecimal("321.0"));
    }
}
