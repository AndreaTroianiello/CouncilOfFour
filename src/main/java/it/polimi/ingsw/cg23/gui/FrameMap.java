package it.polimi.ingsw.cg23.gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.gui.mappanel.EastPanel;
import it.polimi.ingsw.cg23.gui.mappanel.MapPanel;
import it.polimi.ingsw.cg23.gui.mappanel.SouthPanel;
import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

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
	private JTextField write;
	private transient ClientModel model;
	private static Logger logger;

	private EastPanel eastPanel;
	private MapPanel mapPanel;
	private SouthPanel southPanel;
	private transient ControllerGUI controller;

	/**
	 * Create the frame.
	 * @param controller
	 */
	public FrameMap(ControllerGUI controller, ClientModel model) {
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
		
		this.controller=controller;
		this.model=model;
		loggerArea=new JTextArea();
		write=new JTextField();
		//pannel
		eastPanel=new EastPanel(loggerArea, write, controller);
		mapPanel=new MapPanel(loggerArea, model);
		southPanel=new SouthPanel(model, loggerArea);
		setTitle("Mappa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width-100, Toolkit.getDefaultToolkit().getScreenSize().height-200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		grid();
	}

	/**
	 * create the main panel (map + south + logger)
	 */
	private void grid(){
		GridBagLayout layout = new GridBagLayout();
		contentPane.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints();

		//----------text area (logger)----------
		loggerArea=new JTextArea();
		write=new JTextField();

		Component scrollLogger = new JScrollPane(eastPanel);
		scrollLogger.setName("scrollPane text area logger");
		lim.gridx = 3;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=3;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//occupazione dello spazio libero della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		layout.setConstraints(scrollLogger, lim); //Associazione
		contentPane.add(scrollLogger); //Inserimento

		//----------pannello nord (mappa)----------
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=3;
		lim.fill=GridBagConstraints.NONE;//occupazione dello spazio libero della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		layout.setConstraints(mapPanel, lim);
		contentPane.add(mapPanel);


		//----------pannello sud (informazioni)----------
		JScrollPane scroll=new JScrollPane(southPanel);
		southPanel.setName("south panel");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		lim.fill=GridBagConstraints.BOTH;//occupazione dello spazio libero della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		layout.setConstraints(scroll, lim); //Associazione
		contentPane.add(scroll); //Inserimento
		pack();//necessario
	}
	
	public void updateInfo(Change change){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
		    public void run() {
		    	loggerArea.setText(loggerArea.getText()+"\n"+change.toString());
		    }
		});
	}
	
	public void update(){
		southPanel.update();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ClientModel models=new ClientModel();
					models.setModel(new Board(null,null,null,null,null,null));
					new Avvio("map8.xml",models.getModel()).startPartita();
					Player p=new Player("user",models.getModel().getNobilityTrack());
					models.setPlayer(p);
					List<PoliticCard> list=Arrays.asList(new PoliticCard(Color.BLACK, false),
							new PoliticCard(Color.ORANGE, false),
							new PoliticCard(null, true),
							new PoliticCard(null, true),
							new PoliticCard(Color.BLUE, false),
							new PoliticCard(Color.PINK, false));
					models.getPlayer().getHand().addAll(list);
					ControllerGUI controllers=new ControllerGUI(new HomeFrame());
					FrameMap frame = new FrameMap(controllers, models);
					frame.setVisible(true);
				} catch (Exception e) {
					logger.error("errore nel fame map", e);
				}
			}
		});
	}

}
