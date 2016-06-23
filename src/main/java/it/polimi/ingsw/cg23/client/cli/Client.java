package it.polimi.ingsw.cg23.client.cli;

import java.util.Scanner;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg23.utility.Print;

public class Client {

	/**
	 * Lets you choose the type of connection and starts the client.
	 * @param args
	 */
	public static void main(String[] args){
		Print cli=new Print();
		boolean run=true;
		cli.print("","Welcome to Council of Four game!");
		cli.print("","Choose the type of connection. (SOCKET or RMI)");
		CommandLine command=new CommandLine(cli);
		Scanner stdIn = new Scanner(System.in);
		while(run){
			StringTokenizer tokenizer=new StringTokenizer(stdIn.nextLine()," ");
			if(tokenizer.hasMoreTokens())
				switch (tokenizer.nextToken()) {
				case "SOCKET":
					run=command.startSocket(tokenizer, stdIn, cli);
					break;
				case "RMI":
					run=command.startRMI(tokenizer, stdIn, cli);
					break;
				case "QUIT":
					cli.print("", "Bye.");
					run=false;
					break;
				case "HELP":
					new ReaderTextFile(cli).readFile();
					break;
				default:
					cli.print("", "Wrong command.");
					break;
				}
		}
		stdIn.close();
	}
}
