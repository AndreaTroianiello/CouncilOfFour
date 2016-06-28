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
import it.polimi.ingsw.cg23.gui.BonusFrame;
import it.polimi.ingsw.cg23.gui.PlayerStatic;

public class SouthEastPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8844330258965324138L;
	//private BonusPanel bp;
	private BonusFrame bonusf;
	private CouncilPanel cp;
	private PlayerStatic stat;
	private transient ClientModel model;

	/**
	 * Create the panel.
	 */
	public SouthEastPanel(ClientModel model, JTextArea loggerArea) {
		this.cp=new CouncilPanel();
		//this.bp=new BonusPanel(model.getModel(), loggerArea);
		this.stat=new PlayerStatic();
		this.model=model;
		this.bonusf=new BonusFrame(model.getModel(), loggerArea);
	}

	public JPanel createSouthEast(){
		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill = GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri


		//----------------consiglieri del re------------
		JPanel kingCouncillors=cp.kingbalcone(model.getModel().getKing());
		kingCouncillors.setName("consiglieri re");
		kingCouncillors.setBackground(new Color(154, 205, 50));
		kingCouncillors.setOpaque(true);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(kingCouncillors, lim);
		panel.add(kingCouncillors);

		//----------------statistiche player------------
		JButton punteggi=new JButton("Points");
		punteggi.setBackground(new Color(154, 205, 50));
		punteggi.setOpaque(true);
		punteggi.setToolTipText("View your points");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(punteggi, lim);
		panel.add(punteggi);
		punteggi.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mousePressed(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mouseExited(MouseEvent e) {
				stat.removeAll();
				stat.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				stat.createGrid(model.getPlayer());
				stat.setVisible(true);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		//----------------bonus panel------------
		JButton bonusPanel=new JButton("Bonus");
		bonusPanel.setBackground(new Color(154, 205, 50));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(bonusPanel, lim);
		panel.add(bonusPanel);
		bonusPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mousePressed(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mouseExited(MouseEvent e) {
				bonusf.removeAll();
				bonusf.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bonusf.createBonusPanel();
				bonusf.setVisible(true);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		//----------------consiglieri dipsonibilit------------
		JPanel consiglieriColor=new JPanel();
		consiglieriColor.setName("bonus");
		consiglieriColor.setToolTipText("Available bonus");
		consiglieriColor.setBackground(new Color(154, 1, 50));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(consiglieriColor, lim);
		panel.add(consiglieriColor);

		return panel;
	}
}
