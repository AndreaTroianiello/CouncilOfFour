package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.server.model.Player;

public class MarketPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032301896451894064L;

	/**
	 * Create the panel.
	 */
	public MarketPanel() {

	}

	public JPanel market(Player p, JTextArea loggerArea){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints();
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------etichetta market----------
		JLabel marketLabel=new JLabel("Market");
		marketLabel.setForeground(new Color(255, 215, 0));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(marketLabel, lim);
		panel.add(marketLabel);

		//----------tabella----------
		JTable tabel=new Table().table();
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		JScrollPane scrollTable=new JScrollPane(tabel);
		scrollTable.setAutoscrolls(true);
		layout.setConstraints(scrollTable, lim);
		panel.add(scrollTable);

		//----------button panel (sell + buy)----------
		JPanel pan=new JPanel();
		pan.setBackground(new Color(151, 111, 51));

		JButton sellButton=new JButton("Sell");
		pan.add(sellButton);

		JButton buyButton=new JButton("Buy");
		pan.add(buyButton);

		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(pan, lim);
		panel.add(pan);

		sellButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggerArea.append("\nSell button");
				System.out.println(askAssistants(p));
				//azioni vendita ssistenti
			}
		});

		buyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggerArea.append("\nBuy button");
				//azioni compra
			}
		});

		return panel;
	}

	private int askAssistants(Player p){

		JComboBox<Integer> list=new JComboBox<Integer>();//combo box per gli assistenti
		list.setToolTipText("Select the number of assistants you want to sell");

		if(p.getAssistantsPool().getAssistants()==0){
			list.addItem(0);
		}else{
			for(int i=0; i<p.getAssistantsPool().getAssistants(); i++){//riempio la combo box
				list.addItem(i+1);
			}
		}
		int option = JOptionPane.showOptionDialog(null, list, "Sell assistants", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (option == JOptionPane.OK_OPTION){//azioni se viene schiacciato ok
			return list.getSelectedIndex()+1;
		}else
			return 0;


	}
}
