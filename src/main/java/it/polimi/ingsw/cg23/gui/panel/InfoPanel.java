package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.gui.HelloFrame;

/**
 * create the info button of the gui
 * @author viga94_
 *
 */
public class InfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4105281946320933564L;

	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		/**
		 * empty costructor
		 */
	}
	
	/**
	 * create the info action
	 * @return a panel with the info action
	 */
	public JPanel infoAction(JTextArea textArea){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();

		panel.setLayout(layout);
		panel.setBackground(new Color(123,123,123));

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.SOUTH;//posizione componenti nei riquadri

		JLabel label=new JLabel("Info:");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		layout.setConstraints(label, lim);
		panel.add(label);

		JButton button1 = new JButton("Exit");
		button1.setName("Uscita");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button1, lim);
		panel.add(button1);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append(button1.getName());
				//AZIONI AZIONE exit
				new HelloFrame().setVisible(true);
				setVisible(false);
			}
		});

		JButton button2 = new JButton("Clear");
		button2.setName("Uscita");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		layout.setConstraints(button2, lim);
		panel.add(button2);//aggiunta bottone al layer panel
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append(button2.getName());
				//AZIONI AZIONE clear
				textArea.setText("Logger cancellata");

			}
		});
		return panel;
	}
}
