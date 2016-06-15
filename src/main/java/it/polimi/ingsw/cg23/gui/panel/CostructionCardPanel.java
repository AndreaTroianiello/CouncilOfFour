package it.polimi.ingsw.cg23.gui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.gui.FrameMap;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;

/**
 * create the costruction card panel for a region
 * @author viga94_
 *
 */
public class CostructionCardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6311594300963247531L;
	private transient Logger logger;

	/**
	 * Create the panel.
	 */
	public CostructionCardPanel() {
		//configurazione logger
		logger = Logger.getLogger(FrameMap.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}

	/**
	 * create a panel with the costruction card of the region
	 * @param reg, the region
	 * @return the panel with the showed costruction card
	 */
	public JPanel getShowCostructionCard(Region reg){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.SOUTH;//posizione componenti nei riquadri

		JLabel label0 = new JLabel("etichetta costruzione");
		label0.setText("costruzione "+reg.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=4;
		layout.setConstraints(label0, lim);
		panel.add(label0);//aggiunta della label al panel

		//carte costruzione regione
		List<BusinessPermitTile> bpt=reg.getDeck().getShowedDeck();
		
		//----------costruzione 1----------
		BufferedImage img1=getCostructionImg(nameCostructor(bpt.get(0).getCitiesId()));//carta costruzione 1
		JLabel label1 = new JLabel(new ImageIcon(img1));//aggiungo l'immagine alla label
		label1.setName("costruzione 1");
		label1.setBounds(0, 0, img1.getWidth(), img1.getHeight());//dimensioni della label
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(label1, lim);
		panel.add(label1);//aggiunta della label al panel

		//----------costruzione 2----------
		BufferedImage img2=getCostructionImg(nameCostructor(bpt.get(0).getCitiesId()));//carta costruzione 2
		JLabel label2 = new JLabel(new ImageIcon(img2));//aggiungo l'immagine alla label
		label2.setName("costruzione 2");
		label2.setBounds(0, 0, img2.getWidth(), img2.getHeight());//dimensioni della label
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(label2, lim);
		panel.add(label2);//aggiunta della label al panel

		return panel;
	}
	
	/**
	 * read the image
	 * @param name, the image name
	 * @return the buffered image
	 */
	private BufferedImage getCostructionImg(String name){//recupero l'immagine della carta costruzione
		BufferedImage image=null;

		try {
			image = ImageIO.read(new File("src/main/resources/images/costruction/"+name+".jpg"));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine della carta costruzione", e);
		}

		return image;
	}
	
	/**
	 * trasform the list of id in a string
	 * @param id, the list of city id of a costruction card
	 * @return a string with the city id
	 */
	private String nameCostructor(List<Character> id){
		String nome="";
		for(int i=0; i<id.size(); i++){
			nome=nome.concat(""+id.get(i));
		}
		return nome;
	}
}
