package cs1302.identity;

import java.time.LocalDate;

/**
 * An {@code Employee} is a person who is employed.
 */
public class Employee {

    private long id;
    private String name;
    private LocalDate dateOfBirth;
    private LocalDate dateOfHire;

    /**
     * Constructs a {@code Employee} object with the given {@code id},
     * {@code name}, {@code dateOfBirth}, and {@code dateOfHire}.
     *
     * @param id           employee id
     * @param name         name of employee
     * @param dateOfBirth  date of birth
     * @param dateOfHire   date of hire
     */
    public Employee(long id, String name, LocalDate dateOfBirth, LocalDate dateOfHire) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHire = dateOfHire;
    } // Employee

    /**
     * Returns the employee id.
     *
     * @return employee id
     */
    public long getId() {
        return id;
    } // getId

    /**
     * Returns the name of this employee.
     *
     * @return name of this employee
     */
    public String getName() {
        return name;
    } // getName

    /**
     * Returns the date of birth of this employee.
     *
     * @return date of birth of this employee
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    } // getDateOfBirth

    /**
     * Returns the hiring date for this employee.
     *
     * @return hiring date for this employee
     */
    public LocalDate getDateOfHire() {
        return dateOfHire;
    } // getDateOfHire

    /**
     * Computes and returns the current age of this employee.
     *
     * @return current age of this employee
     */
    public int computeAge() {
        final LocalDate now = LocalDate.now();
        return dateOfBirth.until(now).getYears();
    } // computeAge

} // Employee
