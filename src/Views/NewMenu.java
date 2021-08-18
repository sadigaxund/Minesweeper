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

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utils.JHardware;
import Utils.JImages;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.text.PlainDocument;

import Components.CustomButton;
import Datas.Clock;
import Datas.Images;
import Datas.Mode;
import Datas.Timer;
import Util.IntFilter;
import Util.Tools;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * TODO: ADD: when text selected in mode info labels if yout type already
 * existing text will be deleted <br>
 * TODO: make input validation<br>
 * TODO: make absolute values dynamic, add more control.
 * 
 */
public class NewMenu extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 8220035870172267085L;

    /**
     * Launch the application.
     * 
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    NewMenu frame = new NewMenu();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public NewMenu() {
	setupFrame();

	addModeComboBox();
	addClocks();
	addFormattedTextFields();
	addModeLabels();
	addGameButtons();

	Thread gameMechanism = new Thread() {
	    public void run() {
		while (true) {
		    mineCounter.setClock(mines.getTime());
		    Tools.Wait(LATENCY);
		}
	    };
	};
	gameMechanism.start();

	contentPane.add(mainPanel);
    }

    private void setupFrame() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 736, 614);// TODO Change to dynamic variables
	setLocation(SCREEN_WIDTH / 2 - getWidth() / 2, SCREEN_HEIGHT / 2 - getHeight() / 2); // move window to the

	// center
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	topPanel = new JPanel();
	topPanel.setBackground(SystemColor.inactiveCaption);
	topPanel.setBorder(new MatteBorder(2, 4, 2, 4, (Color) new Color(153, 180, 209)));
	topPanel.setBounds(0, 0, 720, 45);
	contentPane.add(topPanel);
	topPanel.setLayout(null);

	int smileFaceSize = (int) (topPanel.getHeight() - COMPONENT_MARGIN * 1); // SMILE FACE ICON SIZE
	smileFaceLbl = new JLabel("");
	smileFaceLbl.setBounds(topPanel.getWidth() / 2 - smileFaceSize / 2,
		topPanel.getHeight() / 2 - smileFaceSize / 2, smileFaceSize, smileFaceSize); // SMILE FACE LOCATION
	Image smileFaceIcon = JImages.scaleImage(new ImageIcon(Images.SMILE_HAPPY).getImage(), smileFaceLbl.getWidth(),
		smileFaceLbl.getHeight());
	smileFaceLbl.setIcon(new ImageIcon(smileFaceIcon));
	topPanel.add(smileFaceLbl);
    }

    private void addClocks() {
	int clockW = 60;
	int clockH = topPanel.getHeight() - COMPONENT_MARGIN;

	mines = new Timer();
	mines.kill(); // timer will be modified by the action not the time
	mineCounter = new Clock(mines, clockW, clockH, smileFaceLbl.getX() - clockW - COMPONENT_MARGIN,
		COMPONENT_MARGIN / 2);
	topPanel.add(mineCounter);

	timer = new Timer();
	timeCounter = new Clock(timer, clockW, clockH, smileFaceLbl.getX() + smileFaceLbl.getWidth() + COMPONENT_MARGIN,
		COMPONENT_MARGIN / 2);
	clockMechanism = new Thread(timeCounter);
	clockMechanism.start();
	topPanel.add(timeCounter);
    }

    private void addFormattedTextFields() {
	ftFieldM = new JFormattedTextField();
	PlainDocument doc = (PlainDocument) ftFieldM.getDocument();
	IntFilter filter = new IntFilter();
	doc.setDocumentFilter(filter);
	ftFieldM.setBounds(119, 10, 28, 24);
	topPanel.add(ftFieldM);

	ftFieldW = new JFormattedTextField();
	doc = (PlainDocument) ftFieldW.getDocument();
	doc.setDocumentFilter(filter);
	ftFieldW.setBounds(176, 10, 28, 24);
	topPanel.add(ftFieldW);

	ftFieldH = new JFormattedTextField();
	doc = (PlainDocument) ftFieldH.getDocument();
	doc.setDocumentFilter(filter);
	ftFieldH.setBounds(233, 10, 28, 24);
	topPanel.add(ftFieldH);

	enableTextFields(false);
	displayModeInfo();

    }

    private void addModeLabels() {
	lblM = new JLabel("M:");
	lblM.setFont(modeLabelFont);
	lblM.setHorizontalAlignment(SwingConstants.CENTER);
	lblM.setBounds(100, 11, 20, 23);
	topPanel.add(lblM);

	lblW = new JLabel("W:");
	lblW.setFont(modeLabelFont);
	lblW.setHorizontalAlignment(SwingConstants.CENTER);
	lblW.setBounds(157, 11, 20, 23);
	topPanel.add(lblW);

	lblH = new JLabel("H:");
	lblH.setHorizontalAlignment(SwingConstants.CENTER);
	lblH.setFont(modeLabelFont);
	lblH.setBounds(214, 11, 20, 23);
	topPanel.add(lblH);
    }

    private void addModeComboBox() {
	int comboBoxWidth = 80;
	int comboBoxHeight = topPanel.getHeight() - COMPONENT_MARGIN * 2; // COMBO BOX SIZE

	comboBox = new JComboBox<String>();
	comboBox.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		comboBoxHandleAction();
	    }
	});

	comboBox.setModel((ComboBoxModel<String>) new DefaultComboBoxModel<String>(
		new String[] { "Easy", "Medium", "Hard", "Custom" }));
	comboBox.setBounds(COMPONENT_MARGIN, COMPONENT_MARGIN, comboBoxWidth, comboBoxHeight);// COMBO BOX LOCATION
	topPanel.add(comboBox);
    }

    private void addGameButtons() {
	int x, y, w, h;

	btnStart = new JButton("START");
	btnStart.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		startAction();
	    }
	});
	btnStart.setFont(new Font("Consolas", Font.BOLD, 13));
	btnStart.setUI(new CustomButton());

	w = topPanel.getWidth() - COMPONENT_MARGIN * 2 - (timeCounter.getX() + timeCounter.getWidth());

	// w = (topPanel.getWidth() - x - 2 * COMPONENT_MARGIN) / 2;
	h = 35;
	x = timeCounter.getX() + timeCounter.getWidth() + COMPONENT_MARGIN;
	y = 5;
	btnStart.setBounds(x, y, w, h);
	topPanel.add(btnStart);
	x = btnStart.getX() + btnStart.getWidth() + COMPONENT_MARGIN;

	mainPanel = new JPanel();
	mainPanel.setBorder(new MatteBorder(2, 4, 6, 4, (Color) new Color(153, 180, 209)));
	mainPanel.setBackground(SystemColor.inactiveCaption);
	mainPanel.setBounds(0, 42, 720, 533);
	mainPanel.setLayout(null);
    }

    private void enableTextFields(boolean state) {
	ftFieldM.setEditable(state);
	ftFieldW.setEditable(state);
	ftFieldH.setEditable(state);
    }

    private void displayModeInfo() {
	ftFieldM.setText(GAMEMODE.getMinesAmount() + "");
	ftFieldW.setText(GAMEMODE.getMapWidth() + "");
	ftFieldH.setText(GAMEMODE.getMapHeight() + "");
	mineCounter.setClock(GAMEMODE.getMinesAmount());
    }

    private void comboBoxHandleAction() {
	String selected = (String) comboBox.getSelectedItem();
	GAMEMODE = Mode.getModeByName(selected);
	enableTextFields(Tools.equalsNoCase(selected, "Custom"));
	displayModeInfo();
    }

    private void startAction() {
	if (gameView != null) {
	    mainPanel.remove(gameView);
	    mainPanel.repaint();
	}
	if (Tools.equalsNoCase(GAMEMODE.getName(), "Custom")) {
	    boolean flag = true;

	    flag &= GAMEMODE.setMapHeight(Integer.parseInt(ftFieldH.getText())) != null;
	    flag &= GAMEMODE.setMapWidth(Integer.parseInt(ftFieldW.getText())) != null;
	    flag &= GAMEMODE.setMineAmount(Integer.parseInt(ftFieldM.getText())) != null;
	    /**************************/
	    if (!flag) {
		JOptionPane.showMessageDialog(mainPanel, "Entered number(s) must not be 0!", "Oops!",
			JOptionPane.WARNING_MESSAGE);
		return;
	    }
	    ///////////////////////////
	    flag = true;

	    if (GAMEMODE.getMapWidth() > 50) {
		GAMEMODE.setMapWidth(50);
		flag = false;
	    }
	    if (GAMEMODE.getMapHeight() > 50) {
		GAMEMODE.setMapHeight(50);
		flag = false;
	    }

	    if (!flag)
		JOptionPane.showMessageDialog(mainPanel,
			"Entered number(s) must be less than 50! Default max parameters will be set.", "Too Big!",
			JOptionPane.WARNING_MESSAGE);

	    ///////////////////////////
	    if (GAMEMODE.getMinesAmount() >= GAMEMODE.getMapHeight() * GAMEMODE.getMapWidth()) {
		GAMEMODE.setMineAmount(GAMEMODE.getMapHeight() * GAMEMODE.getMapWidth() - 1);
		JOptionPane.showMessageDialog(mainPanel, "Too many mines!", "Boom!", JOptionPane.WARNING_MESSAGE);
	    }
	    /**************************/
	    displayModeInfo();
	}

	gameView = new GameView(mainPanel, smileFaceLbl, GAMEMODE, mines, timer);
	mines.set(GAMEMODE.getMinesAmount());
	timer.setup(0, 1000, 999);
	timer.on();

	Image smileFaceIcon = JImages.scaleImage(new ImageIcon(Images.SMILE_HAPPY).getImage(), smileFaceLbl.getWidth(),
		smileFaceLbl.getHeight());
	smileFaceLbl.setIcon(new ImageIcon(smileFaceIcon));

	mainPanel.add(gameView);
	mainPanel.repaint();
    }

    /***********************************************************
     * VARIABLE DECLARATION
     ***********************************************************/
    /**
     * The content pane component
     */
    private JPanel contentPane;

    /**
     * The ratio of a screen that the program window will be.<br>
     */
    private static double RATIO = 0.8;

    /**
     * The Width of the screen
     */
    public static final int SCREEN_WIDTH = (int) JHardware.getScreenSize().getWidth();
    /**
     * The Height of the screen
     */
    public static final int SCREEN_HEIGHT = (int) JHardware.getScreenSize().getHeight();
    /**
     * The Width of the program window
     */
    public static final int WINDOW_WIDTH = (int) (SCREEN_WIDTH * RATIO);
    /**
     * The Height of the program window
     */
    public static final int WINDOW_HEIGHT = (int) (SCREEN_HEIGHT * RATIO);
    /**
     * The margin by how much that all the components must be separated from each
     * other.
     */
    public static final int COMPONENT_MARGIN = 10;
    /**
     * The delay for the game mechanism
     */
    public static final int LATENCY = 100;
    /**
     * The current game mode
     */
    public static Mode GAMEMODE = Mode.EASY;// Default Value is easy
    /**
     * The main canvas that holds the game view. <br>
     * TODO: add some background
     */
    private JPanel mainPanel;
    /**
     * The panel that holds the upper menu which has buttons, input fields and smile
     * face on it.
     */
    private JPanel topPanel;

    /**
     * Text field for the amount of mines.
     */
    private JFormattedTextField ftFieldM;
    /**
     * Text field for the width of the map.
     */
    private JFormattedTextField ftFieldW;
    /**
     * Text field for the height of the map.
     */
    private JFormattedTextField ftFieldH;
    /**
     * Icon for the smile face
     */
    private JLabel smileFaceLbl;
    /**
     * Text label: "M:"
     */
    private JLabel lblM;
    /**
     * Text label: "W:"
     */
    private JLabel lblW;
    /**
     * Text label: "H:"
     */
    private JLabel lblH;
    /**
     * "Start" Button
     */
    private JButton btnStart;
    /**
     * Combobox for choosing the game modes
     */
    private JComboBox<String> comboBox;

    /**
     * Displayable component for an instance of <i>Timer</i> class that holds the
     * amount of mines. <br>
     * The loop is killed from the start, since it will be controlled by the action
     * instead of time itself.
     */
    private Clock mineCounter;
    /**
     * Displayable component for an instance of <i>Timer</i> class that holds the
     * time value.
     */
    private Clock timeCounter;
    /**
     * An instance of <i>Timer</i> class that holds the amount of mines.
     */
    private Timer mines;
    /**
     * An instance of <i>Timer</i> class that holds the time value.
     */
    private Timer timer;

    /**
     * Mechanism that runs the Timer and changes the face of the Clock. See
     * <i>Clock</i> class for how the mechanism works.
     */
    private Thread clockMechanism;

    /**
     * The view where map will be displayed on.
     */
    private GameView gameView;
    /**
     * Font used for Mode labels. <br>
     * <strong>Tahoma Bold 13</strong>
     */
    private static final Font modeLabelFont = new Font("Tahoma", Font.BOLD, 13);

}
