package br.com.hanniere.domain.game.pit.impl;

import br.com.hanniere.domain.game.Pit;

public class House extends Pit{

	public House(int stones) {
		super(stones, TYPE_HOUSE);
	}


	/**
	 * This method can be used in a turn or capture stones.
	 * @return stones qtd
	 */
	public int emptyTheHouse(){
		int stonesToRetry = getStones();
		setStones(0);;
		return stonesToRetry;
	}

	/**
	 * Returns if the house was empty when player dropped the stones.
	 *
	 * @return
	 * <li>true - it was empty
	 * <li>false - it was not empty
	 *
	 */
	public Boolean wasEmpty(){
		return getStones() == 1? true: false;
	}

}
