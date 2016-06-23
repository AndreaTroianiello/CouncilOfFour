package it.polimi.ingsw.cg23.gui.mappanel;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.utility.ColorManager;

/**
 * create the politic card panel
 * @author viga94_
 *
 */
public class PoliticCardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7548143133078585999L;
	private transient Logger logger;
	private Player p;
	private JTextArea loggerArea;

	/**
	 * Create the panel.
	 */
	public PoliticCardPanel(Player p, JTextArea loggerArea) {
		this.p=p;
		this.loggerArea=loggerArea;
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}

	/**
	 * create the politic card panel
	 * @param p, the player
	 * @return a panel with the politic card
	 */
	public JPanel createCard(){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setLayout(layout);
		GridBagConstraints lim = new GridBagConstraints(); 

		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.EAST;//posizione componenti nei riquadri

		for(int i=0; i<p.getHand().size(); i++){//scorre le carte politiche
			//----------carta politica----------
			BufferedImage img;
			String color;
			if(p.getHand().get(i).isJolly()){
				img=politcsImg("Jolly");//carta jolly
				color="Jolly";
			}
			else{
				color=new ColorManager().getColorName(p.getHand().get(i).getColor());
				img=politcsImg(color);//carte colorate
			}
			JButton button1=new JButton(new ImageIcon(img));
			button1.setName("Carta politca "+i+", "+color);
			button1.setToolTipText("Carta politica "+color);
			button1.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
			lim.gridx=i*2;
			lim.gridy=0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(button1, lim);
			panel.add(button1);
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
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
	
	/*public void update(){
		this.repaint();
	}*/
}
