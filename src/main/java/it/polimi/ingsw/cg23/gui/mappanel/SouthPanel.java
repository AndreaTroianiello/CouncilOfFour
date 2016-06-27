package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.gui.PlayerStatic;
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
	private PlayerStatic stat;

	/**
	 * 
	 * @param model
	 * @param loggerArea
	 */
	public SouthPanel(ClientModel model, JTextArea loggerArea) {
		this.ntp=new NobilityTrackPanel(model.getModel().getNobilityTrack(), loggerArea);
		this.ccp=new CostructionCardPanel(loggerArea);
		this.cp=new CouncilPanel();
		this.pcp=new PoliticCardPanel(model.getPlayer(), loggerArea);
		this.bp=new BonusPanel(model.getModel(), loggerArea);
		this.model=model;
		this.stat=new PlayerStatic();
	}

	/**
	 * create the south panel
	 * @return a jpanel with the south panel created
	 */
	public JPanel setSouthPanel(){
		JPanel southPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		southPanel.setLayout(layout);

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
			southPanel.add(costruzione);
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
			southPanel.add(balcone);
		}

		//----------------nobility track------------
		JPanel panelNobility=ntp.createNobility();
		panelNobility.setName("Nobility panel");
		panelNobility.setBackground(new Color(154, 205, 50));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(panelNobility, lim);
		southPanel.add(panelNobility);


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
		southPanel.add(kingCouncillors);

		//----------------statistiche player------------
		JButton punteggi=new JButton("stat");
		punteggi.setBackground(new Color(123, 104, 238));
		punteggi.setOpaque(true);
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(punteggi, lim);
		southPanel.add(punteggi);
		punteggi.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mousePressed(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mouseExited(MouseEvent e) {
				stat.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				stat.createGrid(model.getPlayer());
				stat.setVisible(true);}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		//----------carte politiche------------
		JPanel politics=pcp.createCard();
		politics.setName("Carte politiche");
		politics.setBackground(new Color(154, 205, 50));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(politics, lim);
		southPanel.add(politics);

		//----------my carte costruzione------------
		JPanel avaiableCostrucion=ccp.myCostructionCard(model.getPlayer());
		avaiableCostrucion.setName("costruzione disponibile");
		avaiableCostrucion.setBackground(new Color(154, 205, 50));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(avaiableCostrucion, lim);
		southPanel.add(avaiableCostrucion);

		//----------------bonus panel------------
		JPanel bonusPanel=bp.createBonusPanel();
		bonusPanel.setName("bonus");
		bonusPanel.setBackground(new Color(123, 104, 238));
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(bonusPanel, lim);
		southPanel.add(bonusPanel);

		return southPanel;
	}

	public void update(){
		ntp.createNobility();
		pcp.createCard();
		bp.createBonusPanel();
		this.repaint();
	}
}
