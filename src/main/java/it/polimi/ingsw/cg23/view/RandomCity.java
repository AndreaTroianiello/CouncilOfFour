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
		String[][] cityInfo=new String[cityNumber][6];//array multidimensionale per salvare le informazioni della citta'

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
			cityInfo[k][5]=chooseRegion(k, cityNumber);//aggiunge il tipo di regione
		}
		cityInfo[1][1]="purple";//la citta' del re e' di colore viola
		return cityInfo;
	}

	/**
	 * create the name of the city
	 * @param n number of the city to create
	 * @return a string with the name of the city created
	 */
	public String createCityName(int n){//n numero della citta'
		String alfabeth = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//possibili combinazioni di nomi di citta'
		StringBuilder sb = new StringBuilder();
		int realN=n%26;//se le città da scrivere sono piu' di 26, la prima lettera ricominci da A
		String name=null;
		try{//nel caso 
			name=alfabeth.substring(realN, realN+1);//la prima lettera del nome e' in ordine alfabetico
		}catch(Exception e){
			return "Errore nella creazione del nome della citta'";
		}
		for(int i=0; i<8; i++){//8 lunghezza massima nome della citta'
			name=name+alfabeth.charAt(rnd.nextInt(alfabeth.length()));//aggiunta di nuove lettere al nome della citta'
			sb.append(name);
		}
		return name;
	}

	/**
	 * create the id of the city
	 * @param cityInfo the name of the city
	 * @return a string with the ido of the city
	 */
	public String createCityId(String cityInfo){ //SE PIU' CITTA HANNO LO STESSO ID?
		return cityInfo.substring(0,1);
	}

	/**
	 * choose the color fo the cities from an array
	 * @return the color of the city
	 */
	public String chooseColor(){
		String[] colori=new String[]{"bronze", "gold", "silver", "ferro"};//array con i colori
		int colorNumber=rnd.nextInt(4);//scelta di un numero casuale fra 0 e 3
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
}
