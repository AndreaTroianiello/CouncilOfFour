package it.polimi.ingsw.cg23.gui.mapframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
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
/**
 * create the statistic point of the player
 * @author viga94_
 *
 */
public class PlayerStatic extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -985042074969892802L;
	private JPanel contentPane;
	private transient Logger logger;
	private final double lung;//larghezza scermo

	/**
	 * Create the frame.
	 */
	public PlayerStatic() {
		lung=Toolkit.getDefaultToolkit().getScreenSize().width-10.0;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 400, (int) heightImg()*7);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Points");//nome del frame

		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger



	}

	/**
	 * display the points of the player
	 * @param p, the player
	 */
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
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(userLabel, lim);
		panel.add(userLabel);

		//----------victory points----------
		Image img1=createImage("Victorypoints", p.getVictoryTrack().getVictoryPoints());
		JLabel vicotrypoint = new JLabel(new ImageIcon(img1));//aggiungo l'immagine alla label
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.fill=GridBagConstraints.BOTH;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(vicotrypoint, lim);
		panel.add(vicotrypoint);
		vicotrypoint.add(new JLabel("caio"));

		JLabel vicotrLabel = new JLabel("The victory points you have");//aggiungo l'immagine alla label
		vicotrLabel.setForeground(new Color(255, 255, 255));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(vicotrLabel, lim);
		panel.add(vicotrLabel);

		//----------coin---------
		Image img2=createImage("Coin", p.getRichness().getCoins());
		JLabel coin = new JLabel(new ImageIcon(img2));//aggiungo l'immagine alla label
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(coin, lim);
		panel.add(coin);

		JLabel coinLabel = new JLabel("Your coins");//aggiungo l'immagine alla label
		coinLabel.setForeground(new Color(255, 255, 255));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(coinLabel, lim);
		panel.add(coinLabel);

		//----------nobility track----------
		Image img3=createImage("Nobility track", p.getNobilityBoxPosition());
		JLabel nobilityTrack = new JLabel(new ImageIcon(img3));//aggiungo l'immagine alla label
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(nobilityTrack, lim);
		panel.add(nobilityTrack);

		JLabel nobilityLabel = new JLabel("Your nobility box posiztion");//aggiungo l'immagine alla label
		nobilityLabel.setForeground(new Color(255, 255, 255));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(nobilityLabel, lim);
		panel.add(nobilityLabel);

		//----------assistants----------
		Image img4=createImage("Assistants", p.getAssistantsPool().getAssistants());

		JLabel assistant = new JLabel(new ImageIcon(img4));//aggiungo l'immagine alla label
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(assistant, lim);
		panel.add(assistant);

		JLabel assistantLabel = new JLabel("Your assistants");//aggiungo l'immagine alla label
		assistantLabel.setForeground(new Color(255, 255, 255));
		lim.gridx = 1;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(assistantLabel, lim);
		panel.add(assistantLabel);

		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel);
	}

	/**
	 * load the image and add the text
	 * @param name, the image name
	 * @param num, the text to write over the image
	 * @return, a buffered image
	 */
	private Image createImage(String name, int num){
		BufferedImage img = getImg(name);
		Graphics2D g2d = img.createGraphics();
		g2d.drawImage(img, 0, 0, null);
		g2d.setPaint(Color.white);
		g2d.setFont(new Font("Serif", Font.BOLD, 50));
		String s = Integer.toString(num);
		FontMetrics fm = g2d.getFontMetrics();
		int x = (img.getWidth() - fm.stringWidth(s) )/2;
		int y =(img.getHeight() +fm.getHeight() )/2;
		g2d.drawString(s, x, y);
		g2d.dispose();

		double width= (2.0/50)*lung;
		double height=  ((double) img.getHeight()/img.getWidth())*width;

		return img.getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);//ridimensionamento immagine

	}

	private double heightImg(){
		BufferedImage img=getImg("Coin");
		double width=(2.0/50)*lung;
		return  ((double) img.getHeight()/img.getWidth())*width;

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
