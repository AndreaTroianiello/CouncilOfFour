package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.gui.mapframe.BonusFrame;
import it.polimi.ingsw.cg23.gui.mapframe.CardFrame;
import it.polimi.ingsw.cg23.gui.mapframe.PlayerStatic;

public class SouthEastPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8844330258965324138L;
	private BonusFrame bonusf;
	private transient CouncilPanel cp;
	private PlayerStatic stat;
	private transient ClientModel model;
	private CardFrame cf;

	/**
	 * Create the panel.
	 */
	public SouthEastPanel(ClientModel model, JTextArea loggerArea) {
		this.cp=new CouncilPanel(loggerArea);
		this.stat=new PlayerStatic();
		this.model=model;
		this.bonusf=new BonusFrame(model.getModel(), loggerArea);
		this.cf=new CardFrame(loggerArea);
		init();
	}

	private void init(){
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill = GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------------consiglieri del re------------
		JPanel kingCouncillors=cp.kingBalcone(model.getModel().getKing());
		kingCouncillors.setName("consiglieri re");
		kingCouncillors.setBackground(new Color(154, 205, 50));
		kingCouncillors.setOpaque(true);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		layout.setConstraints(kingCouncillors, lim);
		add(kingCouncillors);

		//----------------statistiche player------------
		JButton punteggi=new JButton("Points");
		punteggi.setBackground(new Color(154, 205, 50));
		punteggi.setOpaque(false);
		punteggi.setContentAreaFilled(false);//contenuto bottone trasparente
		punteggi.setBorderPainted(false);//bordi bottone trasparente
		punteggi.setToolTipText("View your points");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(punteggi, lim);
		add(punteggi);
		punteggi.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mousePressed(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mouseExited(MouseEvent e) {
				stat.setVisible(false);
				stat.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				stat.createGrid(model.getPlayer());
				stat.setVisible(true);
			}
			@Override
			public void mouseClicked(MouseEvent e) {/**empty, not erasable*/}
		});

		//----------------bonus panel------------
		JButton bonusPanel=new JButton("Bonus");
		bonusPanel.setOpaque(false);
		bonusPanel.setContentAreaFilled(false);//contenuto bottone trasparente
		bonusPanel.setBorderPainted(false);//bordi bottone trasparente
		bonusPanel.setBackground(new Color(154, 205, 50));
		bonusPanel.setToolTipText("View the game bonus");
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(bonusPanel, lim);
		add(bonusPanel);
		bonusPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mousePressed(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mouseExited(MouseEvent e) {
				bonusf.dispose();
				bonusf.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bonusf.createBonusPanel();
				bonusf.setVisible(true);
			}
			@Override
			public void mouseClicked(MouseEvent e) {/**empty, not erasable*/}
		});

		//----------------costruction card usate------------
		JButton card=new JButton("Card");
		card.setBackground(new Color(154, 205, 50));
		card.setOpaque(false);
		card.setContentAreaFilled(false);//contenuto bottone trasparente
		card.setBorderPainted(false);//bordi bottone trasparente
		card.setToolTipText("View your old Costruction card");
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(card, lim);
		add(card);
		card.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mousePressed(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mouseExited(MouseEvent e) {
				cf.dispose();
				cf.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				cf.createCard(model.getPlayer());
				cf.setVisible(true);
			}
			@Override
			public void mouseClicked(MouseEvent e) {/**empty, not erasable*/}
		});
		
		//----------------consiglieri dipsonibili------------
		JPanel consiglieriColor=cp.colurCouncillors(model.getModel());
		consiglieriColor.setBackground(new Color(154, 205, 50));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		layout.setConstraints(consiglieriColor, lim);
		add(consiglieriColor);

	}
}