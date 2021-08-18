/***************************************************************************
 *     Copyright 2021 Sadig Akhund @ https://github.com/sadigaxund
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 **************************************************************************/
package Datas;

public class Timer {

    private boolean isON = false;
    private boolean isEnabled = true;
    private int seconds = 0;
    private int delay = 1000;
    private int stopTime = -1;

    public Timer() {
	setup(0, 1000, 999);
	off();
    }

    public Timer(int start, int delay, int stopTime) {
	setup(start, delay, stopTime);
	off();
    }

    public void reset() {
	setup(0, delay, -1);
    }

    public void setup(int start, int delay, int stopTime) {
	set(start);
	setDelay(delay);
	setStopTime(stopTime);
    }

    public void add(int secs) {
	seconds += secs;
    }

    public void plus1() {
	add(1);
    }

    /**
     * @return the isON
     */
    public synchronized boolean isON() {
	return isON;
    }

    public synchronized void on() {
	this.isON = true;
    }

    public synchronized void off() {
	this.isON = false;
    }

    /**
     * @return the seconds
     */
    public synchronized int getTime() {
	return seconds;
    }

    /**
     * @param seconds
     *                    the seconds to set
     */
    public synchronized int set(int seconds) {
	return this.seconds = seconds;
    }

    /**
     * @return the delay
     */
    public synchronized int getDelay() {
	return delay;
    }

    /**
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

    public synchronized void kill() {
	this.isEnabled = false;
    }

}
