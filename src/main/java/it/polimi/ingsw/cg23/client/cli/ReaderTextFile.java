package it.polimi.ingsw.cg23.client.cli;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.utility.Print;

public class ReaderTextFile {

	private Print cli;
	private Logger logger;

	public ReaderTextFile(Print cli) {
		this.cli=cli;
		logger = Logger.getLogger(ReaderTextFile.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	public void readFile(){

		String fileName = "README-CLI.txt";
		String line = null;

		try {
			FileReader fileReader =new FileReader(fileName);
			BufferedReader bufferedReader =new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				cli.print("", line);
			}
			bufferedReader.close();         
		}
		catch(FileNotFoundException e) {
			logger.error(e);                
		}
		catch(IOException e) {
			logger.error(e);
		}
	}
}

