
public class Mode {
    public static final Mode EASY = new Mode().setMapHeight(9).setMapWidth(9).setMineAmount(10).setName("Easy");
    public static Mode MEDIUM = new Mode().setMapHeight(16).setMapWidth(16).setMineAmount(40).setName("Medium");
    public static Mode HARD = new Mode().setMapHeight(16).setMapWidth(30).setMineAmount(99).setName("Hard");

    public Mode() {

    }

    private int mineAmount;
    private int mapWidth;
    private int mapHeight;
    private String name;

    public static Mode getModeByName(String name) {
	for (Mode mode : getStandardModes())
	    if (Tools.equalsNoCase(name, mode.getName()))
		return mode;

	return new Mode().setName(name);
    }

    public static Mode[] getStandardModes() {
	return new Mode[] { EASY, MEDIUM, HARD };
    }

    /**
     * 
     * @return the names of all mode
     */
    public String[] getNames() {
	return new String[] { "Easy", "Medium", "Hard", "Custom" };
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
	if (name == null)
	    return null;
	this.name = name;
	return this;
    }

    /**
     * @return the amount of mines
     */
    public int getMinesAmount() {
	return mineAmount;
    }

    /**
     * @param mineAmount
     *                       the the amount of mines to set
     */
    public Mode setMineAmount(int mineAmount) {
	if (mineAmount <= 0)
	    return null;
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
	if (mapWidth <= 0)
	    return null;
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
	if (mapHeight <= 0)
	    return null;
	this.mapHeight = mapHeight;
	return this;
    }

}
