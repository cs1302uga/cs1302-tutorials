package cs1302.interfaces;

import java.time.Year;

/**
 * Represents an alumnus of a University. Each {@code Alumnus} object contains
 * the name of the alumnus, their current salary and their graduation year.
 */
public class Alumnus implements Donator {

    private String name;
    private double salary;
    private Year graduationYear;

    /**
     * Constructs a new {@code Alumnus} object using user-specified values for
     * name, salary, and graduation year. To keep the focus of the example
     * on interfaces, this constructor assumes that input values are valid.
     *
     * @param name the name of the alumnus.
     * @param salary the annual income of the alumnus.
     * @param graduationYear the year the alumnus graduated from the University.
     */
    public Alumnus(String name, double salary, int graduationYear) {
        this.name = name;
        this.salary = salary;
        this.graduationYear = Year.of(graduationYear);
    } // Alumnus

    /**
     * Returns the number of years that have passed since this {@code Alumnus}
     * graduated school.
     * @return the number of years since graduation.
     */
    public int yearsSinceGraduation() {
        return Year.now().getValue() - graduationYear.getValue();
    } // yearsSinceGraduation

    /**
     * The donation amount for an alumnus is a function of their salary and
     * the number of years that have passed since their graduation. Alumni
     * with a salary greater than $100,000 donate .2% of their salary the
     * first year. Others donate .1% of their salary. The donation amount increases
     * by .1% (or .2% for high earning alumni) each subsequent year.
     *
     * @return the amount donated
     */
    public double donate() {
        double donation = 0.0;

        if (salary > 100_000) {
            donation = salary * .002 * yearsSinceGraduation();
        } else {
            donation = salary * .001 * yearsSinceGraduation();
        } // if

        return donation;
    } // donate

} // Alumnus
