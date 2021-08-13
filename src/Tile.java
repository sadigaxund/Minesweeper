import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Main.Minesweeper;
import Objects.Game.MineField;
import Util.IMinesweeper;
import Utils.JImages;

public class Tile extends JLabel {

    /**
     * 
     */
    private static final long serialVersionUID = -2832906721095943314L;

    public Tile(int x, int y, int w, int h, int i, int j) {
	setSize(w, h);
	setLocation(x, y);
	setIcon(Images.TILE_NOT_PRESSED);
    }

    public void setIcon(String iconPath) {
	super.setIcon(new ImageIcon(JImages.scaleImage(new ImageIcon(iconPath).getImage(), getWidth(), getHeight())));

    }

    public static void open(Tile[][] map, int i, int j) {
	if (i >= map.length || i < 0 || j >= map[i].length || j < 0 || map[i][j].isRevealed())
	    return;
	
	Tile tile = map[i][j].setRevealed(true);
	
	/* If the tile was flagged then don't open it */
	if (tile.flag && !tile.isEmpty())
	    return;

	String icon = Images.getTileDigit(0);
	switch (tile.getContent()) {
	case MINE:
	    icon = Images.MINE_BLOWN;
	    break;
	case NUMBER:
	    icon = Images.getTileDigit(tile.getNumber());
	    break;
	case EMPTY:
	    open(map, i, j + 1);

	    open(map, i + 1, j);

	    open(map, i, j - 1);

	    open(map, i - 1, j);
	    break;
	default:
	    break;
	}
	tile.setIcon(icon);

    }

    public void spread() {

    }

    public static void setNumeral(Tile[][] map, int w, int h) {
	try {
	    /*
	     * in order not to set the content of the tile which has mine to number content
	     */
	    if (map[w][h].getContent().equals(Tile.Content.MINE)) {
		return;
	    }
	    /* Setting the content identifier of a tile to the number */
	    map[w][h].setContent(Tile.Content.NUMBER);

	    /* see: Tile.iterate() */
	    map[w][h].iterate();
	} catch (ArrayIndexOutOfBoundsException e) {
	}
    }

    public void iterate() {
	number++;
    }

    ///////////////////////////////////// SETTERS & GETTERS
    ///////////////////////////////////// /////////////////////////////////////
    /**
     * @return the content
     */
    public synchronized Content getContent() {
	return content;
    }

    /**
     * @param content
     *                    the content to set
     */
    public synchronized void setContent(Content content) {
	if (content.equals(Content.EMPTY))
	    number = 0;
	else if (content.equals(Content.MINE))
	    number = -1;

	this.content = content;
    }

    /**
     * @return the empty
     */
    public synchronized boolean isEmpty() {
	return content.equals(Content.EMPTY);
    }

    /**
     * @param empty
     *                  the empty to set
     */
    public synchronized void setEmpty(boolean empty) {
	content = Content.EMPTY;
    }

    /**
     * @return the flagged
     */
    public synchronized boolean isFlag() {
	return flag;
    }

    /**
     * @param flagged
     *                    the flagged to set
     */
    public synchronized void setFlag(boolean flag) {
	this.flag = flag;
    }

    /**
     * @return the number
     */
    public synchronized byte getNumber() {
	return number;
    }

    /**
     * @param number
     *                   the number to set
     */
    public synchronized void setNumber(byte number) {
	this.number = number;
    }

    /**
     * @return the revealed
     */
    public synchronized boolean isRevealed() {
	return revealed;
    }

    /**
     * @param revealed
     *                     the revealed to set
     */
    public synchronized Tile setRevealed(boolean revealed) {
	this.revealed = revealed;
	return this;
    }

    ///////////////////////////////////// VARIABLES
    ///////////////////////////////////// /////////////////////////////////////

    /** Defines the content of a tile */
    public static enum Content {
	EMPTY, NUMBER, MINE;
    }

    /** The content identifier of a tile */
    private Content content;

    /**
     * if the content of a tile is number, the index is for identifying how much
     * does it contain. Refer to the table:<br>
     * _____________________________<br>
     * n < 0 | tile has a mine in it <br>
     * n = 0 | tile is empty<br>
     * n > 0 | tile contains number and n stands for that number
     */
    private byte number = 0;

    /** the boolean for defining whether a tile is flagged or not */
    private boolean flag = false;

    private boolean revealed = false;

}
