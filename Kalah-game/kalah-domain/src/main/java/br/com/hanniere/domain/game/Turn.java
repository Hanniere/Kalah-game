package br.com.hanniere.domain.game;

/**
 * This class contains informations about the current move of the match.
 * @author Hanniere
 *
 */
public class Turn {

	private int lastDropedIndex;

	private int lastDropedIndexField;

	private int chosenIndex;

	public Turn(int chosenIndex) {
		this.chosenIndex = chosenIndex;
	}

	public int getLastDropedIndex() {
		return lastDropedIndex;
	}

	public void setLastDropedIndex(int lastDropedIndex) {
		this.lastDropedIndex = lastDropedIndex;
	}

	/**
	 * Returns the field of the player
	 *
	 * @return
	 *
	 * <li> 1 - Field of player 1
	 * <li> 2 - Field of player 2
	 */
	public int getLastDropedIndexField() {
		return lastDropedIndexField;
	}

	public void setLastDropedIndexField(int lastDropedIndexField) {
		this.lastDropedIndexField = lastDropedIndexField;
	}

	public int getChosenIndex() {
		return chosenIndex;
	}

}
