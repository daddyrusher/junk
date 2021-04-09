package com.daddyrusher.patterns.observer;

import java.math.BigDecimal;

public class StockObserver implements Observer {
    private static int observerIdTracker = 0;
    private BigDecimal ibmPrice;
    private BigDecimal aaplPrice;
    private BigDecimal googPrice;
    private int observerId;
    private Subject stockGrabber;

    public StockObserver(Subject stockGrabber) {
        this.stockGrabber = stockGrabber;
        this.observerId = ++observerIdTracker;
        System.out.println("added observer with id: " + this.observerId + "\n");
        stockGrabber.register(this);
    }

    @Override
    public void update(BigDecimal ibm, BigDecimal aapl, BigDecimal goog) {
        this.ibmPrice = ibm;
        this.aaplPrice = aapl;
        this.googPrice = goog;

        printPrices();
    }

    private void printPrices() {
        String formattedPriceInfo = "Observer id: " + observerId +
                "\nIBM price: " + ibmPrice +
                "\nAAPL price: " + aaplPrice +
                "\nGOOG price: " + googPrice + "\n";
        System.out.println(formattedPriceInfo);
    }
}
