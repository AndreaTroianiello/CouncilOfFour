package it.polimi.ingsw.cg23.gui;


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.gui.mappanel.EastPanel;
import it.polimi.ingsw.cg23.gui.mappanel.MapPanel;
import it.polimi.ingsw.cg23.gui.mappanel.SouthPanel;
import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.model.Board;

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
	//private transient Avvio model;
	//private ClientController controller;
	private static Logger logger;

	private EastPanel ep;
	private MapPanel mp;
	private SouthPanel sp;
	/**
	 * Create the frame.
	 * @param controller
	 */
	public FrameMap(ClientModel model) {
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
		
		//pannel
		ep=new EastPanel();
		mp=new MapPanel();
		sp=new SouthPanel();
		
		this.model=model;
		//this.controller=controller;
		setTitle("Mappa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1460, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		//setResizable(false);
		//carica le informazioni sulle citta'--- PROVVISORIO (poi gia' caricate)
		/*Board b=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null, null);
		String name="map6.xml";
		try {
			s=new Avvio(name, b);
		} catch (XmlException e) {
			logger.error("Errore nel caricare il file xml: "+name, e);
		}
		s.startPartita();
		 */
		grid();
		update();
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
		JPanel panel=ep.loggerPanel(loggerArea, write);

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

		//----------pannello nord (mappa)----------
		JPanel mapPanel=mp.createMap(model.getModel().getRegions(), loggerArea);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=3;
		lim.fill=GridBagConstraints.BOTH;//occupazione dello spazio libero della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.NORTHWEST;//posizione componenti nei riquadri
		layout.setConstraints(mapPanel, lim);
		contentPane.add(mapPanel);
		
		
		//----------pannello sud (informazioni)----------
		JPanel southPanel=sp.setSouthPanel(model, loggerArea);
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
		
		pack();//necessario
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ClientModel model=new ClientModel();
					model.setModel(new Board(null,null,null,null,null,null));
					new Avvio("map4.xml",model.getModel()).startPartita();
					FrameMap frame = new FrameMap(model);
					frame.setVisible(true);
				} catch (Exception e) {
					logger.error("errore nel fame map", e);
				}
			}
		});
	}

	public void update(){
		ep.update();
		mp.update();
		sp.update();
		this.repaint();
	}
}
