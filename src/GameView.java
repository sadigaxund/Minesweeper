import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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

    public GameView(Component parent, Mode gameMode) {
	GameView.gameMode = gameMode;
	this.parent = parent;
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
	    public void mouseReleased(MouseEvent e) {
		dragOverTile(-1, -1);
	    }
	});

	initMap();
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
		add(map[i][j]);
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
