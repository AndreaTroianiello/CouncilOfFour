package it.polimi.ingsw.cg23.client.cli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.utility.Print;

/**
 * ReaderTextFile reads the file that contains the instructions of the CLI.
 * 
 * @author Andrea
 *
 */
public class ReaderTextFile {

	private Print cli;
	private Logger logger;

	/**
	 * The constructor of ReaderTextFile.
	 * 
	 * @param cli
	 *            the object that prints the informations on the screen.
	 */
	public ReaderTextFile(Print cli) {
		this.cli = cli;
		logger = Logger.getLogger(ReaderTextFile.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * Reads the file README-CLI.txt
	 */
	public void readFile() {
		String fileName = "CLI.txt";
		String line = null;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			while ((line = bufferedReader.readLine()) != null) {
				cli.print("", line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			logger.error(e);
		}
	}
}
