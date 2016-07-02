package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.Dimension;
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
	private JTable table;
	private Table tableCreator;
	private final double lung;
	private final double alt;
	
	/**
	 * Create the panel.
	 */
	public MarketPanel(ControllerGUI controller,JTextArea loggerArea) {
		this.loggerArea=loggerArea;
		this.controller=controller;
		this.tableCreator=new Table(controller.getModel());
		
		lung=Toolkit.getDefaultToolkit().getScreenSize().width-10.0;//lughezza dello schermo meno 10
		alt=Toolkit.getDefaultToolkit().getScreenSize().height-50;//altezza-50
		
		init();
	}

	private void init(){
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints();
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		
		double width= (3.0/4)*lung;
		double height=(3.0/4)*alt;
		setSize(new Dimension((int)width, (int)height));
		
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
											.findItem(""+table.getSelectedRow()
											)));
			}
		});
	}
	
	public void fillTable(){
		tableCreator.fillTable();
	}
}
