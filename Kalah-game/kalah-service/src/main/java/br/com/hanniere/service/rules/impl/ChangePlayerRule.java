package br.com.hanniere.service.rules.impl;

import br.com.hanniere.domain.game.Game;
import br.com.hanniere.domain.player.Player;
import br.com.hanniere.service.rules.KalahRule;

public class ChangePlayerRule implements KalahRule {

	KalahRule nextRule;

	@Override
	public void setNextRule(KalahRule rule) {
		this.nextRule = rule;

	}

	@Override
	public void execute(Game kalahGame, int chosenHouse) {

		Player currentPlayer = kalahGame.getCurrentPlayer();
		kalahGame.setCurrentPlayer(kalahGame.retrievePlayer(currentPlayer.retrieveOponentPlayerNumber()));
		kalahGame.setCurrentTurn(null);

	}

}
