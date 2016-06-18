package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.utility.ColorManager;
import it.polimi.ingsw.cg23.utility.MapSetting;

/**
 * create the panel map
 * @author viga94_
 *
 */
public class MapPanel extends JPanel {
	private ColorManager cm;
	private MapSetting ms;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7690616717551129511L;

	/**
	 * Create the panel.
	 */
	public MapPanel() {
		this.cm=new ColorManager();
		this.ms=new MapSetting();
	}

	public JPanel createMap(List<Region> reg){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();

		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints();
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		List<City> city=ms.getCityfromRegion(reg);
		
		int j=0;
		for(int i=0; i<reg.size()*2; i++){//scorre le colonne
			for(int k=0; k<5; k++){

				JTextArea button1 = new JTextArea();
				if((i+k)%2==0){//posiziona le citta' a scacchiera
					button1.setText(city.get(j).getName());
					button1.setBackground(cm.getColor(city.get(j).getType()));
					button1.append("\n"+ms.getNeighbourID(city.get(j)));
					button1.append("\n"+ms.cityBonus(city.get(j)));
					j++;
				}else
					button1.setText("");

				lim.gridx = i;//posizione componenti nella griglia
				lim.gridy = k;
				lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
				lim.weighty = 1;
				lim.gridheight=1;//grandezza del riquadro
				lim.gridwidth=1;

				layout.setConstraints(button1, lim);
				panel.add(button1);//aggiunta bottone al layer panel
				button1.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
						/**
						 * empty method, not erasable
						 */
					}
					@Override
					public void mousePressed(MouseEvent e) {
						/**
						 * empty method, not erasable
						 */
					}
					@Override
					public void mouseExited(MouseEvent e) {
						if(button1.getBackground().equals(new Color(255,255,255))){

						}else{
							button1.setSize(new Dimension((int)button1.getSize().getWidth()/2,(int)button1.getSize().getHeight()/2));
						}
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						if(button1.getBackground().equals(new Color(255,255,255))){

						}else{
							button1.setSize(new Dimension((int)button1.getSize().getWidth()*2,(int)button1.getSize().getHeight()*2));

						}
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						/**
						 * empty method, not erasable
						 */
					}
				});
			}
		}

		return panel;
	}
}
