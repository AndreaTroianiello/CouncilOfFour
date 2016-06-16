package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

/**
 * create the south panel
 * @author viga94_
 *
 */
public class SouthPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1220509934759316582L;
	private CostructionCardPanel ccp;
	private CouncilPanel cp;
	/**
	 * Create the panel.
	 */
	public SouthPanel() {
		this.ccp=new CostructionCardPanel();
		this.cp=new CouncilPanel();
	}
	
	/**
	 * create the south panel
	 * @param s, the avvio
	 * @return a jpanel with the south panel created
	 */
	public JPanel setSouthPanel(Board b){
		JPanel southPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		southPanel.setLayout(layout);
		
		GridBagConstraints lim = new GridBagConstraints(); 
		lim.fill = GridBagConstraints.HORIZONTAL;//grandezza componenti nei riquadri (both= tutto pieno)
		lim.anchor = GridBagConstraints.CENTER;//posizione componenti nei riquadri

		List<Region> reg=b.getRegions();
		for(int i=0; i<reg.size(); i++){//scorre le regioni-> aggiunge le carte permesso
			//----------carte permesso di costruzione----------
			
			JPanel costruzione=ccp.getShowCostructionCard(reg.get(i));
			costruzione.setName("costruzione "+reg.get(i).getName());
			costruzione.setBackground(new Color(206, 177, 129));
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 0;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=0;
			layout.setConstraints(costruzione, lim);
			southPanel.add(costruzione);
		}
		
		for(int i=0; i<reg.size(); i++){//scorre le regioni-> aggiunge i consiglieri
			JPanel balcone3=cp.balcone(reg.get(i));
			balcone3.setBackground(new Color(116, 184, 181));
			balcone3.setName("balcone "+reg.get(i).getName());
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=0;
			layout.setConstraints(balcone3, lim);
			southPanel.add(balcone3);
		}
		
		//----------------nobility track------------
		JLabel l4=new JLabel();
		
		l4.setName("Nobility panel");
		l4.setText("nobility track");
		Component c8 = new JScrollPane(l4);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=3;
		
		layout.setConstraints(c8, lim);
		southPanel.add(c8);
		
		//----------------carte politiche------------
		Player p=new Player("io", new NobilityTrack(20));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(145,123,241), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(145,255,241), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(255,0,255), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(145,123,241), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(145,255,241), false));//PROVA->provvisorio
		p.getHand().add(new PoliticCard(new Color(255,123,241), false));//PROVA->provvisorio
		
		
		JPanel politics=new PoliticCardPanel().createCard(p);
		politics.setName("Carte politiche");
		politics.setBackground(new Color(193,197,192));
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(politics, lim);
		southPanel.add(politics);
		
		//----------------consiglieri del re------------
		JPanel kingCouncillors=cp.kingbalcone(b.getKing());
		kingCouncillors.setBackground(new Color(116, 184, 181));
		kingCouncillors.setName("Carte politiche");
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(kingCouncillors, lim);
		southPanel.add(kingCouncillors);
		
		return southPanel;
	}
	
	private BufferedImage getCostructionImg(){//recupero l'immagine della carta costruzione
		BufferedImage image=null;
		String path="src/main/resources/images/Nobility track.png";//percorso dell'immagine
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			//logger.error("impossibile caricare l'ìmmagine della carta costruzione: "+path, e);
		}

		return image;
	}
}
