package it.polimi.ingsw.cg23.server.controller.creation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.polimi.ingsw.cg23.server.controller.Setting;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.King;

/**
 * create the councillors and council
 */
public class CreateCouncillor {
	private Setting s;
	
	/**
	 * costructor
	 */
	public CreateCouncillor(){
		this.s=new Setting();
	}
	
	/**
	 * the function create the counsillor
	 * @param numberCouncillor, number of councillors per color to create
	 * @return the list of councillor
	 */
	public List<Councillor>  createCouncillor(int numberCouncillor){
		Color[] arrayColori=s.color();//recupero un array di Color con i possibili colori
		List<Councillor> consiglieri=new ArrayList<>();
		for(int i=0; i<arrayColori.length; i++){//ciclo che scorre i colori
			for(int k=0; k<numberCouncillor; k++){//ciclo che scorre il numero di consiglieri per colore
				Councillor c= new Councillor(arrayColori[i]);//creo un nuovo consigliere
				consiglieri.add(c);//aggiungo il consigliere alla lista
			}
		}
		return consiglieri;
	}

	/**
	 * create the regions councils
	 * @param consiglieri, the consiglieri list
	 * @param reg, the region
	 */
	public void setBalconi(Region reg, List<Councillor>consiglieri){
		Random rnd = new Random();
		List<Councillor> nuoviConsiglieri = new ArrayList<>();//consiglieri della regione
		for(int i=0; i<4; i++){//ciclo che scorre i consiglieri (4 per regione)
			int randomNumber=rnd.nextInt(consiglieri.size());//numero random per recuperrare a caso i consiglieri
			nuoviConsiglieri.add(consiglieri.get(randomNumber));//aggiungo i consiglieri alla nuova lista
			consiglieri.remove(randomNumber);//rimuovo i consiglieri dalla vecchia lista
		}
		reg.getCouncil().getCouncillors().addAll(nuoviConsiglieri);//aggiungo la nuova lista alla regione
	}

	/**
	 * create the king council
	 * @param consiglieri, the consiglieri list
	 * @param k, the king
	 */
	public void setBalconi(King k, List<Councillor>consiglieri){
		Random rnd = new Random();
		List<Councillor> nuoviConsiglieri = new ArrayList<>();//lista dei consiglieri del re
		for(int i=0; i<4; i++){//ciclo che scorre i consiglieri (4 per il balcone del re)
			int randomNumber=rnd.nextInt(consiglieri.size());//numero casuale per trovare i consiglieri
			nuoviConsiglieri.add(consiglieri.get(randomNumber));//aggiungo il consigliere random alla lista dei consiglieri del re
			consiglieri.remove(randomNumber);//cancello il consigliere aggiunto dalla lista dei consiglieri disponibili
		}
		k.getCouncil().getCouncillors().addAll(nuoviConsiglieri);//aggiungo la lista dei 4 consiglieri al re
	}

}
