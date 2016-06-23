package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.cg23.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.model.action.HireAssistant;

/**
 * create the main action button of the gui
 * @author viga94_
 *
 */
public class MainActionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5715872370987341629L;
	private JTextArea text;
	private ControllerGUI controller;
	/**
	 * Create the panel.
	 */
	public MainActionPanel(JTextArea text,ControllerGUI controller) {
		this.text=text;
		this.controller=controller;
	}

	/**
	 * create the main action button
	 * @param text, the text area to view
	 * @return jpanel with the main action
	 */
	public JPanel mainAction(){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setBackground(new Color(245,123,123));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 

		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		JLabel label=new JLabel("Main action");
		label.setName("azioni principali");
		label.setToolTipText(label.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(label, lim);
		panel.add(label);

		JButton button1 = new JButton("Action 1");
		button1.setName("Azione principale 1");
		button1.setToolTipText(button1.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button1, lim);
		panel.add(button1);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button1.getName());
				//AZIONI AZIONE 1
			}
		});

		JButton button2 = new JButton("Action 2");
		button2.setName("Azione principale 2");
		button2.setToolTipText(button2.getName());
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button2, lim);
		panel.add(button2);//aggiunta bottone al layer panel
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button2.getName());
				//AZIONI AZIONE 2
			}
		});

		JButton button3 = new JButton("Action 3");
		button3.setName("Azione principale 3");
		button3.setToolTipText(button3.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button3, lim);
		panel.add(button3);//aggiunta bottone al layer panel
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button3.getName());
				//AZIONI AZIONE 3
			}
		});

		JButton button4 = new JButton("Action 4");
		button4.setName("Azione principale 4");
		button4.setToolTipText(button4.getName());
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button4, lim);
		panel.add(button4);//aggiunta bottone al layer panel
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button4.getName());
				//AZIONI AZIONE 4
			}
		});

		return panel;
	}
	
	/*public void update(){
		this.repaint();
	}*/
}
