package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.utility.ColorManager;

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
	private transient ColorManager cm;
	private NobilityTrackPanel ntp;

	/**
	 * Create the panel.
	 */
	public SouthPanel() {
		this.ntp=new NobilityTrackPanel();
		this.ccp=new CostructionCardPanel();
		this.cp=new CouncilPanel();
		this.cm=new ColorManager();

	}

	/**
	 * create the south panel
	 * @param b, the board
	 * @param loggerArea, the area to write on
	 * @return a jpanel with the south panel created
	 */
	public JPanel setSouthPanel(Board b, JTextArea loggerArea){
		JPanel southPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		southPanel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill = GridBagConstraints.HORIZONTAL;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		List<Region> reg=b.getRegions();
		for(int i=0; i<reg.size(); i++){//scorre le regioni-> aggiunge le carte permesso

			//----------carte permesso di costruzione----------
			JPanel costruzione=ccp.getShowCostructionCard(reg.get(i), loggerArea);
			costruzione.setName("costruzione "+reg.get(i).getName());
			costruzione.setBackground(new Color(90, 255, 55));
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

			//----------consiglieri regione----------
			JPanel balcone=cp.balcone(reg.get(i));
			balcone.setBackground(new Color(90, 255, 55));
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
		JPanel panelNobility=ntp.createNobility(b.getNobilityTrack(), loggerArea);
		panelNobility.setName("Nobility panel");
		JScrollPane scrollNobility=new  JScrollPane(panelNobility);
		scrollNobility.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollNobility.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		//scrollNobility.setPreferredSize(new Dimension(WIDTH, 75));
		lim.fill = GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.WEST;//posizione componenti nei riquadri
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(scrollNobility, lim);
		southPanel.add(scrollNobility);

		//----------------punteggi------------
		JButton button=new JButton();
		button.setText("bottone vuoto");
		lim.fill = GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(button, lim);
		southPanel.add(button);

		//----------carte politiche------------
		Player p=new Player("io", new NobilityTrack(20));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(null, true));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(cm.getColor("Blue"), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(cm.getColor("Brown"), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(cm.getColor("Pink"), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(cm.getColor("Orange"), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(cm.getColor("Violet"), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(cm.getColor("White"), false));//PROVA->provvisorio

		JPanel politics=new PoliticCardPanel().createCard(p, loggerArea);
		politics.setName("Carte politiche");
		politics.setBackground(new Color(193,197,192));
		lim.fill = GridBagConstraints.BOTH;
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(politics, lim);
		southPanel.add(politics);

		//----------------bonus panel------------
		JPanel bonusPanel=new BonusPanel().createBonusPanel(b, loggerArea);
		bonusPanel.setName("bonus");
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(bonusPanel, lim);
		
		southPanel.add(bonusPanel);

		return southPanel;
	}
}
