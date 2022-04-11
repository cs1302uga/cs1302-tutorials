package cs1302.web;

import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Example 4:
 * <pre>
 * $ ./compile cs1302.web/cs1302.web.Example4
 * </pre>
 */
public class Example4 {

    /**
     * Represents a student.
     */
    public static class Student {

        String name;
        int age;
        String[] classes;

        @Override
        public String toString() {
            return String.format(
                "Person(name=%s, age=%s, classes=%s)",
                this.name,
                this.age,
                Arrays.toString(this.classes));
        } // toString

    } // Student

    private static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    public static void main(String[] args) {
        String nl = "\n";
        String jsonString = "{     " + nl
            + "  \"name\": \"Jay\"," + nl
            + "  \"age\": \"19\",  " + nl
            + "  \"classes\": [    " + nl
            + "    \"CSCI 1302\",  " + nl
            + "    \"CSCI 1730\",  " + nl
            + "    \"CSCI 2610\"   " + nl
            + "  ]                 " + nl
            + "}                   ";
        System.out.println(jsonString);
        Student jay = GSON.fromJson(jsonString, Example4.Student.class);
        System.out.println(jay);
    } // main

} // Example4
