package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Region;


public class BonusFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2192875760852143327L;
	private JPanel contentPane;
	private transient Logger logger;
	private Board b;
	private JTextArea loggerArea;

	/**
	 * Create the frame.
	 */
	public BonusFrame(Board b, JTextArea loggerArea) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.b=b;
		this.loggerArea=loggerArea;
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

	}
	
	/**
	 * create the bonus panel
	 * @return the panel
	 */
	public void createBonusPanel(){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill = GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		//----------------name bonus------------
		JLabel nameBonus=new JLabel("Bonus disponibili");
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=b.getRegions().size()+1;
		layout.setConstraints(nameBonus, lim);
		panel.add(nameBonus);

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
		panel.add(kingBonus);
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
			panel.add(regionBonus);
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
				panel.add(cityTypeBonus);
				mouseOverType(cityTypeBonus, loggerArea, b.getTypes().get(i));
			}
		}
		
		contentPane.add(panel);
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
				loggerArea.append("\nBonus ");
				if(!reg.isBonusAvailable())
					loggerArea.append(reg.getName()+" finito!");
				else
					loggerArea.append(reg.getName()+" disponibile: "+reg.getBonus().getName());
			}
		});
	}

	private void mouseOverType(JLabel typeBonus, JTextArea loggerArea, it.polimi.ingsw.cg23.server.model.Type tipo){
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
				loggerArea.append("\nBonus ");
				if(!tipo.isBonusAvailable())
					loggerArea.append(tipo.getName()+" finito!");
				else
					loggerArea.append(tipo.getName()+" disponibile: "+tipo.getBonus().getName());
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
