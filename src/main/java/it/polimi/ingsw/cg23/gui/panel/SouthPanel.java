package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import it.polimi.ingsw.cg23.server.controller.Avvio;
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

	/**
	 * Create the panel.
	 */
	public SouthPanel() {
		this.ccp=new CostructionCardPanel();
	}
	
	/**
	 * create the south panel
	 * @param s, the avvio
	 * @return a jpanel with the south panel created
	 */
	public JPanel setSouthPanel(Avvio s){
		JPanel southPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		southPanel.setLayout(layout);
		
		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill = GridBagConstraints.HORIZONTAL;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		List<Region> reg=s.getBoard().getRegions();
		for(int i=0; i<reg.size(); i++){//scorre le regioni
			//----------carte permesso di costruzione----------
			
			JPanel costruzione=ccp.getShowCostructionCard(reg.get(i));
			costruzione.setName("costruzione "+reg.get(i).getName());
			costruzione.setBackground(new Color(206, 177, 129));
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=0;
			layout.setConstraints(costruzione, lim);
			southPanel.add(costruzione);
		}
		
		for(int i=0; i<reg.size(); i++){
			JPanel balcone3=new CouncilPanel().balcone(reg.get(i));
			balcone3.setBackground(new Color(116, 184, 181));
			balcone3.setName("balcone "+reg.get(i).getName());
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=0;
			layout.setConstraints(balcone3, lim);
			southPanel.add(balcone3);
		}
		
		/*
		//----------balcone 1----------
		JPanel balcone1=new CouncilPanel().balcone(s.getBoard().getRegions().get(0));
		balcone1.setBackground(new Color(116, 184, 181));
		balcone1.setName("balcone costa");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=0;
		layout.setConstraints(balcone1, lim);
		southPanel.add(balcone1);

		//----------balcone 2----------
		JPanel balcone2=new CouncilPanel().balcone(s.getBoard().getRegions().get(1));
		balcone2.setName("balcone collina");
		balcone2.setBackground(new Color(116, 184, 181));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=0;
		layout.setConstraints(balcone2, lim);
		southPanel.add(balcone2);
		
		//----------balcone 3----------
		JPanel balcone3=new CouncilPanel().balcone(s.getBoard().getRegions().get(2));
		balcone3.setBackground(new Color(116, 184, 181));
		balcone3.setName("balcone montagna");
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=0;
		layout.setConstraints(balcone3, lim);
		southPanel.add(balcone3);

*/
		//----------------nobility track------------
		JTextPane l4=new JTextPane();
		l4.setEditable(false);
		l4.setName("Nobility panel");
		l4.setText("nobility track");
		Component c8 = new JScrollPane(l4);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		
		layout.setConstraints(c8, lim);
		southPanel.add(c8);
		
		//----------------carte politiche------------
		Player p=new Player("io", new NobilityTrack(20));
		p.getHand().add(new PoliticCard(new Color(145,123,241), false));
		JPanel politics=new PoliticCardPanel().createCard(p);
		politics.setBackground(new Color(116, 184, 255));
		politics.setName("Carte politiche");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=0;
		layout.setConstraints(politics, lim);
		southPanel.add(politics);
		
		return southPanel;
	}
}
