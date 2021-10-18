package cs1302.lambda;

import static java.time.LocalDate.parse;
import java.util.List;

/** Utility class with some lists. */
public class Lists {

    /** Suppress default constructor. */
    private Lists() {}

    public static List<String> WORDS = List.<String>of(
        "snatch",
        "resident",
        "atmosphere",
        "trade",
        "poison",
        "dividend",
        "helicopter",
        "deliver",
        "cellar",
        "jury");

    public static List<Employee> EMPLOYEES = List.<Employee>of(
        new Employee("Jennifer", "Whalen", parse("1972-11-27"), parse("2013-09-17")),
        new Employee("Michael", "Hartstein", parse("1975-09-16"), parse("2014-02-17")),
        new Employee("Pat", "Fay", parse("1981-10-14"), parse("2015-08-17")),
        new Employee("Susan", "Mavris", parse("1983-03-28"), parse("2012-06-02")),
        new Employee("Hermann", "Baer", parse("1985-01-01"), parse("2012-06-07")),
        new Employee("Shelley", "Higgins", parse("1973-11-27"), parse("2011-06-03")),
        new Employee("William", "Gietz", parse("1973-11-27"), parse("2013-07-02")),
        new Employee("Steven", "King", parse("1947-09-21"), parse("2011-06-20")),
        new Employee("Neena", "Kochhar", parse("1983-12-12"), parse("2013-03-03")),
        new Employee("Lex", "De Haan", parse("1982-03-09"), parse("2013-05-21")));

} // Lists
