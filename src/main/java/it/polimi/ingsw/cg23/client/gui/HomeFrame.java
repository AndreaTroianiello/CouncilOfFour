package it.polimi.ingsw.cg23.client.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.gui.homepanels.PanelConnection;
import it.polimi.ingsw.cg23.client.gui.homepanels.PanelLogin;
import it.polimi.ingsw.cg23.server.controller.change.InfoChange;

/**
 * The class that starts the client GUI.
 * 
 * @author Andrea
 *
 */
public class HomeFrame extends JFrame {

	private static final long serialVersionUID = -7250417194617661777L;
	private transient ControllerGUI controller;
	private PanelConnection panelConnection;
	private PanelLogin panelLogin;
	private static Logger logger;
	private JLabel labelBackground;

	/**
	 * The constructor of HomeFrame. It creates the frame.
	 */
	public HomeFrame() {
		logger = Logger.getLogger(HomeFrame.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		controller = new ControllerGUI(this);
		initComponents();
	}

	/**
	 * Initializes the componets of the JFrame.
	 */
	private void initComponents() {
		panelLogin = new PanelLogin(controller);
		panelConnection = new PanelConnection(controller, this);
		labelBackground = new JLabel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Council of Four");
		setBounds(new Rectangle(100, 100, 400, 497));
		setMaximumSize(new Dimension(700, 870));
		setMinimumSize(new Dimension(400, 497));
		setName("home"); // NOI18N
		setPreferredSize(new Dimension(400, 497));
		setResizable(false);
		getContentPane().setLayout(null);

		// Panel Login.
		getContentPane().add(panelLogin);
		panelLogin.setBounds(-10, 230, 400, 220);
		panelLogin.setVisible(false);

		// Panel Connection.
		getContentPane().add(panelConnection);
		panelConnection.setBounds(0, 230, 400, 230);

		try {
			BufferedImage image = ImageIO.read(new File("src/main/resources/images/Home-CouncilOfFour.jpg"));
			labelBackground.setIcon(new ImageIcon(image));
			labelBackground.setAlignmentY(0.0F);
			getContentPane().add(labelBackground);
			labelBackground.setBounds(0, 0, image.getWidth(), image.getHeight());
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine", e);
		}

		pack();
	}

	/**
	 * Switches the panelLogin with panelConnection and vice versa.
	 */
	public void switchPanel() {
		panelLogin.setVisible(!panelLogin.isVisible());
		panelConnection.setVisible(!panelConnection.isVisible());
	}

	/**
	 * Updates the HomeFrame with the informations received.
	 * 
	 * @param info
	 *            The informations received.
	 */
	public void updateInfo(InfoChange info) {
		panelLogin.infoPopup(" " + info.getInfo());
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new HomeFrame().setVisible(true));
	}
}
