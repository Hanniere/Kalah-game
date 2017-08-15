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
	public int emptyHouse(){
		int stonesToRetry = getStones();
		setStones(0);;
		return stonesToRetry;
	}

}
