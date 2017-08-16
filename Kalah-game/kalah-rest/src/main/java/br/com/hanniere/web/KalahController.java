package br.com.hanniere.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.hanniere.domain.game.Game;
import br.com.hanniere.service.KalahService;

/**
 * This class has the REST API's of that has to be used to play the game.
 * @author Hanniere
 *
 */
@RestController
@RequestMapping("/kalah")
public class KalahController {

    @Autowired
    KalahService kalahService;

    /**
     * This is the REST API to start the game.
     * @param numOfStones - Num of stones to game start in each house.
     * @return matchId - This API returns the match id of the game that was started.
     *
     * Example: 54103c66-9b23-4427-82ee-787e71121b6f
     */
    @PostMapping(value = "/start")
    public ResponseEntity<String> initiateGame(@RequestParam("stones") int numOfStones){

        return ResponseEntity.status(HttpStatus.OK).body(kalahService.initializeGame(numOfStones));
    }

    /**
     * This is the REST API to make the moves in the game according to the house chosen
     * by the current player.
     * @param chosenHouse - Index of the house to make the move. It can have values in the range 0-5.
     * @param matchId - Id of the current match. It is returned by the initiate API.
     * @return Game - JSON containing all the game current data.
     */
    @PostMapping(value = "/play")
    public ResponseEntity<Game> play(@RequestParam("house") int chosenHouse, String matchId){
        return ResponseEntity.status(HttpStatus.OK).body(kalahService.performMove(matchId, chosenHouse));
    }

    //TODO Reset game
}
