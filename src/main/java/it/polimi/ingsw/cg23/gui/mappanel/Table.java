package it.polimi.ingsw.cg23.gui.mappanel;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;
import it.polimi.ingsw.cg23.server.model.marketplace.Market;

/**
 * create the table
 * @author viga94
 *
 */
public class Table{

	private ClientModel clientModel;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public Table(ClientModel clientModel) {
		this.clientModel=clientModel;
		this.table=new JTable();//tabella
	}
	
	private void init(){
		model=new DefaultTableModel(
	            new Object [][] {
	            },
	            new String [] {
	                "Position","Type", "Coins", "Player"
	            }
	        ) {
				private static final long serialVersionUID = -4879233531111422052L;
				boolean[] canEdit = new boolean [] {
	                false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	      };
	      table.setModel(model);//aggiungo alla tabella il modello
	}
	public JTable createTableMarket(){
		init();	
		return table;
	}
	
	public void fillTable(){
		init();
		Market market=clientModel.getModel().getMarket();
		List<Item> items=market.getItems();
		for(int index=0;index<items.size();index++){
			Item item=items.get(index);
			model.addRow(new Object[]{index+1,
									  item.getItem().toString(),
									  item.getCoins(),
									  item.getPlayer().getUser()});
		}
	}
}
