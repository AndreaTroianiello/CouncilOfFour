package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class LoggerPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public LoggerPanel() {

	}
	
	public JPanel loggerPanel(){
		JPanel logger=new JPanel();
		
		GridBagLayout layout = new GridBagLayout();
		logger.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		JTextPane loggerArea=new JTextPane();
		loggerArea.setName("textara");
		loggerArea.setText("Benvenuti a Cof");
		loggerArea.setEditable(false);
		Component scrollLogger1 = new JScrollPane(loggerArea);
		scrollLogger1.setName("scrollPane text area logger");
		lim.gridx = 3;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=3;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(scrollLogger1, lim); //Associazione
		logger.add(scrollLogger1); //Inserimento*/
		
		JTextArea writeArea=new JTextArea(10, 10);
		writeArea.setName("write area");
		Component scrollLogger2 = new JScrollPane(writeArea);
		scrollLogger2.setName("scrollPane write area");
		lim.gridx = 3;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=3;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(scrollLogger2, lim); //Associazione
		logger.add(scrollLogger2); //Inserimento
		
		return logger;
	}

}
