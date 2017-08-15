package br.com.hanniere.domain.game;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.hanniere.domain.player.Player;

/**
 * This class represents the current game.
 * @author Hanniere
 *
 */
@Component
public class Game {

	private Board gameBoard;

	private GameStatus gameStatus;

	//Current house index of the turn.
	private Turn currentTurn;

	private List<Player> playersList;

	private Player currentPlayer;

	private Player winner;



	//getters and setters
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Turn getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(Turn currentTurn) {
		this.currentTurn = currentTurn;
	}

	public Board getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public List<Player> getPlayersList() {
		return playersList;
	}

	public void setPlayersList(List<Player> playersList) {
		this.playersList = playersList;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}


}
