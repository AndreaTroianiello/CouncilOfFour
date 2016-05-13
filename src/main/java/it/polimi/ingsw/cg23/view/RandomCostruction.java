package it.polimi.ingsw.cg23.view;

import java.util.Random;

public class RandomCostruction {
	Random rnd = new Random();

	public String[][] PermitCard(int numPerReg){
		String[][]Card=new String[numPerReg*3][3];//array che contiene le carte politiche

		for(int i=0; i<numPerReg; i++){//scorre il numero di regioni
			String region="";
			int j=0;
			if(i==0){
				region="costa";
				j=0;
			}
			if(i==1){
				region="collina";
				j=numPerReg-1;
			}
			if(i==2){
				region="montagna";
				j=(numPerReg-1)*2;
			}
			for(int k=0; k<numPerReg; k++,j++){
				Card[j][0]=region;
				Card[j][1]=RandomCity(region);
				Card[j][2]=RandomBonus();
			}
		}
		return Card;
	}

	public String RandomCity(String region){
		String idCity="";
		String[]city=new String[5];
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
		int n=rnd.nextInt(3);
		for(int i=0; i<n+1; i++){
			idCity+=city[rnd.nextInt(5)];
		}
		return idCity;
	}

	public String RandomBonus(){
		String bonus="";
		String[]bonusArray=new String[9];
		bonusArray[0]="AdditionalAction";
		bonusArray[1]="Assistants";
		bonusArray[2]="CityToken";
		bonusArray[3]="PermitTile";
		bonusArray[4]="Nobility";
		bonusArray[5]="Politics";
		bonusArray[6]="TileBonus";
		bonusArray[7]="VictoryPoints";
		bonusArray[8]="Coin";	
		for(int i=0; i<rnd.nextInt(2)+1; i++){
			bonus+=bonusArray[rnd.nextInt(9)]+",";
		}
		
		return bonus.substring(0, bonus.length()-1);
	}
}
