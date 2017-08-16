package br.com.hanniere.domain.game.pit.impl;

import br.com.hanniere.domain.game.Pit;

/**
 * Concrete class that represents a Store. Each player has one store, it is the place where the player gather
 * all of its stones.
 * @author Hanniere
 *
 */
public class Store extends Pit{

	public Store(int stones) {
		super(stones, TYPE_STORE);
	}

}
