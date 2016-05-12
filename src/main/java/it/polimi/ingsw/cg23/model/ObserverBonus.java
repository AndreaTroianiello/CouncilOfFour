package it.polimi.ingsw.cg23.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.bonus.BonusKing;
import it.polimi.ingsw.cg23.model.components.Emporium;

public class ObserverBonus {

	private BonusKing bonusKing;
	private final List<Region> regions;
	private final Map<String,Bonus> information;

	public ObserverBonus(List<Region> regions){
		this.information=new HashMap<>();
		this.bonusKing=null;
		this.regions=regions;
		
	}
	
	//aggiunge l'osservatore alla città
	public void setCities(){
		for(Region region: regions){
			List<City> cities=region.getCities();
			for(City city: cities)
				city.setObserverBonus(this);
		}
	}
	
	//aggiunge il bonus alla mappa
	public void addBonus(String type,Bonus bonus){
		this.information.put(type, bonus);
	}
	
	//controlla lo stato del tipo
	public boolean controlType(Player player,String type){
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
		Bonus bonus=information.get(type);
		bonus.giveBonus(player);
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
