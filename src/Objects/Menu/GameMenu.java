package Objects.Menu;

import java.awt.event.WindowEvent;

import Main.Minesweeper;
import Util.IMinesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
public class GameMenu extends javax.swing.JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** The dificulty of the game */
    public static IMinesweeper.Complexity complexity = IMinesweeper.Complexity.BEGINNER;

    /**
     * Creates new form NewJFrame
     */
    public GameMenu() {
	initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    @SuppressWarnings("deprecation")
    private void initComponents() {
	setLocation(450, 150);
	/* Separators */
	jSeparator2 = new javax.swing.JPopupMenu.Separator();
	jSeparator3 = new javax.swing.JPopupMenu.Separator();
	jSeparator4 = new javax.swing.JPopupMenu.Separator();

	/* Menu items */
	MenuItem_Open = new javax.swing.JMenuItem();
	MenuItem_Save = new javax.swing.JMenuItem();
	MenuItem_SaveAs = new javax.swing.JMenuItem();
	MenuItem_Exit = new javax.swing.JMenuItem();
	Menu_Difficulty = new javax.swing.JMenu();
	MenuItem_Beginner = new javax.swing.JMenuItem();
	MenuItem_Intermediate = new javax.swing.JMenuItem();
	MenuItem_Expert = new javax.swing.JMenuItem();
	MenuItem_Custom = new javax.swing.JMenuItem();
	Menu_Edit = new javax.swing.JMenu();
	MenuItem_Cut = new javax.swing.JMenuItem();
	MenuItem_Copy = new javax.swing.JMenuItem();
	MenuItem_Paste = new javax.swing.JMenuItem();
	MenuItem_Delete = new javax.swing.JMenuItem();

	/* Other Components */
	START_Button = new javax.swing.JButton();
	Difficulty_Identifier_Label = new javax.swing.JLabel();
	MenuBar = new javax.swing.JMenuBar();
	Menu_File = new javax.swing.JMenu();

	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	setBackground(new java.awt.Color(0, 51, 51));

	START_Button.setFont(new java.awt.Font("Emulogic", 0, 11)); // NOI18N
	START_Button.setLabel("START GAME");
	START_Button.addMouseListener(new java.awt.event.MouseListener() {

	    @Override
	    public void mouseReleased(java.awt.event.MouseEvent arg0) {

	    }

	    @Override
	    public void mousePressed(java.awt.event.MouseEvent arg0) {

	    }

	    @Override
	    public void mouseExited(java.awt.event.MouseEvent arg0) {

	    }

	    @Override
	    public void mouseEntered(java.awt.event.MouseEvent arg0) {

	    }

	    @Override
	    public void mouseClicked(java.awt.event.MouseEvent evt) {
		START_ButtonActionPerformed(evt);

	    }
	});

	Difficulty_Identifier_Label.setFont(new java.awt.Font("Emulogic", 0, 11)); // NOI18N
	Difficulty_Identifier_Label.setText("Difficulty: Beginner");

	Menu_File.setMnemonic('f');
	Menu_File.setText("File");

	MenuItem_Open.setMnemonic('o');
	MenuItem_Open.setText("Open");
	Menu_File.add(MenuItem_Open);

	MenuItem_Save.setMnemonic('s');
	MenuItem_Save.setText("Save");
	Menu_File.add(MenuItem_Save);

	MenuItem_SaveAs.setMnemonic('a');
	MenuItem_SaveAs.setText("Save As ...");
	MenuItem_SaveAs.setDisplayedMnemonicIndex(5);
	Menu_File.add(MenuItem_SaveAs);

	MenuItem_Exit.setMnemonic('x');
	MenuItem_Exit.setText("Exit");
	MenuItem_Exit.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		exitMenuItemActionPerformed(evt);
	    }
	});
	Menu_File.add(MenuItem_Exit);

	MenuBar.add(Menu_File);

	Menu_Difficulty.setText("Difficulty");

	MenuItem_Beginner.setFont(new java.awt.Font("Emulogic", 0, 11)); // NOI18N
	MenuItem_Beginner.setText("Beginner");
	MenuItem_Beginner.addActionListener(new java.awt.event.ActionListener() {

	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent arg0) {
		setComplexity(IMinesweeper.Complexity.BEGINNER);
	    }
	});
	Menu_Difficulty.add(MenuItem_Beginner);
	Menu_Difficulty.add(jSeparator2);

	MenuItem_Intermediate.setFont(new java.awt.Font("Emulogic", 0, 11)); // NOI18N
	MenuItem_Intermediate.setText("Intermediate");
	MenuItem_Intermediate.addActionListener(new java.awt.event.ActionListener() {

	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent arg0) {
		setComplexity(IMinesweeper.Complexity.MEDIUM);
	    }
	});
	Menu_Difficulty.add(MenuItem_Intermediate);
	Menu_Difficulty.add(jSeparator3);

	MenuItem_Expert.setFont(new java.awt.Font("Emulogic", 0, 11)); // NOI18N
	MenuItem_Expert.setText("Expert");
	Menu_Difficulty.add(MenuItem_Expert);

	MenuItem_Expert.addActionListener(new java.awt.event.ActionListener() {

	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent arg0) {
		setComplexity(IMinesweeper.Complexity.EXPERT);
	    }
	});
	Menu_Difficulty.add(jSeparator4);

	MenuItem_Custom.setFont(new java.awt.Font("Emulogic", 0, 11)); // NOI18N
	MenuItem_Custom.setText("Custom");
	MenuItem_Custom.setActionCommand("Custom");

	MenuItem_Custom.addActionListener(new java.awt.event.ActionListener() {

	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent arg0) {
		setComplexity(IMinesweeper.Complexity.CUSTOM);
	    }
	});

	Menu_Difficulty.add(MenuItem_Custom);

	MenuBar.add(Menu_Difficulty);

	Menu_Edit.setText("Edit");

	MenuItem_Cut.setMnemonic('t');
	MenuItem_Cut.setText("Cut");
	Menu_Edit.add(MenuItem_Cut);

	MenuItem_Copy.setMnemonic('y');
	MenuItem_Copy.setText("Copy");
	Menu_Edit.add(MenuItem_Copy);

	MenuItem_Paste.setMnemonic('p');
	MenuItem_Paste.setText("Paste");
	Menu_Edit.add(MenuItem_Paste);

	MenuItem_Delete.setMnemonic('d');
	MenuItem_Delete.setText("Delete");
	Menu_Edit.add(MenuItem_Delete);

	MenuBar.add(Menu_Edit);

	setJMenuBar(MenuBar);

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

	getContentPane().setLayout(layout);

	layout.setHorizontalGroup(// g4

		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

			.addGroup(// g3

				layout.createSequentialGroup().addContainerGap(57, Short.MAX_VALUE)

					.addGroup(// g2

						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

							.addGroup(// g5

								javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup()
									.addComponent(START_Button).addGap(88, 88, 88)
							/* g5 */)

							.addGroup(// g1
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup()
									.addComponent(Difficulty_Identifier_Label)
									.addGap(46, 46, 46))// g1
				)// g2
		)// g3
	);// g4

	layout.setVerticalGroup(

		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(

			javax.swing.GroupLayout.Alignment.TRAILING,
			layout.createSequentialGroup().addContainerGap(188, Short.MAX_VALUE)

				/* Component */
				.addComponent(Difficulty_Identifier_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				/* Gap */
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				/* Component */
				.addComponent(START_Button)
				/* Gap */
				.addGap(49, 49, 49)));

	pack();
    }// </editor-fold>

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
	System.exit(0);
    }

    /**
     * When the START button is pressed, start new game by opening new window while
     * closing previous one of menu
     * 
     * @param evt
     *            Mouse Event
     */
    private void START_ButtonActionPerformed(java.awt.event.MouseEvent evt) {
	new Minesweeper().start();
	this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
	/* Set the Nimbus look and feel */
	// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
	// (optional) ">
	/*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
	 * look and feel. For details see
	 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(GameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(GameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(GameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(GameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	// </editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new GameMenu().setVisible(true);
	    }
	});
    }

    /**
     * @return the complexity
     */
    public static IMinesweeper.Complexity getComplexity() {
	return complexity;
    }

    /**
     * @param complexity
     *            the complexity to set
     */
    public void setComplexity(IMinesweeper.Complexity complexity) {
	GameMenu.complexity = complexity;
	Difficulty_Identifier_Label.setText("Difficulty: " + complexity);
    }

    // Variables declaration - do not modify
    /** The label that shows the difficulty of the game */
    private javax.swing.JLabel Difficulty_Identifier_Label;

    /** Menu bar of the game window */
    private javax.swing.JMenuBar MenuBar;

    /** Menu for selecting the difficulty of the game */
    private javax.swing.JMenu Menu_Difficulty;
    private javax.swing.JMenu Menu_Edit;
    private javax.swing.JMenu Menu_File;
    private javax.swing.JButton START_Button;

    /* Separators */
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;

    /* Menu items */

    /* items of the file menu */
    private javax.swing.JMenuItem MenuItem_Open;
    private javax.swing.JMenuItem MenuItem_Exit;
    private javax.swing.JMenuItem MenuItem_SaveAs;
    private javax.swing.JMenuItem MenuItem_Save;

    /* items of the Difficulty menu */
    /** Menu item that changes the difficulty of the game to the Beginner */
    private javax.swing.JMenuItem MenuItem_Beginner;

    /** Menu item that changes the difficulty of the game to the Intermediate */
    private javax.swing.JMenuItem MenuItem_Intermediate;

    /** Menu item that changes the difficulty of the game to the Expert */
    private javax.swing.JMenuItem MenuItem_Expert;

    /** Menu item that changes the difficulty of the game to the Custom */
    private javax.swing.JMenuItem MenuItem_Custom;

    /* items of the edit menu */
    private javax.swing.JMenuItem MenuItem_Copy;
    private javax.swing.JMenuItem MenuItem_Cut;
    private javax.swing.JMenuItem MenuItem_Delete;
    private javax.swing.JMenuItem MenuItem_Paste;

    // End of variables declaration
}
