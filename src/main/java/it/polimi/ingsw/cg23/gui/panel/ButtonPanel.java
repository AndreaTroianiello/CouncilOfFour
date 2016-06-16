package it.polimi.ingsw.cg23.gui.panel;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1351973823788889664L;

	/**
	 * Create the panel.
	 */
	public ButtonPanel() {

	}
	
	public JPanel buttonPanel(JTextArea textArea, JTextArea write){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 

		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		//----------azioni principali----------
		JPanel mainActionPanel=new MainActionPanel().mainAction(textArea);//richiamo il pannello azioni principali
		mainActionPanel.setName("label azioni principali");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(mainActionPanel, lim);
		panel.add(mainActionPanel);

		//----------azioni secondarie----------
		JPanel secActionPanel=new SecondaryActionPanel().secondAction(textArea);//richiamo il pannello azioni secondarie
		mainActionPanel.setName("label azioni secondarie");
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(secActionPanel, lim);
		panel.add(secActionPanel);

		//----------info panel----------
		JPanel infoPanel=new InfoPanel().infoAction(textArea, write);//richiamo il pannello info
		infoPanel.setName("label info panel");
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(infoPanel, lim);
		panel.add(infoPanel);
		
		return panel;
	}
}
