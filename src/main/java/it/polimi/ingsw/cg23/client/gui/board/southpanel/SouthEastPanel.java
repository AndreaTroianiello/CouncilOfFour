package it.polimi.ingsw.cg23.client.gui.board.southpanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.client.gui.board.BonusFrame;
import it.polimi.ingsw.cg23.client.gui.board.CardFrame;
import it.polimi.ingsw.cg23.client.gui.board.PlayerStatic;

/**
 * create the south east panel
 * @author viga94_
 *
 */
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
	 * @param controller, the controller
	 * @param loggerArea the area to read on
	 */
	public SouthEastPanel(ControllerGUI controller, JTextArea loggerArea) {
		this.cp=new CouncilPanel(loggerArea,controller);
		this.stat=new PlayerStatic();
		this.model=controller.getModel();
		this.bonusf=new BonusFrame(model.getBoard(), loggerArea);
		this.cf=new CardFrame(loggerArea,controller);
		
		init();
	}

	/**
	 * inizializate the componente
	 */
	private void init(){
		GridBagLayout layout = new GridBagLayout();//layout
		setLayout(layout);//applicazione del layout al pannello

		GridBagConstraints lim = new GridBagConstraints(); //impostazioni layout
		lim.fill = GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------------consiglieri del re------------
		JPanel kingCouncillors=cp.kingBalcone(model.getBoard().getKing());
		kingCouncillors.setName("consiglieri re");
		kingCouncillors.setOpaque(false);
		
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		
		layout.setConstraints(kingCouncillors, lim);
		add(kingCouncillors);

		//----------------statistiche player------------
		JButton punteggi=new JButton("Stats");
		punteggi.setForeground(new Color(255, 215, 0));
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
			public void mouseClicked(MouseEvent e) {
				stat.setVisible(true);
			}
		});

		//----------------bonus panel------------
		JButton bonus=new JButton("Bonus");
		bonus.setForeground(new Color(255, 215, 0));
		bonus.setOpaque(false);
		bonus.setContentAreaFilled(false);//contenuto bottone trasparente
		bonus.setBorderPainted(false);//bordi bottone trasparente
		bonus.setToolTipText("View the game bonus");
		
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(bonus, lim);
		add(bonus);
		actionBonus(bonus);

		//----------------costruction card usate------------
		JButton card=new JButton("Tiles");
		card.setForeground(new Color(255, 215, 0));
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
		actionCard(card);

		//----------------consiglieri dipsonibili------------
		JPanel consiglieriColor=cp.colurCouncillors(model.getBoard());
		consiglieriColor.setOpaque(false);
		
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		
		layout.setConstraints(consiglieriColor, lim);
		add(consiglieriColor);
	}

	/**
	 * action used costruction card
	 * @param card, the card button
	 */
	private void actionCard(JButton card){
		card.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mousePressed(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mouseEntered(MouseEvent e) {
				cf.createCard(model.getPlayer());
				cf.setVisible(true);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cf.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {/**empty, not erasable*/}
			
		});
	}

	/**
	 * bonus panel action
	 * @param bonus, the bonus button
	 */
	private void actionBonus(JButton bonus){
		bonus.addMouseListener(new MouseListener() {
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
			public void mouseClicked(MouseEvent e) {
				bonusf.setVisible(true);
			}
		});
	}
}
