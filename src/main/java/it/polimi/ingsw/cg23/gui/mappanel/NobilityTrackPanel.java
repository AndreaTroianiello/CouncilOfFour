package it.polimi.ingsw.cg23.gui.mappanel;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;


/**
 * create the nobility track
 * @author viga94_
 *
 */
public class NobilityTrackPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2715614441311015393L;
	private transient Logger logger;
	private JTextArea loggerArea;

	private final double lung;
	private transient ClientModel model;
	private double widthBox;
	private NobilityTrack nt;

	/**
	 * 
	 * @param model, the model
	 * @param loggerArea, the area to read on
	 */
	public NobilityTrackPanel(ClientModel model, JTextArea loggerArea) {
		lung=Toolkit.getDefaultToolkit().getScreenSize().width-10.0;
		this.model=model;
		this.loggerArea=loggerArea;
		this.nt=model.getModel().getNobilityTrack();

		widthBox= (9.0/16)*lung/nt.getNobilityBoxes().size();

		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger

		init();
	}

	/**
	 * create the nobility track boxes
	 * @return a panel with the nobility track
	 */
	private void init(){
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		for(int i=0; i<nt.getNobilityBoxes().size(); i++){

			BufferedImage img=nobilityImg(i);
			double height=  ((double) img.getHeight()/img.getWidth())*widthBox;
			Image myim=img.getScaledInstance((int) widthBox, (int) height, Image.SCALE_DEFAULT);//ridimensionamento immagine

			JLabel boxLabel = new JLabel(new ImageIcon(myim));

			boxLabel.setName("box "+i);
			boxLabel.setToolTipText("Box Nobility Track "+i);
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=0;//espansione in verticale e orizzontale
			lim.weighty=1;

			layout.setConstraints(boxLabel, lim);
			add(boxLabel);//aggiunta della label al panel

			final int k=i;
			boxLabel.addMouseListener(new MouseListener() {
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
					writeArea(loggerArea, nt, k);
				}
			});
		}
	}

	/**
	 * calcola the le lenght of nobility track
	 * @return, the lenght of nobility track
	 */
	public int nobilityLenght(){
		return (int) widthBox*(model.getModel().getNobilityTrack().getNobilityBoxes().size());
	}

	private void writeArea(JTextArea loggerArea, NobilityTrack nt, int k){
		loggerArea.append("\nNobility Track "+k+"");
		if(nt.getNobilityBoxes().get(k).getBonus().toString()!="[]"){//bonus nullo
			loggerArea.append("\n  Bonus: ");
			String bonus="";

			for(int j=0; j<nt.getNobilityBoxes().get(k).getBonus().size(); j++){
				bonus=bonus.concat(nt.getNobilityBoxes().get(k).getBonus().get(j).getName()+", ");
			}

			loggerArea.append(bonus.substring(0, bonus.length()-2));
		}
		if(nt.getNobilityBoxes().get(k).getPlayers().toString()!="[]"){//nessun player
			loggerArea.append("\n  Player: ");
			String player="";

			for(int j=0; j<nt.getNobilityBoxes().get(k).getPlayers().size(); j++){
				if("NaN".equals(nt.getNobilityBoxes().get(k).getPlayers().get(j).getUser()))
					player=player.concat("");
				else
					player=player.concat(nt.getNobilityBoxes().get(k).getPlayers().get(j).getUser()+", ");

			}
			loggerArea.append(player.substring(0,player.length()-2));
		}
	}

	private BufferedImage nobilityImg(int num){//recupero l'immagine del nobility box
		BufferedImage image=null;
		String path="src/main/resources/images/nobilityTrack/"+num+".png";//percorso dell'immagine

		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			logger.error("impossibile caricare l'ìmmagine del nobility track: "+path, e);
		}

		return image;
	}
}
