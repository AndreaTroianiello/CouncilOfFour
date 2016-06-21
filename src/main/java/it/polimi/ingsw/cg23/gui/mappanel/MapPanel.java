package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.utility.MapSetting;

/**
 * create the panel map
 * @author viga94_
 *
 */
public class MapPanel extends JPanel {
	private transient MapSetting ms;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7690616717551129511L;

	/**
	 * Create the panel.
	 */
	public MapPanel() {
		this.ms=new MapSetting();
	}

	/**
	 * create the map panel
	 * @param reg, the regions list
	 * @return a panel with the map
	 */
	public JPanel createMap(List<Region> reg, JTextArea loggerArea){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints();
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		List<City> city=ms.getCityfromRegion(reg);

		int j=0;
		for(int i=0; i<reg.size()*2; i++){//scorre le colonne
			for(int k=0; k<5; k++){//scorre le righe

				JPanel citta = new JPanel();
				if((i+k)%2==0){//posiziona le citta' a scacchiera
					citta=new CityPanel().createCity(city.get(j), loggerArea);
					j++;
				}

				citta.setBackground(new Color(153,255,153));
				lim.gridx = i;//posizione componenti nella griglia
				lim.gridy = k;
				lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
				lim.weighty = 1;
				lim.gridheight=1;//grandezza del riquadro
				lim.gridwidth=1;

				layout.setConstraints(citta, lim);
				panel.add(citta);//aggiunta bottone al layer panel
				
			}
		}

		return panel;
	}
}
