package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.JTextArea;

/**
 * create the map
 */
public class FrameMap extends JFrame {

	private static final long serialVersionUID = 7022521494087312889L;
	private JLayeredPane contentPane;
	private static Logger logger;
	
	private int totalLengh=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int totalHeight=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	/**
	 * Create the frame.
	 */
	public FrameMap() {
		//configurazione logger
		logger = Logger.getLogger(FrameMap.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, totalLengh, totalHeight);//dimensione finestra
		setBounds(0, 0, 2000, 2000);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
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
			image = ImageIO.read(new File("src/main/resources/images/BackgroundIMG.jpg"));
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
		scrollPanel.setPreferredSize(new Dimension(totalLengh-325, totalHeight));
		scrollPanel.getVerticalScrollBar();//barra scorrimento verticale
		scrollPanel.getHorizontalScrollBar();//barra scorrimento orizzontale

		return scrollPanel;
	}

	/**
	 * create the logger plane
	 * @return the scrollpane
	 */
	private JScrollPane setLoggerPanel(){
		JPanel layeredPane= new JPanel();
		layeredPane.setPreferredSize(new Dimension(280, totalHeight));
		layeredPane.setBackground(new Color(0, 0, 0));
		
		//Create Layered Pane
		JPanel layeredPane1= new JPanel();
		layeredPane1.setPreferredSize(new Dimension(280, 35));
		layeredPane1.setBackground(new Color(0, 0, 0));

		JButton button1 = new JButton("Exit");
		layeredPane1.add(button1, JLayeredPane.TOP_ALIGNMENT);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelloFrame().setVisible(true);//apro la finestra HelloFrame
				setVisible(false);//chiudo la finestra corrente
			}
		});
		
		JLabel label1=new JLabel("Logger");
		label1.setFont(new Font(null, Font.PLAIN, 20));
		label1.setForeground(new Color(255,255,255));
		layeredPane1.add(label1);
		
		layeredPane.add(layeredPane1);//aggiungo pane1 al pane
		
		
		JPanel layeredPane2= new JPanel();
		layeredPane2.setPreferredSize(new Dimension(280, totalHeight-30));
		layeredPane2.setBackground(new Color(0, 0, 0));
		layeredPane2.add(createTextArea(), JLayeredPane.TOP_ALIGNMENT);
		
		layeredPane.add(layeredPane2);//aggiungo pane2 al pane
		
		//Create ScrollPane
		JScrollPane scrollPanel = new JScrollPane(layeredPane);
		
		scrollPanel.setPreferredSize(new Dimension(300, totalHeight));
		scrollPanel.setAutoscrolls(true);
		scrollPanel.getVerticalScrollBar();//barra scorrimento verticale
		scrollPanel.getHorizontalScrollBar();//barra scorrimento orizzontale

		return scrollPanel;
	} 
	
	/**
	 * create the logger textarea
	 * @return
	 */
	private JTextArea createTextArea(){
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font(null, Font.PLAIN, 15));
		textArea.setPreferredSize(new Dimension(280, totalHeight-30));//dimensione text area
		textArea.setBackground(new Color(0, 0, 0));//sfondo nero
		textArea.setForeground(new Color(255,255,255));//scritte bianche
		textArea.setText("\n\nciao come va \npoi non so come sono \n e non sei \n per \nperc \nsedf \nedcome");
		
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

