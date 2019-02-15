package cs1302;

public class Utility {

    /**
     * Throws a NullPointerException if any values in the
     * varargs parameter, {@code o} are {@code null}. The 
     * method performs no actions if all values in {@code o} 
     * are non-null.
     *
     * @param method the name of the calling method.
     * @param o varargs parameter containing all objects to verify
     * @throws NullPointerException if any element of parameter 
     * {@code o} is null.
     */
    public static void nullCheck(String method, Object ... o) {
	for(Object obj: o) {
	    if(o == null) {
		throw new NullPointerException(method +
				       ": Null Argument Provided");
	    } // if
	} // for
    } // checkNull

} // Utility
