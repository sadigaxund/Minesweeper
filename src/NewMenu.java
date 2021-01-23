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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.PlainDocument;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class NewMenu extends JFrame {
    /*
     * TODO: Optimize: in Clock: Instead of removing and repainting all digits, try
     * to do it on only one digit
     */
    /**
     * 
     */
    private static final long serialVersionUID = 8220035870172267085L;
    private JPanel contentPane;

    private static double RATIO = 0.8;

    public static final int SCREEN_WIDTH = (int) JHardware.getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int) JHardware.getScreenSize().getHeight();
    public static final int WINDOW_WIDTH = (int) (SCREEN_WIDTH * RATIO);
    public static final int WINDOW_HEIGHT = (int) (SCREEN_HEIGHT * RATIO);
    public static final int COMPONENT_MARGIN = 10;

    private Mode gameMode;

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
	gameMode = new Mode(Mode.EASY);// Default Value is easy
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

	JPanel topPanel = new JPanel();
	topPanel.setBackground(SystemColor.inactiveCaption);
	topPanel.setBorder(new MatteBorder(2, 4, 2, 4, (Color) new Color(153, 180, 209)));
	topPanel.setBounds(0, 0, 720, 45);
	contentPane.add(topPanel);
	topPanel.setLayout(null);

	int smileFaceSize = (int) (topPanel.getHeight() - COMPONENT_MARGIN * 1.3); // SMILE FACE ICON SIZE
	JLabel smileFaceLabel = new JLabel("");
	// smileFaceLabel.setBorder(UIManager.getBorder("DesktopIcon.border"));
	smileFaceLabel.setBounds(topPanel.getWidth() / 2 - smileFaceSize / 2,
		topPanel.getHeight() / 2 - smileFaceSize / 2, smileFaceSize, smileFaceSize); // SMILE FACE LOCATION
	Image smileFaceIcon = JImages.scaleImage(new ImageIcon(".\\img\\smile\\smile1.png").getImage(),
		smileFaceLabel.getWidth(), smileFaceLabel.getHeight());
	smileFaceLabel.setIcon(new ImageIcon(smileFaceIcon));
	topPanel.add(smileFaceLabel);

	int comboBoxWidth = 158;
	int comboBoxHeight = topPanel.getHeight() - COMPONENT_MARGIN * 2; // COMBO BOX SIZE

	JComboBox<Object> comboBox = new JComboBox<Object>();
	comboBox.setModel((ComboBoxModel<Object>) new DefaultComboBoxModel<Object>(gameMode.getNames()));
	comboBox.setBounds(10, 10, 80, 25);// COMBO BOX LOCATION
	topPanel.add(comboBox);

	int clockW = 60;
	int clockH = topPanel.getHeight() - COMPONENT_MARGIN;

	Timer timer = new Timer();
	timer.disable();
	timer.setStopTime(999);

	Clock cl = new Clock(timer, clockW, clockH, smileFaceLabel.getX() - clockW - COMPONENT_MARGIN,
		COMPONENT_MARGIN / 2);
	cl.setClock(100);
	topPanel.add(cl);
	new Thread(cl).start();

	Timer timer2 = new Timer();
	timer2.enable();
	timer2.setStopTime(999);
	timer2.setDelay(1000);

	Clock cl2 = new Clock(timer2, clockW, clockH,
		smileFaceLabel.getX() + smileFaceLabel.getWidth() + COMPONENT_MARGIN, COMPONENT_MARGIN / 2);

	topPanel.add(cl2);

	JLabel lblW = new JLabel("W:");
	lblW.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblW.setHorizontalAlignment(SwingConstants.CENTER);
	lblW.setBounds(100, 11, 20, 23);
	topPanel.add(lblW);

	JFormattedTextField formattedTextField = new JFormattedTextField();
	PlainDocument doc = (PlainDocument) formattedTextField.getDocument();
	doc.setDocumentFilter(new IntFilter());
	formattedTextField.setBounds(119, 10, 28, 24);
	topPanel.add(formattedTextField);

	JFormattedTextField formattedTextField_1 = new JFormattedTextField();
	doc = (PlainDocument) formattedTextField_1.getDocument();
	doc.setDocumentFilter(new IntFilter());
	formattedTextField_1.setBounds(176, 10, 28, 24);
	topPanel.add(formattedTextField_1);

	JLabel lblH = new JLabel("H:");
	lblH.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblH.setHorizontalAlignment(SwingConstants.CENTER);
	lblH.setBounds(157, 11, 20, 23);
	topPanel.add(lblH);

	JPanel mainPanel = new JPanel();
	mainPanel.setBorder(new MatteBorder(2, 4, 6, 4, (Color) new Color(153, 180, 209)));
	mainPanel.setBackground(SystemColor.inactiveCaption);
	mainPanel.setBounds(0, 42, 720, 533);
	mainPanel.setLayout(null);

	contentPane.add(mainPanel);

    }
}
