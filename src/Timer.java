
public class Timer {

    private boolean isON = false;
    private boolean isEnabled = false;
    private int seconds = 0;
    private int delay = 1000;
    private int stopTime = -1;

    public static void main(String[] args) {
	Timer t = new Timer();
	t.plus1();
	t.add(5);
	System.out.println(t.getTime());
    }

    public Timer() {
    }

    public void reset() {
	seconds = 0;
	stopTime = -1;
	isON = false;
	disable();
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

    public synchronized void enable() {
	this.isEnabled = true;
    }

    public synchronized void disable() {
	this.isEnabled = false;
    }

}
