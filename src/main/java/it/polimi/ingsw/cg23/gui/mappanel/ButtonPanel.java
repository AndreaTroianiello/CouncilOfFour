package it.polimi.ingsw.cg23.gui.mappanel;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.polimi.ingsw.cg23.gui.ControllerGUI;
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
	private MainActionPanel mainActionPanel;
	private SecondaryActionPanel secActionPanel;
	private InfoPanel infoPanel;

	/**
	 * @param textArea, the area to read on
	 * @param write, the area to write on
	 * @param controller
	 */
	public ButtonPanel(JTextArea textArea, JTextField write, ControllerGUI controller) {
		this.mainActionPanel=new MainActionPanel(textArea,controller);
		this.secActionPanel=new SecondaryActionPanel(textArea,controller);
		this.infoPanel=new InfoPanel(textArea, write,controller);
		init();
	}

	/**
	 * create the button panel
	 */
	private void init(){
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 

		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------azioni principali----------
		mainActionPanel.setName("label azioni principali");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(mainActionPanel, lim);
		add(mainActionPanel);

		//----------azioni secondarie----------
		secActionPanel.setName("label azioni secondarie");
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(secActionPanel, lim);
		add(secActionPanel);

		//----------info panel----------
		infoPanel.setName("label info panel");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(infoPanel, lim);
		add(infoPanel);

		setBackground(new Color(123,123,123));
	}
}
