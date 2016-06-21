package it.polimi.ingsw.cg23.gui.mappanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
/**
 * create the bonus panel
 * @author viga94_
 *
 */
public class BonusPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7246573558727825743L;
	private transient Logger logger;
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

	/**
	 * create the bonus panel
	 * @param b, the board
	 * @param loggerArea, the area to write on
	 * @return the panel
	 */
	public JPanel createBonusPanel(Board b, JTextArea loggerArea){
		JPanel bonusPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		bonusPanel.setLayout(layout);
		bonusPanel.setBackground(new Color(245, 124, 255));

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill = GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------------consiglieri del re------------
		JPanel kingCouncillors=cp.kingbalcone(b.getKing());
		kingCouncillors.setName("consiglieri re");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=b.getTypes().size()-1;
		layout.setConstraints(kingCouncillors, lim);
		bonusPanel.add(kingCouncillors);

		//----------------bonus king------------
		JLabel kingBonus;
		if(b.getBonusKing().getCurrentBonusKing()!=0){
			BufferedImage img=getImg("bonusKing/"+b.getBonusKing().getCurrentBonusKing());
			kingBonus=new JLabel(new ImageIcon(img));
		}else{
			kingBonus=new JLabel("Bonus king finiti");
		}
		kingBonus.setName("bonus king");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(kingBonus, lim);
		bonusPanel.add(kingBonus);
		mouseOverKing(kingBonus, loggerArea, b);

		//----------------bonus region------------
		for(int k=0; k<b.getRegions().size(); k++){
			JLabel regionBonus;
			if(b.getRegions().get(k).isBonusAvailable()){
				BufferedImage img=getImg("bonusRegion/"+b.getRegions().get(k).getName());
				regionBonus=new JLabel(new ImageIcon(img));
			}else
				regionBonus=new JLabel("Bonus finiti");

			regionBonus.setName("bonus type "+b.getRegions().get(k).getName());
			lim.gridx = k+1;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.weightx=0;//espansione in verticale e orizzontale
			lim.weighty=0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			layout.setConstraints(regionBonus, lim);
			bonusPanel.add(regionBonus);
			mouseOverRegion(regionBonus, loggerArea, b.getRegions().get(k));
		}

		//----------------city type bonus------------
		for(int i=0; i<b.getTypes().size(); i++){
			JLabel cityTypeBonus;
			if(("Purple").equals(b.getTypes().get(i).getName())){
				lim.anchor = GridBagConstraints.CENTER;
			}else{
				if(b.getTypes().get(i).isBonusAvailable()){
					BufferedImage img=getImg("bonusType/"+b.getTypes().get(i).getName());
					cityTypeBonus=new JLabel(new ImageIcon(img));
				}else
					cityTypeBonus=new JLabel("Bonus finiti");

				cityTypeBonus.setName("bonus type "+b.getTypes().get(i).getName());
				lim.gridx = i;//posizione componenti nella griglia
				lim.gridy = 2;
				lim.weightx=1;//espansione in verticale e orizzontale
				lim.weighty=1;
				lim.gridheight=1;//grandezza del riquadro
				lim.gridwidth=1;
				layout.setConstraints(cityTypeBonus, lim);
				bonusPanel.add(cityTypeBonus);
				mouseOverType(cityTypeBonus, loggerArea, b.getTypes().get(i));
			}
		}

		return bonusPanel;
	}

	private void mouseOverKing(JLabel kingBonus, JTextArea loggerArea, Board b){
		kingBonus.addMouseListener(new MouseListener() {
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
				if(b.getBonusKing().getCurrentBonusKing()==0)
					loggerArea.append("\nBonus king finiti!");
				else
					loggerArea.append("\nBonus king disponibile: "+b.getBonusKing().getCurrentBonusKing()+"VictoryPoints");
			}
		});
	}

	private void mouseOverRegion(JLabel regionBonus, JTextArea loggerArea, Region reg){
		regionBonus.addMouseListener(new MouseListener() {
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
				if(!reg.isBonusAvailable())
					loggerArea.append("\nBonus "+reg.getName()+" finito!");
				else
					loggerArea.append("\nBonus "+reg.getName()+" disponibile: "+reg.getBonus().getName());
			}
		});
	}

	private void mouseOverType(JLabel typeBonus, JTextArea loggerArea, Type tipo){
		typeBonus.addMouseListener(new MouseListener() {
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
				if(!tipo.isBonusAvailable())
					loggerArea.append("\nBonus "+tipo.getName()+" finito!");
				else
					loggerArea.append("\nBonus "+tipo.getName()+" disponibile: "+tipo.getBonus().getName());
			}
		});
	}

	/**
	 * load the image
	 * @param name, the name of the image 
	 * @return the image
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
