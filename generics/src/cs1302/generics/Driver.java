public class Driver {

    public static void main(String[] args) {
	StringContainer sc = new StringContainer("I wish I had a next reference");
	DoubleContainer dc = new DoubleContainer(4.5);
	IntegerContainer ic = new IntegerContainer(7);

	System.out.println(sc.get());
	System.out.println(dc.get());
	System.out.println(ic.get());
    } // main
    
} // Driver
