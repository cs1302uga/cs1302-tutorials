package cs1302.animal;

/**
 * A {@code Dog} is a domesticated, usually carnivorous,
 * {@link cs1302.animal.Animal}. A {@code Dog} typically has a long snout, an acute sense
 * of smell, nonretractable claws, and a barking, howling, or whining voice.
 */
public class Dog extends Animal {

    private String breed;

    /**
     * Constructs a {@code Dog} object with the specified {@code breed}.
     *
     * @param breed  breed name of the dog
     */
    public Dog(String breed) {
        this.breed = breed;
    } // Dog

    /**
     * Returns the breed name of the dog.
     *
     * @return breed name of the dog
     */
    public String getBreed() {
        return breed;
    } // getBreed

} // Dog
