import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

public class GameView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 4493755493576259516L;
    private Component parent;

    public static final int TILE_SIZE = 10;

    public static volatile int FLAG_COUNTER = 0;

    private static Mode gameMode;// use only getter to access

    private int tileSize;

    private Tile[][] map;
    private ArrayList<Vector2D<Integer, Integer>> minemap;

    // static Clock clock;
    // static Thread clockMechanism;

    public GameView(Component parent, Mode gameMode, Timer mines) {
	this.parent = parent;
	GameView.gameMode = (gameMode == null) ? Mode.getStandardModes()[0] : gameMode; // Null check
	// GameView.clock = clock;
	minemap = new ArrayList<Vector2D<Integer, Integer>>();
	initView();
	initMap();
	initMines();
	// centralize
	setLocation(parent.getWidth() / 2 - getWidth() / 2, parent.getHeight() / 2 - getHeight() / 2);
	repaint();
	// if (clockMechanism != null)
	//
	//
	// clockMechanism = new Thread(clock);
	// clockMechanism.start();
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
		if (i >= map.length || i < 0 || j >= map[i].length || j < 0 || map[i][j].isRevealed())
		    return;

		if (prevI != -1)
		    map[prevI][prevJ].setIcon(Images.TILE_NOT_PRESSED);
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
		if (i >= map.length || i < 0 || j >= map[i].length || j < 0 || map[i][j].isRevealed())
		    return;

		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
		    Tile.open(map, i, j);

		    break;
		case MouseEvent.BUTTON3:
		    if (map[i][j].isFlag()) {
			map[i][j].setIcon(Images.TILE_NOT_PRESSED);
			map[i][j].setFlag(false);
		    } else {
			map[i][j].setIcon(Images.FLAG);
			map[i][j].setFlag(true);
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
	} else {
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

    private void setupClock() {

    }

    public void reveal() {

	// clock.getTimer().disable();
	for (int i = 0; i < gameMode.getMapWidth(); i++)
	    for (int j = 0; j < gameMode.getMapHeight(); j++) {
		if (map[i][j].getContent().equals(Tile.Content.MINE) && map[i][j].isFlag())
		    continue;
		Tile.open(map, i, j);
	    }

    }

    /**
     * @param gameMode
     *                     the gameMode to set
     */
    public void setGameMode(Mode gameMode) {
	GameView.gameMode = gameMode;
    }

}
