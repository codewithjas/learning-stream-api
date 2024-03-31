package com.codewithjas;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamObjectCreation {

    public static void main(String[] args) {

        //Stream API can operate on:
        //1. Collection Objects
        //2. Group of objects like Array

        //Various ways to get Stream object
        //Way 1: To create an Empty Stream, use Stream.empty() method
        Stream<Object> empty = Stream.empty();
        empty.filter(Objects::isNull).forEach(System.out::println);

        //Way 2: To create stream from an Array, use Stream.of() method
        String[] names = {"ase", "yug", "iuhy", "hgj", "aytg"};
        Stream<String> namesStream = Stream.of(names);
        namesStream.forEach(System.out::println);
        //namesStream.filter(s->s.startsWith("a")).forEach(System.out::println); //won't work because stream has already been consumed in above line.

        //Way3: Using Builder pattern
        Stream<Object> streamBuilder = Stream.builder().build(); //returns empty stream
        Stream.Builder<String> stringStream = Stream.<String>builder().add("First").add("second")
                .add("Third").add("and so on..");
        
        //Way 4: Arrays.stream() method
        IntStream stream = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7});
        stream.forEach(System.out::println);

        //Way 5: by calling stream() method on any collection object
        List<Integer> list1 = List.of(2, 6, 4, 87, 9, 5, 32, 43, 88, 1);
        list1.stream().filter(s->s%2==0).forEach(System.out::println);


        //Way 6: For primitives use respective Stream classes, like IntStream, DoubleStream etc..
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6);
        System.out.println("Sum is: " + intStream.sum());

        DoubleStream doubleStream = DoubleStream.of(1, 2, 3, 4, 5, 6);
        System.out.println("Sum is: " + doubleStream.sum());

        IntStream range = IntStream.range(1, 10); //1 to 9
        IntStream closedRange = IntStream.rangeClosed(1, 10); //1 to 10
        System.out.println("Stream created from range method:");
        range.forEach(System.out::println);
        System.out.println("Closed Range Stream created from range method:");
        closedRange.forEach(System.out::println);
    }
}
