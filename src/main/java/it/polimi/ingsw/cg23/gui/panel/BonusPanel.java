package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Color;
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

import it.polimi.ingsw.cg23.server.model.Board;

public class BonusPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7246573558727825743L;
	private Logger logger;
	private CouncilPanel cp;

	/**
	 * Create the panel.
	 */
	public BonusPanel() {
		this.cp=new CouncilPanel();
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

	}

	public JPanel createBonusPanel(Board b){
		JPanel bonusPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		bonusPanel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill = GridBagConstraints.HORIZONTAL;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------------consiglieri del re------------
		JPanel kingCouncillors=cp.kingbalcone(b.getKing());
		kingCouncillors.setBackground(new Color(245, 124, 125));
		kingCouncillors.setName("consiglieri re");
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=b.getTypes().size();
		layout.setConstraints(kingCouncillors, lim);
		bonusPanel.add(kingCouncillors);

		//----------------bonus king------------
		JLabel kingBonus = null;
		if(b.getBonusKing().getCurrentBonusKing()!=0){
			BufferedImage img=getImg("bonusKing/"+b.getBonusKing().getCurrentBonusKing());
			kingBonus=new JLabel(new ImageIcon(img));
		}else{
			kingBonus=new JLabel("Bonus king finiti");
		}
		kingBonus.setName("bonus king");
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(kingBonus, lim);
		bonusPanel.add(kingBonus);

		//----------------bonus region------------
		for(int k=0; k<b.getRegions().size(); k++){
			JLabel regionBonus;
			if(b.getRegions().get(k).isBonusAvailable()){
				BufferedImage img=getImg("bonusRegion/"+b.getRegions().get(k).getName());
				regionBonus=new JLabel(new ImageIcon(img));
			}else
				regionBonus=new JLabel("Bonus finiti");

			regionBonus.setName("bonus type "+b.getRegions().get(k).getName());
			lim.fill = GridBagConstraints.BOTH;
			lim.gridx = k+1;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.weightx=0;//espansione in verticale e orizzontale
			lim.weighty=0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(regionBonus, lim);
			bonusPanel.add(regionBonus);
		}

		//----------------city type bonus------------
		for(int i=0; i<b.getTypes().size(); i++){
			JLabel cityTypeBonus;
			if(("Purple").equals(b.getTypes().get(i).getName())){
			}else{
				if(b.getTypes().get(i).isBonusAvailable()){
					BufferedImage img=getImg("bonusType/"+b.getTypes().get(i).getName());
					cityTypeBonus=new JLabel(new ImageIcon(img));
				}else
					cityTypeBonus=new JLabel("Bonus finiti");

				cityTypeBonus.setName("bonus type "+b.getTypes().get(i).getName());
				lim.fill = GridBagConstraints.BOTH;
				lim.gridx = i;//posizione componenti nella griglia
				lim.gridy = 2;
				lim.weightx=0;//espansione in verticale e orizzontale
				lim.weighty=0;
				lim.gridheight=1;//grandezza del riquadro
				lim.gridwidth=1;
				layout.setConstraints(cityTypeBonus, lim);
				bonusPanel.add(cityTypeBonus);

			}
		}

		return bonusPanel;
	}

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
