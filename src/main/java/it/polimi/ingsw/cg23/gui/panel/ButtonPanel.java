package it.polimi.ingsw.cg23.gui.panel;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * create the button bunel (main action, secondary action, info button)
 * @author viga94_
 *
 */
public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1351973823788889664L;

	/**
	 * Create the panel.
	 */
	public ButtonPanel() {
		/**
		 * empty costructor
		 */
	}
	
	/**
	 * create the button panel
	 * @param textArea, to read
	 * @param write, to write
	 * @return the panel
	 */
	public JPanel buttonPanel(JTextArea textArea, JTextField write){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 

		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
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
