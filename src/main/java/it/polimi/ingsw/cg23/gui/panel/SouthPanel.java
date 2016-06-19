package it.polimi.ingsw.cg23.gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

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
			if(i==0)costruzione.setBackground(new Color(204, 255, 255));
			if(i==1)costruzione.setBackground(new Color(204, 255, 204));
			if(i==2)costruzione.setBackground(new Color(188, 144, 101));
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
			JPanel balcone=cp.balcone(reg.get(i));
			if(i==0)balcone.setBackground(new Color(204, 255, 255));
			if(i==1)balcone.setBackground(new Color(204, 255, 204));
			if(i==2)balcone.setBackground(new Color(188, 144, 101));
			balcone.setName("balcone "+reg.get(i).getName());
			lim.gridx = i;//posizione componenti nella griglia
			lim.gridy = 1;
			lim.gridheight=1;//grandezza del riquadro
			lim.gridwidth=1;
			lim.weightx=1;//espansione in verticale e orizzontale
			lim.weighty=0;
			layout.setConstraints(balcone, lim);
			southPanel.add(balcone);
		}
		
		//----------------nobility track------------
		BufferedImage img=nobilityImg();
		JLabel l4=new JLabel(new ImageIcon(img));
		l4.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
		l4.setName("Nobility panel");
		JScrollPane c8 = new JScrollPane(l4);
		c8.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()+3));
		c8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		c8.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		lim.gridx = 0;//posizione componenti nella griglia
		lim.gridy = 2;
		lim.weightx = 1;//occupa tutto lo spazio all'interno del riquadro
		lim.weighty = 1;
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
		lim.gridheight=3;//grandezza del riquadro
		lim.gridwidth=2;
		layout.setConstraints(politics, lim);
		southPanel.add(politics);
		
		//----------------consiglieri del re------------
		JPanel kingCouncillors=cp.kingbalcone(b.getKing());
		kingCouncillors.setBackground(new Color(116, 255, 181));
		kingCouncillors.setName("consiglieri re");
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 3;
		lim.weightx=1;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(kingCouncillors, lim);
		southPanel.add(kingCouncillors);
		
		JLabel kingBonus=new JLabel();
		//kingBonus.setFont(new Font("Calibre", Font.PLAIN, 14));
		kingBonus.setText("Bonus king corrente: "+b.getBonusKing().getCurrentBonusKing());
		kingBonus.setBackground(new Color(116, 255, 181));
		kingBonus.setOpaque(true);
		kingBonus.setName("bonus king");
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 4;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(kingBonus, lim);
		southPanel.add(kingBonus);
		
		JLabel cityTypeBonus=new JLabel();
		
		String typeBonus="";
		for(int i=0; i<b.getTypes().size(); i++){
			if(b.getTypes().get(i).getName().equals("Purple")){
				
			}else
				typeBonus+=b.getTypes().get(i).getName()+": "+b.getTypes().get(i).getBonus().getNumber()+", ";
		}
		cityTypeBonus.setText("Type: "+typeBonus.substring(0, typeBonus.length()-2));
		cityTypeBonus.setBackground(new Color(116, 255, 181));
		cityTypeBonus.setOpaque(true);
		cityTypeBonus.setName("bonus type");
		lim.fill = GridBagConstraints.BOTH;
		lim.gridx = 2;//posizione componenti nella griglia
		lim.gridy = 5;
		lim.weightx=0;//espansione in verticale e orizzontale
		lim.weighty=0;
		lim.gridheight=1;//grandezza del riquadro
		lim.gridwidth=1;
		layout.setConstraints(cityTypeBonus, lim);
		southPanel.add(cityTypeBonus);
		
		return southPanel;
	}
	
	private BufferedImage nobilityImg(){//recupero l'immagine della carta costruzione
		BufferedImage image=null;
		String path="src/main/resources/images/Nobility track.png";//percorso dell'immagine
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("err");
			//logger.error("impossibile caricare l'ìmmagine della carta costruzione: "+path, e);
		}

		return image;
	}
	
}
