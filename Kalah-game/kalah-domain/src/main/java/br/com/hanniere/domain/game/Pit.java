package br.com.hanniere.domain.game;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.hanniere.domain.deserializer.PitDeserializer;

@JsonDeserialize(using = PitDeserializer.class)
public abstract class Pit {
	public final static String TYPE_HOUSE = "house";
	public final static String TYPE_STORE = "store";

	private int stones;

	private String type;

	public Pit(int stones, String type) {
		this.stones = stones;
		this.type = type;
	}

	public void addStone(int stones) {
		this.stones += stones;
	}

	public void setStones(int stones) {
		this.stones = stones;
	}

	public int getStones() {
		return stones;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Pit with: "+ stones + " ";
	}

}
