package org.example;

import java.util.List;

public class Main {
    static void main() {
        List<Integer> list = List.of(1,4,6,23,67,34);
        list.stream().filter(n -> n%2 == 0).forEach(System.out::println);
    }
}
