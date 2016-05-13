package it.polimi.ingsw.cg23.view;

import java.util.Random;

public class RandomCostruction {
	Random rnd = new Random();

	public String[][] permitCard(int numPerReg, int nRegion){
		String[][]card=new String[numPerReg*nRegion][nRegion];//array che contiene le carte politiche
		/*array prototype:
		 * coloumn 0: region
		 * coloumn 1: cityID
		 * coloumn 2: bonus
		 */
		for(int i=0; i<nRegion; i++){//scorre il numero di regioni
			String region="";
			int j=0;
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

	public String randomCity(String region){//RITORNA CITTA' UGUALI-->ERRORE
		String idCity="";
		String[]city=new String[5];//array che contiene gli id delle possibili citta'
		if(region=="costa"){
			city[0]="A";
			city[1]="B";
			city[2]="C";
			city[3]="D";
			city[4]="E";
		}
		if(region=="collina"){
			city[0]="F";
			city[1]="G";
			city[2]="H";
			city[3]="I";
			city[4]="J";
		}
		if(region=="montagna"){
			city[0]="K";
			city[1]="L";
			city[2]="M";
			city[3]="N";
			city[4]="O";
		}
		int n=rnd.nextInt(3);//numero di citta' da stampare sulla carta permesso (1,2,3)
		for(int i=0; i<n+1; i++){
			idCity+=city[rnd.nextInt(5)];//viene scelta l'id della citta'
		}
		return idCity;
	}

	public String randomBonus(){//ERRORE I BONUS SI RIPETONO!
		String bonus="";
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
		for(int i=0; i<rnd.nextInt(3)+1; i++){//viene scelto a caso il numero di bonus (1,2)
			int k=rnd.nextInt(9);
			bonus+=(rnd.nextInt(4)+1)+bonusArray[k]+",";//i bonus sono aggiunti alla stringa
			if(bonusArray[k].equals("AdditionalAction")){//le azioni addizioni possono essere solo 1
				bonus=bonus.substring(0,bonus.length()-18);
				bonus+="1AdditionalAction,";
			}
				
		}
		return bonus.substring(0, bonus.length()-1);//tolo l'umtima virgola
	}
}
