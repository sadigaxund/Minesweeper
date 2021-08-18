/***************************************************************************
 *   MIT License
 *   
 *   Copyright (c) 2021 Sadig Akhund
 *   
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *   
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *   
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 *
 * 
 **************************************************************************/
package Datas;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import Components.BadInputException;
import Util.Tools;
import Utils.JImages;

public class Clock extends JPanel implements Runnable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Data structure that plays the role of clock mechanism
     */
    private Timer timer;

    /**
     * An array of labels each representing a digit on the clock. Mostly three
     * digits.
     */
    private JLabel[] digits;
    /**
     * Number of digits. Default = 3
     */
    private int digitsSize = 3;
    /**
     * The margin from the borders
     */
    private int margin = 4;

    public Clock(Timer timer, int w, int h, int x, int y) {
	setSize(w, h);
	setLocation(x, y);
	this.timer = timer;
	setBorder(new CompoundBorder(
		new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
			new BevelBorder(BevelBorder.LOWERED, null, null, null, null)),
		new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
			new BevelBorder(BevelBorder.RAISED, null, null, null, null))));

	setLayout(null);
	initClock();

    }

    /**
     * Method for initializing the clock
     */
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

    /**
     * Clock Updater. Its job is to refresh the label representing its digits.
     */
    private void updateClock() {
	removeAll();
	for (JLabel label : digits)
	    add(label);
	revalidate();
	repaint();
    }

    /**
     * Change the number that the clock is displaying
     * 
     * @param number
     *                   the number to be set
     */
    public void setClock(int number) {

	// Too many digits
	if (Tools.numOfDigits(number) > digitsSize)
	    throw new BadInputException("Number of digits does not fit to the Clock!");

	// can't display negative numbers
	if (number < 0)
	    throw new BadInputException("Enter positive number!");
	/*
	 * if given number exceeds timer's threshold, increase threshold Ex: if 99 set
	 * to 990
	 */
	if (timer.getStopTime() <= number)
	    /**
	     * This way of threshold increasing was randomly chosen. Change it as you desire
	     */
	    timer.setStopTime(number * 10);
	timer.set(number);

	for (int i = 0; i < digitsSize; i++) {
	    int digit = (int) (number / Math.pow(10, digitsSize - 1 - i)) % 10;
	    setNumber(digits[i], digit);
	}

    }

    /**
     * Method for changing the icon of the given label representing each digit.
     * 
     * @param label
     *                  the label of a digit to be set
     * @param digit
     *                  the digit to set
     */
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
