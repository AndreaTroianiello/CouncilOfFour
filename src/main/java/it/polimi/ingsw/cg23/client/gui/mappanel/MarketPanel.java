package it.polimi.ingsw.cg23.client.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.client.gui.SelectedElements;
import it.polimi.ingsw.cg23.server.model.action.MarketBuy;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;
import it.polimi.ingsw.cg23.server.model.marketplace.Market;

/**
 * create the market panel
 * @author viga94_
 *
 */
public class MarketPanel extends JPanel {

	private static final long serialVersionUID = 4032301896451894064L;
	private JTextArea loggerArea;
	private transient ControllerGUI controller;
	private JTable table;
	private DefaultTableModel model;
	private final int lung;//lunghezza finestra

	/**
	 * Create the panel.
	 * @param controller, the controller
	 * @param loggerArea, the area to read on
	 */
	public MarketPanel(ControllerGUI controller, JTextArea loggerArea) {
		this.loggerArea=loggerArea;
		this.controller=controller;
		lung=Toolkit.getDefaultToolkit().getScreenSize().width;//lughezza dello schermo

		init();
	}

	/**
	 * create the market components
	 */
	private void init(){
		GridBagLayout layout = new GridBagLayout();//nuovo layout
		setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints();//settaggio layout
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------etichetta market----------
		JLabel marketLabel=new JLabel("Market");//etichetta scritta
		marketLabel.setForeground(new Color(255, 215, 0));//colore scritta
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		layout.setConstraints(marketLabel, lim);//applicazione del layout alla label
		add(marketLabel);//aggiunta della label al panel

		//----------tabella finta----------
		JLabel spaceLabel1=new JLabel(addSpace(lung/100));//aggiunge spazi per centrare la tabella
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(spaceLabel1, lim);//applicazione del layout alla label
		add(spaceLabel1);//aggiunta della label al panel


		//----------tabella----------
		table=new JTable();//tabella market
		initMarketTable();
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		JScrollPane scrollTable=new JScrollPane(table);//applicazione dello scroll al panel
		scrollTable.setAutoscrolls(true);
		layout.setConstraints(scrollTable, lim);//applicazione del layout allo scroll
		add(scrollTable);//aggiunta dello scroll al panel

		//----------tabella finta----------
		JLabel spaceLabel2=new JLabel(addSpace(lung/100));//aggiunge spazi per centrare la tabella
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(spaceLabel2, lim);//applicazione del layout alla label
		add(spaceLabel2);//aggiunta della label al panel

		//----------button panel (sell + buy)----------
		JPanel littlePanel=new JPanel();//nuovo pannello per i bottoni buy and sell
		littlePanel.setBackground(new Color(151, 111, 51));//sfondo del panello

		JButton sellButton=new JButton("Sell");//bottone sell
		littlePanel.add(sellButton);//aggiunta del bottone al littlePanel

		JButton buyButton=new JButton("Buy");//bottone buy
		littlePanel.add(buyButton);//aggiunta del bottone al littlePanel

		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(littlePanel, lim);//applicazione del layout al littlePanel
		add(littlePanel);//aggiunta del littlePanel al pannello

		sellButton.addActionListener(new ActionListener() {//azioni di ascolto bottone sell
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

		buyButton.addActionListener(new ActionListener() {//azioni di ascolto buy button
			@Override
			public void actionPerformed(ActionEvent e) {
				loggerArea.append("\nBuy button");
				controller.updateController(new MarketBuy(controller.getModel()
						.findItem(Integer.toString(table.getSelectedRow())
								)));
			}
		});
	}

	/**
	 * Fills the table with the market's items.
	 */
	public void fillTable(){
		initMarketTable();
		Market market=controller.getModel().getBoard().getMarket();
		List<Item> items=market.getItems();
		for(int index=0;index<items.size();index++){
			Item item=items.get(index);
			model.addRow(new Object[]{item.getItem().toString(),
									  item.getItem().getClass().getSimpleName(),
									  item.getAmount(),
									  item.getCoins(),
									  item.getPlayer().getUser()});
		}
	}
	
	/**
	 * Initializes the market table.
	 */
	private void initMarketTable(){
		model=new DefaultTableModel(
	            new Object [][] {
	            },
	            new String [] {
	               "Information", "Type", "Amount", "Coins", "Player"
	            }
	        ) {
				private static final long serialVersionUID = -4879233531111422052L;
				boolean[] canEdit = new boolean [] {
	                false, false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	      };
	      table.setModel(model);//aggiungo alla tabella il modello
	}

	/**
	 * create a tring with the specified space
	 * @param s, the number of space
	 * @return a string with the specified space
	 */
	private String addSpace(int s){
		String space="";
		for(int i=0; i<s; i++){
			space=space.concat(" ");
		}
		return space;
	}
}
