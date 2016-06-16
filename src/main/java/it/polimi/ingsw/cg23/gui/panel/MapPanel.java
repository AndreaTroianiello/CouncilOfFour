package it.polimi.ingsw.cg23.gui.panel;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

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
	
	public JPanel createMap(){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();

		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints();
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		for(int i=0; i<6; i++){
			for(int k=0; k<5; k++){
				
				JButton button1 = new JButton("riga "+k+"colonna "+i);
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
						button1.setSize(new Dimension((int)button1.getSize().getWidth()/2,(int)button1.getSize().getHeight()/2));
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						button1.setSize(new Dimension((int)button1.getSize().getWidth()*2,(int)button1.getSize().getHeight()*2));
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
