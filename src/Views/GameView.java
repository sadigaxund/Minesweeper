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

package Views;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import Datas.Images;
import Datas.Mode;
import Datas.Tile;
import Datas.Timer;
import Datas.Vector2D;
import Utils.JImages;

public class GameView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 4493755493576259516L;
    /**
     * The parent of the Game view. Usually it is a Menu view.
     */
    private Component parent;

    /**
     * The size of a tile.
     */
    public static final int TILE_SIZE = 10;

    /**
     * The amount of flags.
     */
    public static volatile int FLAG_COUNTER = 0;

    /**
     * The current mode of the game.
     */
    private Mode gameMode;// use only getter to access

    /**
     * The size of the tiles.
     */
    private int tileSize;
    /**
     * The Map.
     */
    private Tile[][] map;
    /**
     * A list that holds coordinates of the mines.
     */
    private ArrayList<Vector2D<Integer, Integer>> minemap;
    /**
     * Mine counter.
     */
    private Timer mines;
    /**
     * Timer.
     */
    private Timer timer;
    /**
     * The smile face.
     */
    private JLabel smileFace;
    /**
     * The state of the game. If true no modification actions such as revealing the
     * tile or flagging can be done.
     */
    private boolean GAMEOVER = false;

    public GameView(Component parent, JLabel smileFace, Mode gameMode, Timer mines, Timer timer) {
	this.parent = parent;
	this.mines = mines;
	this.timer = timer;
	this.smileFace = smileFace;
	this.gameMode = (gameMode == null) ? Mode.EASY : gameMode; // Null check
	minemap = new ArrayList<Vector2D<Integer, Integer>>();
	initView();
	initMap();
	initMines();
	// centralize
	setLocation(parent.getWidth() / 2 - getWidth() / 2, parent.getHeight() / 2 - getHeight() / 2);
	repaint();
    }

    private int prevI = -1, prevJ = -1;

    private void initView() {

	setBorder(new CompoundBorder(
		new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
			new BevelBorder(BevelBorder.LOWERED, null, null, null, null)),
		new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
			new BevelBorder(BevelBorder.RAISED, null, null, null, null))));

	setLayout(null);

	addMouseMotionListener(new MouseMotionAdapter() {
	    @Override
	    public void mouseDragged(MouseEvent e) {
		int i = e.getX() / tileSize;
		int j = e.getY() / tileSize;
		if (prevI != -1)
		    map[prevI][prevJ].setIcon(Images.TILE_NOT_PRESSED);
		if (Tile.invalidateTile(map, i, j) || map[i][j].isFlagged() || GAMEOVER)
		    return;

		map[i][j].setIcon(Images.getTileDigit(0));
		prevI = i;
		prevJ = j;

	    }
	});
	addMouseListener(new MouseAdapter() {

	    @Override
	    public void mouseReleased(MouseEvent e) {
		int i = e.getX() / tileSize;
		int j = e.getY() / tileSize;
		prevI = -1;
		prevJ = -1;
		if (Tile.invalidateTile(map, i, j) || GAMEOVER)
		    return;

		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
		    boolean isOK = Tile.reveal(map, i, j);
		    if (!isOK)
			gameLOST();

		    break;
		case MouseEvent.BUTTON3:
		    if (map[i][j].isFlagged()) {
			map[i][j].setFlag(false);
			mines.set(mines.getTime() + 1);
		    } else {

			map[i][j].setFlag(true);
			mines.set(mines.getTime() - 1);
			if (mines.getTime() == 0) {
			    boolean AllMinesWereFlagged = true;
			    System.out.println("Zero Mines...");
			    for (Vector2D<Integer, Integer> index : minemap)
				AllMinesWereFlagged &= map[index.getA()][index.getB()].isFlagged();
			    System.out.println(AllMinesWereFlagged);

			    if (AllMinesWereFlagged) {
				gameWON();
			    }
			}
		    }

		    break;
		}

	    }
	});

    }

    private void initMap() {

	map = new Tile[gameMode.getMapWidth()][gameMode.getMapHeight()];

	if (gameMode.equals(Mode.EASY)) {
	    tileSize = 30;
	} else if (gameMode.equals(Mode.MEDIUM)) {
	    tileSize = 25;
	} else if (gameMode.equals(Mode.HARD)) {
	    tileSize = 20;
	} else if (gameMode.getMapHeight() > Mode.HARD.getMapHeight()
		|| gameMode.getMapWidth() > Mode.HARD.getMapWidth()) {
	    int w = parent.getWidth() / gameMode.getMapWidth();
	    int h = parent.getHeight() / gameMode.getMapHeight();

	    tileSize = (h < w) ? h : w;
	}

	// tileSize = 25;

	setSize(tileSize * gameMode.getMapWidth() + 12, tileSize * gameMode.getMapHeight() + 12);

	for (int i = 0; i < map.length; i++)
	    for (int j = 0; j < map[i].length; j++) {
		int tileX = 5 + i * tileSize;
		int tileY = 5 + j * tileSize;

		map[i][j] = new Tile(tileX, tileY, tileSize, tileSize, i, j);
		map[i][j].setContent(Tile.Content.EMPTY);
		add(map[i][j]);
	    }

    }

    private void initMines() {

	/* The limit of the mines */
	int count = 0;

	Random rd = new Random();
	while (count++ < gameMode.getMinesAmount()) {
	    /* The randomly selected indexes of the mine tile */
	    int w = rd.nextInt(gameMode.getMapWidth());
	    int h = rd.nextInt(gameMode.getMapHeight());
	    /* For making sure that the selected tile has not already had a mine */
	    if (map[w][h].getContent().equals(Tile.Content.MINE)) {
		count--;
		continue;
	    }
	    minemap.add(new Vector2D<>(w, h)); // save location of mines
	    /* Setting the content to the mine */
	    map[w][h].setContent(Tile.Content.MINE);
	    /*** increasing the number of the tile around the mine ***/

	    /** Positions of the Tiles, set all 8 adjacent tile to number **/
	    /* LEFT - UP */
	    Tile.setNumeral(map, w - 1, h + 1);
	    /* LEFT - CENTER */
	    Tile.setNumeral(map, w - 1, h);
	    /* LEFT - BELOW */
	    Tile.setNumeral(map, w - 1, h - 1);

	    /* RIGHT - UP */
	    Tile.setNumeral(map, w + 1, h + 1);
	    /* RIGHT - CENTER */
	    Tile.setNumeral(map, w + 1, h);
	    /* RIGHT - BELOW */
	    Tile.setNumeral(map, w + 1, h - 1);

	    /* CENTER - DOWN */
	    Tile.setNumeral(map, w, h - 1);
	    /* CENTER - UP */
	    Tile.setNumeral(map, w, h + 1);

	}

    }

    public void gameLOST() {
	GAMEOVER = true;
	timer.off();

	for (int i = 0; i < map.length; i++)
	    for (int j = 0; j < map[i].length; j++) {
		Tile tile = map[i][j];
		if (tile.getContent() == Tile.Content.MINE && !tile.isFlagged())
		    tile.setIcon(Images.MINE);

		if (tile.isFlagged() && tile.getContent() != Tile.Content.MINE) {
		    tile.setIcon(Images.MINE_NOT);
		}

	    }
	Image smileFaceIcon = JImages.scaleImage(new ImageIcon(Images.SMILE_DEAD).getImage(), smileFace.getWidth(),
		smileFace.getHeight());
	smileFace.setIcon(new ImageIcon(smileFaceIcon));
	Image Icon = JImages.scaleImage(new ImageIcon(Images.LOSE_SCREEN).getImage(), 35, 35);
	JOptionPane.showMessageDialog(this, "Sorry, You Lost!", "Game Over", JOptionPane.OK_OPTION,
		new ImageIcon(Icon));
    }

    public void gameWON() {
	GAMEOVER = true;
	timer.off();
	Image smileFaceIcon = JImages.scaleImage(new ImageIcon(Images.SMILE_COOL).getImage(), smileFace.getWidth(),
		smileFace.getHeight());
	smileFace.setIcon(new ImageIcon(smileFaceIcon));
	Image Icon = JImages.scaleImage(new ImageIcon(Images.WIN_SCREEN).getImage(), 35, 35);
	JOptionPane.showMessageDialog(this, "Congratulations, You Won!", "Game Over", JOptionPane.OK_OPTION,
		new ImageIcon(Icon));

    }

    /**
     * @param gameMode
     *                     the gameMode to set
     */
    public void setGameMode(Mode gameMode) {
	this.gameMode = gameMode;
    }

}
