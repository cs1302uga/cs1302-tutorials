package cs1302.lambda;

import java.time.LocalDate;

/** Represents an employee. */
public class Employee {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate hireDate;

    /**
     * Create an {@code Employee} object.
     *
     * @param firstName first/given name for employee
     * @param lastName last/family name for employee
     * @param birthDate date of birth
     * @param hireDate employment start date
     */
    public Employee(String firstName, String lastName, LocalDate birthDate, LocalDate hireDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
    } // Employee

    @Override
    public String toString() {
        String name = String.format("%s %s", this.firstName, this.lastName);
        String fmt = "Employee(name = %s, birthDate = %s, hireDate = %s)";
        return String.format(fmt, name, this.birthDate, this.hireDate);
    } // toString

    /**
     * Return the last/family name.
     * @returns the last/family name.
     */
    public String getLastName() {
        return this.lastName;
    } // getLastName

    /**
     * Return the first/given name.
     * @returns the first/given name.
     */
    public String getFirstName() {
        return this.firstName;
    } // getFirstName

    /**
     * Return the date of birth.
     * @returns the date of birth.
     */
    public LocalDate getBirthDate() {
        return this.birthDate;
    } // getBirthdate

    /**
     * Return the employment start date.
     * @returns the employment start date.
     */
    public LocalDate getHireDate() {
        return this.hireDate;
    } // getHireDate

} // Employee
