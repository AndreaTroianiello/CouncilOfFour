package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.utility.MapSetting;

/**
 * create the panel map
 * @author viga94_
 *
 */
public class MapPanel extends JPanel {
	private transient MapSetting ms;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7690616717551129511L;

	/**
	 * Create the panel.
	 */
	public MapPanel() {
		this.ms=new MapSetting();
	}

	/**
	 * create the map panel
	 * @param reg, the regions list
	 * @return a panel with the map
	 */
	public JPanel createMap(List<Region> reg, JTextArea loggerArea){
		JPanel mapPanel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		mapPanel.setLayout(layout);
		//mapPanel.setBackground(new ImageIcon(getImg()));
		
		GridBagConstraints lim = new GridBagConstraints();
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		//mapPanel.setc
		//BufferedImage img=getImg();
		//Image myim=img.getScaledInstance(900, 400, Image.SCALE_DEFAULT);
		//mapPanel=new JPanel(new ImageIcon(img));
		
		
		/*JLabel label=new JLabel(new ImageIcon(img));
		//label.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=5;//grandezza del riquadro
		lim.gridwidth=6;
		//label.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//label.setOpaque(true);
		//label.settr
		Graphics g = img.getGraphics();
		g.
		//layout.setConstraints(label, lim);
		mapPanel.add(g);//aggiunta bottone al layer panel
		
		
		*/
		List<City> city=ms.getCityfromRegion(reg);

		int j=0;
		for(int i=0; i<reg.size()*2; i++){//scorre le colonne
			for(int k=0; k<5; k++){//scorre le righe

				JPanel citta = new JPanel();
				if((i+k)%2==0){//posiziona le citta' a scacchiera
					citta=new CityPanel().createCity(city.get(j), loggerArea);
					j++;
				}

				citta.setBackground(new Color(153,255,153));
				//citta.setOpaque(false);
				//citta.bo
				lim.gridx = i;//posizione componenti nella griglia
				lim.gridy = k;
				lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
				lim.weighty = 1;
				lim.gridheight=1;//grandezza del riquadro
				lim.gridwidth=1;

				layout.setConstraints(citta, lim);
				mapPanel.add(citta);//aggiunta bottone al layer panel
				
			}
		}
		
		return mapPanel;
	}
	
	/*private BufferedImage getImg(){//recupero le immagini
		BufferedImage image=null;
		String path="src/main/resources/images/region 1000.jpg";//percorso dell'immagine

		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("ciao");
			//logger.error("impossibile caricare l'ìmmagine della carta costruzione: "+path, e);
		}

		return image;
	}*/
}
