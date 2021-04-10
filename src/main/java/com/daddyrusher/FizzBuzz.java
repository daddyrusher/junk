package com.daddyrusher;

import java.io.Serializable;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class FizzBuzz {
    private static final IntPredicate DIVIDED_BY_FIVE = i -> i % 5 == 0;
    private static final IntPredicate DIVIDED_BY_THREE = i -> i % 3 == 0;
    private static final IntPredicate DIVIDED_BY_THREE_AND_FIVE = i -> i % 3 == 0 && i % 5 == 0;

    public static void main(String[] args) {
        print();
        printNewApproach();
    }

    private static void print() {
        for (int i = 1; i <= 100; i++) {
            if (DIVIDED_BY_THREE_AND_FIVE.test(i)) {
                System.out.println("FizzBuzz");
            } else if (DIVIDED_BY_THREE.test(i)) {
                System.out.println("Fizz");
            } else if (DIVIDED_BY_FIVE.test(i)) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }

    private static void printNewApproach() {
        IntStream
                .rangeClosed(1, 100)
                .mapToObj(FizzBuzz::apply)
                .forEach(System.out::println);
    }

    private static Serializable apply(int i) {
        return DIVIDED_BY_THREE.test(i)
                ? fizzBuzzOrFizz(i)
                : buzzOrNumber(i);
    }

    private static Serializable buzzOrNumber(int i) {
        return DIVIDED_BY_FIVE.test(i) ? "Buzz" : i;
    }

    private static String fizzBuzzOrFizz(int i) {
        return DIVIDED_BY_FIVE.test(i) ? "FizzBuzz" : "Fizz";
    }
}
