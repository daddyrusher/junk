package com.daddyrusher.patterns.observer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StockGrabber implements Subject {
    private BigDecimal ibmPrice = BigDecimal.ZERO;
    private BigDecimal aaplPrice = BigDecimal.ZERO;
    private BigDecimal googPrice = BigDecimal.ZERO;
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        int observerId = observers.indexOf(observer);
        System.out.println("Observer with id " + (observerId + 1) + " deleted\n");
        observers.remove(observerId);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(ibmPrice, aaplPrice, googPrice));
    }

    public void setIbmPrice(BigDecimal ibmPrice) {
        this.ibmPrice = ibmPrice;
        notifyObservers();
    }

    public void setAaplPrice(BigDecimal aaplPrice) {
        this.aaplPrice = aaplPrice;
        notifyObservers();
    }

    public void setGoogPrice(BigDecimal googPrice) {
        this.googPrice = googPrice;
        notifyObservers();
    }
}
