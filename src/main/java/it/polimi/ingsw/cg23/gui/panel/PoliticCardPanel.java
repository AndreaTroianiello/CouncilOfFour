package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;

public class PoliticCardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7548143133078585999L;

	/**
	 * Create the panel.
	 */
	public PoliticCardPanel() {

	}
	
	public JPanel createCard(Player p){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 

		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		for(int i=0; i<p.getHand().size(); i++){
			
			JLabel label=new JLabel(i+"");
			label.setBackground(p.getHand().get(i).getColor());
			label.setOpaque(true);
			label.setPreferredSize(new Dimension(50, 75));
			lim.gridx=0;
			lim.gridy=1;
			layout.setConstraints(label, lim);
			panel.add(label);
		}
		
		return panel;
	}
}
