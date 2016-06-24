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
	private NobilityTrack nt;
	private JTextArea loggerArea;

	private final double lung;

	/**
	 * 
	 * @param nt, the nobility track
	 * @param loggerArea, the area to read on
	 */
	public NobilityTrackPanel(NobilityTrack nt, JTextArea loggerArea) {
		lung=Toolkit.getDefaultToolkit().getScreenSize().width-10.0;

		this.nt=nt;
		this.loggerArea=loggerArea;
		//configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");//carica la configurazione del logger
	}

	/**
	 * create the nobility track boxes
	 * @return a panel with the nobility track
	 */
	public JPanel createNobility(){
		JPanel panel=new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill=GridBagConstraints.NONE;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		for(int i=0; i<nt.getNobilityBoxes().size(); i++){

			BufferedImage img=nobilityImg(i);
			double width= (9.0/16)*lung/21;
			double height=  ((double) img.getHeight()/img.getWidth())*width;
			Image myim=img.getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);//ridimensionamento immagine


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
			panel.add(boxLabel);//aggiunta della label al panel

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
					writeArea(loggerArea, nt, k);}
			});
		}

		return panel;
	}

	private void writeArea(JTextArea loggerArea, NobilityTrack nt, int k){
		if(nt.getNobilityBoxes().get(k).getBonus().toString()!="[]"){//bonus nullo
			loggerArea.append("\nNobility Track "+k+": ");
			for(int j=0; j<nt.getNobilityBoxes().get(k).getBonus().size(); j++){
				loggerArea.append(nt.getNobilityBoxes().get(k).getBonus().get(j).getName()+" ");
			}
		}
		if(nt.getNobilityBoxes().get(k).getPlayers().toString()!="[]"){//nessun player
			loggerArea.append(" , Player: ");
			for(int j=0; j<nt.getNobilityBoxes().get(k).getPlayers().size(); j++){
				loggerArea.append(nt.getNobilityBoxes().get(k).getPlayers().get(j).getUser());
			}
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

	/*public void update(){
		this.repaint();
	}*/
}
