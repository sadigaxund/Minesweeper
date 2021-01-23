
public class Mode {
    /*
     * Beginner: h:9, w:9, m:10
     * 
     * Intermediate: h:16, w:16, m:40
     * 
     * Expert: h:16, w:30, m:99
     * 
     * Custom: h:?, w:?, m:?
     */
    public static Mode values[] = {
	    new Mode().setMapHeight(9).setMapWidth(9).setMineAmount(10).setName("Easy"), /* Easy */
	    new Mode().setMapHeight(16).setMapWidth(16).setMineAmount(40).setName("Medium"), /* Medium */
	    new Mode().setMapHeight(16).setMapWidth(30).setMineAmount(99).setName("Hard"), /* Hard */
	    new Mode().setName("Custom") /* Custom */ };

    public Mode() {

    }

    private int mineAmount;
    private int mapWidth;
    private int mapHeight;
    private String name;
    private Mode current;

    /**
     * 
     * @return the names of all mode
     */
    public String[] getNames() {
	String[] names = new String[4];
	for (int i = 0; i < 4; i++)
	    names[i] = values[i].getName();
	return names;
    }

    /**
     * @return the current mode
     */
    public Mode getMode() {
	return current;
    }

    /**
     * @param current
     *                    the current mode to set
     */
    public void setMode(Mode current) {
	this.current = current;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *                 the name to set
     */
    public Mode setName(String name) {
	this.name = name;
	return this;
    }

    /**
     * @return the amount of mines
     */
    public int getMineAmount() {
	return mineAmount;
    }

    /**
     * @param mineAmount
     *                       the the amount of mines to set
     */
    public Mode setMineAmount(int mineAmount) {
	this.mineAmount = mineAmount;
	return this;
    }

    /**
     * @return the mapWidth
     */
    public int getMapWidth() {
	return mapWidth;
    }

    /**
     * @param mapWidth
     *                     the mapWidth to set
     */
    public Mode setMapWidth(int mapWidth) {
	this.mapWidth = mapWidth;
	return this;
    }

    /**
     * @return the mapHeight
     */
    public int getMapHeight() {
	return mapHeight;
    }

    /**
     * @param mapHeight
     *                      the mapHeight to set
     */
    public Mode setMapHeight(int mapHeight) {
	this.mapHeight = mapHeight;
	return this;
    }

}
