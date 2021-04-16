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

    public static final String PRESSED = Images.TILE_PRESSED;
    public static final String NOT_PRESSED = Images.TILE;

    public Tile(int x, int y, int w, int h) {
	setSize(w, h);
	setLocation(x, y);
	setIcon(Images.TILE);
    }

    public void setIcon(String iconPath) {
	super.setIcon(new ImageIcon(JImages.scaleImage(new ImageIcon(iconPath).getImage(), getWidth(), getHeight())));

    }

    public void open(boolean is4Reveal) {
	/* If the tile was flagged then don't open it */
	if (flag && !isEmpty())
	    return;

	switch (content) {
	case EMPTY:
	    setIcon(Images.getTileDigit(0));
	    break;
	case MINE:
	    if (is4Reveal)
		setIcon(Images.MINE);
	    else
		setIcon(Images.MINE_DEFUSED);
	    break;
	case NUMBER:
	    setIcon(Images.getTileDigit(number));
	    break;
	}

    }

    public void iterate() {
	number++;
    }

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
    public synchronized boolean getFlag() {
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

}
