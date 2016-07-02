package it.polimi.ingsw.cg23.gui.mapframe;


import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.gui.ControllerGUI;
import it.polimi.ingsw.cg23.gui.HomeFrame;
import it.polimi.ingsw.cg23.gui.mappanel.EastPanel;
import it.polimi.ingsw.cg23.gui.mappanel.MapPanel;
import it.polimi.ingsw.cg23.gui.mappanel.MarketPanel;
import it.polimi.ingsw.cg23.gui.mappanel.SouthPanel;
import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;
import it.polimi.ingsw.cg23.utility.ColorManager;

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
	private static Logger logger;

	private EastPanel eastPanel;
	private MapPanel mapPanel;
	private SouthPanel southPanel;
	private MarketPanel marketPanel;
	private transient ControllerGUI controller;

	/**
	 * Create the frame.
	 * @param controller
	 */
	public FrameMap(ControllerGUI controller) {
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
		
		this.controller=controller;
		loggerArea=new JTextArea();
		write=new JTextField();
		//pannel
		eastPanel=new EastPanel(loggerArea, write, controller);
		mapPanel=new MapPanel(loggerArea, controller);
		southPanel=new SouthPanel(controller, loggerArea);
		setTitle("Mappa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width-200, Toolkit.getDefaultToolkit().getScreenSize().height-200);
		setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width-200, Toolkit.getDefaultToolkit().getScreenSize().height-200));
		
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
		lim.gridx = 3;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty =0;
		lim.gridheight=3;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.VERTICAL;//occupazione dello spazio libero della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		layout.setConstraints(eastPanel, lim); //Associazione
		contentPane.add(eastPanel); //Inserimento
		
		//----------pannello nord (mappa)----------
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=3;
		lim.fill=GridBagConstraints.VERTICAL;//occupazione dello spazio libero della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		layout.setConstraints(mapPanel, lim);
		contentPane.add(mapPanel);
		//mapPanel.setVisible(false);
		
		
		//----------pannello nord (market)----------
		marketPanel=new MarketPanel(controller,loggerArea);
		//marketPanel.setVisible(true);
		marketPanel.setBackground(new Color(151, 111, 51));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=3;
		lim.fill=GridBagConstraints.BOTH;//occupazione dello spazio libero della griglia (both=tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		layout.setConstraints(marketPanel, lim);
		contentPane.add(marketPanel);
		marketPanel.setVisible(false);
		
		//----------pannello sud (informazioni)----------
		JScrollPane scroll=new JScrollPane(southPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		southPanel.setName("south panel");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
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
		loggerArea.append("\n"+change.toString());
	}
	
	public void update(){
		southPanel.update();
		boolean value=controller.getModel().getModel().getStatus().getStatus().contains("MARKET");
		marketPanel.setVisible(value);
		mapPanel.setVisible(!value);
		marketPanel.fillTable();
		mapPanel.update();
		this.revalidate();
		this.repaint();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ClientModel models=new ClientModel();
					models.setModel(new Board(null,null,null,null,null,null));
					new Avvio("map5.xml",models.getModel()).startPartita();
					Player p=new Player("user1",models.getModel().getNobilityTrack());
					models.setPlayer(p);
					List<PoliticCard> list=Arrays.asList(new PoliticCard(Color.BLACK, false),
							new PoliticCard(new ColorManager().getColor("Orange"), false),
							new PoliticCard(null, true),
							new PoliticCard(null, true),
							new PoliticCard(Color.BLUE, false),
							new PoliticCard(Color.PINK, false),
							new PoliticCard(Color.PINK, false),
							new PoliticCard(Color.PINK, false),
							new PoliticCard(Color.BLACK, false),
							new PoliticCard(Color.WHITE, false),
							new PoliticCard(new ColorManager().getColor("Violet"), false));
					models.getPlayer().getHand().addAll(list);
					models.getModel().getKing().setCity(models.getModel().getRegions().get(1).getCities().get(1));
					models.getPlayer().setUsedBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().setUsedBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().setUsedBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().setUsedBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().setUsedBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().setUsedBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().setUsedBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getPlayer().addAvailableBusinessPermit(new BusinessPermitTile(Arrays.asList('A','B','E'), ""));
					models.getModel().getRegions().get(1).setBonusUnavailable();
					models.getModel().getTypes().get(2).setBonusUnavailable();
					models.getModel().getBonusKing().runBonusKing(new Player("user",models.getModel().getNobilityTrack()));
					models.getModel().getMarket().addItemToSell(new Item(new PoliticCard(null, true), p, 5));
					models.getModel().getMarket().addItemToSell(new Item(new BusinessPermitTile(Arrays.asList('A','B'),"ciao"), p, 5));
					AssistantsPool pool=new AssistantsPool();
					pool.setAssistants(10);
					models.getModel().getMarket().addItemToSell(new Item(pool, p, 15));
					ControllerGUI controllers=new ControllerGUI(new HomeFrame());
					controllers.setModel(models);
					FrameMap frame = new FrameMap(controllers);
					frame.setVisible(true);
				} catch (Exception e) {
					logger.error("errore nel frame map", e);
				}
			}
		});
	}

}
