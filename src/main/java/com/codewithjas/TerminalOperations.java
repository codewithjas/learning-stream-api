package com.codewithjas;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class TerminalOperations {

    public static String print(String calledFrom) {
        System.out.println("Called from: " + calledFrom);
        return "printed";
    }

    public static void main(String[] args) {


        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, 10000));
        employees.add(new Employee(2, 20000));
        employees.add(new Employee(3, 30000));
        employees.add(new Employee(4, 40000));
        employees.add(new Employee(5, 50000));
        employees.add(new Employee(6, 50000));

        List<String> names = List.of("asd", "audd", "asas", "ygygt", "ytyty", "ciuiuh");
        List<String> emptyList = List.of();
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6);
        List<Integer> numbers = List.of(2, 6, 4, 87, 9, 5, 32, 43, 88, 1);

        //min method - terminal operation
        //returns optional object
        Optional<Integer> minVal = numbers.stream().min(Integer::compareTo);
        System.out.println("minVal is: " + minVal.get());

        employees.stream().min(comparing(Employee::getSalary))
                .ifPresent(s -> System.out.println("Min salary is: " + s.getSalary()));

        employees.stream().min(comparing(Employee::getSalary))
                .ifPresent(s -> System.out.println("Min salary Employee is: " + s.getEmpId()));

        String val = emptyList.stream().min(comparing(String::new)).orElse("No value found");
        System.out.println("Val is: " + val);

        try {
            emptyList.stream().min(comparing(String::new)).orElseThrow(Exception::new);
        } catch (Exception e) {
            System.out.println("Exception is: " + Arrays.toString(e.getStackTrace()));
            //throw new RuntimeException(e);
        }

        //Difference between orElse and orElseGet and add its example
        //orElse alwasy get executed, orElseGet only if they no value is found

        String val1 = Optional.of("CodeWithJas").orElse(IntermediateOperations.print("orElse"));
        System.out.println(val1);
        String val2 = Optional.of("CodeWithJas").orElseGet(() -> IntermediateOperations.print("orElseGet"));
        System.out.println(val2);
        Object val3 = Optional.empty().orElseGet(() -> IntermediateOperations.print("orElseGet2"));
        System.out.println(val3);


        //max method - terminal operation
        //returns optional object
        Optional<Integer> maxval = numbers.stream().max(Integer::compareTo);
        System.out.println("maxval is: " + maxval.get());

        employees.stream().max(comparing(Employee::getSalary))
                .ifPresent(s -> System.out.println("Max salary is: " + s.getSalary()));

        employees.stream().max(comparing(Employee::getSalary))
                .ifPresent(s -> System.out.println("Max salary Employee is: " + s.getEmpId()));


        //toArray method - terminal operation,
        //Transforms a stream into array
        //if used as is, it returns array of Objects

        Stream<Employee> empStream = employees.stream();
        Stream<Employee> empStream1 = employees.stream();
        Object[] empObjectArray = empStream.toArray();
        Employee[] empArray = empStream1.toArray(Employee[]::new);


        //count method - returns the number of elements inside the stream
        //terminal operation
        //return type is Long
        System.out.println("Number of elements: " + names.stream().count());
        System.out.println("Number of elements: " + (long) names.size());

        //average method - terminal operation
        //returns optional object
        OptionalDouble avg = intStream.average();
        System.out.println("Average is: " + avg.getAsDouble());

        Arrays.stream(new int[]{1, 2, 3, 4, 5})
                .average()
                .ifPresent(s -> System.out.println("Average if present is: " + s));


        //reduce - reduces the stream to a single value depending upon reduce criteria
        Integer sumOfInts = Stream.of(20, 30, 40, 50)
                .reduce(0, Integer::sum);
        System.out.println("Reduced the int stream to sum of its elemsnts: " + sumOfInts);

        String result = names.stream()
                .reduce("", (ele1, ele2) -> ele1 + ele2);
        System.out.println("Concatenated elements of String stream: " + result);

        Integer totalSalaryAmount = employees.stream()
                .reduce(0,
                        (salary, emp) -> salary + emp.getSalary(), Integer::sum);
        System.out.println("Total salary of all employees: " + totalSalaryAmount);

        //findFirst - terminal operation
        // returns the first element from the Stream regardless if the stream is empty or not
        Optional<Employee> firstEmpId = employees.stream().findFirst();
        firstEmpId.ifPresent((employee) -> System.out.println("First Emp id is:" + employee.getEmpId()));

        Optional<Object> found = Optional.empty().stream().findFirst();
        System.out.println("Is found: " + found.isPresent());

        //findFirst - terminal operation
        // returns the first element from the Stream regardless if the stream is empty or not
        Optional<Employee> anyEmpId = employees.stream().findAny();
        anyEmpId.ifPresent((emp) -> System.out.println("Any Emp id is:" + emp.getEmpId()));

        Optional<Object> foundAny = Optional.empty().stream().findFirst();
        System.out.println("Is found any: " + foundAny.isPresent());

        //forEach - terminal opertion
        //to loop over the elements of the Stream and perform any Desired operation
        //it doesn't return a stream and it's result can not be chained with other Stream calls
        employees.stream().forEach(e -> System.out.println(e.getEmpId()));

        //anyMatch - takes a predicate and returns true if any value that passes the predicate
        //allMatch - takes a predicate and returns true if ALL values pass the predicate
        //noneMatch - takes a predicate and returns True all values do NOT pass the predicate

        boolean anymatch = IntStream.of(1, 2, 3, 4, 5, 6).anyMatch(i -> i % 2 == 0);
        System.out.println("anymatch should be true and it is:" + anymatch);
        boolean allmatch = IntStream.of(1, 2, 3, 4, 5, 6).anyMatch(i -> i > 0);
        System.out.println("allmatch should be true and it is:" + allmatch);
        boolean allmatch1 = IntStream.of(1, 2, 3, 4, 5, 6).anyMatch(i -> i < 0);
        System.out.println("allmatch1 should be false and it is:" + allmatch1);
        boolean nonematch = IntStream.of(1, 2, 3, 4, 5, 6).noneMatch(i -> i < 0);
        System.out.println("nonematch should be true and it is:" + nonematch);

        //collect - terminal operation
        List<Employee> empList = employees.stream().collect(Collectors.toList());
        List<Employee> empList1 = employees.stream().toList(); //above line can also be written as this one

        Set<Employee> empSet = employees.stream().collect(Collectors.toSet());
        Set<Employee> empSet1 = new HashSet<>(employees); //above line can also be written as this one

        Vector<Employee> empVector = employees.stream().collect(Collectors.toCollection(Vector::new));
        LinkedList<Employee> linkedListEmp = employees.stream().collect(Collectors.toCollection(LinkedList::new));

        //following terminal operations are passed in collect method

        //joining - joins a list of objects into single string
        //a delimiter can also be passed

        String salaries = employees.stream()
                .map(e -> Integer.toString(e.getSalary()))
                .collect(Collectors.joining("_"));
        System.out.println("printing joined salaries: " + salaries);

        //summary statistics- prints statistical info about the stream
        IntSummaryStatistics summary = employees.stream().collect(Collectors.summarizingInt(Employee::getSalary));
        System.out.println("Summarry statistics: " + summary);

        //partitioningBy
        //creates two groups - one that meets a given condition and another that doesn't.
        //In the below example number is stream is split into two groups even and odds
        Map<Boolean, List<Integer>> evenOdd = numbers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));


        //groupingBy
        //Groups the elements by more than two categories
        //In the below example number is stream is split into two groups even and odds
        Map<Integer, List<Employee>> emps = employees.stream().collect(Collectors.groupingBy(Employee::getSalary));
        System.out.println("Grouping By Salaries " + emps);

        //groupingBy - mapping
        Map<Integer, List<Integer>> emps1 = employees.stream().collect(
                Collectors.groupingBy(
                        Employee::getSalary,
                        Collectors.mapping(Employee::getEmpId, Collectors.toList())));
        System.out.println("Grouping By Salaries - by mapping " + emps1);

        //groupingBy - reduce
        //TODO - raed about it and add its example

    }
}
