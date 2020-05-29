package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Objects.Game.FlagsCounter;
import Objects.Game.MineField;
import Objects.Game.Timer;
import Objects.Menu.GameMenu;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRectangle;
import acm.graphics.GRoundRect;
import acm.graphics.GScalable;
import acm.program.GraphicsProgram;

/*
 * Beginner: h:9, w:9, m:10
 * 
 * Intermediate: h:16, w:16, m:40
 * 
 * Expert: h:16, w:30, m:99
 * 
 * Custom: h:?, w:?, m:?
 */

public class Minesweeper extends GraphicsProgram {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** boolean that defines the end of the game */
    public static boolean THE_END = false;

    /** Timer of the game */
    public static Timer TIMER = new Timer();

    /** Mine field where player is going to play */
    public static MineField mineField = new MineField(GameMenu.complexity);

    /** The counter for flags */
    public static FlagsCounter flagCounter = new FlagsCounter(MineField.mines);

    public static final int APPLICATION_WIDTH = (int) Math.ceil(mineField.getWidth()) + 100;

    public static final int APPLICATION_HEIGHT = (int) (Math.ceil(mineField.getHeight() + 24) + TIMER.getHeight() * 3);

    public Minesweeper() {
	setTitle("Minesweeper");
	createProgramFrame().setLocationRelativeTo(null);
    }

    @Override
    public void init() {
	addMouseListeners();
    }

    @Override
    public void run() {
	GUI gui = new GUI();
	add(gui);

    }

    public static void main(String[] args) {
	new Minesweeper().start();
    }

    /**
     * Class that instantiates the GUI for the game
     * 
     * @author Sadig Akhund
     *
     */
    class GUI extends GCompound {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GRoundRect bg;

	public GUI() {
	    /* Big round rectabgle for the background */
	    bg = new GRoundRect(0, 0, Minesweeper.APPLICATION_WIDTH, Minesweeper.APPLICATION_HEIGHT);
	    bg.setFilled(true);
	    bg.setColor(Color.DARK_GRAY);
	    add(bg);

	    /* Adding mine field */
	    add(mineField, (Minesweeper.APPLICATION_WIDTH - mineField.getWidth()) / 2,
		    (Minesweeper.APPLICATION_HEIGHT - mineField.getHeight()) / 2);

	    add(TIMER, mineField.getX() + mineField.getWidth() - TIMER.getWidth(),
		    mineField.getY() / 2 - TIMER.getHeight() / 2);

	    add(flagCounter, mineField.getX(), mineField.getY() / 2 - TIMER.getHeight() / 2);

	    /* Round rectangle behind the mine field */
	    GRoundRect r = new GRoundRect(mineField.getWidth() + 10, 15 + mineField.getHeight() + TIMER.getHeight());
	    add(r, flagCounter.getLocation());
	    r.setFilled(true);
	    r.setFillColor(Color.GRAY);
	    r.move(-5, -5);
	    r.sendToBack();

	    /* Other settings */
	    bg.sendToBack();
	    mineField.move(0, -5);

	    Button button = new Button(new GRectangle(mineField.getX(), 5 + r.getY() + r.getHeight(),
		    mineField.getWidth(), new GImage("./img/button.png").getHeight()));

	    add(button);

	}

	@Override
	/**
	 * method was Overridden because When moving GUI I needed to move only contents
	 * not the background
	 */
	public void move(double arg0, double arg1) {
	    super.move(arg0, arg1);
	    bg.move(0, -arg1);
	}

	/**
	 * Since 'move' method was overridden, there appeared a need for moving whole
	 * canvas not just its contents
	 */
	public void moveAll(double x, double y) {
	    move(x, y);
	    bg.move(0, y);
	}
    }

    private class Button extends GCompound {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GRectangle bounds;
	GImage img = new GImage("./img/button.png");
	MyLabel label;

	public Button(GRectangle bounds) {
	    add(img);
	    this.bounds = bounds;
	    img.setBounds(bounds);
	    label = new MyLabel("SUBMIT");
	    label.setFont(new Font("Emulogic", Font.BOLD, (int) getHeight()));
	    label.setColor(Color.DARK_GRAY);
	    add(label, bounds.getX(), bounds.getY());
	    switch (GameMenu.complexity) {
	    case BEGINNER:
		label.move(img.getWidth() / 6, label.getAscent() - 4);
		break;
	    case CUSTOM:
		break;
	    case EXPERT:
		label.move(img.getWidth() / 2.5, label.getAscent() - 4);
		break;
	    case MEDIUM:
		label.move(img.getWidth() / 3.5, label.getAscent() - 4);
		break;
	    default:
		break;

	    }

	    addMouseListener(new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent arg0) {
		    // TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		    // TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		    unpress();
		    label.setFont(new Font("Emulogic", Font.BOLD, (int) img.getHeight()));
		    label.move(-7, 0);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		    label.setFont(new Font("Emulogic", Font.BOLD, ((int) img.getHeight() - 2)));
		    label.move(7, 0);
		    press();
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
		    mineField.reveal();
		}
	    });
	}

	private void press() {
	    img.setImage("./img/button2.png");
	    img.setBounds(bounds);
	}

	private void unpress() {
	    img.setImage("./img/button.png");
	    img.setBounds(bounds);
	}

	private class MyLabel extends GCompound implements GScalable {
	    /**
	     * 
	     */
	    private static final long serialVersionUID = 1L;
	    GLabel lab;

	    public MyLabel(String str) {
		lab = new GLabel(str);
		add(lab);
	    }

	    public double getAscent() {
		return lab.getAscent();
	    }

	    public void setFont(Font font) {
		lab.setFont(font);
	    }

	}
    }

}
