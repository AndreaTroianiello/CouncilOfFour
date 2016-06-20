package it.polimi.ingsw.cg23.gui.panel;

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
import javax.swing.JTextField;

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
	 * @param textArea, the area to view
	 * @param write, the area to write (chat)
	 * @return, the info panel
	 */
	public JPanel infoAction(JTextArea textArea, JTextField write){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();

		panel.setLayout(layout);
		panel.setBackground(new Color(123,123,123));

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		JLabel label=new JLabel("Info");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(label, lim);
		panel.add(label);

		JButton button1 = new JButton("Exit");
		button1.setName("Uscita");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button1, lim);
		panel.add(button1);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("\nTentativo di uscita");
				int reply1 = JOptionPane.showConfirmDialog(null, "Vuoi veramente uscire?", "Uscita", JOptionPane.YES_NO_OPTION);

				if(reply1 == JOptionPane.YES_OPTION){
					int reply2 = JOptionPane.showConfirmDialog(null, "Sei veramente sicuro di volre andare?", "Conferma uscita", JOptionPane.YES_NO_OPTION);

					if(reply2 == JOptionPane.YES_OPTION){
						textArea.append("\nUscita");
						setVisible(false);
					}		              
				}				
			}
		});

		JButton button2 = new JButton("Clear");
		button2.setName("Clear");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
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

		JButton button3=new JButton("Invia");
		button3.setName("Invia");
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(button3, lim); //Associazione
		panel.add(button3); //Inserimento
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(write.getText())){
					JOptionPane.showMessageDialog(null, "Devi scrivere del testo!");
				}else{
					textArea.append("\ntesto inviato: "+write.getText());
					write.setText("");
				}
			}
		});
		return panel;
	}
}
