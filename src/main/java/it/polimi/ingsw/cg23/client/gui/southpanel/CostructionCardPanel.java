package it.polimi.ingsw.cg23.client.gui.southpanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.color.ColorSpace;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;

/**
 * create the costruction card panel for a region
 * @author viga94_
 *
 */
public class CostructionCardPanel {

	private Logger logger;
	private JTextArea loggerArea;
	private ControllerGUI controller;

	/**
	 * @param loggerArea, the area to write on
	 * @param controller, the controller
	 */
	public CostructionCardPanel(JTextArea loggerArea, ControllerGUI controller) {
		this.loggerArea=loggerArea;
		this.controller=controller;
		
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}

	/**
	 * create a panel with the costruction card of the region
	 * @param reg, the region
	 * @return the panel with the showed costruction card
	 */
	public JPanel getShowCostructionCard(Region reg){
		JPanel panel=new JPanel();//creazione di un nuovo pannello
		GridBagLayout layout = new GridBagLayout();//creazione di un nuovo layout
		panel.setLayout(layout);//applicazione del layout al pannello

		GridBagConstraints lim = new GridBagConstraints();//settaggio layout
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------carte costruzione----------
		BufferedImage img1=getCostructionImg(reg.getName());//carta costruzione 1
		JLabel defaultCostruction = new JLabel(new ImageIcon(img1));//aggiungo l'immagine alla label
		defaultCostruction.setToolTipText("Mazzo carte costruzione "+reg.getName());
		defaultCostruction.setBounds(0, 0, img1.getWidth(), img1.getHeight());//dimensioni della label
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.ipadx=10;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(defaultCostruction, lim);//applicazione del layout al label
		panel.add(defaultCostruction);//aggiunta della label al panel

		//carte costruzione regione
		List<BusinessPermitTile> bpt=reg.getDeck().getShowedDeck();

		for(int i=0; i<bpt.size(); i++){//scorre le carte costruzione visibili

			//----------carte costruzione----------
			BufferedImage img=getCostructionImg(nameCostructor(bpt.get(i).getCitiesId()));//carta costruzione 1
			JLabel costructionCard = new JLabel(new ImageIcon(img));//aggiungo l'immagine alla label
			costructionCard.setName("costruzione"+i);//nome della label
			costructionCard.setToolTipText("Carta costruzione "+(i+1)+" "+bpt.get(i).getZone());
			costructionCard.setBounds(0, 0, img.getWidth(), img.getHeight());//dimensioni della label
			lim.gridx = i+1;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.weightx=0;//espansione in verticale e orizzontale
			lim.weighty=0;
			lim.ipadx=10;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(costructionCard, lim);//applicazione del layout al label
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
				public void mouseClicked(MouseEvent e) {//azioni al click del mouse 
					if(SwingUtilities.isLeftMouseButton(e)){//bottone sinistro
						controller.getSelectedElements().setTile(bpt.get(k));
						loggerArea.append("\n Element selected(Tile:"+k+").");
					}
					if(SwingUtilities.isRightMouseButton(e))//bottone destro
						writeArea(bpt.get(k));//scrittura delle informazioni della carta costruzione sulla logger area
				}
			});

			panel.add(costructionCard);//aggiunta della label al panel
		}

		return panel;
	}

	/**
	 * print the costruction card avaiable for the player
	 * @param p, the player
	 * @return a panel with the player's costruction card
	 */
	public JPanel myCostructionCard(Player p){
		JPanel panel=new JPanel();//creazioni di un nuovo pannello
		GridBagLayout layout = new GridBagLayout();//creazione di un nuovo layout
		panel.setLayout(layout);//assegnazione del layout al pannello

		GridBagConstraints lim = new GridBagConstraints();//definizione del layout
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.WEST;//posizione componenti nei riquadri

		int q=0;//posizione griglia
		List<BusinessPermitTile> bpt=p.getAvailableBusinessPermits();//carte costruzione disponibili

		for(int i=0; i<bpt.size(); i++){//scorre le carte costruzione visibili

			//----------carte costruzione----------
			BufferedImage img=getCostructionImg(nameCostructor(bpt.get(i).getCitiesId()));//carta costruzione 1
			JLabel costructionCard = new JLabel(new ImageIcon(img));//aggiungo l'immagine alla label
			costructionCard.setName("costruzione"+i);
			costructionCard.setToolTipText("Carta costruzione "+(i+1)+" "+bpt.get(i).getZone());
			costructionCard.setBounds(0, 0, img.getWidth(), img.getHeight());//dimensioni della label
			lim.gridx = q;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.weightx=0;//espansione in verticale e orizzontale
			lim.weighty=0;
			lim.ipadx=10;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(costructionCard, lim);//applicazione del layout alla carta costruzione
			q++;//incremento la posizione sulla griglia
			
			final int k=i;
			costructionCard.addMouseListener(new MouseListener() {//azioni pannello click mouse
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
					if(SwingUtilities.isLeftMouseButton(e)){//bottone sinistro
						controller.getSelectedElements().setTile(bpt.get(k));
						loggerArea.append("\n Element selected(Tile:"+k+").");
					}
					if(SwingUtilities.isRightMouseButton(e))//bottone destro
						writeArea(bpt.get(k));
				}
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
		
		for(int i=0; i<id.size(); i++){//scorro il numero di city id di una carta costruzione
			nome=nome.concat(Character.toString(id.get(i)));
		}
		
		return nome.toUpperCase();//ritorno la stringa in maiuscolo
	}

	/**
	 * write the bpt info on the logger area
	 * @param bpt, the business permit card
	 */
	public void writeArea(BusinessPermitTile bpt){
		loggerArea.append("\nCarta costruzione "+bpt.getZone());//aggiungo alla loggerArea le informazioni della carta costrucione
		loggerArea.append("\n  Citta': ");
		String id = "";
		
		for(int i=0; i<bpt.getCitiesId().size(); i++){//scorro gli id citta' della carta costrucione
			id=id.concat(bpt.getCitiesId().get(i)+", ");
		}
		
		loggerArea.append(id.substring(0, id.length()-2));
		loggerArea.append("\n  Bonus: ");

		if(!bpt.getBonusTile().isEmpty()){//nel caso ci siano bonus
			String bonus= "";
			for(int i=0; i<bpt.getBonusTile().size(); i++){
				bonus=bonus.concat(bpt.getBonusTile().get(i).getName()+", ");
			}
			loggerArea.append(bonus.substring(0, bonus.length()-2));
		}else//nel caso non ci siano bonus
			loggerArea.append("0");
	}


	/**
	 * load and converts an image to a black and white one
	 * @param bpt, the business permit tiles
	 * @return the jlabel image 
	 */
	public JLabel oldCostructionWB(BusinessPermitTile bpt) {
		BufferedImage image=getCostructionImg(nameCostructor(bpt.getCitiesId()));//caricamento immagine
		
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorConvertOp ccop = new ColorConvertOp(cs, null);

		BufferedImage imgWB= ccop.filter(image, null);//trasformazione immagine in bianco e nero

		return new JLabel(new ImageIcon(imgWB));//ritorno la label con la carta costruzione
	}
}

