package br.com.hanniere.service.rules.impl;

import org.springframework.stereotype.Component;

import br.com.hanniere.domain.game.*;
import br.com.hanniere.domain.game.pit.impl.House;
import br.com.hanniere.domain.player.Player;
import br.com.hanniere.service.exception.InvalidMoveException;
import br.com.hanniere.service.exception.InvalidRuleException;
import br.com.hanniere.service.rules.KalahRule;

/**
 * This rule will be the first to be applied. It will perform the distribution of the
 * stones according to the current status of the game and the chosen house.
 *
 * @author Hanniere
 *
 */
@Component(value="distributeStonesRule")
public class DistributeStonesRule implements KalahRule{

	private KalahRule nextRule;

	@Override
	public void setNextRule(KalahRule rule) {
		this.nextRule = rule;

	}

	public DistributeStonesRule() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game kalahGame, int chosenHouse) {

		kalahGame.setCurrentTurn(new Turn(chosenHouse));
		distributeStones(kalahGame, chosenHouse);
		if(nextRule != null)
			nextRule.execute(kalahGame, chosenHouse);

	}

	public void distributeStones(Game kalahGame, int chosenHouse){

		Board board = kalahGame.getGameBoard();
		Player currentPlayer = kalahGame.getCurrentPlayer();

		int dropIndex = chosenHouse + 1;
		int playerField = currentPlayer.getPlayerNumber();

		Pit[] playerHouses = board.retrievePlayerHouses(playerField);

		int numOfStones = ((House)playerHouses[chosenHouse]).emptyTheHouse();

		if(numOfStones == 0){
			throw new InvalidMoveException("This house is empty, please choose another house");
		}

		while(numOfStones != 0){

			//next pit to drop the stone is a store
			if(dropIndex >= Board.HOUSES_NUM){
				Pit store = board.retrievePlayerStore(playerField);

				//the player cannot drop a stone in the opponent store
				if(playerField == currentPlayer.getPlayerNumber()){
					store.addStone(1);
					numOfStones--;
				}

				//if there are no more stones, the last index is the player's store
				if(numOfStones == 0){
					dropIndex = Board.STORE_INDEX;
				}

				else{
					//retrieve the opponent houses
					playerField = playerField==Player.FIRST_PLAYER? Player.SECOND_PLAYER: Player.FIRST_PLAYER;
					playerHouses = board.retrievePlayerHouses(playerField);

					dropIndex = 0;
				}
			}
			else{
				playerHouses[dropIndex].addStone(1);
				numOfStones--;

				if(numOfStones > 0)
					dropIndex++;
			}
		}

		kalahGame.getCurrentTurn().setLastDropedIndex(dropIndex);
		kalahGame.getCurrentTurn().setLastDropedIndexField(playerField);
	}

}
