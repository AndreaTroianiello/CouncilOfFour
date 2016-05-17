package it.polimi.ingsw.cg23.view;

import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg23.model.City;

public class AutoCostruction {//CLASSE PeR GENERARE TESSERE COSTRUZIONE GENERICHE
	Random rnd = new Random();
	
	public String[][] createIdCity(List<City> citta, int cityPerReg, int cardsperReg){
		int regNum=regionsNumber(citta);//numero di regioni

		System.out.println("numero regioni "+regNum);

		
		String[][] card=new String[cardsperReg*regNum][3];//array che contiene le carte costruzione
		
		for(int i=1; i<=regNum; i++){//scorre il numero di regioni
			
			for(int k=(i-1)*cardsperReg; k<cardsperReg*i; k++){//scorre il numero di carte per regione
				card[k][0]=citta.get(i*(cityPerReg-1)).getRegion().getName();//recupero il nome della regione
				//card[k][1]=randomCity(card[k][0]);//citta'
				card[k][2]=randomBonus();
			}
		}
		System.out.println("ok");
		return card;
	}
	
	/**
	 * create random cities
	 * @param region, the actual region
	 * @return a string with the city id
	 */
	public String randomCity(String region){
		String idCity;//stringa con gli id della citta'
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
		
		do{
			bonus="";//annullo i bonus
			for(int i=0; i<rnd.nextInt(3)+1; i++){//viene scelto a caso il numero di bonus (1,2)
				int k=rnd.nextInt(9);//recupero un numero random da 0 a 8
				bonus+=(rnd.nextInt(4)+1)+bonusArray[k]+",";//i bonus sono aggiunti alla stringa

				if("AdditionalAction".equals(bonusArray[k])){//le azioni addizioni possono essere solo 1
					bonus=bonus.substring(0,bonus.length()-(bonusArray[k].length()+2));//tolgo il bonus aggiunto
					bonus+="1AdditionalAction,";//aggiungo il bonus azione aggiuntiva da 1
				}
			}
		}while(!differentTokenizer(bonus));//controllo che i bonus siano diversi uno dall'altro
		
		return bonus.substring(0, bonus.length()-1);//tolgo l'ultima virgola
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
	public boolean differentTokenizer(String nome){
		String name=nome.toLowerCase();//uniformo tutti i caratteri
		StringTokenizer st = new StringTokenizer(name);
		String a=st.nextToken(",");//primo token
		String[]token= new String[st.countTokens()+1];//array con i token

		token[0]=a.substring(1, a.length());//assegno il primo token all'array
		int i=1;
		while(st.hasMoreTokens()){//ciclo finche' ci sono token
			String toke=st.nextToken(",");//aggiungo all'array il nuovo token
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
