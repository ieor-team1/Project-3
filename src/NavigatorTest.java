import lejos.nxt.Button;


public class NavigatorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Navigator nav = new Navigator();
		Button.waitForAnyPress();
		nav.go();
	}

}
