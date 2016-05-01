package it.polimi.ingsw.cg23.view;

/**
 * classe per stampare le info sulla cli
 */
public class CliInterface {
	//per main
	//CliInterface nuovo=new CliInterface();
	//nuovo.startPartita();

	ReadXml lettureXml=new ReadXml();
	final int citynum=lettureXml.cityNumber();//numero di citta'
	final int cityNodeNumber=lettureXml.cityNodeNumber();//numero di attributi delle citta'

	String[][] cityInfo=new String[citynum][cityNodeNumber];//array multidim con city name, link, color, zone

	/**
	 * carica il file con le infromazioni della partita
	 * @return void
	 * @param number of players
	 */
	public void startPartita(){
		//System.out.print(cityNodeNumber);
		cityInfo=lettureXml.readFileXml();
		createMap(cityInfo);//stampa array solo per le prove
	}

	/**
	 * stampa un array bidinemsionale
	 * @return void
	 * @param bidimensional array
	 */
	public void printArray(String[][] array){
		for(int i=0;i<array.length;i++){
			for(int k=0; k<array[0].length; k++){
				System.out.print(array[i][k]+"    ");
			}
			System.out.print("\n");
		}
	}

	/**
	 * stampa una qualunque cosa gli viene passata
	 * @return void
	 * @param object (something to print)
	 * @param testo da stampare
	 */
	public void print(Object ogg, String testo){
		System.out.println(testo+" "+ogg);
	}

	/**
	 * stampa la mappa ( funziona parzialmente)
	 * @return void
	 * @param bidimensional array with city
	 */
	public void createMap(String[][] city){
		String plancia="     Costa             Collina              Montagna\n";//la stringa che stampa la plancia di gioco
		int i=0, c=city.length/3, m=city.length/3*2;
		for(i=0; i<city.length/3;){//le righe da stampare sono 5
			//System.out.println("II"+i);
			for(int k=0; k<3; k++){//aggiunge le 3 citta', una per ogni regione
				int kk=k;
				if(k==0)k=i;
				if(k==1)k=c;
				if(k==2)k=m;
				
				String newcity=city[k][0]+"("+city[k][1]+") ";

					//CONTROLLARE
				int length=20-newcity.length();
				if(length<20){
					for(int j=0; j<length; j++){
						newcity+=" ";
						//System.out.println(add+"|");
					}
				}
				plancia+=newcity;
				k=kk;
			}
			i++;
			c++;
			System.out.println(i);
			m++;
			System.out.println(m);
			plancia+="\n";
		}

		System.out.println(plancia);

	}

}
