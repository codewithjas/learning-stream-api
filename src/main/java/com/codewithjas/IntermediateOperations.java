package com.codewithjas;

import java.util.*;
import java.util.stream.Stream;

public class IntermediateOperations {

    public static String print(String calledFrom){
        System.out.println("Called from: " + calledFrom);
        return "printed";
    }
    public static void main(String[] args) {


        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4, 10000));
        employees.add(new Employee(1, 30000));
        employees.add(new Employee(3, 20000));
        employees.add(new Employee(2, 50000));
        employees.add(new Employee(5, 40000));

        List<String> names = List.of("john", "teddy", "asda", "denis", "charlie", "alpha");


        //skip method - skips the number of elements provide as argument
        employees.stream().skip(2).forEach(System.out::println);  //will print emp 3,4,5 only. Emp 1 ,2 will be skipped
        System.out.println(employees.stream().skip(2).count());  //will print 3

        //limit - limits the number of elements to be procesed in the stream
        employees.stream().limit(1).forEach(System.out::println);

        //limit method can also be used to halt the creation of infinite stream, example below

        //generate methods
        List<Double> random = Stream.generate(Math::random)
                .limit(10)
                .toList();
        System.out.println("Random numbers: " + random);

        Stream.generate(() -> "CodewithJas")
                .limit(5)
                        .forEach(System.out::println); //print CodeWithJas 10 times

        //iterate method
        List<Integer> intsFrom0To9 = Stream.iterate(0, n -> n + 1)
                .limit(10)
                .toList();

        System.out.println(intsFrom0To9);

        Stream.iterate(1, n -> n+2)
                .limit(10)
                        .forEach(System.out::println); //prints 10 odd numbers

        //change in itreate introduced after Java 8 - way to halt infinite loop without limit method
        //this change kinda make it look same as traditional for loop syntax
        Stream.iterate(1, n -> n < 20, n -> n + 2)
                .forEach(System.out::println);

        //distinct - used to get rid of duplicate values from a stream
        System.out.println("Printing distinct values");
        Stream.of(10, 10, 20, 20, 30, 40, 50).distinct().forEach(System.out::println);


        //filter(Predicate) method: to filter the stream elements, takes a Predicate, meaning a condition that returns a boolean
        //Example - filter names ending with y from above list
        names.stream().filter(s -> s.endsWith("y")).forEach(System.out::println);
        //print all names
        names.forEach(n->System.out.println("name is: "+ n));


        //:: - is called method reference, compact form of lambda expression

        //map(Function): to perform operation on each stream element, takes a Function
        //Example: return square of each number in list
        List<Integer> numbers = List.of(2, 6, 4, 87, 9, 1);
        List<Integer> square = numbers.stream().map(integer -> integer * 2).toList();
        System.out.println(square);

        List<Integer> sals = employees.stream().map(Employee::getSalary).toList(); //creates a list of salaries of all emps


        //sorted method:
        System.out.println("Printing sorted numbers");
        numbers.stream().sorted().forEach(System.out::println); //default sorting

        System.out.println("EMployees before sorting: ");
        employees.stream().forEach(System.out::println);

        System.out.println("EMployees AFTER sorting: ");
        employees.stream().sorted(Comparator.comparing(Employee::getEmpId))
                .forEach(System.out::println);

        //Example of how to provide two sorting criterias
        employees.stream().sorted(Comparator.comparing(Employee::getEmpId)
                        .thenComparing(Employee::getSalary))
                .forEach(System.out::println);


        //peek operation - same as for each except it intermediate operation and other stream methods
        //can be chained after it.
        System.out.println("Peek example");
        List<Employee> emps = employees.stream().peek(System.out::println).toList();

        System.out.println("Increase salaries and print");
        List<Employee> alterSalariesAndCollect = employees.stream().peek(e -> e.setSalary(e.getSalary()+100)).toList();
        System.out.println(alterSalariesAndCollect);

        //flatMap - to flatten nested lists into one list

        List<List<Integer>> nestedtListed = Arrays.asList(
                Arrays.asList(10, 20, 40),
                Arrays.asList(70, 25, 31)
            );

        List<Integer> flatList = nestedtListed.stream()
                .flatMap(Collection::stream)
                .toList();

        System.out.println("flatList is " + flatList);

        //two additional methods introduced to Stream API after Java 8
        //takeWhile - work similar to filter operation but stops as soon as the condition is false
        //dropWhile - exact opposite of takeWhile

        //comparison of filter, takeWhile, and dropWhile

        List<Integer> numList = Arrays.asList(25, 15, 75, 35, 40, 5, 10, 55, 60);

        numList.stream().filter(n -> n<30).forEach(System.out::println); //will print 25, 15, 5, 10
        numList.stream().takeWhile(n -> n<30).forEach(System.out::println); //will print 25, 15
        numList.stream().dropWhile(n -> n<30).forEach(System.out::println); //will print 75, 35, 40, 5, 10, 55, 60

    }

}
