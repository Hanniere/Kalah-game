package br.com.hanniere.service.rules.impl;

import org.springframework.stereotype.Component;

import br.com.hanniere.domain.game.Board;
import br.com.hanniere.domain.game.Game;
import br.com.hanniere.domain.game.pit.impl.House;
import br.com.hanniere.service.exception.InvalidRuleException;
import br.com.hanniere.service.rules.KalahRule;

/**
 * This rule is applied when the last drop index is in a player's empty house and it is empty
 * @author Hanniere
 *
 */
@Component(value="lastDropPlayerHouseRule")
public class LastDropPlayerHouseRule implements KalahRule {

	KalahRule nextRule;

	@Override
	public void setNextRule(KalahRule rule) {
		this.nextRule = rule;

	}

	public LastDropPlayerHouseRule() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game kalahGame, int chosenHouse) {
		int lastDropIndex = kalahGame.getCurrentTurn().getLastDropedIndex();
		Board board = kalahGame.getGameBoard();
		int currentPlayerNumber = kalahGame.getCurrentPlayer().getPlayerNumber();
		int opponentPlayerNumber = kalahGame.getCurrentPlayer().retrieveOponentPlayerNumber();


		if(lastDropIndex == Board.STORE_INDEX){
			throw new InvalidRuleException("This rule can not be applied.");
		}

		House lastDroppedHouse = ((House) board.retrievePlayerHouses(currentPlayerNumber)[lastDropIndex]);

		//if the last dropped stone was in the player's house and it was empty
		if(kalahGame.getCurrentTurn().getLastDropedIndexField() == currentPlayerNumber
				&& lastDroppedHouse.wasEmpty()){

			int oppositeIndex = (Board.HOUSES_NUM - 1) - lastDropIndex;

			House opponentHouse = ((House) board.retrievePlayerHouses(opponentPlayerNumber)[oppositeIndex]);

			int stones = lastDroppedHouse.emptyTheHouse() + opponentHouse.emptyTheHouse();

			board.retrievePlayerStore(currentPlayerNumber).addStone(stones);

		}
		if(nextRule != null)
			nextRule.execute(kalahGame, chosenHouse);

	}

}
