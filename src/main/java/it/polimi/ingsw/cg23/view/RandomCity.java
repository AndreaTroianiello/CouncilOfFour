package it.polimi.ingsw.cg23.view;

import java.util.Random;

public class RandomCity {//PROTOTIPO
	int cityNumber;
	String[][] cityInfo=new String[cityNumber][6];//PROVVISORIO
	
	CliInterface cl=new CliInterface();
	//cl.printArray(cityInfo);
	
	public String[][] CreateCity(){
		System.out.println(cityNumber);
		cl.printArray(cityInfo);
		System.out.println(cityNumber);
		if(cityNumber%3!=0)
			return null;
		for(int k=0; k<cityNumber; k++){//ciclo che scorre le citta'
			cityInfo[k][0]=CreateCityName(k);
			cityInfo[k][1]=CreateCityId(cityInfo[k][0]);
		}
		cl.printArray(cityInfo);
		return cityInfo;
	}

	public String CreateCityName(int n){//n numero della citta'--> DA RIVEDERE PER PIU' DI 52 CITTA'
		String alfabeth = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random();
		if(n>=26)
			n=n-26;
		String name=alfabeth.substring(n, n+1);
		
		for(int i=0; i<8; i++){//8 lunghezza massima nome della citta'
			name=name+alfabeth.charAt(rnd.nextInt(alfabeth.length()));
			
			sb.append(name);
		}
		//System.out.println(name);
		return name;
	}
	
	public String CreateCityId(String cityInfo){ //SE PIU' CITTA HANNO LO STESSO ID?
		return cityInfo.substring(0,1);
	}
	
	public void CitiesNumber(int number){//setta il numero di citta' da creare
		cityNumber=number;
	}
}
