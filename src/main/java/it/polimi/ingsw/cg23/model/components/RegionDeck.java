package it.polimi.ingsw.cg23.model.components;

import java.util.ArrayList;
import java.util.List;

public class RegionDeck {
	private final List<BusinessPermitTitle> businessPermitHidden;
	private final List<BusinessPermitTitle> businessPermitShowed;
	
	public RegionDeck(){
		businessPermitHidden=new ArrayList<>();
		businessPermitShowed=new ArrayList<>();
	}

	/**
	 * @return the businessPermitHidden
	 */
	public List<BusinessPermitTitle> getBusinessPermitHidden() {
		return businessPermitHidden;
	}

	/**
	 * @return the businessPermitShowed
	 */
	public List<BusinessPermitTitle> getBusinessPermitShowed() {
		return businessPermitShowed;
	}
	
	

}
