package br.com.hanniere.domain.game;

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
