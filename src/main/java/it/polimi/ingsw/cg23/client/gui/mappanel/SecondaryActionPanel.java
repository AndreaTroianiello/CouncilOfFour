package it.polimi.ingsw.cg23.client.gui.mappanel;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.client.gui.SelectedElements;
import it.polimi.ingsw.cg23.server.model.action.AdditionalAction;
import it.polimi.ingsw.cg23.server.model.action.ChangeBusinessPermit;
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillorAssistant;
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
	private transient Logger logger;

	/**
	 * 
	 * @param text, the area to read on
	 * @param controller
	 */
	public SecondaryActionPanel(JTextArea text,ControllerGUI controller) {
		this.text=text;
		this.controller=controller;

		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

		init();
	}

	/**
	 * create the secondary action button
	 * @param text, the text area to write on
	 * @return the panel
	 */
	private void init(){
		setBackground(new Color(123,255,123));
		setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagLayout layout = new GridBagLayout();


		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		setLayout(layout);

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
		add(label);

		JButton button1 = new JButton("Hire");
		button1.setName("Hire Assistants");
		button1.setToolTipText(button1.getName());

		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;

		layout.setConstraints(button1, lim);
		add(button1);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button1.getName());
				//AZIONI AZIONE 1
				controller.updateController(new HireAssistant());
			}
		});

		JButton button2 = new JButton("Addictional");
		button2.setName("Addictional Action");
		button2.setToolTipText(button2.getName());

		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;

		layout.setConstraints(button2, lim);
		add(button2);//aggiunta bottone al layer panel
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button2.getName());
				//AZIONI AZIONE 2
				controller.updateController(new AdditionalAction());
			}
		});

		JButton button3 = new JButton("Change");
		button3.setName("Change");
		button3.setToolTipText(button3.getName());

		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;

		layout.setConstraints(button3, lim);
		add(button3);//aggiunta bottone al layer panel

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button3.getName());
				//AZIONI AZIONE 3
				try{
					controller.updateController(new ChangeBusinessPermit(controller.getSelectedElements().getRegion()));
					controller.getSelectedElements().resetAll();
				}catch(NullPointerException ex){
					JOptionPane.showMessageDialog(null, "Region unselected.", "INFO", JOptionPane.INFORMATION_MESSAGE);
					controller.getSelectedElements().resetAll();
					logger.error("Error!", ex);
				}
			}
		});

		JButton button4 = new JButton("Elect");
		button4.setName("Elect a councillor");
		button4.setToolTipText(button4.getName());

		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;

		layout.setConstraints(button4, lim);
		add(button4);//aggiunta bottone al layer panel

		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button4.getName());
				SelectedElements elements=controller.getSelectedElements();
				boolean king=elements.getRegion()==null;
				//AZIONI AZIONE 4
				try{
					controller.updateController(new ElectCouncillorAssistant(elements.getCouncillor(),elements.getRegion(),king));
					elements.resetAll();
				}catch(NullPointerException ex){
					JOptionPane.showMessageDialog(null, "Elements unselected.", "INFO", JOptionPane.INFORMATION_MESSAGE);
					elements.resetAll();
					logger.error("Error!", ex);
				}
			}
		});
	}
}
