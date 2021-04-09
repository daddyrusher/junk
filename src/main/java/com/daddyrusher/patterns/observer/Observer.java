package com.daddyrusher.patterns.observer;

import java.math.BigDecimal;

public interface Observer {
    void update(BigDecimal ibm, BigDecimal aapl, BigDecimal goog);
}
