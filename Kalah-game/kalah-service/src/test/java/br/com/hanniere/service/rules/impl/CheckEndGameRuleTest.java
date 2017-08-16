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
public class CheckEndGameRuleTest {

	@Autowired
	private PlayerRepository playerRepository;

	@Resource(name="checkEndGameRule")
	private KalahRule checkEndGameRule;

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
	public void testEndPlayer1() {

		kalahGame.setCurrentPlayer(kalahGame.getPlayersList().get(0));


		kalahGame.getGameBoard().getPlayer1HouseList()[0].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[1].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[2].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[3].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[4].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[5].setStones(0);
		kalahGame.getGameBoard().getPlayer1Store().setStones(1);

		checkEndGameRule.execute(kalahGame, -1);

		assertEquals(GameStatus.FINISHED, kalahGame.getGameStatus());
	}

	@Test
	public void testEndPlayer2() {

		kalahGame.setCurrentPlayer(kalahGame.getPlayersList().get(1));


		kalahGame.getGameBoard().getPlayer2HouseList()[0].setStones(0);
		kalahGame.getGameBoard().getPlayer2HouseList()[1].setStones(0);
		kalahGame.getGameBoard().getPlayer2HouseList()[2].setStones(0);
		kalahGame.getGameBoard().getPlayer2HouseList()[3].setStones(0);
		kalahGame.getGameBoard().getPlayer2HouseList()[4].setStones(0);
		kalahGame.getGameBoard().getPlayer2HouseList()[5].setStones(0);
		kalahGame.getGameBoard().getPlayer2Store().setStones(1);

		checkEndGameRule.execute(kalahGame, -1);

		assertEquals(GameStatus.FINISHED, kalahGame.getGameStatus());
	}

	@Test
	public void testPlayer1Winner() {

		kalahGame.setCurrentPlayer(kalahGame.getPlayersList().get(0));


		kalahGame.getGameBoard().getPlayer2HouseList()[0].setStones(0);
		kalahGame.getGameBoard().getPlayer2HouseList()[1].setStones(0);
		kalahGame.getGameBoard().getPlayer2HouseList()[2].setStones(0);
		kalahGame.getGameBoard().getPlayer2HouseList()[3].setStones(0);
		kalahGame.getGameBoard().getPlayer2HouseList()[4].setStones(0);
		kalahGame.getGameBoard().getPlayer2HouseList()[5].setStones(0);
		kalahGame.getGameBoard().getPlayer2Store().setStones(35);

		checkEndGameRule.execute(kalahGame, -1);

		assertEquals(GameStatus.FINISHED, kalahGame.getGameStatus());
		assertEquals(kalahGame.getPlayersList().get(0), kalahGame.getWinner());
	}

	@Test
	public void testPlayer2Winner() {

		kalahGame.setCurrentPlayer(kalahGame.getPlayersList().get(0));


		kalahGame.getGameBoard().getPlayer1HouseList()[0].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[1].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[2].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[3].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[4].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[5].setStones(0);
		kalahGame.getGameBoard().getPlayer1Store().setStones(35);

		checkEndGameRule.execute(kalahGame, -1);

		assertEquals(GameStatus.FINISHED, kalahGame.getGameStatus());
		assertEquals(kalahGame.getPlayersList().get(1), kalahGame.getWinner());
	}

	@Test
	public void testGameDraw() {

		kalahGame.setCurrentPlayer(kalahGame.getPlayersList().get(0));


		kalahGame.getGameBoard().getPlayer1HouseList()[0].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[1].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[2].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[3].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[4].setStones(0);
		kalahGame.getGameBoard().getPlayer1HouseList()[5].setStones(0);
		kalahGame.getGameBoard().getPlayer1Store().setStones(36);

		checkEndGameRule.execute(kalahGame, -1);

		assertEquals(GameStatus.FINISHED, kalahGame.getGameStatus());
		assertEquals(null, kalahGame.getWinner());
	}

}
