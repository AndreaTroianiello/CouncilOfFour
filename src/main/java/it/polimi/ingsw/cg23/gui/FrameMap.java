package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class FrameMap extends JFrame {

	private static final long serialVersionUID = 7022521494087312889L;
	private JLayeredPane contentPane;
	private static Logger logger;

	/**
	 * Create the frame.
	 */
	public FrameMap() {
		//configurazione logger
		logger = Logger.getLogger(FrameMap.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 800);//dimensione finestra


		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(setMapPanel(), BorderLayout.WEST);//aggiunta dell'immagine di sfondo al jpanel
		
		contentPane.add(setLoggerPanel(), BorderLayout.EAST);//aggiunta dell'immagine di sfondo al jpanel

	}

	/**
	 * add the background image
	 * @return scrollPanel
	 */
	private JScrollPane setMapPanel(){
		
		
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
		JPanel layeredPane1 = new JPanel();
		layeredPane1.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

		//Populate Layered Pane
		layeredPane1.add(label, JLayeredPane.DEFAULT_LAYER);
		
		//Create ScrollPane
		JScrollPane scrollPanel = new JScrollPane(layeredPane1);
		scrollPanel.setPreferredSize(new Dimension(800, 1000));
		scrollPanel.getVerticalScrollBar();//barra scorrimento verticale
		scrollPanel.getHorizontalScrollBar();//barra scorrimento orizzontale

		return scrollPanel;
	}
	
	private JScrollPane setLoggerPanel(){

		//Create Layered Pane
		JPanel layeredPane= new JPanel();

		
		layeredPane.setPreferredSize(new Dimension(200, 800));
		layeredPane.setBackground(new Color(255, 120, 120));
		
		JButton button1 = new JButton("Back");
		layeredPane.add(button1, JLayeredPane.DEFAULT_LAYER);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		//Create ScrollPane
		JScrollPane scrollPanel = new JScrollPane(layeredPane);
		scrollPanel.setPreferredSize(new Dimension(250, 800));
		scrollPanel.setAutoscrolls(true);
		//scrollPanel.setMaximumSize(new Dimension(200, 800));
		scrollPanel.getVerticalScrollBar();//barra scorrimento verticale
		//scrollPanel.getHorizontalScrollBar();//barra scorrimento orizzontale

		return scrollPanel;
	} 
	
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
	
}

