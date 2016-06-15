package it.polimi.ingsw.cg23.gui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.gui.FrameMap;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.utility.ColorManager;

/**
 * create the council panel for the gui
 * @author viga94_
 *
 */
public class CouncilPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5988789718682147839L;
	private transient Logger logger;

	/**
	 * Create the panel.
	 */
	public CouncilPanel() {
		//configurazione logger
		logger = Logger.getLogger(FrameMap.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}

	/**
	 * create the balcone of region
	 * @param reg, the region
	 * @return a panel with the balcone
	 */
	public JPanel balcone(Region reg){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.SOUTH;//posizione componenti nei riquadri

		JLabel label0 = new JLabel("etichetta consiglieri");
		label0.setText("Consiglieri "+reg.getName());
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=4;
		layout.setConstraints(label0, lim);
		panel.add(label0);//aggiunta della label al panel

		//----------consigliere 1----------
		//recupero l'immagine con il consigliere
		BufferedImage img1=getCouncilImg(new ColorManager().getColorName(reg.getCouncil().getCouncillors().get(0).getColor()));
		JLabel label1 = new JLabel(new ImageIcon(img1));//aggiungo l'immagine alla label
		label1.setName("consigliere 1");
		label1.setBounds(0, 0, img1.getWidth(), img1.getHeight());//dimensioni della label
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(label1, lim);
		panel.add(label1);//aggiunta della label al panel

		//----------consigliere 2----------
		//recupero l'immagine con il consigliere
		BufferedImage img2=getCouncilImg(new ColorManager().getColorName(reg.getCouncil().getCouncillors().get(1).getColor()));
		JLabel label2 = new JLabel(new ImageIcon(img2));//aggiungo l'immagine alla label
		label2.setName("consigliere 2");
		label2.setBounds(0, 0, img2.getWidth(), img2.getHeight());//dimensioni della label
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(label2, lim);
		panel.add(label2);//aggiunta della label al panel

		//----------consigliere 3----------
		//recupero l'immagine con il consigliere
		BufferedImage img3=getCouncilImg(new ColorManager().getColorName(reg.getCouncil().getCouncillors().get(2).getColor()));
		JLabel label3 = new JLabel(new ImageIcon(img3));//aggiungo l'immagine alla label
		label3.setName("consigliere 3");
		label3.setBounds(0, 0, img3.getWidth(), img3.getHeight());//dimensioni della label
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(label3, lim);
		panel.add(label3);//aggiunta della label al panel

		//----------consigliere 4----------
		//recupero l'immagine con il consigliere
		BufferedImage img4=getCouncilImg(new ColorManager().getColorName(reg.getCouncil().getCouncillors().get(3).getColor()));
		JLabel label4 = new JLabel(new ImageIcon(img4));//aggiungo l'immagine alla label
		label4.setName("consigliere 4");
		label4.setBounds(0, 0, img4.getWidth(), img4.getHeight());//dimensioni della label
		lim.gridx = 3;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.ipadx=0;//bordi componente
		lim.ipady=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(label4, lim);
		panel.add(label4);//aggiunta della label al panel

		return panel;
	}

	private BufferedImage getCouncilImg(String color){//recupero l'immagine del consigliere
		BufferedImage image=null;
		String path="src/main/resources/images/councillors/"+color+".png";
		
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {

			logger.error("impossibile caricare l'ìmmagine del consigliere: "+path, e);
		}

		return image;
	}

}
