package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * create the nobility track
 * @author viga94_
 *
 */
public class NobilityTrackPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2715614441311015393L;
	private transient Logger logger;
	
	/**
	 * Create the panel.
	 */
	public NobilityTrackPanel() {
		
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}
		
	/**
	 * create the nobility track boxes
	 * @param length, the number of nobility boxes
	 * @return a panel with the nobility track
	 */
	public JPanel createNobility(int length){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		for(int i=0; i<length; i++){
			JLabel label0 = new JLabel();
			label0.setBackground(new Color(124, i*10, 123));
			label0.setOpaque(true);
			label0.setText(i+"");
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=1;
			layout.setConstraints(label0, lim);
			panel.add(label0);//aggiunta della label al panel
		}
		
		return panel;
	}
	

	private BufferedImage nobilityImg(){//recupero l'immagine del nobility box
		BufferedImage image=null;
		String path="src/main/resources/images/Nobility track.png";//percorso dell'immagine
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine del nobility track: "+path, e);
		}

		return image;
	}

}
