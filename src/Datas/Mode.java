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

import Util.Tools;

public class Mode {
    /**
     * Instance of itself. Holds data for the game mode Easy
     */
    public static final Mode EASY = new Mode().setMapHeight(9).setMapWidth(9).setMineAmount(10).setName("Easy");
    /**
     * Instance of itself. Holds data for the game mode Medium
     */
    public static Mode MEDIUM = new Mode().setMapHeight(16).setMapWidth(16).setMineAmount(40).setName("Medium");
    /**
     * Instance of itself. Holds data for the game mode Hard
     */
    public static Mode HARD = new Mode().setMapHeight(16).setMapWidth(30).setMineAmount(99).setName("Hard");

    /**
     * The amount of mines/bombs
     */
    private int mineAmount;
    /**
     * The width of the map. <br>
     * Used to define borders of an array or map.
     */
    private int mapWidth;
    /**
     * The height of the map. <br>
     * Used to define borders of an array or map.
     */
    private int mapHeight;
    /**
     * The name of the mode
     */
    private String name;

    public Mode() {

    }

    /**
     * Static method that returns an instance of a <b>Mode</b> class depending in
     * entered String <i>name</i>.
     * 
     * @param name
     *                 The name of a mode
     * @return desired instance of a <b>Mode</b> class
     */
    public static Mode getModeByName(String name) {
	for (Mode mode : new Mode[] { EASY, MEDIUM, HARD })
	    if (Tools.equalsNoCase(name, mode.getName()))
		return mode;

	return new Mode().setName(name);
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
     * @return the height of a map
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
