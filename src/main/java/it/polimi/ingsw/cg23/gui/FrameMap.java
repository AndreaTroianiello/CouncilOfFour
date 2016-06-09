package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * create the map
 */
public class FrameMap extends JFrame {

	private static final long serialVersionUID = 7022521494087312889L;
	private JLayeredPane contentPane;
	private static Logger logger;

	private final int totalLengh;
	private final int totalHeight;
	
	private transient BufferedImage pointsImg;
	private transient BufferedImage mapImg;
	
	/**
	 * Create the frame.
	 */
	public FrameMap() {
		//configurazione logger
		logger = Logger.getLogger(FrameMap.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

		//configurazione immagini
		pointsImg=createPointImg();
		mapImg=createMapImg();
		
		//configurazione dimensioni
		totalHeight=pointsImg.getHeight()+mapImg.getHeight()+65;//altezza totale (la mappa completa: mappa + points)
		totalLengh=mapImg.getWidth()+400;//larghezza totale (immagine + spazio per il logger)
		
		//configurazione contentPane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, totalLengh, totalHeight);//dimensione finestra
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//aggiungo i layer
		contentPane.add(setMapPanel(), BorderLayout.WEST);//aggiunta dell'immagine di sfondo al jpanel
		contentPane.add(setLoggerPanel(), BorderLayout.EAST);//aggiunta dell'immagine di sfondo al jpanel
		
	}
		
	private BufferedImage createPointImg(){
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("src/main/resources/images/points.png"));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine", e);
		}
		
		return image;
	}
	
	private BufferedImage createMapImg(){
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("src/main/resources/images/BackgroundIMG.jpg"));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine", e);
		}
		
		return image;
	}
	
	/**
	 * add the background image
	 * @return scrollPanel
	 */
	private JScrollPane setMapPanel(){

		//Create Image Label1 (mappa)
		JLabel label1 = new JLabel(new ImageIcon(mapImg));
		label1.setBounds(0, 0, mapImg.getWidth(), mapImg.getHeight());
		
		//Create Image Label2 (points)
		JLabel label2 = new JLabel(new ImageIcon(pointsImg));
		label2.setBounds(0, 0, pointsImg.getWidth(), pointsImg.getHeight());
		
		//Create Layered Pane
		JPanel layeredPane1 = new JPanel();
		layeredPane1.setPreferredSize(new Dimension(mapImg.getWidth(), totalHeight-50));

		//Populate Layered Pane
		layeredPane1.add(label1, JLayeredPane.TOP_ALIGNMENT);
		layeredPane1.add(label2, JLayeredPane.TOP_ALIGNMENT);

		//Create ScrollPanel
		JScrollPane scrollPanel = new JScrollPane(layeredPane1);
		
		//preferences scrollPanel
		scrollPanel.setPreferredSize(new Dimension(mapImg.getWidth()+20, totalHeight-50));
		scrollPanel.getVerticalScrollBar();//barra scorrimento verticale
		scrollPanel.getHorizontalScrollBar();//barra scorrimento orizzontale

		return scrollPanel;
	}
	
	/**
	 * create the logger plane
	 * @return the scrollpane
	 */
	private JScrollPane setLoggerPanel(){
		//crezione layer Panel
		JPanel layeredPane= new JPanel();
		layeredPane.setPreferredSize(new Dimension(1000, totalHeight-50));//dimensione
		layeredPane.setBackground(new Color(255, 255, 255));//colore sfondo
		
		//creazione bottone exit
		JButton button1 = new JButton("Exit");
		layeredPane.add(button1, JPanel.LEFT_ALIGNMENT);//aggiunta bottone al layer panel
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelloFrame().setVisible(true);//apro la finestra HelloFrame
				setVisible(false);//chiudo la finestra corrente
			}
		});
		
		//creazione etichetta logger
		JLabel label1=new JLabel("Logger");//etichetta
		label1.setFont(new Font(null, Font.PLAIN, 20));//font della label
		label1.setForeground(new Color(0,0,0));//colore della label
		layeredPane.add(label1);//aggiuno la label al layer panel
		//creazione textArea
		JTextArea textArea=createTextArea();
		
		//creazione bottone clear
		JButton button2 = new JButton("Clear");
		layeredPane.add(button2);//aggiunta bottone al layer panel
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		
		//aggiungo la text area al layer panel
		layeredPane.add(textArea, JLayeredPane.TOP_ALIGNMENT);
		
		//creazione scroll panel
		JScrollPane scrollPanel = new JScrollPane(layeredPane,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,//barra verticale
		        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED//barra orizzontale
		);
		
		//settaggio scroll panel
		scrollPanel.setPreferredSize(new Dimension(360, totalHeight-50));
		scrollPanel.setAutoscrolls(true);

		return scrollPanel;
	} 

	/**
	 * create the logger textarea
	 * @return
	 */
	private JTextArea createTextArea(){
		JTextArea textArea = new JTextArea();
		
		textArea.setFont(new Font(null, Font.PLAIN, 15));//font della text area
		textArea.setPreferredSize(new Dimension(1000, totalHeight-100));//dimensione text area
		textArea.setBackground(new Color(255,255,255));//sfondo bianco
		textArea.setForeground(new Color(0,0,0));//scritte nere

		//replica la console sulla text area
		TextAreaOutputStream taos = new TextAreaOutputStream(textArea, 10);
		PrintStream ps = new PrintStream( taos );
		System.setOut(ps);//scrive l'out nella text area
		System.setErr(ps);//scrive l'err sulla text area
		return textArea;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				FrameMap frame = null;
				try {
					frame = new FrameMap();
					frame.setVisible(true);
				} catch (Exception e) {
					logger.error("errore frame", e);				
				}
			}
		});
	}

}

