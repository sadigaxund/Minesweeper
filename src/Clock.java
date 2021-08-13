import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import Utils.JImages;

public class Clock extends JPanel implements Runnable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private JLabel[] digits;
    private int digitsSize = 3;
    private int margin = 4;

    // public static void main(String[] str) {
    // // Clock c = new Clock(new Timer(), 5, 4, 4, 5, 5);
    // // c.setClock(4);
    // }

    public Clock(Timer timer, int w, int h, int x, int y) {
	setSize(w, h);
	setLocation(x, y);
	this.timer = timer;
	digitsSize = Tools.numOfDigits(timer.getStopTime());
	setBorder(new CompoundBorder(
		new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
			new BevelBorder(BevelBorder.LOWERED, null, null, null, null)),
		new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
			new BevelBorder(BevelBorder.RAISED, null, null, null, null))));

	setLayout(null);
	initClock();

    }

    private void initClock() {
	digits = new JLabel[digitsSize];
	digits[0] = new JLabel();
	digits[0].setBounds(margin, margin, (getWidth() - margin * 2) / digitsSize, getHeight() - margin * 2);
	setNumber(digits[0], 0);
	add(digits[0]);

	for (int i = 1; i < digits.length; i++) {
	    digits[i] = new JLabel();
	    digits[i].setBounds(digits[i - 1].getX() + digits[i - 1].getWidth(), digits[i - 1].getY(),
		    digits[i - 1].getWidth(), digits[i - 1].getHeight());
	    setNumber(digits[i], 0);
	    add(digits[i]);
	}
	revalidate();
	repaint();

	setClock(0);
    }

    private void updateClock() {
	removeAll();
	for (JLabel label : digits) {
	    add(label);
	}
	revalidate();
	repaint();
    }

    public void setClock(int number) {
	if (Tools.numOfDigits(number) > digitsSize)
	    throw new BadInputException("Number of digits does not fit to the Clock!");

	if (number < 0)// cant display negative numbers
	    number = 0;

	if (timer.getStopTime() <= number)// if given number exceeds timer's threshold, increase threshold Ex: if 99 set
					  // to 990
	    timer.setStopTime(number * 10);
	timer.set(number);

	for (int i = 0; i < digitsSize; i++) {
	    int digit = (int) (number / Math.pow(10, digitsSize - 1 - i)) % 10;
	    setNumber(digits[i], digit);
	}

    }

    private void setNumber(JLabel label, int digit) {
	if (!Tools.isDigit(digit))
	    throw new BadInputException("Input must be positive and digit: " + digit);

	label.setIcon(new ImageIcon());
	Image icon = JImages.scaleImage(new ImageIcon(Images.getHexDigit(digit)).getImage(), label.getWidth(),
		label.getHeight());
	label.setIcon(new ImageIcon(icon));
    }

    @Override
    public void run() {
	while (timer.isEnabled()) {
	    setClock(timer.getTime()); // display
	    updateClock();
	    Tools.Wait(timer.getDelay()); // Wait

	    if (timer.isON()) {
		timer.plus1();// Time + 1

		if (timer.getStopTime() != -1 && timer.getTime() == timer.getStopTime())
		    timer.off();// stop the timer
	    }

	}
    }

    /**
     * @return the timer
     */
    public synchronized Timer getTimer() {
	return timer;
    }

    /**
     * @param timer
     *                  the timer to set
     */
    public synchronized void setTimer(Timer timer) {
	this.timer = timer;
    }

    /**
     * @return the timer
     */
    public synchronized int getValue() {
	return timer.getTime();
    }

}
