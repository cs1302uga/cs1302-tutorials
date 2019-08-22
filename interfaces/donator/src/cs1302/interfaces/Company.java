package cs1302.interfaces;

/**
 * Represents a company. Each {@code Company} object contains the name
 * of the company, its yearly earnings and the number of alumni employed
 * by a particular University.
 */
public class Company implements Donator {

    private String name;
    private double yearlyEarnings;
    private int numAlumni;

    /**
     * Constructs a new {@code Company} object using user-specified values for
     * name, yearly earnings, and the number of alumni. To keep the focus of
     * the example on interfaces, this constructor assumes that input values are valid.
     *
     * @param name the name of the company.
     * @param yearlyEarnings the yearly earnings of the company.
     * @param numAlumni the number of alumni from a particular University.
     */
    public Company(String name, double yearlyEarnings, int numAlumni) {
        this.name = name;
        this.yearlyEarnings = yearlyEarnings;
        this.numAlumni = numAlumni;
    } // Company

    /**
     * The amount donated by the company to its affiliated University. Each company
     * donates .05% of its yearly earnings for each alumni that it has hired.
     * These companies are quite generous.
     *
     * @return the donation amount.
     */
    public double donate() {
        return .0005 * yearlyEarnings * numAlumni;
    } // donate

} // Company
