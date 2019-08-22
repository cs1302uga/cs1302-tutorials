package cs1302.interfaces;

import java.time.Year;

/**
 * Represents a faculty member of a University. Each {@code Faculty}
 * object contains the name of the faculty member, their annual salary
 * and the year they started working at the University.
 */
public class Faculty implements Donator {

    private String name;
    private double salary;
    private Year startYear;

    /**
     * Constructs a new {@code Faculty} object using user-specified values for
     * name, salary, and start year. To keep the focus of the example
     * on interfaces, this constructor assumes that input values are valid.
     *
     * @param name the name of the faculty member.
     * @param salary the annual income of the faculty member.
     * @param startYear the year the faculty member started working for the University.
     */
    public Faculty(String name, double salary, int startYear) {
        this.name = name;
        this.salary = salary;
        this.startYear = Year.of(startYear);
    } // Faculty

    /**
     * The donation amount for a faculty member. In this example, all faculty
     * members donate the same amount: $100.00.
     *
     * @return the donation amount.
     */
    public double donate() {
        return 100.0;
    } // donate

} // Faculty
