package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.Councillor;

/**
 * create the council panel for the gui
 * @author viga94_
 *
 */
public class CouncilPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5988789718682147839L;

	/**
	 * Create the panel.
	 */
	public CouncilPanel() {
		/**
		 * empty costructor
		 */
	}

	/**
	 * create the balcone of region
	 * @param reg, the region
	 * @return a panel with the balcone
	 */
	public JPanel balcone(Region reg){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		JLabel label0 = new JLabel("etichetta consiglieri");
		label0.setText("Consiglieri "+reg.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=8;
		layout.setConstraints(label0, lim);
		panel.add(label0);//aggiunta della label al panel
		
		List<Councillor> councillors=reg.getCouncil().getCouncillors();
		for(int i=0; i<councillors.size(); i++){//scrorre i consiglieri
			
			JLabel label1 = new JLabel();
			label1.setBackground(councillors.get(i).getColor());
			label1.setName("consigliere "+i);
			label1.setOpaque(true);
			label1.setPreferredSize(new Dimension(50, 20));
			lim.gridx = i*2;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(label1, lim);
			panel.add(label1);//aggiunta della label al panel
			
			JLabel label2 = new JLabel();//label per aggiungere spazio
			label2.setPreferredSize(new Dimension(10, 20));
			lim.gridx = i*2+1;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(label2, lim);
			panel.add(label2);//aggiunta della label al panel
		}

		return panel;
	}

}