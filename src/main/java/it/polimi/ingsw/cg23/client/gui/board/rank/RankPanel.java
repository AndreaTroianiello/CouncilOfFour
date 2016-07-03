package it.polimi.ingsw.cg23.client.gui.board.rank;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import it.polimi.ingsw.cg23.server.model.Player;

/**
 * create the rank panel
 * @author Andrea
 *
 */
public class RankPanel extends JPanel {
	
	private static final long serialVersionUID = 4982507882652463872L;
	private JTable rank;
	private DefaultTableModel model;
	private final int lung;//lunghezza finestra

	/**
	 * Create the panel.
	 */
	public RankPanel() {
		lung=Toolkit.getDefaultToolkit().getScreenSize().width;//lughezza dello schermo
		init();
	}

	/**
	 * Initializes the components.
	 */
	private void init(){
		GridBagLayout layout = new GridBagLayout();//nuovo layout
		setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints();//settaggio layout
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------etichetta rank----------
		JLabel rankLabel=new JLabel("Final Rank");//etichetta scritta
		rankLabel.setForeground(new Color(255, 215, 0));//colore scritta
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		layout.setConstraints(rankLabel, lim);//applicazione del layout alla label
		add(rankLabel);//aggiunta della label al panel

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
		rank=new JTable();//tabella market
		initRankTable();
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		JScrollPane scrollTable=new JScrollPane(rank);//applicazione dello scroll al panel
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

		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		layout.setConstraints(littlePanel, lim);//applicazione del layout al littlePanel
		add(littlePanel);//aggiunta del littlePanel al pannello

	}
	
	/**
	 * Initializes the rank table.
	 */
	private void initRankTable(){
		model=new DefaultTableModel(
	            new Object [][] {
	            },
	            new String [] {
	               "Position", "Username", "#Emporiums built", "Assistants", "Richness","#Business Permit Tiles","Hand","Nobility Track","Victory Track"
	            }
	        ) {
				private static final long serialVersionUID = -7634473526817086902L;
				boolean[] canEdit = new boolean [] {
	                false, false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	      };
	      rank.setModel(model);//aggiungo alla tabella il modello
	}
	
	/**
	 * Fills the table with the players.
	 */
	public void fillRankTable(List<Player> players){
		
		for(int index=0;index<players.size();index++){
			Player player=players.get(index);
			model.addRow(new Object[]{index+1,
									  player.getUser(),
									  player.getEmporiums().size(),
									  player.getAssistantsPool().getAssistants(),
									  player.getRichness().getCoins(),
									  player.getAvailableBusinessPermits().size()+player.getUsedBusinessPermit().size(),
									  player.getHand().size(),
									  player.getNobilityBoxPosition(),
									  player.getVictoryTrack().getVictoryPoints()});
		}
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
