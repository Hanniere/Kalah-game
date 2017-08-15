package br.com.hanniere.domain.game;

import br.com.hanniere.domain.game.pit.impl.House;
import br.com.hanniere.domain.game.pit.impl.Store;
import br.com.hanniere.domain.player.Player;

public class Board {


	/**
	 * Number of Houses for each player
	 */
	public static final int HOUSES_NUM = 6;
	public static final int STORE_INDEX = -1;

	private int numberOfStonesForPit;

	private Pit[] player1HouseList;
	private Pit[] player2HouseList;
	private Pit player1Store;
	private Pit player2Store;

	/**
	 * empty constructor
	 */
	public Board() {
	}

	public Board(int numberOfStonesForPit){
		this.numberOfStonesForPit = numberOfStonesForPit;
	}

	public void initialize(){

		player1HouseList = new House[HOUSES_NUM];
		player2HouseList = new House[HOUSES_NUM];
		player1Store = new Store(0);
		player2Store = new Store(0);

		for (int i = 0; i < HOUSES_NUM; i++) {
			player1HouseList[i] = new House(numberOfStonesForPit);
			player2HouseList[i] = new House(numberOfStonesForPit);
		}
	}

	public Pit[] retrievePlayerHouses(int playerNumber){
		return playerNumber == Player.FIRST_PLAYER? player1HouseList: player2HouseList;
	}

	public Pit retrievePlayerStore(int playerNumber){
		return playerNumber == Player.FIRST_PLAYER? (Store)player1Store: (Store)player2Store;
	}

	/**
	 * Verifies if the houses of a player are empties.
	 * @param playerNumber
	 * @return
	 * <li> true - are empties.
	 * <li> false - at least one house has stones.
	 */
	public Boolean verifyIfPlayerHousesIsEmpty(int playerNumber){

		Pit[] playerHouses = retrievePlayerHouses(playerNumber);

		for (Pit pit : playerHouses) {

			if(pit.getStones() > 0){
				return false;
			}
		}

		return true;
	}

	//getters and setters

	public int getNumberOfStonesForPit() {
		return numberOfStonesForPit;
	}

	public void setNumberOfStonesForPit(int numberOfStonesForPit) {
		this.numberOfStonesForPit = numberOfStonesForPit;
	}

	public Pit[] getPlayer1HouseList() {
		return player1HouseList;
	}

	public void setPlayer1HouseList(Pit[] player1HouseList) {
		this.player1HouseList = player1HouseList;
	}

	public Pit[] getPlayer2HouseList() {
		return player2HouseList;
	}

	public void setPlayer2HouseList(Pit[] player2HouseList) {
		this.player2HouseList = player2HouseList;
	}

	public Pit getPlayer1Store() {
		return player1Store;
	}

	public void setPlayer1Store(Pit player1Store) {
		this.player1Store = player1Store;
	}

	public Pit getPlayer2Store() {
		return player2Store;
	}

	public void setPlayer2Store(Pit player2Store) {
		this.player2Store = player2Store;
	}

}
