package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Dimension;
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
import javax.swing.JTextArea;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.utility.MapSetting;

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
	private transient Logger logger;
	private transient MapSetting ms;
	private CityPanel cp;

	/**
	 * Create the panel.
	 */
	public MapPanel() {
		this.ms=new MapSetting();
		this.cp=new CityPanel();

		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}

	/**
	 * create the map panel
	 * @param reg, the regions list
	 * @return a panel with the map
	 */
	public JPanel createMap(List<Region> reg, JTextArea loggerArea){
		JPanel mapPanel=new JPanel();//pannello con la mappa
		GridBagLayout layout = new GridBagLayout();//layout GridBagLayout
		mapPanel.setLayout(layout);//il pannello usa il layout grid bag
		
		GridBagConstraints lim = new GridBagConstraints();//impostazioni layout
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		BufferedImage img=getImg();//immagine di sfondo
		//Image myim=img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);

		JLabel label=new JLabel(new ImageIcon(img));//etichetta con l'immagine di sfondo
		label.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));//dimensione etichetta immagine di sfondo
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;

		label.setLayout(layout);//la label usa il layout grid bag

		List<City> city=ms.getCityfromRegion(reg);//lista con le citta'

		int j=0;
		for(int i=0; i<reg.size()*2; i++){//scorre le colonne
			for(int k=0; k<5; k++){//scorre le righe

				JPanel citta = new JPanel();//pannello con le citta'
				if((i+k)%2==0){//posiziona le citta' a scacchiera
					citta=cp.createCity(city.get(j), loggerArea);//recupero il pannello con la citta'
					citta.setToolTipText(city.get(j).getName());
					j++;
					citta.setOpaque(false);
				}else{
					citta.setOpaque(false);
				}

				lim.gridx = i;//posizione componenti nella griglia
				lim.gridy = k;
				lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
				lim.weighty = 1;
				lim.gridheight=1;//grandezza del riquadro
				lim.gridwidth=1;

				layout.setConstraints(citta, lim);//applico il layout al pannello delle citta'
				label.add(citta);//aggiunta il panel alla label

			}
		}

		layout.setConstraints(label, lim);//applico il layout alla label con lo sfondo
		mapPanel.add(label);//aggiungo la label al panel

		return mapPanel;
	}

	private BufferedImage getImg(){//recupero le immagini
		BufferedImage image=null;
		String path="src/main/resources/images/region 1200.jpg";//percorso dell'immagine

		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine della carta costruzione: "+path, e);
		}

		return image;
	}

	public void update(){
		cp.update();
		this.repaint();
	}
}
