import java.awt.Component;

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

    public GameView(Component parent, int w, int h) {
	setSize(w, h);
	this.parent = parent;
	setBorder(new CompoundBorder(
		new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
			new BevelBorder(BevelBorder.LOWERED, null, null, null, null)),
		new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
			new BevelBorder(BevelBorder.RAISED, null, null, null, null))));

	setLayout(null);
	init();

    }

    private void init() {
	Mode mode = getGameMode();
	Tile[][] map = new Tile[mode.getMapWidth()][mode.getMapHeight()];

    }

    public void update() {
	setLocation(parent.getWidth() / 2 - getWidth() / 2, parent.getHeight() / 2 - getHeight() / 2);
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
