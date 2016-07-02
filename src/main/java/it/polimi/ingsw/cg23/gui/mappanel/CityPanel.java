package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.gui.ControllerGUI;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.utility.MapSetting;

/**
 * create the city panel
 * @author viga94
 *
 */
public class CityPanel{

	private Logger logger;
	private MapSetting ms;
	private final double lung;
	private JTextArea loggerArea;
	private ControllerGUI controller;

	/**
	 * @param loggerArea, the area area to read on
	 */
	public CityPanel(JTextArea loggerArea,ControllerGUI controller) {
		lung=Toolkit.getDefaultToolkit().getScreenSize().width-10.0;
		
		this.loggerArea=loggerArea;
		this.ms=new MapSetting();
		this.controller=controller;
		
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}

	/**
	 * @param k, the king
	 * @param c, the city
	 * @return the city panel
	 */
	public JPanel createCity(City c, King k){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints();
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		int p=0;//posizione componenti nella griglia in orizzontale
		//----------nome citta'----------
		JLabel nameLabel=new JLabel(Character.toString(c.getId()));
		double font= ((double) 2/100)*lung;
		nameLabel.setFont(new Font("Calibre", Font.ITALIC,(int) font));
		nameLabel.setForeground(new Color(255,255,255));
		lim.gridx = p;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(nameLabel, lim);
		panel.add(nameLabel);//aggiunta bottone al layer panel
		p++;

		//----------king label----------
		if(c.equals(k.getCity())){
			BufferedImage imgKing=getImg("king");//lettura immagine
			JLabel kingLabel=new JLabel(new ImageIcon(imgKing));
			lim.gridx = p;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
			lim.weighty = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			if(c.getToken().isEmpty())
				p++;

			layout.setConstraints(kingLabel, lim);
			panel.add(kingLabel);//aggiunta bottone al layer panel
		}

		//----------bonus label----------
		if(!c.getToken().isEmpty()){
			BufferedImage imgCityBonus=getImg("cityBonus/"+ms.cityBonus(c));//lettura immagine	
			double width= (2.0/50)*lung;
			double height=  ((double) imgCityBonus.getHeight()/imgCityBonus.getWidth())*width;
			Image myim=imgCityBonus.getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);//ridimensionamento immagine

			JLabel bonusCityLabel=new JLabel(new ImageIcon(myim));
			lim.gridx = p;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
			lim.weighty = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			p++;

			layout.setConstraints(bonusCityLabel, lim);
			panel.add(bonusCityLabel);//aggiunta bottone al layer panel
		}

		//----------immagine citta'----------
		BufferedImage img=getImg("city/"+c.getType()+"City");//lettura immagine
		double width= ((double) 3/50)*lung;
		double height=  ((double) img.getHeight()/img.getWidth())*width;
		Image myim=img.getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);//ridimensionamento immagine

		JLabel label=new JLabel(new ImageIcon(myim));
		lim.gridx =p;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=2;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(label, lim);
		panel.add(label);//aggiunta bottone al layer panel
		listener(panel, c, k);

		return panel;
	}

	private void listener(JPanel panel, City c, King k){
		panel.addMouseListener(new MouseListener() {
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
					controller.getSelectedElements().setCity(c);
					loggerArea.append("\n Element selected (City:"+c.getName()+").");
				}
				if(SwingUtilities.isRightMouseButton(e))//bottone destro
					writeArea(c, k);
			}
		});
	}
	private void writeArea(City c, King k){

		loggerArea.append("\nCitta' "+c.getName());
		loggerArea.append("\n  "+ms.getNeighbourID(c));
		loggerArea.append("\n  Regione: "+c.getRegion().getName());

		if(c.equals(k.getCity()))
			loggerArea.append("\n  King city");
		if(!c.getToken().isEmpty())
			loggerArea.append("\n  Bonus: "+ms.cityBonus(c));

		loggerArea.append("\n  Tipo: "+c.getType());
		loggerArea.append("\n  Empori: ");
		if(c.getEmporiums().isEmpty())
			loggerArea.append("0");
		else{
			for(int i=0; i<c.getEmporiums().size(); i++){//aggiunge tutti gli empori della citta'
				if("NaN".equals(c.getEmporiums().get(i).getPlayer()))
					loggerArea.append("");
					else
				loggerArea.append(c.getEmporiums().get(i).toString()+" ");
			}
		}
	}

	/**
	 * load the image
	 * @param name, the image name
	 * @return
	 */
	private BufferedImage getImg(String name){//recupero le immagini
		BufferedImage image=null;
		String path="src/main/resources/images/"+name+".png";//percorso dell'immagine

		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine della carta costruzione: "+path, e);
		}

		return image;
	}
}
