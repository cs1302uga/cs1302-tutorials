package cs1302.lambda;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/** Simple driver for {@code cs1302-lambda} tutorial. */
public class Driver {

    public static void main(String[] args) {

        List<Employee> employees = Lists.EMPLOYEES; // from Lists.java

        // Consumer<Employee> println = void accept(Employee t) {
        //     System.out.println(t);
        // };
        Consumer<Employee> println = null; // REPLACE null
        Pipeline.<Employee>from(employees).forEach(println);

    } // main

} // Driver
