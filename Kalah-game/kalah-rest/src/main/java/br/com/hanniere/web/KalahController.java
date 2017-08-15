package br.com.hanniere.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.hanniere.domain.game.Game;
import br.com.hanniere.domain.player.Player;
import br.com.hanniere.service.KalahService;

@RestController
@RequestMapping("/kalah")
public class KalahController {

    @Autowired
    KalahService kalahService;


    @PostMapping(value = "/start")
    public ResponseEntity<Game> initiateGame(@RequestParam("stones") int numOfStones, @RequestBody Player player1, Player player2){

        return ResponseEntity.status(HttpStatus.OK).body(kalahService.initializeGame(numOfStones, player1, player2));
    }


    @PostMapping(value = "/play")
    public ResponseEntity<Game> play(@RequestParam("house") int chosenHouse, @RequestBody Game kalahGame){

        return ResponseEntity.status(HttpStatus.OK).body(kalahService.performTurn(kalahGame, chosenHouse));
    }

    //TODO Reset game
}
