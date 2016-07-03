package it.polimi.ingsw.cg23.client.gui.eastpanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.client.gui.SelectedElements;
import it.polimi.ingsw.cg23.server.model.action.BuildEmporiumKing;
import it.polimi.ingsw.cg23.server.model.action.BuildEmporiumTile;
import it.polimi.ingsw.cg23.server.model.action.BuyPermitTile;
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillor;


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
	private transient ControllerGUI controller;
	private transient Logger logger;

	/**
	 * @param controller, the controller
	 * @param text, the area to read on
	 */
	public MainActionPanel(JTextArea text, ControllerGUI controller) {
		this.text=text;
		this.controller=controller;

		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

		init();
	}

	/**
	 * create the main action button
	 * @return jpanel with the main action
	 */
	private void init(){
		GridBagLayout layout = new GridBagLayout();
		setBackground(new Color(245,123,123));
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(layout);
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
		add(label);

		JButton button1 = new JButton("BuildTile");
		button1.setName("Build emporium by tile.");
		button1.setToolTipText(button1.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button1, lim);
		add(button1);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button1.getName());
				//AZIONI AZIONE 1
				SelectedElements elements=controller.getSelectedElements();
				try{
					controller.updateController(new BuildEmporiumTile(elements.getTile(), elements.getCity()));
					elements.resetAll();
				}catch(NullPointerException ex){
					JOptionPane.showMessageDialog(null, "Elements unselected.", "INFO", JOptionPane.INFORMATION_MESSAGE);
					controller.getSelectedElements().resetAll();
					logger.error("Error!", ex);
				}
			}
		});

		JButton button2 = new JButton("BuildKing");
		button2.setName("Build emporium by king.");
		button2.setToolTipText(button2.getName());
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button2, lim);
		add(button2);//aggiunta bottone al layer panel
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button2.getName());
				//AZIONI AZIONE 2
				SelectedElements elements=controller.getSelectedElements();
				try{
					controller.updateController(new BuildEmporiumKing(elements.getCards(), elements.getCity()));
					elements.resetAll();
				}catch(NullPointerException ex){
					JOptionPane.showMessageDialog(null, "Elements unselected.", "INFO", JOptionPane.INFORMATION_MESSAGE);
					elements.resetAll();
					logger.error("Error!", ex);
				}
			}
		});

		JButton button3 = new JButton("BuyTile");
		button3.setName("Buy a business permit tile.");
		button3.setToolTipText(button3.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button3, lim);
		add(button3);//aggiunta bottone al layer panel
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button3.getName());
				//AZIONI AZIONE 3
				SelectedElements elements=controller.getSelectedElements();
				try{
					controller.updateController(new BuyPermitTile(elements.getCards(),
							controller.getModel().findRegion(elements.getTile().getZone()),
							elements.getTile()));
					elements.resetAll();
				}catch(NullPointerException ex){
					JOptionPane.showMessageDialog(null, "Elements unselected.", "INFO", JOptionPane.INFORMATION_MESSAGE);
					elements.resetAll();
					logger.error("Error!", ex);
				}
			}
		});

		JButton button4 = new JButton("Elect");
		button4.setName("Elect a councillor.");
		button4.setToolTipText(button4.getName());
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button4, lim);
		add(button4);//aggiunta bottone al layer panel
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append("\n"+button4.getName());
				//AZIONI AZIONE 4
				SelectedElements elements=controller.getSelectedElements();
				boolean king=elements.getRegion()==null;
				try{
					controller.updateController(new ElectCouncillor(elements.getCouncillor(),elements.getRegion(),king));
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
