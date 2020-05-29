package Objects.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Main.Minesweeper;
import Util.IMinesweeper;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GScalable;

/**
 * Class for creating the tile
 * 
 * @author Sadig Akhund
 *
 */
public class Tile extends GCompound implements GScalable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** The appearance of a tile */
    private GObject outface = null;

    /** The content identifier of a tile */
    private IMinesweeper.Content content;

    /**
     * if the content of a tile is number, the index is for identifying how much
     * does it contain
     */
    private byte index = -1;

    /** the boolean for defining whether a tile is opened or not */
    private boolean empty = false;

    /** the boolean for defining whether a tile is flagged or not */
    private boolean flagged = false;

    /**
     * The index of a tile for identifying the location of a tile on the
     * map/minefield
     */
    private GIndex indexOfTile;

    /** The amount of flags that were put onto the map */
    public static byte flagCounter = 0;

    /** The mouse listener for a tile */
    private MouseListener ml = new MouseListener() {

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	    if (isFlagged())
		return;
	    unPress();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	    /* For starting the timer if it is not started once a tile was clicked */
	    if (!Main.Minesweeper.TIMER.MECHANISM.isAlive()) {
		Main.Minesweeper.TIMER.MECHANISM.start();
	    }

	    /* If a tile was right clicked and is not flagged */
	    if (e.getButton() == 1 && !isFlagged()) {
		/* Open it */
		openRecursively();
		/* If a tile was left */
	    } else if (e.getButton() == 3) {
		flag_Unflag();
	    }
	}
    };

    public Tile() {
	outface = new GImage(IMinesweeper.NOTPRESSED_TILE);
	add(outface);
	addMouseListener(ml);
    }

    /**
     * Method for pressing the tile
     */
    public void press() {
	changeLook(IMinesweeper.PRESSED_TILE);
    }

    /**
     * Method for unpressing the tile
     */
    public void unPress() {
	changeLook(IMinesweeper.NOTPRESSED_TILE);
    }

    @SuppressWarnings("deprecation")
    public void openRecursively() {

	switch (content) {
	/* opening an empty tile */
	case EMPTY:
	    changeLook(IMinesweeper.PRESSED_TILE);
	    unload(this);
	    break;

	/* opening a mined tile */
	case MINE:

	    /* Stopping the timer */
	    Main.Minesweeper.TIMER.MECHANISM.suspend();
	    Main.Minesweeper.TIMER.MECHANISM = null;

	    /* Changing to the detonated tile */
	    changeLook(IMinesweeper.DETONATED_MINE);
	    setContent(IMinesweeper.Content.EMPTY);

	    for (int i = 0; i < MineField.width; i++) {
		for (int j = 0; j < MineField.height; j++) {

		    Tile tile = MineField.tiles[i][j];

		    /* For opening all mined tiles */
		    if (tile.getContent().equals(IMinesweeper.Content.MINE)) {
			tile.setContent(IMinesweeper.Content.END);
			tile.openRecursively();
			unload(tile);
			continue;
		    }
		    /* If the player guessed wrong tile */
		    if (tile.isFlagged()) {
			tile.changeLook(IMinesweeper.NOT_MINED);
		    }
		    unload(tile);
		}
	    }
	    unload(this);
	    return;
	/* opening a numbered tile */
	case NUMBER:
	    changeLook(IMinesweeper.NUMBER_TILES[index]);
	    unload(this);
	    return;
	case END:
	    if (!flagged) {
		changeLook(IMinesweeper.MINED);
	    }
	    return;
	default:
	    break;
	}

	/* If the tile was flagged then don't open other tiles* around it */
	if (flagged) {
	    return;
	}

	int i = (int) indexOfTile.getX_Index();
	int j = (int) indexOfTile.getY_Index();

	/* opening the empty tiles around the tile */

	/* Positions of the Tiles */

	/* Left Center */
	openIndividually(i - 1, j);

	/* Right Center */
	openIndividually(i + 1, j);

	/* Left Below */
	openIndividually(i - 1, j - 1);

	/* Below Center */
	openIndividually(i, j - 1);

	/* Right Below */
	openIndividually(i + 1, j - 1);

	/* Left Up */
	openIndividually(i - 1, j + 1);

	/* Center Up */
	openIndividually(i, j + 1);

	/* Up Right */
	openIndividually(i + 1, j + 1);
    }

    /**
     * Method for opening tiles around the tile with the given index
     * 
     * @param i
     *            - index in the first dimension, or number of the row
     * @param j
     *            - index in the second dimension, or number of the column
     */
    public void openIndividually(int i, int j) {
	try {
	    Tile tile = MineField.tiles[i][j];

	    /* If the tile was flagged then don't open it */
	    if (tile.flagged)
		return;

	    /* If the tile is empty and was not opened */
	    if (tile.getContent().equals(IMinesweeper.Content.EMPTY) && !tile.isEmpty()) {
		tile.openRecursively();
		/* If the tiles around the empty tiles contain numbered tile open it */
	    } else if (tile.getContent().equals(IMinesweeper.Content.NUMBER) && !tile.isEmpty()) {
		tile.changeLook(IMinesweeper.NUMBER_TILES[tile.getIndex()]);
		unload(tile);
	    }
	} catch (ArrayIndexOutOfBoundsException e) {
	    System.err.println("===> No such tile to be opened <===");
	}
    }

    /**
     * Method for flagging/unflagging the tile
     */
    private void flag_Unflag() {
	/* flagging/unflagging the tile */
	flagged = !flagged;

	/* Flagging the tile */
	// if the tile is flagged and the number of tiles does not exceed the limit
	if (isFlagged() && MineField.mines - flagCounter > 0) {

	    /* increase the number of all flags */
	    flagCounter++;

	    /* Change the label of the flag counter */
	    Minesweeper.flagCounter.changeLabel(MineField.mines - flagCounter);

	    /* Changing the tile itself */
	    changeLook(IMinesweeper.FLAG);
	} else {
	    /* decrease the number of all flags */
	    flagCounter--;

	    /* Change the label of the flag counter */
	    Minesweeper.flagCounter.changeLabel(MineField.mines - flagCounter);

	    /* Changing the tile itself */
	    changeLook(IMinesweeper.NOTPRESSED_TILE);
	}
    }

    /**
     * Method for releasing the memory that took mouselistener of the given tile
     * 
     * @param tile
     *            given tile
     */
    private void unload(Tile tile) {
	tile.empty = true;
	tile.removeMouseListener(tile.getMouseListener());
	tile.setMouseListener(null);
    }

    /**
     * Method for changing the tile's outface
     * 
     * @param repos
     *            the repository of the outface image
     */
    public void changeLook(String repos) {
	/* Once tile has been opened no need to change it */
	if (empty) {
	    return;
	}
	/* removing previous tile and releasing the memory */
	{
	    remove(outface);
	    outface = null;
	}

	outface = new GImage(repos);
	add(outface);
    }

    /**
     * Method for increasing the number of tile's index
     */
    public void iterate() {
	index++;
    }

    /**
     * @return the content
     */
    public IMinesweeper.Content getContent() {
	return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(IMinesweeper.Content content) {
	this.content = content;
    }

    /**
     * @return the index
     */
    public byte getIndex() {
	return index;
    }

    /**
     * @param index
     *            the index to set
     */
    public void setIndex(byte index) {
	this.index = index;
    }

    /**
     * @return the index of Tile
     */
    public GPoint getIndexOfTile() {
	return indexOfTile;
    }

    /**
     * @param indexOfTile
     *            the index of Tile to set
     */
    public void setIndexOfTile(GIndex indexOfTile) {
	this.indexOfTile = indexOfTile;
    }

    /**
     * @return the boolean defining empty tile
     */
    public boolean isEmpty() {
	return empty;
    }

    /**
     * @param empty
     *            the boolean defining empty tile to set
     */
    public void setEmpty(boolean empty) {
	this.empty = empty;
    }

    /**
     * @return the Mouse Listener
     */
    public MouseListener getMouseListener() {
	return ml;
    }

    /**
     * @param ml
     *            the Mouse Listener to set
     */
    public void setMouseListener(MouseListener ml) {
	this.ml = ml;
    }

    /**
     * @return the flagged
     */
    public boolean isFlagged() {
	return flagged;
    }

    /**
     * @param flagged
     *            the flagged to set
     */
    public void setFlagged(boolean flagged) {
	this.flagged = flagged;
    }

}

/**
 * Class for holding the index of a tile
 * 
 * @author Sadig Akhund
 *
 */
class GIndex extends GPoint {
    /**
     * 
     */
    private static final long serialVersionUID = 6915963953289941948L;

    public GIndex(double x, double y) {
	super(x, y);
    }

    public GIndex(int x, int y) {
	super(x, y);
    }

    public double getX_Index() {
	return getX();

    }

    public double getY_Index() {
	return getY();
    }
}
