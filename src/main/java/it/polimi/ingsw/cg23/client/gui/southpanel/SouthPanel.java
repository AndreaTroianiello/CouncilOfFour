package it.polimi.ingsw.cg23.client.gui.southpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.model.Region;

/**
 * create the south panel
 * @author viga94_
 *
 */
public class SouthPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1220509934759316582L;
	private transient CostructionCardPanel ccp;
	private transient CouncilPanel cp;
	private NobilityTrackPanel panelNobility;
	private transient PoliticCardPanel pcp;
	private transient ClientModel model;
	private SouthEastPanel southEastPanel;
	private JTextArea loggerArea;
	private transient ControllerGUI controller;

	/**
	 * 
	 * @param controller, the controller
	 * @param loggerArea, the area to read on
	 */
	public SouthPanel(ControllerGUI controller, JTextArea loggerArea) {
		this.loggerArea=loggerArea;
		this.controller=controller;
		this.model=controller.getModel();
		
		init();
	}

	private void init(){
		this.panelNobility=new NobilityTrackPanel(model, loggerArea);
		this.ccp=new CostructionCardPanel(loggerArea,controller);
		this.cp=new CouncilPanel(loggerArea,controller);
		this.pcp=new PoliticCardPanel(controller, loggerArea);
		this.southEastPanel=new SouthEastPanel(controller, loggerArea);

		GridBagLayout layout = new GridBagLayout();//nuovo layout
		setLayout(layout);//applicazione layout al pannello

		GridBagConstraints lim = new GridBagConstraints();//impostazioni layout
		lim.fill = GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		List<Region> reg=model.getBoard().getRegions();//lista delle regioni
		for(int i=0; i<reg.size(); i++){//scorre le regioni-> aggiunge le carte permesso

			//----------carte permesso di costruzione----------
			JPanel costruzione=ccp.getShowCostructionCard(reg.get(i));//creazione pannello carte costruzione
			costruzione.setName("costruzione "+reg.get(i).getName());//nome pannello carte costruzione
			costruzione.setBackground(new Color(151, 111, 51));//colore sfondo pannello carte costruzione
			
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=1;
			
			layout.setConstraints(costruzione, lim);//applicazione layout al pannello carte costruzione
			add(costruzione);//aggiunta del pannello carte costruzione al pannello
		}

		for(int i=0; i<reg.size(); i++){//scorre le regioni-> aggiunge i consiglieri

			//----------consiglieri regione----------
			JPanel balcone=cp.regionBalcone(reg.get(i));//creazione pannello consiglieri
			balcone.setBackground(new Color(151, 111, 51));//colore sfondo pannello consiglieri
			balcone.setName("balcone "+reg.get(i).getName());//nome pannello consiglieri
			
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=1;
			
			layout.setConstraints(balcone, lim);//applicazione layout al pannello consiglieri
			add(balcone);//aggiunta pannello consiglieri al pannello
		}

		//----------------nobility track------------
		panelNobility.setName("Nobility panel");//nome pannello nobility track
		int nobilityLenght=new NobilityTrackPanel(model, loggerArea).nobilityLenght();//lunghezza del nobility track
		panelNobility.setBackground(new Color(151, 111, 51));//colore sfondo pannello nobility track
		
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		
		layout.setConstraints(panelNobility, lim);//applicazione layout al pannello nobility track
		add(panelNobility);//aggiunta pannello nobility track al pannello

		//----------------southeast panel------------
		southEastPanel.setBackground(new Color(151, 111, 51));//colore sfondo southeast panel
		
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=1;
		
		layout.setConstraints(southEastPanel, lim);//applicazione layout al southeast panel
		add(southEastPanel);//aggiunta southeast panel al pannello

		//----------carte politiche------------
		JPanel politics=pcp.createCard();//creazione pannello carte politiche
		JScrollPane scrollPolitics=new JScrollPane(politics);//screzione scrollpane carte politiche
		scrollPolitics.setPreferredSize(new Dimension(nobilityLenght/2, 100));//dimensioni scrollpane
		scrollPolitics.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPolitics.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		politics.setName("Carte politiche");//nome pannello carte politiche
		politics.setBackground(new Color(151, 111, 51));//colore sfondo pannello carte politiche
		
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		
		layout.setConstraints(scrollPolitics, lim);//applicazione layout allo scrollpane carte politiche
		add(scrollPolitics);//aggiunta scroll pane carte politiche al pannello 

		//----------my carte costruzione------------
		JPanel avaiableCostrucion=ccp.myCostructionCard(model.getPlayer());//creazione pannello carte costruzione disponibili
		JScrollPane scrollCostruction=new JScrollPane(avaiableCostrucion);//creazione scrollpane
		scrollCostruction.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollCostruction.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollCostruction.setPreferredSize(new Dimension(nobilityLenght/2, 80));//dimensioni scrollpane
		avaiableCostrucion.setName("costruzione disponibile");//nome scrollpane
		avaiableCostrucion.setBackground(new Color(151, 111, 51));//colore sfondo scroll pane
		
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		
		layout.setConstraints(scrollCostruction, lim);//applicazione layout allo scrollpane
		add(scrollCostruction);//aggiunta scrollpane al pannello
	}

	/**
	 * update the component
	 */
	public void update(){
		removeAll();
		init();
	}

}
