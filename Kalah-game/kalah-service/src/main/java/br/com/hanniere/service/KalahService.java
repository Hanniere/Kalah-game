package br.com.hanniere.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hanniere.domain.game.Board;
import br.com.hanniere.domain.game.Game;
import br.com.hanniere.domain.game.GameStatus;
import br.com.hanniere.domain.player.Player;
import br.com.hanniere.repository.GameCache;
import br.com.hanniere.repository.PlayerRepository;
import br.com.hanniere.service.exception.InvalidMoveException;
import br.com.hanniere.service.rules.RuleChainConfigurator;

/**
 * This class is the implementation of game service. It provides the methods to initiate
 * and perform a move.
 * @author Hanniere
 *
 */
@Service
public class KalahService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private RuleChainConfigurator ruleChainConfigurator;

	@Autowired
	private GameCache gameCache;

	public String initializeGame(int numberOfStonesForPit){

		List<Player> playersList = this.playerRepository.findAll();
		playersList.get(0).setPlayerNumber(1);
		playersList.get(1).setPlayerNumber(2);


		Game kalahGame = new Game();
		Board kalahBoard = new Board(numberOfStonesForPit);

		kalahBoard.initialize();
		kalahGame.setGameBoard(kalahBoard);
		kalahGame.setGameStatus(GameStatus.INPROGRESS);
		kalahGame.setPlayersList(playersList);
		kalahGame.setCurrentPlayer(playersList.get(0));

		String matchId = gameCache.insertCache(kalahGame);

		return matchId;
	}

	public Game performMove(String matchId, int chosenHouse){
		Game kalahGame = gameCache.retrieveGame(matchId);

		checkIfMoveIsValid(kalahGame, chosenHouse);

		ruleChainConfigurator.getaDistributeRule().execute(kalahGame, chosenHouse);

		//Match is over, remove from cache
		if(kalahGame.getGameStatus() == GameStatus.FINISHED){
			gameCache.removeFromCache(matchId);
		}else{
			gameCache.updateCache(kalahGame, matchId);
		}

		return kalahGame;

	}

	/**
	 * Method that checks if the current move is valid before executing the rules.
	 * @param kalahGame
	 * @param chosenHouse
	 */
	public void checkIfMoveIsValid(Game kalahGame, int chosenHouse){

		if(kalahGame == null){
			throw new InvalidMoveException("This match is lo longer available, please start a new one");
		}

		if(chosenHouse < 0 || chosenHouse > Board.HOUSES_NUM - 1){
			throw new InvalidMoveException("This house is invalid, please choose a valid one.");
		}

	}

}
