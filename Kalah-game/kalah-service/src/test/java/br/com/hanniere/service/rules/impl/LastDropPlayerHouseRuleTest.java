package br.com.hanniere.service.rules.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.hanniere.domain.game.Board;
import br.com.hanniere.domain.game.Game;
import br.com.hanniere.domain.game.GameStatus;
import br.com.hanniere.domain.player.Player;
import br.com.hanniere.repository.PlayerRepository;
import br.com.hanniere.service.rules.KalahRule;

public class LastDropPlayerHouseRuleTest {

	@Autowired
	private PlayerRepository playerRepository;

	@Resource(name="lastDropPlayerHouseRule")
	private KalahRule lastDropPlayerHouseRule;

	private Game kalahGame;

	@Before
	public void initializeTest(){

		List<Player> playersList = this.playerRepository.findAll();
		playersList.get(0).setPlayerNumber(1);
		playersList.get(1).setPlayerNumber(2);

		kalahGame = new Game();

		Board kalahBoard = new Board(6);
		kalahBoard.initialize();
		kalahGame.setGameBoard(kalahBoard);
		kalahGame.setGameStatus(GameStatus.INPROGRESS);
		kalahGame.setPlayersList(playersList);

	}


}
