package Objects.Game;
import java.util.Random;

import Util.IMinesweeper;
import acm.graphics.GCompound;

public class MineField extends GCompound {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** The tiles of the Mine Field */
    public static Tile[][] tiles;

    /** The width of the mine field */
    public static int width = 0;

    /** The height of the mine field */
    public static int height = 0;

    /** The amount of mines of the mine field */
    public static int mines = 0;

    public MineField(IMinesweeper.Complexity cp) {

	/* Each of the game modes has its own parameters */
	switch (cp) {
	case BEGINNER:
	    width = height = 9;
	    mines = 10;
	    break;
	case MEDIUM:
	    width = height = 16;
	    mines = 40;
	    break;
	case EXPERT:
	    width = 30;
	    height = 16;
	    mines = 99;
	    break;
	case CUSTOM:
	    break;
	default:
	    break;
	}
	tiles = new Tile[width][height];
	initMap(width, height);
	initMines(width, height, mines);
    }

    public MineField(int mineAmount, int width, int height) {
	tiles = new Tile[width][height];
	initMap(width, height);
	initMines(width, height, mineAmount);
    }

    /**
     * Method for the initializing tiles on the map
     * 
     * @param width
     *            Width of the Map
     * @param height
     *            Height of the Map
     */
    private void initMap(int width, int height) {
	for (int i = 0; i < width; i++) {
	    for (int j = 0; j < height; j++) {
		/* Initializing the Tiles */
		tiles[i][j] = new Tile();

		/* Setting index for the each tile */
		tiles[i][j].setIndexOfTile(new GIndex(i, j));

		/* Initially all the tiles are empty */
		tiles[i][j].setContent(IMinesweeper.Content.EMPTY);

		/* adding... */
		add(tiles[i][j], i * tiles[i][j].getWidth(), j * tiles[i][j].getHeight());
	    }
	}
    }

    /**
     * Method for initializing mines on the map
     * 
     * @param width
     *            Width of the Map
     * @param height
     *            Height of the Map
     * @param Amount
     *            the Amount of the Bombs
     */
    private void initMines(int width, int height, int Amount) {

	/* The limit of the mines */
	int count = 0;

	Random rd = new Random();

	while (count++ < Amount) {

	    /* The randomly selected indexes of the mine tile */
	    int w = rd.nextInt(width);
	    int h = rd.nextInt(height);

	    /* For making sure that the selected tile has not already had a mine */
	    while (tiles[w][h].getContent().equals(IMinesweeper.Content.MINE)) {
		w = rd.nextInt(width);
		h = rd.nextInt(height);
		System.out.println("Try to find appropriate place for mine...");
	    }

	    /* Setting the content to the mine */
	    tiles[w][h].setContent(IMinesweeper.Content.MINE);

	    /* increasing the number of the tile around the mine */

	    /* Positions of the Tiles */

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

    /**
     * Making tiles around the mine to have the numbered index, that is more than
     * previous one
     * 
     * @param w
     *            - width of the map
     * @param h
     *            - height of the map
     */
    private void numberTile(int w, int h) {
	try {
	    /*
	     * in order not to set the content of the tile which has mine to number content
	     */
	    if (tiles[w][h].getContent().equals(IMinesweeper.Content.MINE)) {
		return;
	    }
	    /* Setting the content identifier of a tile to the number */
	    tiles[w][h].setContent(IMinesweeper.Content.NUMBER);

	    /* see: Tile.iterate() */
	    tiles[w][h].iterate();
	} catch (ArrayIndexOutOfBoundsException e) {
	    System.err.println("===> There is no such tile out of the map <===");
	}
    }

    /**
     * Method that opens all the mines, when the submit button was pressed
     */
    public void reveal() {
	/* For stopping the timer, whether or not it is on or of */
	try {
	    Main.Minesweeper.TIMER.MECHANISM.interrupt();
	} catch (NullPointerException e) {

	}
	for (int i = 0; i < width; i++) {
	    for (int j = 0; j < height; j++) {
		if (tiles[i][j].getContent().equals(IMinesweeper.Content.MINE) && !tiles[i][j].isFlagged()) {
		    tiles[i][j].openRecursively();
		    tiles[i][j].setEmpty(false);
		    tiles[i][j].changeLook(IMinesweeper.MINED);
		    tiles[i][j].setEmpty(true);
		    return;
		}
	    }
	}
    }
}