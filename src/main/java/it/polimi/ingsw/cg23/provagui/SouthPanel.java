package it.polimi.ingsw.cg23.provagui;

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
	private NobilityTrackPanel ntp;
	private PoliticCardPanel pcp;
	private BonusPanel bp;
	private transient ClientModel model;
	private JTextArea loggerArea;

	/**
	 * 
	 * @param model
	 * @param loggerArea
	 */
	public SouthPanel(ClientModel model, JTextArea loggerArea) {
		this.model=model;
		this.loggerArea=loggerArea;
		init();
	}

	/**
	 * create the south panel
	 * @return a jpanel with the south panel created
	 */
	private void init(){
		this.ntp=new NobilityTrackPanel(model, loggerArea);
		this.ccp=new CostructionCardPanel(loggerArea);
		this.cp=new CouncilPanel();
		this.pcp=new PoliticCardPanel(model, loggerArea);
		this.bp=new BonusPanel(model.getModel(), loggerArea);
		
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
			JPanel balcone=cp.balcone(reg.get(i));
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
		ntp.setName("Nobility panel");
		ntp.setBackground(new Color(154, 205, 50));
		//JScrollPane scrollNobility=new  JScrollPane(panelNobility);
		////scrollNobility.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scrollNobility.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		//
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(ntp, lim);
		add(ntp);

		//----------------consiglieri del re------------
		JPanel kingCouncillors=cp.kingbalcone(model.getModel().getKing());
		kingCouncillors.setName("consiglieri re");
		kingCouncillors.setBackground(new Color(123, 104, 238));
		kingCouncillors.setOpaque(true);
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(kingCouncillors, lim);
		add(kingCouncillors);

		//----------------consiglieri del re------------
		JTextArea punteggi=new JTextArea();
		punteggi.setText("Coin: "+model.getPlayer().getRichness().getCoins());
		punteggi.append("\nVictory points: "+model.getPlayer().getVictoryTrack().getVictoryPoints());
		punteggi.append("\nNobility Track position: "+model.getPlayer().getNobilityBoxPosition());
		punteggi.append("\nNumero assistenti "+model.getPlayer().getAssistantsPool().getAssistants());
		punteggi.setName("consiglieri re");
		punteggi.setBackground(new Color(123, 104, 238));
		punteggi.setOpaque(true);
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(punteggi, lim);
		add(punteggi);

		//----------carte politiche------------
		pcp.setName("Carte politiche");
		pcp.setBackground(new Color(154, 205, 50));
		//JScrollPane scroll=new JScrollPane(politics);
		//scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(pcp, lim);
		add(pcp);

		//----------my carte costruzione------------
		JPanel avaiableCostrucion=ccp.myCostructionCard(model.getPlayer());
		avaiableCostrucion.setName("costruzione disponibile");
		avaiableCostrucion.setBackground(new Color(154, 205, 50));
		//JScrollPane scroll=new JScrollPane(politics);
		//scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(avaiableCostrucion, lim);
		add(avaiableCostrucion);

		//----------------bonus panel------------
		bp.setName("bonus");
		bp.setBackground(new Color(123, 104, 238));
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(bp, lim);
		add(bp);
	}
	
	public void update(){
		removeAll();
		init();
	}
}
