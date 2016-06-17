package it.polimi.ingsw.cg23.gui.panel;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;

/**
 * create the panel map
 * @author viga94_
 *
 */
public class MapPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7690616717551129511L;

	/**
	 * Create the panel.
	 */
	public MapPanel() {
		/**
		 * empty costructor
		 */
	}

	public JPanel createMap(List<Region> reg){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();

		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints();
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		City[][] city=createArrayMap(reg);
		for(int k=0; k<5; k++){
			for(int i=0; i<6; i++){

				JButton button1 = new JButton();
				if(city[k][i]==null){
					button1.setText("");
				}else
					button1.setText(city[k][i].getName()+"\n"+"");

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
						if(button1.getText()==""){

						}else{
							button1.setSize(new Dimension((int)button1.getSize().getWidth()/2,(int)button1.getSize().getHeight()/2));
						}
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						if(button1.getText()==""){

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

	public City[][] createArrayMap(List<Region> reg){
		City[][] citta=new City[5][6];

		for(int k=0; k<5; k++){
			for(int i=0; i<6; i++){
				citta[k][i]=null;
			}
		}
		//region1
		citta[0][0]=reg.get(0).getCities().get(0);
		citta[1][1]=reg.get(0).getCities().get(1);
		citta[2][0]=reg.get(0).getCities().get(2);
		citta[3][1]=reg.get(0).getCities().get(3);
		citta[4][0]=reg.get(0).getCities().get(4);

		//region2
		citta[0][2]=reg.get(1).getCities().get(0);
		citta[1][3]=reg.get(1).getCities().get(1);
		citta[2][2]=reg.get(1).getCities().get(2);
		citta[3][3]=reg.get(1).getCities().get(3);
		citta[4][2]=reg.get(1).getCities().get(4);

		//region3
		citta[0][4]=reg.get(2).getCities().get(0);
		citta[1][5]=reg.get(2).getCities().get(1);
		citta[2][4]=reg.get(2).getCities().get(2);
		citta[3][5]=reg.get(2).getCities().get(3);
		citta[4][4]=reg.get(2).getCities().get(4);

		return citta;
	}

}
