package cs1302.interfaces;

/**
 * Provides a variety of methods for generating random donators in the cs1302 donation
 * system.
 */
public class Utility {

    /**
     * Generates and returns a {@link Faculty} object reference with a random
     * salary and start year. The name of this randomly generated object will be
     * "Faculty" followed by the number provided in the {@code id} parameter.
     *
     * @return the randomly generated {@code Faculty} reference.
     * @param id the id number for the name field.
     */
    private static Faculty genFaculty(int id) {
        double salary = Math.random() * 50_000 + 50_000;
        int startYear = (int)(Math.random() * 40) + 1970;
        return new Faculty("Faculty" + id, salary, startYear);
    } // genFaculty

    /**
     * Generates and returns a {@link Alumnus} object reference with a random
     * salary and graduation year. The name of this randomly generated object will be
     * "Alumnus" followed by the number provided in the {@code id} parameter.
     *
     * @return the randomly generated {@code Alumnus} reference.
     * @param id the id number for the name field.
     */
    private static Alumnus genAlumnus(int id) {
        double salary = Math.random() * 30_000 + 70_000;
        int graduationYear = (int)(Math.random() * 40) + 1970;
        return new Alumnus("Alumnus" + id, salary, graduationYear);
    } // genAlumnus

    /**
     * Generates and returns a {@link Company} object reference with a random
     * yearly earnings and alumni count. The name of this randomly generated object
     * will be "Company" followed by the number provided in the {@code id} parameter.
     *
     * @return the randomly generated {@code Company} reference.
     * @param id the id number for the name field.
     */
    private static Company genCompany(int id) {
        double yearlyEarnings = Math.random() * 10_000_000 + 1_000_000;
        int numAlumni = (int)(Math.random() * 10) + 1;
        return new Company("Company" + id, yearlyEarnings, numAlumni);
    } // genCompany

    /**
     * Populates an array of {@link Faculty} references with
     * randomly assigned values for salary and start year.
     *
     * @param faculty a reference to a pre-allocated array of
     * {@link Faculty} references.
     */
    public static void readFaculty(Faculty[] faculty) {
        for (int i = 0; i < faculty.length; i++) {
            faculty[i] = genFaculty(i);
        } // for
    } // genFaculty

    /**
     * Populates an array of {@link Alumni} references with
     * randomly assigned values for salary and graduation year.
     *
     * @param alumni a reference to a pre-allocated array of
     * {@link Alumnus} references.
     */
    public static void readAlumni(Alumnus[] alumni) {
        for (int i = 0; i < alumni.length; i++) {
            alumni[i] = genAlumnus(i);
        } // for
    } // genFaculty

    /**
     * Populates an array of {@link Company} references with
     * randomly assigned values for yearly earnings and alumni count.
     *
     * @param companies a reference to a pre-allocated array of
     * {@link Company} references.
     */
    public static void readCompany(Company[] companies) {
        for (int i = 0; i < companies.length; i++) {
            companies[i] = genCompany(i);
        } // for
    } // readCompany

    /**
     * Populates an array of {@link Donator} references with
     * randomly assigned values. Each array entry is randomly selected
     * to be either a {@link Faculty}, {@link Alumnus}, or {@link Company}
     * object.
     *
     * @param donators a reference to a pre-allocated array of
     * {@link Donator} references.
     */
    public static void readDonators(Donator[] donators) {
        for (int i = 0; i < donators.length; i++) {
            int rand = (int)(Math.random() * 3);

            switch (rand) {
            case 0:
                donators[i] = genFaculty(i);
                break;
            case 1:
                donators[i] = genAlumnus(i);
                break;
            case 2:
                donators[i] = genCompany(i);
            } // switch
        } // for
    } // readDonors
} // Utility
