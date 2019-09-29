package cs1302;

/**
 * This class contains a utility methods for checking the validity of
 * input parameters.
 */
public class Utility {

    /**
     * Throws a {@code NullPointerException} if the value of any
     * references in {@code o} is {@code null}. The method performs no
     * actions if none of the references are {@code null}.
     *
     * @param method the name of the calling method.
     * @param o parameter containing the object to verify.
     * @throws NullPointerException if parameter {@code o}
     * is {@code null}.
     */
    public static void checkNull(String method, Object ... o) {
        for (Object obj : o) {
            if(obj == null) {
                String message = method + ": Null Argument Provided";
                throw new NullPointerException(message);
            } // if
        } // for
    } // checkNull

} // Utility
