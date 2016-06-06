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

public class FrameMap extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7022521494087312889L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMap frame = new FrameMap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameMap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		//JScrollPane scrollPane = new JScrollPane();
		
		panel.add(setBackground());
		
	}

	public JScrollPane setBackground(){
		//Load Image
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("src/main/resources/BackgroundIMG.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Create Image Label
		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(0, 0, image.getWidth(), image.getHeight());

		//Create Layered Pane
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));


		//Populate Layered Pane
		layeredPane.add(label, JLayeredPane.DEFAULT_LAYER-1);

		//Create ScrollPane
		JScrollPane scrollPanel = new JScrollPane(layeredPane);
		scrollPanel.setVisible(true);
		return scrollPanel;
	}
}
