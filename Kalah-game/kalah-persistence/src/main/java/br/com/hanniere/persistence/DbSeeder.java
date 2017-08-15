package br.com.hanniere.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.hanniere.domain.player.Player;

@Component
public class DbSeeder implements CommandLineRunner {
    private PlayerRepository playerRepository;

    public DbSeeder(PlayerRepository hotelRepository){
        this.playerRepository = hotelRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Player firstPlayer = new Player("Player north");
        Player secondPlayer = new Player("Player South");


        List<Player> players = new ArrayList<>();
        players.add(firstPlayer);
        players.add(secondPlayer);
        this.playerRepository.save(players);
    }
}
