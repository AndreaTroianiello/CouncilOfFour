package it.polimi.ingsw.cg23.controller.creation;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.view.CliInterface;

public class CreateCostruction {

	private CreateBonus cb=new CreateBonus("ConfigurazionePartita.xml");
	private CliInterface cl=new CliInterface();
	private List <BusinessPermitTile> costructionCard;//lista di carte costruzione
	
	public CreateCostruction(){
		costructionCard=new ArrayList<>();
	}
	
	/**
	 * create and add at the list the costruction cards
	 */
	public List<BusinessPermitTile> createCardCostruction(){
		String[][] array=cl.getCostruction("CostructionCard.xml");//informazioni sulle carte costruzione

		for(int i=0; i<array.length; i++){//ciclo che scorre tutte le carte costruzione
			List<Character> citiesId=new ArrayList<>();//lista di id delle citta'

			for(int j=0; j<array[i][1].length(); j++){//ciclo che scorre il numero di citta' della carta costruzione
				citiesId.add(array[i][1].charAt(j));//aggiungo l'id della citta' alla lista
			}
			
			BusinessPermitTile bpt=new BusinessPermitTile(citiesId, array[i][0]);//creo una nuova carta costruzione
			cb.getCostructorBonus(bpt, array[i][2]);//aggiungo i bonus alla carta costruzione
			costructionCard.add(bpt);//aggiungo la nuova carta costruzione alla lista			
		}
		return costructionCard;
	}

	/**
	 * fill the regiondeck with the costruction card
	 * @param region, the regions list
	 */
	public void createRegionDeck(List<Region> region){
		List<BusinessPermitTile> costructionRegionlist=new ArrayList<>();
		for(int k=0; k<region.size(); k++){//scorre le regioni
			for(int i=0; i<costructionCard.size(); i++){//scorre le carte costruzione
				if(region.get(k).getName().equals(costructionCard.get(i).getZone()))
					costructionRegionlist.add(costructionCard.get(i));
			}
			region.get(k).getDeck().setBusinessPermitTiles(costructionRegionlist);
		}
	}
}
