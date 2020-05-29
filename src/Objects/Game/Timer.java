package Objects.Game;

import Util.IMinesweeper;
import acm.graphics.GImage;

public final class Timer extends NumberLabel {
    /**
     * 
     */
    private static final long serialVersionUID = -638825486638313830L;

    /**
     * The mechanism that will animate the timer and by time will change the label
     */
    public Mechanism MECHANISM = new Mechanism();

    public Timer() {
	indexes = new byte[3];
	digits = new Digit[3];
	changeLabel(0);
    }

    /**
     * Mechanism for the timer
     * 
     * @author Sadig Akhund
     *
     */
    class Mechanism extends Thread {
	private short time = 0;

	private Mechanism() {

	}

	@Override
	public void run() {
	    while (getTime() < 999) {
		time++;
		changeLabel(time);
		try {
		    sleep(1000);
		} catch (InterruptedException e) {
		}
	    }

	}

	/**
	 * @return the time
	 */
	public short getTime() {
	    return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(short time) {
	    this.time = time;
	}

    }

}

/**
 * Class for creating the digit on the timer
 * 
 * @author Sadig Akhund
 *
 */
class Digit extends GImage {
    /**
     * 
     */
    private static final long serialVersionUID = -140089431074218552L;

    public Digit(int digit) {
	super(IMinesweeper.DIGITS[digit]);
    }
}