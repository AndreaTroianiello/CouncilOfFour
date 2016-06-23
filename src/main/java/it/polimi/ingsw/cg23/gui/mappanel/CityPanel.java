package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.Dimension;
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

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.utility.MapSetting;

/**
 * create the city panel
 * @author viga94
 *
 */
public class CityPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1040653424377395735L;
	private transient Logger logger;
	private transient MapSetting ms;
	final double lung=Toolkit.getDefaultToolkit().getScreenSize().width;
	final double alt=Toolkit.getDefaultToolkit().getScreenSize().height;

	/**
	 * Create the panel.
	 */
	public CityPanel() {
		this.ms=new MapSetting();

		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

	}

	public JPanel createCity(City c, JTextArea loggerArea){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints();
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		

		//----------nome citta'----------
		JLabel nameLabel=new JLabel(c.getId()+"");
		double font= ((double) 3/100)*lung;
		nameLabel.setFont(new Font("Calibre", Font.ITALIC,(int) font));
		nameLabel.setForeground(new Color(255,255,255));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=0;
		lim.anchor = GridBagConstraints.EAST;//posizione componenti nei riquadri
		layout.setConstraints(nameLabel, lim);
		panel.add(nameLabel);//aggiunta bottone al layer panel

		//----------immagine citta'----------
		BufferedImage img=getImg(c.getType());
		double width= ((double) 3/50)*lung;
		double height=  ((double) img.getHeight()/img.getWidth())*width;
		Image myim=img.getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);
		
		JLabel label=new JLabel(new ImageIcon(myim));

		//label.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
		lim.anchor = GridBagConstraints.WEST;//posizione componenti nei riquadri
		lim.gridx =1;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx = 0;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;

		layout.setConstraints(label, lim);
		panel.add(label);//aggiunta bottone al layer panel
		 
		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mousePressed(MouseEvent e) {/**empty, not erasable*/}
			@Override
			public void mouseExited(MouseEvent e) {
				//	label.setSize(new Dimension((int)label.getSize().getWidth()/2,(int)label.getSize().getHeight()/2));
				 }
			@Override
			public void mouseEntered(MouseEvent e) {
					//label.setSize(new Dimension((int)label.getSize().getWidth()*2,(int)label.getSize().getHeight()*2));
				 }
			@Override
			public void mouseClicked(MouseEvent e) {
				loggerArea.append("\nCitta' "+c.getName());
				loggerArea.append("\n  "+ms.getNeighbourID(c));
				loggerArea.append("\n  Bonus: "+ms.cityBonus(c));
				loggerArea.append("\n  Tipo: "+c.getType());
				loggerArea.append("\n  Empori: ");
				if(c.getEmporiums().isEmpty())
					loggerArea.append("0");
				else{
					for(int i=0; i<c.getEmporiums().size(); i++){
						loggerArea.append(c.getEmporiums().get(i).toString()+" ");
					}
				}
			}
		});

		return panel;
	}

	private BufferedImage getImg(String name){//recupero le immagini
		BufferedImage image=null;
		String path="src/main/resources/images/city/"+name+"City.png";//percorso dell'immagine

		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine della carta costruzione: "+path, e);
		}

		return image;
	}
	
	public void update(){
		this.repaint();
	}
	
}
