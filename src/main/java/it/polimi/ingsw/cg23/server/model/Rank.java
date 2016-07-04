package it.polimi.ingsw.cg23.server.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.polimi.ingsw.cg23.server.model.components.NobilityBox;

/**
 * Rank updates all players' score and orders the players' list.
 * 
 * @author Andrea
 *
 */
public class Rank {

	private Board model;

	/**
	 * The constructor of Rank.
	 * 
	 * @param model
	 *            The board of the game.
	 */
	public Rank(Board model) {
		this.model = model;
	}

	/**
	 * Updates the victory points of the player whom has built the last
	 * emporium.
	 */
	private void updateFinalPlayer() {
		State status = model.getStatus();
		if (status.getFinalPlayer() != null) {
			int points = status.getFinalPlayer().getVictoryTrack().getVictoryPoints();
			status.getFinalPlayer().getVictoryTrack().setVictoryPoints(points + 3);
		}
	}

	/**
	 * Updates the victory points of the player
	 */
	private void updateTiles() {
		int maxTiles = model.getPlayers().stream()
				.mapToInt(p -> p.getUsedBusinessPermit().size() + p.getAvailableBusinessPermits().size()).max()
				.orElse(-1);
		model.getPlayers().stream()
				.filter(p -> p.getUsedBusinessPermit().size() + p.getAvailableBusinessPermits().size() == maxTiles)
				.forEach(p -> p.getVictoryTrack().setVictoryPoints(p.getVictoryTrack().getVictoryPoints() + 3));
	}

	/**
	 * Updates the victory points of the players who arrived later in the
	 * nobility track.
	 */
	private void updateNobilityTrack() {
		List<Integer> points = Arrays.asList(5, 2);
		List<NobilityBox> boxes = model.getNobilityTrack().getNobilityBoxes();
		List<NobilityBox> boxesTarget = new ArrayList<>();
		for (int index = boxes.size() - 1; index >= 0 && !(boxesTarget.size() == points.size()); index--) {
			NobilityBox box = boxes.get(index);
			if (!box.getPlayers().isEmpty()) {
				boxesTarget.add(box);
				if (box.getPlayers().size() > 1)
					break;
			}
		}
		for (int index = 0; index < boxesTarget.size(); index++) {
			for (Player p : boxesTarget.get(index).getPlayers())
				p.getVictoryTrack().setVictoryPoints(p.getVictoryTrack().getVictoryPoints() + points.get(index));
		}
	}

	/**
	 * Updates the victory points of the players you create a rank by ordering
	 * list.
	 */
	public void createRank() {
		List<Player> players = model.getPlayers();
		updateFinalPlayer();
		updateNobilityTrack();
		updateTiles();
		for (int index = 0; index < players.size(); index++) {
			boolean flag = false;
			for (int j = 0; j < players.size() - 1; j++) {
				// If the score is less than the next time we exchange values.
				if (players.get(j).getVictoryTrack().getVictoryPoints() < players.get(j + 1).getVictoryTrack()
						.getVictoryPoints()) {
					players.add(j, players.remove(j + 1));
					flag = true; // I put it to true to indicate that an
									// exchange has taken place.
				} else
					// If they have the same score I look assistants and politic
					// cards.
					if (players.get(j).getVictoryTrack().getVictoryPoints() == players.get(j + 1).getVictoryTrack()
							.getVictoryPoints()
							&& players.get(j).getAssistantsPool().getAssistants() + players.get(j).getHand()
									.size() < players.get(j + 1).getAssistantsPool().getAssistants()
											+ players.get(j + 1).getHand().size()) {
					players.add(j, players.remove(j + 1));
					flag = true; // I put it to true to indicate that an
									// exchange has taken place.
				}

			}
			if (!flag)
				return; // If flag = false then it means that last iteration
						// there were no trade, then the method can terminate.
		}

	}

}
