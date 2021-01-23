
public class Mode {
    public static final Mode EASY = new Mode().setMapHeight(9).setMapWidth(9).setMineAmount(10).setName("Easy");
    public static Mode MEDIUM = new Mode().setMapHeight(16).setMapWidth(16).setMineAmount(40).setName("Medium");
    public static Mode HARD = new Mode().setMapHeight(16).setMapWidth(30).setMineAmount(99).setName("Hard");

    public Mode() {

    }

    public Mode(Mode mode) {
	setMapHeight(mode.getMapHeight());
	setMapWidth(mode.getMapWidth());
	setMineAmount(mode.getMineAmount());
	setName(mode.getName());
    }

    private int mineAmount;
    private int mapWidth;
    private int mapHeight;
    private String name;

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
