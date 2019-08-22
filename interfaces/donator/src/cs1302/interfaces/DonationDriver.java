package cs1302.interfaces;

/**
 * The main class for the donation system. This class is responsible for
 * reading in a list of donators and then asking them all to donate money
 * to the University by calling the appropriate method on each donator type.
 * Normally, faculty, alumni, and company data would be read in from a database.
 * In this simplified application, faculty, alumni, and company objects are
 * instantiated with random instance variable values. Object creation is handled
 * in a separate {@link Utility} class. The details are hidden to bring focus to
 * the important concepts of the example.
 */
public class DonationDriver {

    public static void main(String[] args) {
        Faculty[] faculty = new Faculty[10];
        Alumnus[] alumni = new Alumnus[10];
        Company[] companies = new Company[20];

        // Utility methods generate random faculty and alumnus objects.
        Utility.readFaculty(faculty);
        Utility.readAlumni(alumni);
        Utility.readCompany(companies);

        double totalDonations = 0.0;

        totalDonations = gatherTotalDonations(faculty, alumni, companies);

        System.out.printf("Total Donations: %.2f\n", totalDonations);

    } // main

    /**
     * Returns the total donations from all {@code Faculty}, {@code Alumni}, and
     * {@code Company} objects provided in the array parameters.
     *
     * @param faculty an array of references to all {@code Faculty} donators.
     * @param alumni an array of references to all {@code Alumni} donators.
     * @param companies an array of references to all {@code Company} donators.
     * @return the total donations received.
     */
    public static double gatherTotalDonations(Faculty[] faculty,
                                              Alumnus[] alumni,
                                              Company[] companies) {

        double totalDonations = 0.0;

        for (int i = 0; i < faculty.length; i++) {
            totalDonations += faculty[i].donate();
        } // for

        for (int i = 0; i < alumni.length; i++) {
            totalDonations += alumni[i].donate();
        } // for

        for (int i = 0; i < companies.length; i++) {
            totalDonations += companies[i].donate();
        } // for

        return totalDonations;
    } // gatherTotalDonations

} // DonationDriver
