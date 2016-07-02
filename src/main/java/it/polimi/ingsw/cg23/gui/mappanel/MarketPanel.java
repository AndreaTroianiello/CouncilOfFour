package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.gui.ControllerGUI;
import it.polimi.ingsw.cg23.gui.SelectedElements;
import it.polimi.ingsw.cg23.server.model.action.MarketBuy;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;

public class MarketPanel extends JPanel {

	private static final long serialVersionUID = 4032301896451894064L;
	private JTextArea loggerArea;
	private ControllerGUI controller;
	private JTable fakeTable;
	private Table tableCreator;
	private final int lung;
	//private final double alt;
	
	/**
	 * Create the panel.
	 */
	public MarketPanel(ControllerGUI controller,JTextArea loggerArea) {
		this.loggerArea=loggerArea;
		this.controller=controller;
		this.tableCreator=new Table(controller.getModel());
		
		lung=Toolkit.getDefaultToolkit().getScreenSize().width;//lughezza dello schermo meno 10
		//alt=Toolkit.getDefaultToolkit().getScreenSize().height-50;//altezza-50
		
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
		lim.gridwidth=3;
		layout.setConstraints(marketLabel, lim);
		add(marketLabel);

		//----------tabella finta----------
		JLabel lab=new JLabel(addSpace(lung/100));//aggiunge spazi per centrare la tabella
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(lab, lim);
		add(lab);
		
		
		//----------tabella----------
		fakeTable=tableCreator.createTableMarket();
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		JScrollPane scrollTable=new JScrollPane(fakeTable);
		scrollTable.setAutoscrolls(true);
		layout.setConstraints(scrollTable, lim);
		add(scrollTable);

		//----------tabella finta----------
		JLabel lab2=new JLabel(addSpace(lung/100));//aggiunge spazi per centrare la tabella
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(lab2, lim);
		add(lab2);
		
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
		lim.gridwidth=3;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(panel, lim);
		add(panel);

		sellButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggerArea.append("\nSell button");
				SelectedElements elements=controller.getSelectedElements();
				if(!elements.getCards().isEmpty())
					new MarketDialog(controller, elements.getCards().get(0)).setVisible(true);
				else
					if(elements.getTile()!=null)
						new MarketDialog(controller, elements.getTile()).setVisible(true);
					else
						new MarketDialog(controller, new AssistantsPool()).setVisible(true);
			}
		});

		buyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggerArea.append("\nBuy button");
				controller.updateController(new MarketBuy(controller.getModel()
											.findItem(""+fakeTable.getSelectedRow()
											)));
			}
		});
	}
	
	public void fillTable(){
		tableCreator.fillTable();
	}
	
	private String addSpace(int s){
		String space="";
		
		for(int i=0; i<s; i++){
			space=space.concat(" ");
		}
		
		return space;
	}
}
