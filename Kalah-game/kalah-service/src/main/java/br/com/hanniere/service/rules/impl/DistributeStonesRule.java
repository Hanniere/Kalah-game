package br.com.hanniere.service.rules.impl;

import br.com.hanniere.domain.game.*;
import br.com.hanniere.domain.game.pit.impl.House;
import br.com.hanniere.domain.player.Player;
import br.com.hanniere.service.rules.KalahRule;

public class DistributeStonesRule implements KalahRule{

	private KalahRule nextRule;

	@Override
	public void setNextRule(KalahRule rule) {
		this.nextRule = rule;

	}

	@Override
	public void execute(Game kalahGame, int chosenHouse) {

		kalahGame.setCurrentTurn(new Turn(chosenHouse));
		distributeStones(kalahGame, chosenHouse);

	}

	public void distributeStones(Game kalahGame, int chosenHouse){

		Board board = kalahGame.getGameBoard();
		Player currentPlayer = kalahGame.getCurrentPlayer();

		int dropIndex = chosenHouse + 1;
		int playerField = currentPlayer.getPlayerNumber();

		Pit[] playerHouses = board.retrievePlayerHouses(playerField);

		int numOfStones = ((House)playerHouses[chosenHouse]).emptyHouse();

		while(numOfStones != 0){

			//next pit to drop the stone is a store
			if(dropIndex >= Board.HOUSES_NUM){
				Pit store = board.retrievePlayerStore(playerField);
				store.addStone(1);
				//retrieve the opponent houses
				dropIndex = 0;
				playerField = playerField==Player.FIRST_PLAYER? Player.SECOND_PLAYER: Player.FIRST_PLAYER;
				playerHouses = board.retrievePlayerHouses(playerField);
			}
			else{
				playerHouses[dropIndex].addStone(1);
				dropIndex++;
			}

			numOfStones--;
		}

		kalahGame.getCurrentTurn().setLastDropedIndex(dropIndex);
		kalahGame.getCurrentTurn().setLastDropedIndexField(playerField);

	}

}
