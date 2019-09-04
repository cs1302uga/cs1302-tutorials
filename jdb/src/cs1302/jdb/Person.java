package cs1302.jdb;

/** 
 * Represents a person.
 */
public class Person {

    private String name;

    /**
     * Constructs a {@code Person} object with the specified {@code name}.
     *
     * @param name  name of the person
     */
    public Person(String name) {
        this.name = name;
    } // Person

    /**
     * Returns the name of this {@code Person}.
     * @return name of this person
     */
    public String getName() {
        return name;
    } // getName

    @Override
    public String toString() {
        return String.format("Person(name = %s)", name);
    } // toString

} // Person
