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

import it.polimi.ingsw.cg23.gui.ControllerGUI;
import it.polimi.ingsw.cg23.gui.SelectedElements;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.action.MarketBuy;
import it.polimi.ingsw.cg23.server.model.action.MarketSell;

public class MarketPanel extends JPanel {

	private static final long serialVersionUID = 4032301896451894064L;
	private JTextArea loggerArea;
	private ControllerGUI controller;
	private JTable table;
	private Table tableCreator;
	
	/**
	 * Create the panel.
	 */
	public MarketPanel(ControllerGUI controller,JTextArea loggerArea) {
		this.loggerArea=loggerArea;
		this.controller=controller;
		this.tableCreator=new Table(controller.getModel());
		init();
	}

	private void init(){
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

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
		add(marketLabel);

		//----------tabella----------
		table=tableCreator.createTableMarket();
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		JScrollPane scrollTable=new JScrollPane(table);
		scrollTable.setAutoscrolls(true);
		layout.setConstraints(scrollTable, lim);
		add(scrollTable);

		//----------button panel (sell + buy)----------
		JPanel panel=new JPanel();
		panel.setBackground(new Color(151, 111, 51));

		JButton sellButton=new JButton("Sell");
		panel.add(sellButton);

		JButton buyButton=new JButton("Buy");
		panel.add(buyButton);

		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(panel, lim);
		add(panel);

		sellButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggerArea.append("\nSell button");
				SelectedElements elements=controller.getSelectedElements();
				if(elements.getCards().isEmpty()||elements.getTile()==null)
					askAssistants(controller.getModel().getPlayer());
				else
					controller.updateController(new MarketSell(null, 0));
			}
		});

		buyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggerArea.append("\nBuy button");
				controller.updateController(new MarketBuy(controller.getModel()
											.findItem(""+table.getColumn(table.getSelectedRow()).getModelIndex()
											)));
			}
		});
	}
	
	public void fillTable(){
		tableCreator.fillTable();
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
