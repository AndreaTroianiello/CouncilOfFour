package it.polimi.ingsw.cg23.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.bonus.BonusKing;
import it.polimi.ingsw.cg23.model.bonus.BonusVictoryPoints;
import it.polimi.ingsw.cg23.model.components.Emporium;

public class Type1 {

	private BonusKing bonusKing;
	private final Board board;
	private final Map<String,Integer> bonus;

	public Type1(BonusKing bonusKing, Board board){
		this.bonus=new HashMap<>();
		this.bonusKing= bonusKing;		
		this.board=board;
		setCities();
	}
	
	//aggiunge l'osservatore alla città
	public void setCities(){
		List<Region> regions=board.getRegions();
		for(Region region: regions){
			List<City> cities=region.getCities();
			for(City city: cities);
				//city.setObjectType(this);
		}
	}
	
	//aggiunge il bonus alla mappa
	public void addBonus(String type,int value){
		this.bonus.put(type, value);
	}
	
	//controlla lo stato del tipo
	public boolean controlType(Player player,String type){
		List<Region> regions=board.getRegions();
		for(Region region: regions){
			List<City> cities=region.searchCityByType(type);
			for(City city: cities)
				if(!city.containsEmporium(player))
					return false;
		}
		return true;
	}
	
	//esegue il bonus della regione
	public boolean bonusRegion(Player player,Region region){
		if(region.isBonusAvailable())
			region.runBonusRegion(player);
		return false;
	}
	
	//esegue il bonus del tipo
	public boolean bonusType(Player player,String type){
		if(!controlType(player, type))
			return false;
		int value=bonus.get(type);
		new BonusVictoryPoints(value).giveBonus(player);
		return true;
	}
	
	//avvia i bonus ed esegue il bonus del re
	public void update(Emporium emporium) {
		Player player=emporium.getPlayer();
		City city=emporium.getCity();
		if(bonusRegion(player,city.getRegion()) || bonusType(player,city.getType()))
			bonusKing.runBonusKing(player);
	}

}
