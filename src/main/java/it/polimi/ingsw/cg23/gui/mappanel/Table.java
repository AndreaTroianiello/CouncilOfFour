package it.polimi.ingsw.cg23.gui.mappanel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * create the table
 * @author viga94
 *
 */
public class Table extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2865067592198214851L;

	/**
	 * Create the panel.
	 */
	public Table() {
		/**
		 * empty costructor
		 */
	}

	public JTable table(){
		JTable table=new JTable();//tabella

		DefaultTableModel model=new DefaultTableModel();
		table.setModel(model);//aggiungo alla tabella il modello

		//colonne
		model.addColumn("Position");
		model.addColumn("Type");
		model.addColumn("Value");
		model.addColumn("Coins");
		model.addColumn("Player");

		//righe
		model.addRow(new Object[]{"kldjfksjlfjslfjksdjflsjksj",2,3,4,5});
		model.addRow(new Object[]{"ma",2,3,4,5});
		model.addRow(new Object[]{"poi",2,3,4,5});
		model.addRow(new Object[]{"scrivi",2,3,4,5});
		return table;
	}
}
