import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import Util.IMinesweeper;

public class GameView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 4493755493576259516L;
    private Component parent;

    public static final int TILE_SIZE = 10;

    public static volatile int FLAG_COUNTER = 0;

    private static Mode gameMode;// use only getter to access

    private Clock clock;

    private int tileSize;

    private Tile[][] map;

    public GameView(Component parent, Mode gameMode, Clock timer) {
	GameView.gameMode = gameMode;
	this.parent = parent;
	this.clock = timer;
	init();

    }

    private void init() {

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
		// TODO: Try to make dragging more smooth, idea: maybe by adding some hitbox
		// inside
		dragOverTile(i, j);

	    }
	});
	addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
		int i = e.getX() / tileSize;
		int j = e.getY() / tileSize;
		dragOverTile(i, j);
		dragOverTile(-1, -1);
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
		
		int i = e.getX() / tileSize;
		int j = e.getY() / tileSize;
		map[i][j].open(false);
	    }
	});

	initMap();
	initMines();
    }

    private int prevI, prevJ;

    private void dragOverTile(int i, int j) {
	try {
	    if (prevI != -1 && prevJ != -1)
		map[prevI][prevJ].setIcon(Tile.NOT_PRESSED);
	    map[i][j].setIcon(Tile.PRESSED);
	    prevI = i;
	    prevJ = j;
	} catch (ArrayIndexOutOfBoundsException ex) {
	    // Ignore
	}
    }

    private void initMap() {
	Mode mode = getGameMode();
	map = new Tile[mode.getMapWidth()][mode.getMapHeight()];

	if (mode.equals(Mode.EASY)) {
	    tileSize = 30;
	} else if (mode.equals(Mode.MEDIUM)) {
	    tileSize = 25;
	} else if (mode.equals(Mode.HARD)) {
	    tileSize = 20;
	} else {
	    int w = parent.getWidth() / mode.getMapWidth();
	    int h = parent.getHeight() / mode.getMapHeight();

	    tileSize = (h < w) ? h : w;
	}

	// tileSize = 25;

	setSize(tileSize * mode.getMapWidth() + 12, tileSize * mode.getMapHeight() + 12);

	for (int i = 0; i < map.length; i++)
	    for (int j = 0; j < map[i].length; j++) {
		int tileX = 5 + i * tileSize;
		int tileY = 5 + j * tileSize;

		map[i][j] = new Tile(tileX, tileY, tileSize, tileSize);
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
	    while (map[w][h].getContent().equals(Tile.Content.MINE)) {
		w = rd.nextInt(gameMode.getMapWidth());
		h = rd.nextInt(gameMode.getMapHeight());
	    }

	    /* Setting the content to the mine */
	    map[w][h].setContent(Tile.Content.MINE);
	    /*** increasing the number of the tile around the mine ***/

	    /** Positions of the Tiles **/
	    /* Left Center */
	    numberTile(w - 1, h);
	    /* Right Center */
	    numberTile(w + 1, h);

	    /* Left Below */
	    numberTile(w - 1, h - 1);
	    /* Below Center */
	    numberTile(w, h - 1);
	    /* Right Below */
	    numberTile(w + 1, h - 1);

	    /* Left Up */
	    numberTile(w - 1, h + 1);
	    /* Center Up */
	    numberTile(w, h + 1);
	    /* Up Right */
	    numberTile(w + 1, h + 1);
	}

    }

    private void numberTile(int w, int h) {
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

    public void reveal() {

	clock.getTimer().disable();

	for (int i = 0; i < gameMode.getMapWidth(); i++) {
	    for (int j = 0; j < gameMode.getMapHeight(); j++) {
		if (map[i][j].getContent().equals(Tile.Content.MINE) && map[i][j].getFlag()) {
		    continue;
		}

		map[i][j].open(true);

	    }
	}
    }

    public void update() {
	setLocation(parent.getWidth() / 2 - getWidth() / 2, parent.getHeight() / 2 - getHeight() / 2);
	repaint();
    }

    /**
     * @return the gameMode
     */
    public static synchronized Mode getGameMode() {

	if (gameMode == null)
	    return Mode.getStandardModes()[0];

	return gameMode;
    }

    /**
     * @param gameMode
     *                     the gameMode to set
     */
    public synchronized void setGameMode(Mode gameMode) {
	GameView.gameMode = gameMode;
    }

}
