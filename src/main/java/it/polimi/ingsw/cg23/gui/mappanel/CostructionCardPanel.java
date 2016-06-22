package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}

	/**
	 * create a panel with the costruction card of the region
	 * @param reg, the region
	 * @return the panel with the showed costruction card
	 */
	public JPanel getShowCostructionCard(Region reg, JTextArea loggerArea){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		JLabel nameCostruzione = new JLabel("etichetta costruzione");
		nameCostruzione.setText("Carte costruzione "+reg.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(nameCostruzione, lim);
		panel.add(nameCostruzione);//aggiunta della label al panel

		//carte costruzione regione
		List<BusinessPermitTile> bpt=reg.getDeck().getShowedDeck();

		for(int i=0; i<bpt.size(); i++){//scorre le carte costruzione visibili

			//----------carte costruzione----------
			BufferedImage img=getCostructionImg(nameCostructor(bpt.get(i).getCitiesId()));//carta costruzione 1
			JLabel costructionCard = new JLabel(new ImageIcon(img));//aggiungo l'immagine alla label
			costructionCard.setName("costruzione"+i);
			costructionCard.setBounds(0, 0, img.getWidth(), img.getHeight());//dimensioni della label
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.weightx=0;//espansione in verticale e orizzontale
			lim.weighty=0;
			lim.ipadx=10;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(costructionCard, lim);
			final int k=i;
			costructionCard.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {/**empty, not erasable*/}
				@Override
				public void mousePressed(MouseEvent e) {/**empty, not erasable*/}
				@Override
				public void mouseExited(MouseEvent e) {/**empty, not erasable*/}
				@Override
				public void mouseEntered(MouseEvent e) {/**empty, not erasable*/}
				@Override
				public void mouseClicked(MouseEvent e) {
					writeArea(loggerArea, bpt.get(k));}
			});
		
			panel.add(costructionCard);//aggiunta della label al panel
		}

		return panel;
	}

	/**
	 * read the image
	 * @param name, the image name
	 * @return the buffered image
	 */
	private BufferedImage getCostructionImg(String name){//recupero l'immagine della carta costruzione
		BufferedImage image=null;
		String path="src/main/resources/images/costruction/"+name+".png";//percorso dell'immagine

		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine della carta costruzione: "+path, e);
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
		return nome.toUpperCase();
	}
	
	private void writeArea(JTextArea loggerArea, BusinessPermitTile bpt){
		loggerArea.append("\nCarta costruzione "+bpt.getZone());
		loggerArea.append("\n  Citta': ");
		String id = "";
		for(int i=0; i<bpt.getCitiesId().size(); i++){
			id+=bpt.getCitiesId().get(i)+", ";
		}
		loggerArea.append(id.substring(0, id.length()-2));
		loggerArea.append("\n  Bonus: ");
		String bonus= "";
		for(int i=0; i<bpt.getBonusTile().size(); i++){
			bonus+=bpt.getBonusTile().get(i).getName()+", ";
		}
		loggerArea.append(bonus.substring(0, bonus.length()-2));
	}
}

