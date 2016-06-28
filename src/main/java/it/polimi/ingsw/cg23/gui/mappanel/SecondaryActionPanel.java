package it.polimi.ingsw.cg23.gui.mappanel;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.cg23.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.model.action.AdditionalAction;
import it.polimi.ingsw.cg23.server.model.action.HireAssistant;

/**
 * create the secondar
 * @author viga94_
 *
 */
public class SecondaryActionPanel extends JPanel {

	private static final long serialVersionUID = -1693691013491659757L;
	private JTextArea text;
	private transient ControllerGUI controller;

	/**
	 * 
	 * @param text, the area to read on
	 * @param controller
	 */
	public SecondaryActionPanel(JTextArea text,ControllerGUI controller) {
		this.text=text;
		this.controller=controller;
	}

	/**
	 * create the secondary action button
	 * @param text, the text area to write on
	 * @return the panel
	 */
	public JPanel secondAction(){
		JPanel panel=new JPanel();
		panel.setBackground(new Color(123,255,123));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagLayout layout = new GridBagLayout();


		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		panel.setLayout(layout);

		JLabel label=new JLabel("Secondary action");
		label.setName("secondary action");
		label.setToolTipText(label.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(label, lim);
		panel.add(label);

		JButton button1 = new JButton("Action 1");
		button1.setName("Azione secondaria 1");
		button1.setToolTipText(button1.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(button1, lim);
		panel.add(button1);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button1.getName());
				//AZIONI AZIONE 1
				controller.updateController(new HireAssistant());
			}
		});

		JButton button2 = new JButton("Action 2");
		button2.setName("Azione secondaria 2");
		button2.setToolTipText(button2.getName());
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(button2, lim);
		panel.add(button2);//aggiunta bottone al layer panel
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button2.getName());
				//AZIONI AZIONE 2
				controller.updateController(new AdditionalAction());
			}
		});

		JButton button3 = new JButton("Action 3");
		button3.setName("Azione secondaria 3");
		button3.setToolTipText(button3.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
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
		button4.setName("Azione secondaria 4");
		button4.setToolTipText(button4.getName());
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
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
