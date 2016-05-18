package it.polimi.ingsw.cg23.view;

import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg23.model.City;

/**
 * generate the costruction card randomly 
 * indipendentemente dal numero di citta'
 *
 */
public class AutoCostruction {
	Random rnd = new Random();

	public String[][] createIdCity(List<City> citta, int cityPerReg, int cardsperReg){
		int regNum=regionsNumber(citta);//numero di regioni
		String[][] card=new String[cardsperReg*regNum][3];//array che contiene le carte costruzione
		for(int i=1; i<=regNum; i++){//scorre il numero di regioni
			for(int k=(i-1)*cardsperReg; k<cardsperReg*i; k++){//scorre il numero di carte per regione
				card[k][0]=citta.get(i*(cityPerReg-1)).getRegion().getName();//recupero il nome della regione
				card[k][1]=randomCity(card[k][0],cityPerReg,citta);//recupero gli id delle citta'
				card[k][2]=randomBonus();//recupero i bonus
			}
		}
		return card;
	}

	/**
	 * create the city random
	 * @param region, the reagion of the city to create it
	 * @param cityPerReg, number od city to create for each region
	 * @param citta, the city list
	 * @return
	 */
	public String randomCity(String region, int cityPerReg, List<City> citta){
		String idCity;//stringa con gli id della citta'
		String[]city=new String[cityPerReg];//array che contiene gli id delle possibili citta'

		if("costa".equals(region)){//id delle citta' della costa
			for(int i=0; i<cityPerReg; i++){
				city[i]=Character.toString(citta.get(i).getId());
			}
		}
		if("collina".equals(region)){//id delle citta' della collina
			for(int i=0; i<cityPerReg; i++){
				city[i]=Character.toString(citta.get(i+cityPerReg).getId());
			}
		}
		if("montagna".equals(region)){//id delle citta' della montagna
			for(int i=0; i<cityPerReg; i++){
				city[i]=Character.toString(citta.get(i+cityPerReg*2).getId());
			}
		}
		int n=rnd.nextInt(3);//numero di citta' da stampare al massimo sulla carta permesso (1,2,3)
		do{//gli id delle citta' in una carta premesso non si possono ripetere
			idCity="";//annullo i precedenti id
			for(int i=0; i<n+1; i++){//ciclo aggiunge il numero di id delle citta' scelti a random
				idCity+=city[rnd.nextInt(cityPerReg)];//viene scelta l'id della citta'
			}
		}while(!different(idCity));//vengono ristampati gli id delle citta' finche' non sono diversi 
		return idCity;
	}

	/**
	 * controll if in a string there is the same caracter
	 * @param nome, the string you want to analyze
	 * @return true if the caracters of the string are all differents, false otherwise
	 */
	public boolean different(String nome){
		String name=nome.toLowerCase();//uniformo tutti i caratteri
		for(int i=0; i<name.length(); i++){//due cili che scorrono le lettere del nome
			for(int k=i+1; k<name.length(); k++){
				if(name.charAt(i)==name.charAt(k))//controlle che le lettere siano uguali
					return false;
			}
		}
		return true;
	}

	/**
	 * create random bonus
	 * @return a string with the bonuses
	 */
	public String randomBonus(){
		String bonusname;
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

		do{
			bonusname="";//annullo i bonus
			for(int i=0; i<rnd.nextInt(3)+1; i++){//viene scelto a caso il numero di bonus (1,2)
				int k=rnd.nextInt(bonusArray.length);//recupero un numero random da 0 a 8
				bonusname+=(rnd.nextInt(4)+1)+bonusArray[k]+",";//i bonus sono aggiunti alla stringa

				if("AdditionalAction".equals(bonusArray[k])){//le azioni addizioni possono essere solo 1
					bonusname=bonusname.substring(0,bonusname.length()-(bonusArray[k].length()+2));//tolgo il bonus aggiunto
					bonusname+="1AdditionalAction,";//aggiungo il bonus azione aggiuntiva da 1
				}
			}
		}while(!differentTok(bonusname));//controllo che i bonus siano diversi uno dall'altro

		return bonusname.substring(0, bonusname.length()-1);//tolgo l'ultima virgola
	}

	public int numberCityReg(List<City> citta, String region){//ritorna il numero di citta' per una data regione
		int c=0;
		for(int i=0; i<citta.size(); i++){
			if(region.equals(citta.get(i).getRegion().getName()))
				c++;
		}
		return c;
	}

	/**
	 * controll if in a string there is the same substring (separated by a ,)
	 * @param nome, the string you want to analyze
	 * @return true if the substring are all different, false if otherwise
	 */
	public boolean differentTok(String nome){
		String name=nome.toLowerCase();//uniformo tutti i caratteri
		StringTokenizer stok = new StringTokenizer(name);
		String a=stok.nextToken(",");//primo token
		String[]token= new String[stok.countTokens()+1];//array con i token

		token[0]=a.substring(1, a.length());//assegno il primo token all'array
		int i=1;
		while(stok.hasMoreTokens()){//ciclo finche' ci sono token
			String toke=stok.nextToken(",");//aggiungo all'array il nuovo token
			token[i]=toke.substring(1,toke.length());//tolo il primo caratterre perche' e' il numero
			i++;
		}

		for(int j=0; j<token.length; j++){
			for(int k=j+1; k<token.length; k++){
				if(token[j].equals(token[k]))//controllo se nell'array ci sono delle parole uguali

					return false;
			}
		}
		return true;
	}

	public int regionsNumber(List<City> citta){//ritorna il numero delle regioni
		int c=0;
		for(int i=0; i<citta.size()-1; i++){
			if(citta.get(i).getRegion()!=citta.get(i+1).getRegion())
				c++;
		}
		return c+1;
	}
}
