package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import it.polimi.ingsw.cg23.model.action.Action;


public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;

	public ClientOutHandler(ObjectOutputStream socketOut) {
		this.socketOut = socketOut;
	}

	@Override
	public void run() {

		System.out.println("RUNNING");
		Scanner stdIn = new Scanner(System.in);

		while (true) {

			String inputLine = stdIn.nextLine();

			Action action;
			switch (inputLine) {
			//DA FARE GLI INPUT DA INSERIRE PER OGNI AZIONE
			default:
				break;
			}

		}
	}
}

