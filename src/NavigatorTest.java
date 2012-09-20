import lejos.nxt.Button;


public class NavigatorTest { //main class to test Navigator 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Navigator nav = new Navigator(); //creates navigator class
		Button.waitForAnyPress(); //wait to 
		nav.go();
	}

}
