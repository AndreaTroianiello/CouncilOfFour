package it.polimi.ingsw.cg23.view;

/**
 * classe per stampare le info sulla cli
 */
public class CliInterface {
	//per main
	//CliInterface nuovo=new CliInterface();
	//nuovo.StartPartita(4);

	ReadXml lettureXml=new ReadXml();
	final int citynum=lettureXml.cityNumber();//numero di citta'
	final int cityNodeNumber=lettureXml.cityNodeNumber();//numero di attributi delle citta'
	String[][] cityInfo=new String[citynum][cityNodeNumber];//array multidim con city name, link, color, zone
	
	/**
	 * carica il file con le infromazioni della partita
	 * @return void
	 * @param number of players
	 */
	public void StartPartita(int players){
		cityInfo=lettureXml.ReadFileXml();
		PrintArray(cityInfo);
	}
	
	/**
	 * stampa un array bidinemsionale
	 * @return void
	 * @param bidimensional array
	 */
	public void PrintArray(String[][] array){
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
	public void Print(Object ogg, String testo){
		System.out.println(testo+" "+ogg);
	}
	
	/**
	 * stampa la mappa (non funziona)
	 * @return void
	 * @param bidimensional array with city
	 */
	public void CreateMap(String[][] city){
		/*String plancia="   costa         collina          montagna\n";//la stringa che stampa la plancia di gioco
		//System.out.println(city.length);
		int n=city.length;
		for(int i=0; i<n; i++){//le righe da stampare sono 3
			//for(int k=0; k<3; k++){
				plancia+=city[i][0]+"("+city[i][1]+") ";//0: nome citta', 1: colore citta'
				if(i<=9)plancia+="  ";//aggiunge piu' spazi se 
			//}
				if((i+1)%6==0)plancia+="\n";
				//if()

		}

		System.out.println(plancia);
		 */	
	}

}
