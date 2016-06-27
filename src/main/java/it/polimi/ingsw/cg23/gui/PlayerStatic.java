package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.Player;

public class PlayerStatic extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -985042074969892802L;
	private JPanel contentPane;
	private transient Logger logger;



	/**
	 * Create the frame.
	 */
	public PlayerStatic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setResizable(false);
		setBackground(new Color(0, 0, 0));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

		
	}
	
	public void createGrid(Player p){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri
		
		JLabel userLabel = new JLabel(p.getUser());//aggiungo l'immagine alla label
		userLabel.setFont(new Font("Calibre", Font.PLAIN, 20));
		userLabel.setForeground(new Color(255, 255, 255));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 0;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(userLabel, lim);
		panel.add(userLabel);
		
		BufferedImage img1=getImg("Victorypoints");
		JLabel vicotrypoint = new JLabel(new ImageIcon(img1));//aggiungo l'immagine alla label
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(vicotrypoint, lim);
		panel.add(vicotrypoint);
		
		BufferedImage img2=getImg("Coin");
		JLabel coin = new JLabel(new ImageIcon(img2));//aggiungo l'immagine alla label
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(coin, lim);
		panel.add(coin);
		
		BufferedImage img3=getImg("Nobility track");
		JLabel nobilityTrack = new JLabel(new ImageIcon(img3));//aggiungo l'immagine alla label
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(nobilityTrack, lim);
		panel.add(nobilityTrack);
		
		BufferedImage img4=getImg("Assistants");
		JLabel assistant = new JLabel(new ImageIcon(img4));//aggiungo l'immagine alla label
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(assistant, lim);
		panel.add(assistant);
		
		panel.setOpaque(false);
		contentPane.add(panel);
	}
	
	private BufferedImage getImg(String name){//recupero l'immagine della carta costruzione
		BufferedImage image=null;
		String path="src/main/resources/images/stat/"+name+".png";//percorso dell'immagine

		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine: "+path, e);
		}

		return image;
	}
}
