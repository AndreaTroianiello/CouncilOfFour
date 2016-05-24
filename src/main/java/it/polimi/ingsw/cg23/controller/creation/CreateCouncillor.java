package it.polimi.ingsw.cg23.controller.creation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.polimi.ingsw.cg23.controller.Setting;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.components.King;

public class CreateCouncillor {
	Setting s=new Setting();
	
	/**
	 * the function create the counsillor
	 * @param numberCouncillor, number of councillors per color to create
	 * @param b, the board
	 */
	public List<Councillor>  createCouncillor(int numberCouncillor, Board b){
		Color[] arrayColori=s.color();//recupero un array di Color con i possibili colori
		List<Councillor> consiglieri=new ArrayList<>();
		for(int i=0; i<arrayColori.length; i++){//ciclo che scorre i colori
			for(int k=0; k<numberCouncillor; k++){//ciclo che scorre il numero di consiglieri per colore
				Councillor c= new Councillor(arrayColori[i]);//creo un nuovo consigliere
				consiglieri.add(c);//aggiungo il consigliere alla lista
				b.setCouncillor(c);//aggiungo il consigliere alla board
			}
		}
		return consiglieri;
	}

	/**
	 * create the balconi region
	 * @param b, the board
	 * @param reg, the region
	 */
	public void setBalconi(Board b, Region reg){
		List<Councillor> consiglieri=b.getCouncillorPool();//lista di tutti i consiglieri
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
	 * create the balconi king
	 * @param b, the board
	 * @param k, the king
	 */
	public void setBalconi(Board b, King k){
		List<Councillor> consiglieri=b.getCouncillorPool();//lista di tutti consiglieri
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
