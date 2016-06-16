package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * create the logger panel
 * @author viga94_
 *
 */
public class LoggerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7843525022282542659L;
	/**
	 * Create the panel.
	 */
	public LoggerPanel() {
		/**
		 * empty costructor
		 */
	}
	
	/**
	 * create the logger panel
	 * @param loggerArea, the area to view
	 * @param write, the area to write on
	 * @return the panel
	 */
	public JPanel loggerPanel(JTextArea loggerArea, JTextArea write){
		JPanel logger=new JPanel();
		
		GridBagLayout layout = new GridBagLayout();
		logger.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		//----------logger area----------
		loggerArea.setName("textara");
		loggerArea.setText("Benvenuti a Cof");
		loggerArea.setEditable(false);
		Component scrollLogger1 = new JScrollPane(loggerArea);
		scrollLogger1.setName("scrollPane text area logger");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 2;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 2;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(scrollLogger1, lim); //Associazione
		logger.add(scrollLogger1); //Inserimento*/
		
		//----------write area----------
		write.setEditable(true);
		write.setName("write area");
		Component scrollLogger2 = new JScrollPane(write);
		scrollLogger2.setName("scrollPane write area");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 2;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 2;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(scrollLogger2, lim); //Associazione
		logger.add(scrollLogger2); //Inserimento
		
		
		//----------button----------
		JPanel panel=new ButtonPanel().buttonPanel(loggerArea, write);
		panel.setName("button panel");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 4;
		//lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		//lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(panel, lim); //Associazione
		logger.add(panel); //Inserimento
		return logger;
	}

}
