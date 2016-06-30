package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.controller.action.EndTurn;

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
	private JTextArea loggerArea;
	private transient ControllerGUI controller;


	/**
	 * 
	 * @param textArea, the area to read on
	 * @param controller
	 */
	public InfoPanel(JTextArea loggerArea, ControllerGUI controller) {
		this.loggerArea=loggerArea;
		this.controller=controller;
		init();
	}

	/**
	 * create the info action
	 * @return, the info panel
	 */
	private void init(){
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		setOpaque(false);

		GridBagConstraints lim = new GridBagConstraints();
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		JLabel label=new JLabel("Info");
		label.setName("info label");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=4;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)

		layout.setConstraints(label, lim);
		add(label);

		JButton button1 = new JButton("Exit");
		button1.setName("Uscita");
		button1.setToolTipText("Exit from the game");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button1, lim);
		add(button1);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int reply1 = JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
				if(reply1 == JOptionPane.YES_OPTION){
					controller.closeAll();
				}				
			}
		});

		JButton button2 = new JButton("Clear log");
		button2.setName("Clearl");
		button1.setToolTipText("Cancella la log area");
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(button2, lim);
		add(button2);//aggiunta bottone al layer panel
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggerArea.append(button2.getName());
				//AZIONI AZIONE clear
				loggerArea.setText("Logger cancellata");

			}
		});

		JButton button3 = new JButton("Clear selec");
		button3.setName("clear selected");
		button3.setToolTipText("Clear the selected");
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button3, lim);
		add(button3);//aggiunta bottone al layer panel
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggerArea.append("\n"+button3.getName());			
			}
		});
		
		JButton button4 = new JButton("End Turn");
		button4.setName("End Turn");
		button4.setToolTipText("Finisci il turno");
		lim.gridx = 3;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(button4, lim);
		add(button4);//aggiunta bottone al layer panel
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggerArea.append("\nEnd Turn");
				//azione fine turno
				controller.updateController(new EndTurn());
			}
		});
	}

}
