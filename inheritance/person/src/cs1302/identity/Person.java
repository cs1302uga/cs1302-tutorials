package cs1302.identity;

import java.time.LocalDate;

/**
 * A {@code Person} is a human being regarded as an individual.
 */
public class Person {

    private String name;
    private LocalDate dateOfBirth;

    /**
     * Constructs a {@code Person} object with the given {@code name}
     * and {@code dateOfHire}.
     *
     * @param name         name of person
     * @param dateOfBirth  date of birth
     */
    public Person(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    } // Person

    /**
     * Returns the name of this person.
     *
     * @return name of this person
     */
    public String getName() {
        return name;
    } // getName

    /**
     * Returns the date of birth of this person.
     *
     * @return date of birth of this person
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    } // getDateOfBirth

    /**
     * Computes and returns the current age of this person.
     *
     * @return current age of this person
     */
    public int computeAge() {
        final LocalDate now = LocalDate.now();
        return dateOfBirth.until(now).getYears();
    } // computeAge

    @Override
    public String toString() {
        return String.format("Person(name = %s, dateOfBirth = %s)", name, dateOfBirth);
    } // toString

} // Person
