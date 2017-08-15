package br.com.hanniere.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hanniere.domain.game.*;
import br.com.hanniere.domain.player.Player;
import br.com.hanniere.persistence.PlayerRepository;
import br.com.hanniere.service.rules.RuleChainConfigurator;

@Service
public class KalahService {

	@Autowired
	private Game kalahGame;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private RuleChainConfigurator ruleChainConfigurator;


	public Game initializeGame(int numberOfStonesForPit, Player player1, Player player2){

		List<Player> playersList = this.playerRepository.findAll();

		for (Player player : playersList) {
			if(player.equals(player1))
				player.setPlayerNumber(1);
			else
				player.setPlayerNumber(2);

		}

		Board kalahBoard = new Board(numberOfStonesForPit);
		kalahBoard.initialize();
		kalahGame.setGameBoard(kalahBoard);
		kalahGame.setGameStatus(GameStatus.INPROGRESS);
		kalahGame.setPlayersList(playersList);
		kalahGame.setCurrentPlayer(playersList.get(0));

		return kalahGame;
	}

	public Game performTurn(Game kalahGame, int chosenHouse){

		ruleChainConfigurator.getaDistributeRule().execute(kalahGame, chosenHouse);

		return kalahGame;
	}

	//TODO implement method to reset the game

}
