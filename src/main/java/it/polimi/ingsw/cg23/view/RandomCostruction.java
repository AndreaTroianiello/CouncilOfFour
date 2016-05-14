package it.polimi.ingsw.cg23.view;

import java.util.Random;

public class RandomCostruction {
	Random rnd = new Random();

	/**
	 * create random costruction card 
	 * @param numPerReg, the number of costruction card per region
	 * @param nRegion, the number of regions
	 * @return a bidimensional array with the costruction card
	 */
	public String[][] permitCard(int numPerReg, int nRegion){
		String[][]card=new String[numPerReg*nRegion][nRegion];//array che contiene le carte politiche
		/*array prototype:
		 * coloumn 0: region
		 * coloumn 1: cityID
		 * coloumn 2: bonus
		 */
		for(int i=0; i<nRegion; i++){//scorre il numero di regioni
			String region="";//nome della regione
			int j=0;//segna l'inizio di una regione
			if(i==0){
				region="costa";
				j=0;
			}
			if(i==1){
				region="collina";
				j=numPerReg;

			}
			if(i==2){
				region="montagna";
				j=(numPerReg)*2;
			}
			for(int k=0; k<numPerReg; k++,j++){//scorre il numero di carte permesso per regione
				card[j][0]=region;//regione
				card[j][1]=randomCity(region);//citta'
				card[j][2]=randomBonus();//bonus
			}
		}
		return card;
	}

	/**
	 * create random cities
	 * @param region, the actual region
	 * @return a string with the city id
	 */
	public String randomCity(String region){
		String idCity;
		String[]city=new String[5];//array che contiene gli id delle possibili citta'
		if(region=="costa"){//id delle citta' della costa
			city[0]="A";
			city[1]="B";
			city[2]="C";
			city[3]="D";
			city[4]="E";
		}
		if(region=="collina"){//id delle citta' della collina
			city[0]="F";
			city[1]="G";
			city[2]="H";
			city[3]="I";
			city[4]="J";
		}
		if(region=="montagna"){//id delle citta' della montagna
			city[0]="K";
			city[1]="L";
			city[2]="M";
			city[3]="N";
			city[4]="O";
		}
		int n=rnd.nextInt(3);//numero di citta' da stampare sulla carta permesso (1,2,3)
		do{//gli id delle citta' in una carta premesso non si possono ripetere
			idCity="";//annullo i precedenti id
			for(int i=0; i<n+1; i++){//ciclo aggiunge il numero di id delle citta' scelti a random
				idCity+=city[rnd.nextInt(5)];//viene scelta l'id della citta'
			}
		}while(!different(idCity));//vengono ristampati gli id delle citta' finche' non sono diversi 
		return idCity;
	}

	/**
	 * create random bonus
	 * @return a string with the bonuses
	 */
	public String randomBonus(){//ERRORE I BONUS SI RIPETONO!
		String bonus;
		String[]bonusArray=new String[9];//contiene i possibili bonus assegnabili alle carte permesso
		bonusArray[0]="AdditionalAction";
		bonusArray[1]="Assistants";
		bonusArray[2]="CityToken";
		bonusArray[3]="PermitTile";
		bonusArray[4]="Nobility";
		bonusArray[5]="Politics";
		bonusArray[6]="TileBonus";
		bonusArray[7]="VictoryPoints";
		bonusArray[8]="Coin";	

			bonus="";//annullo i bonus
			for(int i=0; i<rnd.nextInt(3)+1; i++){//viene scelto a caso il numero di bonus (1,2)
				int k=rnd.nextInt(9);
				bonus+=(rnd.nextInt(4)+1)+bonusArray[k]+",";//i bonus sono aggiunti alla stringa

				if("AdditionalAction".equals(bonusArray[k])){//le azioni addizioni possono essere solo 1
					bonus=bonus.substring(0,bonus.length()-(bonusArray[k].length()+2));
					bonus+="1AdditionalAction,";
				}
			}
		return bonus.substring(0, bonus.length()-1);//tolgo l'ultima virgola
	}

	/**
	 * controll if in a string there is the same caracter
	 * @param nome, the string you want to analyze
	 * @return true if the caracters of the string are all differents, false otherwise
	 */
	public boolean different(String nome){
		String name=nome.toLowerCase();//uniformo tutti i caratteri
		for(int i=0; i<name.length(); i++){
			for(int k=i+1; k<name.length(); k++){
				if(name.charAt(i)==name.charAt(k))
					return false;
			}
		}
		return true;
	}
}
