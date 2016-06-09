package it.polimi.ingsw.cg23.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * fist gui class
 *
 */
public class HelloFrame extends JFrame {

	private static final long serialVersionUID = -5251939376496501206L;
	private JPanel contentPane;
	private static Logger logger;
	private FrameMap fm;
	private BufferedImage image = null;
	final int time=20000;

	/**
	 * Create the frame.
	 */
	public HelloFrame() {
		fm=new FrameMap();

		//configurazione logger
		logger = Logger.getLogger(FrameMap.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger


		//immagine per informazioni grandezza
		try {
			image = ImageIO.read(new File("src/main/resources/images/preview.jpg"));
		} catch (IOException e) {

			logger.error("impossibile caricare l'ìmmagine", e);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, image.getWidth()+50, image.getHeight()+90);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setMaximumSize(new Dimension(image.getWidth()+50, image.getHeight()+90));
		setContentPane(contentPane);


		setPanel();

	}
	private void setPanel(){
		//panel north
		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.NORTH);
		panel1.setBorder(null);
		setBackgorund(panel1);//aggiungo lo sfondo

		//panel south
		JPanel panel2 = new JPanel();
		contentPane.add(panel2, BorderLayout.SOUTH);
		panel2.setBorder(null);
		addButton(panel2);//aggiungo i bottoni
	}

	private JLabel labelCountdown(){
		JLabel label=new JLabel(); 
		new TimerClass(label, time);//countdown
		return label;
	}

	/**
	 * create the background
	 * @param panel2
	 */
	private void setBackgorund(JPanel panel){

		//Create Image Label
		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(0, 0, image.getWidth(), image.getHeight());
		panel.add(label, JPanel.CENTER_ALIGNMENT);
	}

	/**
	 * add rmi and socket button
	 * @param panel2
	 */
	private void addButton(JPanel panel2){
		JButton btnRmi = new JButton("RMI");//bottone rmi
		btnRmi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.add(labelCountdown());//countdown di attesa
				fm.setVisible(true);//apro la finestra FrameMap
				setVisible(true);//chiudo la finestra corrente

				//azioni per rmi

			}
		});
		panel2.add(btnRmi, JPanel.BOTTOM_ALIGNMENT);

		JButton socket = new JButton("Socket");//bottone socket
		socket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.add(labelCountdown());//countodwn di attesa
				fm.setVisible(true);//apro la finestra FrameMap
				setVisible(false);//chiudo la finestra corrente
				//azioni per socket
			}
		});
		panel2.add(socket, JPanel.BOTTOM_ALIGNMENT);
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		//configurazione logger
		logger = Logger.getLogger(HelloFrame.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
		         
					HelloFrame frame = new HelloFrame();
					frame.setMaximumSize(new Dimension(500, 500));
					frame.setVisible(true);
				} catch (Exception e) {
					logger.error("Impossibile creare il frame", e);
				}
			}
		});
	}
}

