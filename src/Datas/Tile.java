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

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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

    /**
     * Method for changing the icon of a tile
     * 
     * @param iconPath
     *                     the source of an image
     */
    public void setIcon(String iconPath) {
	super.setIcon(new ImageIcon(JImages.scaleImage(new ImageIcon(iconPath).getImage(), getWidth(), getHeight())));

    }

    /**
     * Method to check if the tile is inside the boundaries of the map and if it was
     * revealed.
     * 
     * @param map
     *                the map this tile is supposed to be on
     * @param i
     *                row index
     * @param j
     *                column index
     * @return true if tile is valid, false otherwise
     */
    public static boolean invalidateTile(Tile[][] map, int i, int j) {
	return i >= map.length || i < 0 || j >= map[i].length || j < 0 || map[i][j].isRevealed();
    }

    /**
     * Method for revealing the tile
     * 
     * @param map
     *                the map this tile is on
     * @param i
     *                row index
     * @param j
     *                column index
     * @return true if no mine was revealed, false if mine was blown
     */
    public static boolean reveal(Tile[][] map, int i, int j) {
	if (invalidateTile(map, i, j) || map[i][j].isFlagged()) // even though we check
	    return true;

	Tile tile = map[i][j].setRevealed(true);

	/* If the tile was flagged then don't open it */
	if (tile.flag && !tile.isEmpty())
	    return true;

	String icon = Images.getTileDigit(0);
	switch (tile.getContent()) {
	case MINE:
	    tile.setFlag(true);
	    tile.setIcon(Images.MINE_BLOWN);
	    return false; // The only case when the method return false is game over

	case NUMBER:
	    icon = Images.getTileDigit(tile.getNumber());
	    break;
	case EMPTY:
	    reveal(map, i, j - 1);
	    reveal(map, i, j + 1);
	    reveal(map, i + 1, j);
	    reveal(map, i - 1, j);
	    reveal(map, i + 1, j + 1);
	    reveal(map, i + 1, j - 1);
	    reveal(map, i - 1, j + 1);
	    reveal(map, i - 1, j - 1);

	    break;
	default:
	    break;
	}
	tile.setIcon(icon);

	return true;
    }

    /**
     * Method used while creating a map. Each time a mine is generated on the map,
     * this method is called on every tile which is 1 tile away from mine.
     * 
     * @param map
     *                the map this tile is on
     * @param i
     *                row index
     * @param j
     *                column index
     */
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
	    map[w][h].number++;
	} catch (ArrayIndexOutOfBoundsException e) {
	}
    }

    /**************************************************************************************************
     * *************************************** SETTERS & GETTERS
     **************************************************************************************************/

    /**
     * @return the content
     */
    public Content getContent() {
	return content;
    }

    /**
     * @param content
     *                    the content to set
     */
    public void setContent(Content content) {
	if (content.equals(Content.EMPTY))
	    number = 0;
	else if (content.equals(Content.MINE))
	    number = -1;

	this.content = content;
    }

    /**
     * @return the empty
     */
    public boolean isEmpty() {
	return content.equals(Content.EMPTY);
    }

    /**
     * @param empty
     *                  the empty to set
     */
    public void setEmpty(boolean empty) {
	content = Content.EMPTY;
    }

    /**
     * @return the flagged
     */
    public boolean isFlagged() {
	return flag;
    }

    /**
     * @param flagged
     *                    the flagged to set
     */
    public void setFlag(boolean flag) {
	setIcon((flag) ? Images.FLAG : Images.TILE_NOT_PRESSED);
	this.flag = flag;
    }

    /**
     * @return the number
     */
    public byte getNumber() {
	return number;
    }

    /**
     * @param number
     *                   the number to set
     */
    public void setNumber(byte number) {
	this.number = number;
    }

    /**
     * @return the revealed
     */
    public boolean isRevealed() {
	return revealed;
    }

    /**
     * @param revealed
     *                     the revealed to set
     */
    public Tile setRevealed(boolean revealed) {
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
