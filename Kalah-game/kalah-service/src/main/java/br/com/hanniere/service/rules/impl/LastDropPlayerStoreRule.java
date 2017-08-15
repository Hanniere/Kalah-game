package br.com.hanniere.service.rules.impl;

import br.com.hanniere.domain.game.Board;
import br.com.hanniere.domain.game.Game;
import br.com.hanniere.service.rules.KalahRule;

/**
 * This rule is applied in case the last stone was dropped in the player's store
 * @author Hanniere
 *
 */
public class LastDropPlayerStoreRule implements KalahRule{
	KalahRule nextRule;

	@Override
	public void setNextRule(KalahRule rule) {
		this.nextRule = rule;

	}

	@Override
	public void execute(Game kalahGame, int chosenHouse) {

		int lastDropIndex = kalahGame.getCurrentTurn().getLastDropedIndex();

		//the current player has another turn and there is no need to execute the others rules
		if(lastDropIndex == Board.STORE_INDEX){
			kalahGame.setCurrentTurn(null);
		}
		else{
			nextRule.execute(kalahGame, chosenHouse);
		}

	}


}
