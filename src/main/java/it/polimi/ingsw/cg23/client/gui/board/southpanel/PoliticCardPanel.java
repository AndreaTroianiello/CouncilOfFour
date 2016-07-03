package it.polimi.ingsw.cg23.client.gui.board.southpanel;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.utility.ColorManager;

/**
 * create the politic card panel
 * @author viga94_
 *
 */
public class PoliticCardPanel {

	private Logger logger;
	private Player p;
	private JTextArea loggerArea;
	private ControllerGUI controller;

	/**
	 * 
	 * @param controller, the controller
	 * @param loggerArea, the area to read on
	 */
	public PoliticCardPanel(ControllerGUI controller, JTextArea loggerArea) {
		this.controller=controller;
		this.p=controller.getModel().getPlayer();
		this.loggerArea=loggerArea;
		
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}

	/**
	 * create the politic card panel
	 * @return the politic card panel
	 */
	public JPanel createCard(){
		JPanel panel=new JPanel();//nuovo pannello
		GridBagLayout layout = new GridBagLayout();//nuovo layout
		panel.setLayout(layout);//applicazione del layout al pannelo
		
		GridBagConstraints lim = new GridBagConstraints(); //impostazioni layout
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.EAST;//posizione componenti nei riquadri

		for(int i=0; i<p.getHand().size(); i++){//scorre le carte politiche
			//----------carta politica----------
			BufferedImage img;
			String color;
			PoliticCard card=p.getHand().get(i);
			
			if(card.isJolly()){//carta politica jolly
				img=politcsImg("Jolly");//carta jolly
				color="Jolly";
			}
			else{
				color=new ColorManager().getColorName(card.getColor());//recupero il colore della carta politica
				img=politcsImg(color);//carte colorate
			}
			
			JButton politicButton=new JButton(new ImageIcon(img));//bottone carta politica
			politicButton.setName("Carta politca "+i+", "+color);
			politicButton.setToolTipText("Carta politica "+color);
			politicButton.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
			
			lim.gridx=i*2;
			lim.gridy=0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			
			layout.setConstraints(politicButton, lim);//applicazione del layout al bottone della carta politica
			panel.add(politicButton);//aggiunta della carta politica al pannello
			politicButton.addMouseListener(new MouseListener() {//azioni del mouse per la carta politica
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
						controller.getSelectedElements().addCard(card);
						
						loggerArea.append("\n Element selected(Card:"+color+").");
					}
					if(SwingUtilities.isRightMouseButton(e))//bottone destro
						loggerArea.append("\nSelezionata la carta politica: "+color);
					}
			});
			
			//----------etichetta spazio----------
			//Aggiunge lo spazio dopo aver messo la carta politica
			JLabel label2=new JLabel();
			label2.setName(i+" spazio");
			label2.setPreferredSize(new Dimension(5, 50));
			
			lim.gridx=i*2+1;
			lim.gridy=0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			
			layout.setConstraints(label2, lim);
			panel.add(label2);
		}

		return panel;
	}

	/**
	 * load the image
	 * @param name, the color of the politic card
	 * @return the politic card image
	 */
	private BufferedImage politcsImg(String name){//recupero l'immagine delle carte politiche
		BufferedImage image=null;
		String path="src/main/resources/images/politics/"+name+".png";//percorso dell'immagine

		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine del nobility track: "+path, e);
		}

		return image;
	}

}
