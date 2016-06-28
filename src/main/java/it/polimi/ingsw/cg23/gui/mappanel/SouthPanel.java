package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.client.ClientModel;
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
	private CostructionCardPanel ccp;
	private CouncilPanel cp;
	private NobilityTrackPanel panelNobility;
	private PoliticCardPanel pcp;
	private transient ClientModel model;
	private SouthEastPanel southEastPanel;
	private JTextArea loggerArea;

	/**
	 * 
	 * @param model
	 * @param loggerArea
	 */
	public SouthPanel(ClientModel model, JTextArea loggerArea) {
		this.loggerArea=loggerArea;
		this.model=model;
		init();
	}

	/**
	 * create the south panel
	 * @return a jpanel with the south panel created
	 */
	private void init(){
		this.panelNobility=new NobilityTrackPanel(model, loggerArea);
		this.ccp=new CostructionCardPanel(loggerArea);
		this.cp=new CouncilPanel(loggerArea);
		this.pcp=new PoliticCardPanel(model.getPlayer(), loggerArea);
		this.southEastPanel=new SouthEastPanel(model, loggerArea);

		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill = GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		List<Region> reg=model.getModel().getRegions();
		for(int i=0; i<reg.size(); i++){//scorre le regioni-> aggiunge le carte permesso

			//----------carte permesso di costruzione----------
			JPanel costruzione=ccp.getShowCostructionCard(reg.get(i));
			costruzione.setName("costruzione "+reg.get(i).getName());
			costruzione.setBackground(new Color(154, 205, 50));
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=0;
			layout.setConstraints(costruzione, lim);
			add(costruzione);
		}

		for(int i=0; i<reg.size(); i++){//scorre le regioni-> aggiunge i consiglieri

			//----------consiglieri regione----------
			JPanel balcone=cp.regionBalcone(reg.get(i));
			balcone.setBackground(new Color(154, 205, 50));
			balcone.setName("balcone "+reg.get(i).getName());
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=0;
			layout.setConstraints(balcone, lim);
			add(balcone);
		}

		//----------------nobility track------------
		panelNobility.setName("Nobility panel");
		panelNobility.setBackground(new Color(154, 205, 50));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(panelNobility, lim);
		add(panelNobility);

		//----------------southeast panel------------
		southEastPanel.setBackground(new Color(154, 205, 50));
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(southEastPanel, lim);
		add(southEastPanel);

		//----------carte politiche------------
		JPanel politics=pcp.createCard();
		politics.setName("Carte politiche");
		politics.setBackground(new Color(154, 205, 50));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(politics, lim);
		add(politics);

		//----------my carte costruzione------------
		JPanel avaiableCostrucion=ccp.myCostructionCard(model.getPlayer());
		avaiableCostrucion.setName("costruzione disponibile");
		avaiableCostrucion.setBackground(new Color(154, 205, 50));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(avaiableCostrucion, lim);
		add(avaiableCostrucion);
	}

}
