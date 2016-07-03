package it.polimi.ingsw.cg23.client.gui.mapframe;

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
import it.polimi.ingsw.cg23.server.model.components.BonusKing;

/**
 * BonusFrame manages the game's bonuses.
 * @author Andrea
 *
 */
public class BonusFrame extends JFrame {

	private static final long serialVersionUID = -2192875760852143327L;
	private JPanel contentPane;
	private transient Logger logger;
	private Board b;
	private JTextArea loggerArea;

	/**
	 * Create the frame.
	 * @param b, the baord
	 * @param loggerArea, the area to read on
	 */
	public BonusFrame(Board b, JTextArea loggerArea) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 500, 200);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Bonus");

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
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=b.getBonusKing().getBonusValues().size();
		layout.setConstraints(nameBonus, lim);
		panel.add(nameBonus);

		int p=0;//posizione colonna componenti bonus king
		//----------------bonus king label------------
		BonusKing bk=b.getBonusKing();
		JLabel bonuskingLabel=new JLabel("King");
		lim.gridx = p;//posizione componenti nella griglia
		lim.gridy = 1;
		lim.anchor = GridBagConstraints.WEST;//posizione componenti nei riquadri
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(bonuskingLabel, lim);
		panel.add(bonuskingLabel);
		p++;

		//----------------bonus king------------
		JLabel kingBonus;
		for(int i=0; i<bk.getBonusValues().size()-1; i++){//scrorre i bonus king disponibili
			if(bk.getBonusValues().get(i).intValue()<=bk.getCurrentBonusKing()){
				BufferedImage img=getImg("bonusKing/"+bk.getBonusValues().get(i).intValue());
				kingBonus=new JLabel(new ImageIcon(img));
				kingBonus.setName("bonus king");
				lim.gridx = p;//posizione componenti nella griglia
				lim.gridy = 1;
				lim.weightx=1;//espansione in verticale e orizzontale
				lim.weighty=1;
				lim.gridheight=1;//grandezza del riquadro
				lim.gridwidth=1;
				layout.setConstraints(kingBonus, lim);
				panel.add(kingBonus);
				mouseOverKing(kingBonus, loggerArea, b);
				p++;
			}
		}

		int q=0;//posizione componenti colonna bonus region
		//----------------bonus region label------------
		JLabel bonusRegionLabel=new JLabel("Region");
		lim.gridx = q;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(bonusRegionLabel, lim);
		panel.add(bonusRegionLabel);
		q++;

		//----------------bonus region------------
		for(int k=0; k<b.getRegions().size(); k++){//scorre le regioni
			JLabel regionBonus;
			if(b.getRegions().get(k).isBonusAvailable()){
				BufferedImage img=getImg("bonusRegion/"+b.getRegions().get(k).getName());
				regionBonus=new JLabel(new ImageIcon(img));

				lim.gridx = q;//posizione componenti nella griglia
				lim.gridy = 2;
				lim.weightx=1;//espansione in verticale e orizzontale
				lim.weighty=1;
				lim.gridheight=1;//grandezza del riquadro
				lim.gridwidth=1;

				layout.setConstraints(regionBonus, lim);
				panel.add(regionBonus);
				mouseOverRegion(regionBonus, loggerArea, b.getRegions().get(k));
				q++;
			}
		}

		int r=0;//posizione componenti colonna bonus type
		//----------------bonus region label------------
		JLabel bonusTypeLabel=new JLabel("Type");

		lim.gridx = r;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=1;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;

		layout.setConstraints(bonusTypeLabel, lim);
		panel.add(bonusTypeLabel);
		r++;

		//----------------city type bonus------------
		for(int i=0; i<b.getTypes().size(); i++){
			JLabel cityTypeBonus;
			if(("Purple").equals(b.getTypes().get(i).getName())){
				lim.anchor = GridBagConstraints.CENTER;
			}else{
				if(b.getTypes().get(i).isBonusAvailable()){
					BufferedImage img=getImg("bonusType/"+b.getTypes().get(i).getName());
					cityTypeBonus=new JLabel(new ImageIcon(img));
					cityTypeBonus.setName("bonus type "+b.getTypes().get(i).getName());

					lim.gridx = r;//posizione componenti nella griglia
					lim.gridy = 3;
					lim.weightx=1;//espansione in verticale e orizzontale
					lim.weighty=1;
					lim.gridheight=1;//grandezza del riquadro
					lim.gridwidth=1;

					layout.setConstraints(cityTypeBonus, lim);
					panel.add(cityTypeBonus);
					mouseOverType(cityTypeBonus, loggerArea, b.getTypes().get(i));
					r++;
				}
			}
		}

		contentPane.add(panel);
	}

	/**
	 * action listener mouse
	 * @param kingBonus, the king bonus
	 * @param loggerArea, the logger area
	 * @param b, the board
	 */
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

	/**
	 * mouse action over region
	 * @param regionBonus, the region bonuses
	 * @param loggerArea
	 * @param reg, the regions
	 */
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

	/**
	 * mouse listener over types
	 * @param typeBonus
	 * @param loggerArea
	 * @param tipo
	 */
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
