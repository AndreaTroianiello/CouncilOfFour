package it.polimi.ingsw.cg23.controller.creation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.components.BonusKing;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.RegionDeck;

public class CreateCouncollorTest {

	private CreateCouncillor cc;
	private King k;

	private List<Region> regions;
	private Type type1,type2;
	private List<Integer> bonuses;
	private List<Type> tipes;
	private List<City> cities;

	@Before
	public void setUp(){
		cc=new CreateCouncillor();


		//Set up the bonus king
		bonuses=new ArrayList<>();
		bonuses.add(10);
		bonuses.add(3);
		bonuses.add(0);
		BonusKing bonusKing=new BonusKing(bonuses);


		cities=new ArrayList<>();
		tipes=new ArrayList<>();
		regions=new ArrayList<>();

		//Set up the types.
		type1=new Type("Gold",10,bonusKing);
		tipes.add(type1);
		type2=new Type("Silver",10,bonusKing);
		tipes.add(type2);

		//Set up the regions and cities
		regions.add(new Region("Region0",5,new RegionDeck(2),bonusKing));
		regions.add(new Region("Region1",5,new RegionDeck(2),bonusKing));

		cities.add(new City('A', "Aosta", type1 , regions.get(0)));
		cities.add(new City('B', "Bari", type2 , regions.get(0)));
		cities.add(new City('C', "Crotone", type1 , regions.get(0)));
		cities.add(new City('R', "Roma", type1 , regions.get(1)));

		k=new King(cities.get(1));
	}


	@Test
	public void createCouncillorTest() {
		int i=10;
		List<Councillor> c=cc.createCouncillor(i);//creo i consiglieri
		assertEquals(c.size(), i*6);//6 è il numero di colori
	}


	@Test
	public void balconiKingtest() {
		List<Councillor> c=cc.createCouncillor(10);//creo i consiglieri
		cc.setBalconi(k, c);//setto il balcone del re
		assertNotNull(k.getCouncil().getCouncillors());
		assertEquals(k.getCouncil().getCouncillors().size(), 4);//controllo che il balcone del re abbia 4 consiglieri
	}

	@Test
	public void balconiRegionTest() {
		List<Councillor> c=cc.createCouncillor(10);//creo i consiglieri
		cc.setBalconi(regions.get(1), c);//setto il balcone del re
		assertNotNull(regions.get(1).getCouncil().getCouncillors());
		assertEquals(regions.get(1).getCouncil().getCouncillors().size(), 4);//controllo che il balcone del re abbia 4 consiglieri
	}
}
