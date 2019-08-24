package cs1302.identity;

import java.time.LocalDate;

/**
 * Test driver for {@code Person} class, etc.
 */
public class Driver {

    public static void main(String[] args) {

        Employee[] employees = new Employee[] {
            new Employee(1, "Katherine", LocalDate.of(1918,  8, 26), LocalDate.of(1953, 6, 1)),
            new Employee(2,       "Ada", LocalDate.of(1815, 12, 10), LocalDate.of(1335, 6, 1)),
            new Employee(3,      "Alan", LocalDate.of(1912,  6, 23), LocalDate.of(1939, 9, 1))
        };

        for (Employee employee : employees) {
            System.out.println(employee);
        } // for

    } // main

} // Driver
