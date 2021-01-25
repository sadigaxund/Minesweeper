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

    public Tile() {
	setIcon(Images.TILE);
	// addMouseListener(ml);
    }

    public void setIcon(String iconPath) {
	super.setIcon(new ImageIcon(
		JImages.scaleImage(new ImageIcon(Images.SMILE_HAPPY).getImage(), getWidth(), getHeight())));
    }

    public void open() {
	/* If the tile was flagged then don't open it */
	if (flag && !isEmpty())
	    return;

	switch (content) {
	case EMPTY:
	    setIcon(Images.getTileDigit(0));
	    break;
	case MINE:
	    setIcon(Images.MINE_DEFUSED);
	    break;
	case NUMBER:
	    setIcon(Images.getTileDigit(number));
	    break;
	}

    }

    private void flag() {
	/* flagging/unflagging the tile */
	flag = !flag;

	/* Flagging the tile */
	// if the tile is flagged and the number of tiles does not exceed the limit
	if (flag && GameView.getGameMode().getMinesAmount() - GameView.FLAG_COUNTER > 0) {

	    /* increase the number of all flags */
	    GameView.FLAG_COUNTER++;

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
     * n > 1 | tile contains number and n stands for that number
     */
    private byte number = 0;

    /** the boolean for defining whether a tile is flagged or not */
    private boolean flag = false;

    /** The mouse listener for a tile */
    // private MouseAdapter ml = new MouseAdapter() {
    //
    // @Override
    // public void mouseExited(MouseEvent e) {
    // if (isFlagged())
    // return;
    // unPress();
    // }
    //
    // @Override
    // public void mouseClicked(MouseEvent e) {
    // /* For starting the timer if it is not started once a tile was clicked */
    // if (!Main.Minesweeper.TIMER.MECHANISM.isAlive()) {
    // Main.Minesweeper.TIMER.MECHANISM.start();
    // }
    //
    // /* If a tile was right clicked and is not flagged */
    // if (e.getButton() == 1 && !isFlagged()) {
    // /* Open it */
    // openRecursively();
    // /* If a tile was left */
    // } else if (e.getButton() == 3) {
    // flag_Unflag();
    // }
    // }
    // };

}
