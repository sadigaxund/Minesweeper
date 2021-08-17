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
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewMenu extends JFrame {

    /*
     * TODO: ADD: when text selected in mode info labels if yout type already
     * existing text will be deleted <br> TODO: make input validation
     * 
     */
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
	mines.disable();
	mines.setStopTime(999);

	mineCounter = new Clock(mines, clockW, clockH, smileFaceLbl.getX() - clockW - COMPONENT_MARGIN,
		COMPONENT_MARGIN / 2);
	topPanel.add(mineCounter);
	clockMechanism = new Thread(timeCounter);
	clockMechanism.start();
	time = new Timer();
	time.enable();
	time.setStopTime(999);
	time.setDelay(1000);

	timeCounter = new Clock(time, clockW, clockH, smileFaceLbl.getX() + smileFaceLbl.getWidth() + COMPONENT_MARGIN,
		COMPONENT_MARGIN / 2);

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

	comboBox.setModel((ComboBoxModel<String>) new DefaultComboBoxModel<String>(GAMEMODE.getNames()));
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
	timeCounter.getTimer().on();
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
	    topPanel.repaint();
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

	gameView = new GameView(mainPanel, GAMEMODE, mines, time);
	mines.set(GAMEMODE.getMinesAmount());
	mainPanel.add(gameView);
	mainPanel.repaint();
    }

    private JPanel contentPane;

    private static double RATIO = 0.8;

    public static final int SCREEN_WIDTH = (int) JHardware.getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int) JHardware.getScreenSize().getHeight();
    public static final int WINDOW_WIDTH = (int) (SCREEN_WIDTH * RATIO);
    public static final int WINDOW_HEIGHT = (int) (SCREEN_HEIGHT * RATIO);
    public static final int COMPONENT_MARGIN = 10;
    public static final int LATENCY = 100;

    public static Mode GAMEMODE = Mode.EASY;// Default Value is easy

    private JPanel mainPanel;
    private JPanel topPanel;

    private JFormattedTextField ftFieldM;
    private JFormattedTextField ftFieldW;
    private JFormattedTextField ftFieldH;

    private JLabel smileFaceLbl;
    private JLabel lblM;
    private JLabel lblW;
    private JLabel lblH;

    private JButton btnStart;

    private JComboBox<String> comboBox;

    private Clock mineCounter;
    private Clock timeCounter;

    private Timer mines;
    private Timer time;

    private Thread clockMechanism;
    private Thread mineCntMechanism;

    private GameView gameView;

    private static final Font modeLabelFont = new Font("Tahoma", Font.BOLD, 13);

}
