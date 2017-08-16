package br.com.hanniere.service.rules.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.hanniere.domain.game.Board;
import br.com.hanniere.domain.game.Game;
import br.com.hanniere.domain.game.GameStatus;
import br.com.hanniere.domain.player.Player;
import br.com.hanniere.repository.PlayerRepository;
import br.com.hanniere.service.ServiceApplication;
import br.com.hanniere.service.rules.KalahRule;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"embeddeddb"})
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = ServiceApplication.class)
public class ChangePlayerRuleTest {

	@Autowired
	private PlayerRepository playerRepository;

	@Resource(name="changePlayerRule")
	private KalahRule changePlayerRule;

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

	@Test
	public void testChangeFromPlayer1ToPlayer2() {
		kalahGame.setCurrentPlayer(kalahGame.getPlayersList().get(0));
		changePlayerRule.execute(kalahGame, -1);
		assertEquals(kalahGame.getCurrentPlayer().getPlayerNumber(), 2);

	}

	@Test
	public void testChangeFromPlayer2ToPlayer1() {
		kalahGame.setCurrentPlayer(kalahGame.getPlayersList().get(1));
		changePlayerRule.execute(kalahGame, -1);
		assertEquals(kalahGame.getCurrentPlayer().getPlayerNumber(), 1);
	}

}
