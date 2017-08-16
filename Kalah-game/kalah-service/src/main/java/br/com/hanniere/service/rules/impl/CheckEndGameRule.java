package br.com.hanniere.service.rules.impl;

import org.springframework.stereotype.Component;

import br.com.hanniere.domain.game.*;
import br.com.hanniere.domain.game.pit.impl.House;
import br.com.hanniere.domain.player.Player;
import br.com.hanniere.service.rules.KalahRule;

/**
 * This rule is applied when the game finish according to the game rule. This rule also retrieve the winner.
 * @author Hanniere
 *
 */
@Component(value="checkEndGameRule")
public class CheckEndGameRule implements KalahRule {

	KalahRule nextRule;

	@Override
	public void setNextRule(KalahRule rule) {
		this.nextRule = rule;

	}

	public CheckEndGameRule() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void execute(Game kalahGame, int chosenHouse) {

		Board board = kalahGame.getGameBoard();

		//game over, the winner must be checked
		if(board.verifyIfPlayerHousesIsEmpty(Player.FIRST_PLAYER) || board.verifyIfPlayerHousesIsEmpty(Player.SECOND_PLAYER)){

			Pit[] player1Houses = board.retrievePlayerHouses(Player.FIRST_PLAYER);
			Pit[] player2Houses = board.retrievePlayerHouses(Player.SECOND_PLAYER);

			for (int i = 0; i < Board.HOUSES_NUM; i++) {
				House house1 = (House)player1Houses[i];
				House house2 = (House)player2Houses[i];

				int stones1 = house1.emptyTheHouse();
				int stones2 = house2.emptyTheHouse();

				board.retrievePlayerStore(Player.FIRST_PLAYER).addStone(stones1);
				board.retrievePlayerStore(Player.SECOND_PLAYER).addStone(stones2);

			}

			kalahGame.setGameStatus(GameStatus.FINISHED);
			kalahGame.setCurrentTurn(null);

			//first player is the winner
			if(board.retrievePlayerStore(Player.FIRST_PLAYER).getStones() > board.retrievePlayerStore(Player.SECOND_PLAYER).getStones())
				kalahGame.setWinner(kalahGame.retrievePlayer(Player.FIRST_PLAYER));
			//second player is the winner
			else if (board.retrievePlayerStore(Player.FIRST_PLAYER).getStones() < board.retrievePlayerStore(Player.SECOND_PLAYER).getStones())
				kalahGame.setWinner(kalahGame.retrievePlayer(Player.SECOND_PLAYER));
			//it is a draw
			else
				kalahGame.setWinner(null);

		}
		else if(nextRule != null){
			nextRule.execute(kalahGame, chosenHouse);
		}
	}

}
