package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		//----------etichetta nome----------
		JLabel label0=new JLabel("Carte politiche");
		lim.gridx=1;
		lim.gridy=0;
		layout.setConstraints(label0, lim);
		panel.add(label0);
		
		for(int i=0; i<p.getHand().size(); i++){//scorre le carte politiche
			//----------carta politica----------
			JLabel label1=new JLabel();
			label1.setName(i+" politiche");
			label1.setBackground(p.getHand().get(i).getColor());
			label1.setOpaque(true);
			label1.setPreferredSize(new Dimension(50, 75));
			lim.gridx=i*2;
			lim.gridy=1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(label1, lim);
			panel.add(label1);
			
			//----------etichetta spazio----------
			//Aggiunge lo spazio dopo aver messo la carta politica
			JLabel label2=new JLabel();
			label2.setName(i+" spazio");
			label2.setBackground(new Color(0, 0, 0));
			label2.setOpaque(true);
			label2.setPreferredSize(new Dimension(5, 75));
			lim.gridx=i*2+1;
			lim.gridy=1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(label2, lim);
			panel.add(label2);
		}
		
		return panel;
	}
}
