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
import java.awt.event.ActionEvent;

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

    private Mode gameMode = new Mode();

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
	initComponents();
	initVars();

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
	comboBox.setBounds(COMPONENT_MARGIN, topPanel.getHeight() / 2 - comboBoxHeight / 2, comboBoxWidth,
		comboBoxHeight);// COMBO BOX LOCATION
	topPanel.add(comboBox);
	// smileFaceLabel.getX() - comboBoxWidth - COMPONENT_MARGIN
	JPanel panel_1 = new JPanel();
	panel_1.setBounds(178, 11, 109, 23);
	topPanel.add(panel_1);

	JPanel mainPanel = new JPanel();
	mainPanel.setBorder(new MatteBorder(2, 4, 6, 4, (Color) new Color(153, 180, 209)));
	mainPanel.setBackground(SystemColor.inactiveCaption);
	mainPanel.setBounds(0, 42, 720, 533);
	mainPanel.setLayout(null);

	Timer timer = new Timer();
	 timer.enable();
	timer.setStopTime(999);
	timer.setDelay(1000);

	Clock cl = new Clock(timer, 190, 44, 35, 38);
	new Thread(cl).start();

	mainPanel.add(cl);

	contentPane.add(mainPanel);

	JButton btnNewButton = new JButton("start");
	btnNewButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		 timer.on();
	    }
	});
	btnNewButton.setBounds(76, 93, 89, 23);
	mainPanel.add(btnNewButton);

    }

    private void initVars() {

    }
}
