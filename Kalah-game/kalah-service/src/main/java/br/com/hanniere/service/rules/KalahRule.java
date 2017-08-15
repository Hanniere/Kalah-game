package br.com.hanniere.service.rules;

import br.com.hanniere.domain.game.Game;


/**
 * This class is an interface that determines how the different rules will be applied
 * to the game.
 *
 * @author Hanniere
 *
 */
public interface KalahRule {

	public void setNextRule(KalahRule rule);

	public void execute(Game kalahGame, int chosenHouse);

}
