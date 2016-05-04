package it.polimi.ingsw.cg23.view;

import java.util.Random;

public class RandomCity {//PROTOTIPO
	Random rnd = new Random();
	/**
	 * create the city random
	 * @param cityNumber the number of cities to create
	 * @return a bidimensional array with the city info
	 */
	public String[][] createCity(int cityNumber){
		//array multidimensionale per salvare le informazioni della citta'
		String[][] cityInfo=new String[cityNumber][6];
		
		if(cityNumber%3!=0){//le citta' devono essere multile di 6
			for(int i=0;i<cityInfo.length;i++){//cicli per annullare l'array (richiesto da sonar)
				for(int k=0; k<cityInfo[0].length; k++){
					cityInfo[i][k]=null;
				}
			}
			return cityInfo;
		}
		for(int k=0; k<cityNumber; k++){//ciclo che scorre le citta'
			cityInfo[k][0]=createCityName(k);//aggiunta del nome della citta' creato all'array
			cityInfo[k][1]=chooseColor();//aggiungta del colore della citta' all'array
			cityInfo[k][3]=createCityId(cityInfo[k][0]);//aggiunta dell'id citta' all'array
			cityInfo[k][4]=chooseBonus();//aggiunta dei bonus all'array
			cityInfo[k][5]=chooseRegion(k, cityNumber);//aggiunge il tipo di regione
		}
		int kingCity=chooseKingCity(cityInfo.length);//recupera la citta' del re
		cityInfo[kingCity][0]=cityInfo[kingCity][0]+"(K)";
		cityInfo[kingCity][1]="purple";//la citta' del re e' di colore viola
		cityInfo[kingCity][4]="";//la citta' del re non ha bonus
		return cityInfo;
	}
	
	/**
	 * choose the city for the king
	 * @param citiesNumber the number of total cities
	 * @return a random int from 0 to the number of cities
	 */
	public int chooseKingCity(int citiesNumber){
		return rnd.nextInt(citiesNumber);//ritorna una città a caso che sara quella del re
	}
	
	/**
	 * create the name of the city
	 * @param n number of the city to create
	 * @return a string with the name of the city created
	 */
	public String createCityName(int n){//n numero della citta'
		String alfabeth = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//alfabeto completo e maiscolo per la prima lettera
		String consonants= "bcdfghjklmnpqrstvwxyz";//alfabeto di consonanti
		String vocali ="aeiou";//alfabeto di vocali
		StringBuilder sb = new StringBuilder();
		int realN=n%26;//se le città da scrivere sono piu' di 26, la prima lettera ricominci da A
		String name="";
		try{//puo' esserci un errore nell'esecuzione della substring
			name=alfabeth.substring(realN, realN+1);//la prima lettera del nome e' in ordine alfabetico
		}catch(ArrayIndexOutOfBoundsException e){
			return "Errore nella creazione del nome della citta'";
		}
		for(int i=0; i<7; i++){//8 lunghezza massima nome della citta'
			if(i%2!=0)//alterna una vocale e una consonate
				//aggiunta di consonanti al nome della citta'
				name+=Character.toString(consonants.charAt(rnd.nextInt(consonants.length())));//sonar consigla Character.toString
			else
				//aggiunta di vocali al nome della citta'
			name+=Character.toString(vocali.charAt(rnd.nextInt(vocali.length())));//sonar consigla Character.toString
		}
		sb.append(name);//aggiunge il nome della citta' allo stream builder
		return name;
	}

	/**
	 * create the id of the city
	 * @param cityInfo the name of the city
	 * @return a string with the ido of the city
	 */
	public String createCityId(String cityInfo){ //SE PIU' CITTA HANNO LO STESSO ID?
		return cityInfo.substring(0,1);//ritorna la prima lettra del nome della citta'
	}

	/**
	 * choose the color fo the cities from an array
	 * @return the color of the city
	 */
	public String chooseColor(){
		String[] colori=new String[]{"bronze", "gold", "silver", "ferro"};//array con i colori
		int colorNumber=rnd.nextInt(colori.length);//scelta di un numero casuale fra 0 e 3
		return colori[colorNumber];
	}

	/**
	 * choose the region type
	 * @param actualCity the city i want the region
	 * @param totalCity the number of total city
	 * @return
	 */
	public String chooseRegion(int actualCity, int totalCity){
		if(actualCity<totalCity/3)
			return "costa";//primo terzo di citta'
		else{
			if(actualCity<totalCity/3*2)
				return "collina";//secondo terzo di citta'
			else
				return "montagna";//terzo terzo di citta'
		}
	}
	
	/**
	 * assign at every city a casual bonus
	 * @return the city bonus
	 */
	public String chooseBonus(){
		//array con i diversi tipi bonus
		String[] bonus={"1nobility","2aiuntanti","1aiutante","1coin","1aiuntante,1card","2coins",
				"3victory","1aiutante,1coin","1victory","1card","2victory","3coins","1card,1victory"};
		int bonusNumber=rnd.nextInt(bonus.length);
		return bonus[bonusNumber];
	}
}
