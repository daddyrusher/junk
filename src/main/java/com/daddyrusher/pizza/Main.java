package com.daddyrusher.pizza;

import static com.daddyrusher.pizza.NyPizza.Size.SMALL;
import static com.daddyrusher.pizza.Pizza.Topping.*;

public class Main {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addToppings(SAUSAGE)
                .addToppings(ONION)
                .build();
        Calzone calzone = new Calzone.Builder()
                .addToppings(HAM)
                .sauceInside()
                .build();
    }
}
