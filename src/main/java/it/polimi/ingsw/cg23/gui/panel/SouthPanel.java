package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

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

	/**
	 * Create the panel.
	 */
	public SouthPanel() {
		this.ccp=new CostructionCardPanel();
		this.cp=new CouncilPanel();

	}

	/**
	 * create the south panel
	 * @param s, the avvio
	 * @return a jpanel with the south panel created
	 */
	public JPanel setSouthPanel(Board b){
		JPanel southPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		southPanel.setLayout(layout);
		southPanel.setBackground(new Color(123, 158, 237));

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill = GridBagConstraints.HORIZONTAL;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		List<Region> reg=b.getRegions();
		for(int i=0; i<reg.size(); i++){//scorre le regioni-> aggiunge le carte permesso

			//----------carte permesso di costruzione----------
			JPanel costruzione=ccp.getShowCostructionCard(reg.get(i));
			costruzione.setName("costruzione "+reg.get(i).getName());
			addBackground(costruzione, i);
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=0;
			layout.setConstraints(costruzione, lim);
			southPanel.add(costruzione);
		}

		for(int i=0; i<reg.size(); i++){//scorre le regioni-> aggiunge i consiglieri
			JPanel balcone=cp.balcone(reg.get(i));
			addBackground(balcone, i);
			balcone.setName("balcone "+reg.get(i).getName());
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=0;
			layout.setConstraints(balcone, lim);
			southPanel.add(balcone);
		}

		//----------------nobility track------------
		JPanel l4=new NobilityTrackPanel().createNobility(21);
		l4.setName("Nobility panel");
		JScrollPane c8 = new JScrollPane(l4);
		c8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		c8.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;

		layout.setConstraints(c8, lim);
		southPanel.add(c8);

		//----------------carte politiche------------
		Player p=new Player("io", new NobilityTrack(20));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(145,123,241), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(145,255,241), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(255,0,255), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(145,123,241), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(145,255,241), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(255,123,241), false));//PROVA->provvisorio


		JPanel politics=new PoliticCardPanel().createCard(p);
		politics.setName("Carte politiche");
		politics.setBackground(new Color(193,197,192));
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(politics, lim);
		southPanel.add(politics);

		//----------------bonus panel------------
		JPanel bonusPanel=new BonusPanel().createBonusPanel(b);
		bonusPanel.setName("bonus");
		bonusPanel.setBackground(new Color(193,197,192));
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(bonusPanel, lim);
		southPanel.add(bonusPanel);

		return southPanel;
	}

	/**
	 * add the background color
	 * @param c, the component to add color
	 * @param i
	 */
	private void addBackground(Component c, int i){
		if(i==0)
			c.setBackground(new Color(204, 255, 255));
		if(i==1)
			c.setBackground(new Color(204, 255, 204));
		if(i==2)
			c.setBackground(new Color(188, 144, 101));
	}

}
