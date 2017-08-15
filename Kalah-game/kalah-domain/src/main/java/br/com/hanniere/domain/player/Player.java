package br.com.hanniere.domain.player;

import javax.persistence.*;

@Entity
public class Player {
	public static final int FIRST_PLAYER = 1;
	public static final int SECOND_PLAYER = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    @Transient
    private int playerNumber;

    public Player(String name) {
    	this.name = name;
	}
    //default constructor
    public Player() {

	}

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getPlayerNumber() {
		return playerNumber;
	}

    public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

    @Override
    public boolean equals(Object obj) {
    	Player player = (Player) obj;

    	if(player.getId() == this.id){
    		return true;
    	}

    	return false;
    }

}
