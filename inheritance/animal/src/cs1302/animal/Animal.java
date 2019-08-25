package cs1302.animal;

/**
 * An {@code Animal} represents a living organism that feeds on organic matter,
 * typically having specialized sense organs and nervous system and able to
 * respond rapidly to stimuli. Objects of this class are <em>immutable</em>,
 * i.e., they cannot be modified after construction.
 */
public class Animal {

    private String genus;
    private String species;

    /**
     * Constructs a {@code Animal} object with the specified {@code genus} and
     * {@code species}.
     *
     * @param genus    genus name of the animal
     * @param species  species name of the animal
     */
    public Animal(String genus, String species) {
        this.genus = genus;
        this.species = species;
    } // Animal

    /**
     * Returns the genus name of the animal.
     *
     * @return genus name of the animal
     */
    public String getGenus() {
        return genus;
    } // getGenus

    /**
     * Returns the species name of the animal.
     *
     * @return species name of the animal
     */
    public String getSpecies() {
        return species;
    } // getSpecies

} // Animal
