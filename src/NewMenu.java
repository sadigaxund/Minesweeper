import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
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
import javax.swing.ImageIcon;
import javax.swing.text.PlainDocument;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NewMenu extends JFrame {

    /*
     * TODO: ADD: when text selected in mode info labels if yout type already
     * existing text will be deleted
     * 
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
	initVars();
	initComponents();

    }

    private void initVars() {

    }

    private void initComponents() {
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

	addModeComboBox();
	addClocks();
	addFormattedTextFields();
	addModeLabels();

	btnNewButton = new JButton("START");
	btnNewButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		startAction();
	    }
	});
	btnNewButton.setFont(new Font("Consolas", Font.BOLD, 13));
	btnNewButton.setUI(new CustomButton());
	btnNewButton.setBounds(622, 5, 89, 35);
	topPanel.add(btnNewButton);

	mainPanel = new JPanel();
	mainPanel.setBorder(new MatteBorder(2, 4, 6, 4, (Color) new Color(153, 180, 209)));
	mainPanel.setBackground(SystemColor.inactiveCaption);
	mainPanel.setBounds(0, 42, 720, 533);
	mainPanel.setLayout(null);

	contentPane.add(mainPanel);

	GameView gameView = new GameView(mainPanel, 219, 182);
	gameView.update();
	mainPanel.add(gameView);
    }

    private void addClocks() {
	int clockW = 60;
	int clockH = topPanel.getHeight() - COMPONENT_MARGIN;

	Timer timer = new Timer();
	timer.disable();
	timer.setStopTime(999);

	cl = new Clock(timer, clockW, clockH, smileFaceLbl.getX() - clockW - COMPONENT_MARGIN, COMPONENT_MARGIN / 2);
	topPanel.add(cl);
	new Thread(cl).start();

	Timer timer2 = new Timer();
	timer2.enable();
	timer2.setStopTime(999);
	timer2.setDelay(1000);

	cl2 = new Clock(timer2, clockW, clockH, smileFaceLbl.getX() + smileFaceLbl.getWidth() + COMPONENT_MARGIN,
		COMPONENT_MARGIN / 2);

	topPanel.add(cl2);
    }

    private void addFormattedTextFields() {
	formattedTextFieldM = new JFormattedTextField();
	PlainDocument doc = (PlainDocument) formattedTextFieldM.getDocument();
	IntFilter filter = new IntFilter();
	doc.setDocumentFilter(filter);
	formattedTextFieldM.setBounds(119, 10, 28, 24);
	topPanel.add(formattedTextFieldM);

	formattedTextFieldW = new JFormattedTextField();
	doc = (PlainDocument) formattedTextFieldW.getDocument();
	doc.setDocumentFilter(filter);
	formattedTextFieldW.setBounds(176, 10, 28, 24);
	topPanel.add(formattedTextFieldW);

	formattedTextFieldH = new JFormattedTextField();
	doc = (PlainDocument) formattedTextFieldH.getDocument();
	doc.setDocumentFilter(filter);
	formattedTextFieldH.setBounds(233, 10, 28, 24);
	topPanel.add(formattedTextFieldH);

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

    private void enableTextFields(boolean state) {
	formattedTextFieldM.setEditable(state);
	formattedTextFieldW.setEditable(state);
	formattedTextFieldH.setEditable(state);
    }

    private void displayModeInfo() {
	formattedTextFieldM.setText(GAMEMODE.getMinesAmount() + "");
	formattedTextFieldW.setText(GAMEMODE.getMapWidth() + "");
	formattedTextFieldH.setText(GAMEMODE.getMapHeight() + "");
	cl.setClock(GAMEMODE.getMinesAmount());
    }

    private void comboBoxHandleAction() {
	String selected = (String) comboBox.getSelectedItem();
	GAMEMODE = Mode.getModeByName(selected);
	enableTextFields(Tools.equalsNoCase(selected, "Custom"));
	displayModeInfo();
    }

    private void startAction() {

    }

    private JPanel contentPane;

    private static double RATIO = 0.8;

    public static final int SCREEN_WIDTH = (int) JHardware.getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int) JHardware.getScreenSize().getHeight();
    public static final int WINDOW_WIDTH = (int) (SCREEN_WIDTH * RATIO);
    public static final int WINDOW_HEIGHT = (int) (SCREEN_HEIGHT * RATIO);
    public static final int COMPONENT_MARGIN = 10;

    public static Mode GAMEMODE = Mode.EASY;// Default Value is easy

    private JPanel mainPanel;
    private JPanel topPanel;

    private JFormattedTextField formattedTextFieldM;
    private JFormattedTextField formattedTextFieldW;
    private JFormattedTextField formattedTextFieldH;

    private JLabel smileFaceLbl;
    private JLabel lblM;
    private JLabel lblW;
    private JLabel lblH;

    private JButton btnNewButton;

    private JComboBox<String> comboBox;

    private Clock cl;
    private Clock cl2;

    private static final Font modeLabelFont = new Font("Tahoma", Font.BOLD, 13);
}
