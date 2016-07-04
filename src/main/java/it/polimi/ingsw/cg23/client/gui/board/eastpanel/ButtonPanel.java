package it.polimi.ingsw.cg23.client.gui.board.eastpanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;

/**
 * create the button bunel (main action, secondary action, info button)
 * 
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
	 * @param textArea,
	 *            the area to read on
	 * @param write,
	 *            the area to write on
	 * @param controller
	 */
	public ButtonPanel(JTextArea textArea, ControllerGUI controller) {
		this.mainActionPanel = new MainActionPanel(textArea, controller);
		this.secActionPanel = new SecondaryActionPanel(textArea, controller);
		this.infoPanel = new InfoPanel(textArea, controller);

		init();
	}

	/**
	 * create the button panel
	 */
	private void init() {
		GridBagLayout layout = new GridBagLayout();// creo un nuovo layout
		setLayout(layout);// setto il layout al pannello

		GridBagConstraints lim = new GridBagConstraints();// settaggio del
															// layout
		lim.fill = GridBagConstraints.BOTH;// grandezza componenti nei riquadri
											// (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;// posizione componenti nei
												// riquadri

		// ----------azioni principali----------
		mainActionPanel.setName("label azioni principali");// pannello azioni
															// principali
		lim.gridx = 0;// posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;// spazio occupato
		lim.weightx = 1;
		lim.gridheight = 1;// grandezza del riquadro
		lim.gridwidth = 1;
		layout.setConstraints(mainActionPanel, lim);// applicazione del layout
													// al pannello azioni
													// principali
		add(mainActionPanel);// aggiunta del pannello azioni principali al
								// pannello

		// ----------azioni secondarie----------
		secActionPanel.setName("label azioni secondarie");// pannello azioni
															// secondarie
		lim.gridx = 1;// posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;// spazio occupato
		lim.weightx = 1;
		lim.gridheight = 1;// grandezza del riquadro
		lim.gridwidth = 1;
		layout.setConstraints(secActionPanel, lim);// applicazione del layout al
													// pannello azioni
													// secondarie
		add(secActionPanel);// aggiunta del pannello azioni secondarie al
							// pannello

		// ----------info panel----------
		infoPanel.setName("label info panel");// pannello azioni generali
		lim.gridx = 0;// posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;// spazio occupato
		lim.weightx = 1;
		lim.gridheight = 1;// grandezza del riquadro
		lim.gridwidth = 2;
		layout.setConstraints(infoPanel, lim);// applicazione layout al pannello
												// azioni generali
		add(infoPanel);// aggiunta del pannello azioni generali al panello

		setBackground(new Color(123, 123, 123));// setto lo sfondo del pannello
	}
}
