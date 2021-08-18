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

public class Timer {

    /**
     * Flag variable that determines the Timer mechanism's state. <br>
     * If true mechanism's loop will run, otherwise loop will not execute anything.
     */
    private boolean isON = false;
    /**
     * Flag variable in order to kill the loop
     */
    private boolean isEnabled = true;

    /**
     * Current value of time, when set it is a start time for the timer.<br>
     * Defined in seconds.
     */
    private int seconds = 0;
    /**
     * The value of delay each iteration should wait.
     */
    private int delay = 1000;
    /**
     * The threshold until which the mechanism should run until.
     */
    private int stopTime = -1;

    public Timer() {
	setup(0, 1000, 999);
	off();
    }

    public Timer(int start, int delay, int stopTime) {
	setup(start, delay, stopTime);
	off();
    }

    /**
     * Sets the timer value to default
     */
    public void reset() {
	setup(0, delay, -1);
    }

    /**
     * The method used to set the timer
     * 
     * @param start
     *                     the value of time the timer starts at
     * @param delay
     *                     the value of time the timer will wait for
     * @param stopTime
     *                     the value of time the timer will stop at
     */
    public void setup(int start, int delay, int stopTime) {
	set(start);
	setDelay(delay);
	setStopTime(stopTime);
    }

    /**
     * The method that adds the given number to the current value of seconds
     * 
     * @param secs
     *                 value by how much to increase the seconds
     */
    public void add(int secs) {
	seconds += secs;
    }

    /**
     * Auxiliary method for increasing the value of seconds by 1
     */
    public void plus1() {
	add(1);
    }

    /**************************************************************************************************
     * *************************************** SETTERS & GETTERS
     **************************************************************************************************/

    /**
     * Method that disables the mechanism, effectively killing the loop. <br>
     * See <i>Clock</i> class.
     */
    public synchronized void kill() {
	this.isEnabled = false;
    }

    /**
     * 
     * Turns on the mechanism.
     */
    public synchronized void on() {
	this.isON = true;
    }

    /**
     * 
     * Turns off the mechanism.
     */
    public synchronized void off() {
	this.isON = false;
    }

    /**
     * 
     * Return true if the mechanism is on
     * 
     * @return the isON the state of mechanism
     */
    public synchronized boolean isON() {
	return isON;
    }

    /**
     * 
     * 
     * @return the seconds the value of current time.
     */
    public synchronized int getTime() {
	return seconds;
    }

    /**
     * 
     * @param seconds
     *                    the seconds to set
     */
    public synchronized int set(int seconds) {
	return this.seconds = seconds;
    }

    /**
     * 
     * @return the delay
     */
    public synchronized int getDelay() {
	return delay;
    }

    /**
     * 
     * @param delay
     *                  the delay to set
     */
    public synchronized void setDelay(int delay) {
	this.delay = delay;
    }

    /**
     * @return the stopTime
     */
    public synchronized int getStopTime() {
	return stopTime;
    }

    /**
     * @param stopTime
     *                     the stopTime to set
     */
    public synchronized void setStopTime(int stopTime) {
	this.stopTime = stopTime;
    }

    /**
     * @return the isEnabled
     */
    public synchronized boolean isEnabled() {
	return isEnabled;
    }

}
