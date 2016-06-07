package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class FrameMap extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7022521494087312889L;
	private JPanel contentPane;
	private static Logger logger;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMap frame = new FrameMap();
					frame.setVisible(true);
				} catch (Exception e) {
					logger.error("errore frame", e);				
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameMap() {

		//configurazione logger
		logger = Logger.getLogger(FrameMap.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 1000);//dimensione finestra


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);


		contentPane.add(setBackground());//aggiunta dell'immagine di sfondo al jpanel

	}

	public JScrollPane setBackground(){
		//Load Image
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("src/main/resources/BackgroundIMG.jpg"));
		} catch (IOException e) {

			logger.error("impossibile caricare l'ìmmagine", e);
		}

		//Create Image Label
		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(0, 0, image.getWidth(), image.getHeight());

		//Create Layered Pane
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));


		//Populate Layered Pane
		layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);

		//Create ScrollPane
		JScrollPane scrollPanel = new JScrollPane(layeredPane);
		scrollPanel.getVerticalScrollBar();//barra scorrimento verticale
		scrollPanel.getHorizontalScrollBar();//barra scorrimento orizzontale

		return scrollPanel;
	}
}

