package it.polimi.ingsw.cg23.gui;


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.gui.panel.LoggerPanel;
import it.polimi.ingsw.cg23.gui.panel.SouthPanel;
import it.polimi.ingsw.cg23.server.controller.Avvio;

/**
 * create the map
 * @author viga94_
 *
 */
public class FrameMap extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328028752338623444L;
	private JPanel contentPane;
	private JTextArea loggerArea;
	private JTextArea write;
	private transient Avvio s;
	private ClientController controller;
	private static Logger logger;

	/**
	 * Create the frame.
	 */
	public FrameMap(ClientController controller) {
		//configurazione logger
		logger = Logger.getLogger(FrameMap.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
		
		this.controller=controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		//carica le informazioni sulle citta'--- PROVVISORIO (poi gia' caricate)
		s=new Avvio("RegionCity.xml");
		s.startPartita();

		grid();
	}
	
	/**
	 * create the main panel (map + south + logger)
	 */
	private void grid(){
		GridBagLayout layout = new GridBagLayout();
		contentPane.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints();

		//----------pannello nord (mappa)----------
		JButton b1=new JButton();
		b1.setText("b1");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=3;
		lim.fill=GridBagConstraints.BOTH;//occupazione dello spazio libero della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.NORTHWEST;//posizione componenti nei riquadri
		layout.setConstraints(b1, lim);
		contentPane.add(b1);

		//----------text area (logger)----------
		loggerArea=new JTextArea();
		write=new JTextArea();
		JPanel panel=new LoggerPanel().loggerPanel(loggerArea, write);
		
		Component scrollLogger = new JScrollPane(panel);
		scrollLogger.setName("scrollPane text area logger");
		lim.gridx = 3;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=3;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.VERTICAL;//occupazione dello spazio libero della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.EAST;//posizione componenti nei riquadri
		layout.setConstraints(scrollLogger, lim); //Associazione
		contentPane.add(scrollLogger); //Inserimento

		//----------pannello sud (informazioni)----------
		JPanel southPanel=new SouthPanel().setSouthPanel(s);
		southPanel.setName("south panel");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		lim.fill=GridBagConstraints.HORIZONTAL;//occupazione dello spazio libero della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.SOUTHWEST;//posizione componenti nei riquadri
		layout.setConstraints(southPanel, lim); //Associazione
		contentPane.add(southPanel); //Inserimento
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameMap frame = new FrameMap(new ControllerGUI());

					frame.setVisible(true);
				} catch (Exception e) {
					logger.error("errore nel fame map", e);
				}
			}
		});
	}

}
