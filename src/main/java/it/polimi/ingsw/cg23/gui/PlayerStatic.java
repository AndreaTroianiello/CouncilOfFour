package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
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

		//----------nome utente----------
		JLabel userLabel = new JLabel("Point "+p.getUser());//aggiungo l'immagine alla label
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

		//----------victory points----------
		BufferedImage img1=createImage("Victorypoints", p.getVictoryTrack().getVictoryPoints());
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
		vicotrypoint.add(new JLabel("caio"));

		//----------coin---------
		BufferedImage img2=createImage("Coin", p.getRichness().getCoins());
		JLabel coin = new JLabel(new ImageIcon(img2));//aggiungo l'immagine alla label
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(coin, lim);
		panel.add(coin);

		//----------nobility track----------
		BufferedImage img3=createImage("Nobility track", p.getNobilityBoxPosition());
		JLabel nobilityTrack = new JLabel(new ImageIcon(img3));//aggiungo l'immagine alla label
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(nobilityTrack, lim);
		panel.add(nobilityTrack);

		//----------assistants----------
		BufferedImage img4=createImage("Assistants", p.getAssistantsPool().getAssistants());
		JLabel assistant = new JLabel(new ImageIcon(img4));//aggiungo l'immagine alla label
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(assistant, lim);
		panel.add(assistant);

		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel);
	}

	/**
	 * load the image and add the text
	 * @param name, the image name
	 * @param num, the text to write over the image
	 * @return, a buffered image
	 */
	private BufferedImage createImage(String name, int num){
		BufferedImage img = getImg(name);
		Graphics2D g2d = img.createGraphics();
		g2d.drawImage(img, 0, 0, null);
		g2d.setPaint(Color.white);
		g2d.setFont(new Font("Serif", Font.BOLD, 80));
		String s = Integer.toString(num);
		FontMetrics fm = g2d.getFontMetrics();
		int x = (img.getWidth() - fm.stringWidth(s) )/2;
		int y = (img.getHeight() +fm.getHeight() )/2;
		g2d.drawString(s, x, y);
		g2d.dispose();
		return img;
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
