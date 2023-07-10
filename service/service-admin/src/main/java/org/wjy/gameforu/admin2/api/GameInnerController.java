package org.wjy.gameforu.admin2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wjy.gameforu.admin2.service.GameService;
import org.wjy.gameforu.model.entity.Game;

/**
 * inner controller for rpc
 * inner api for elasticsearch or others
 */
@RestController
@RequestMapping("/api/admin/game")
public class GameInnerController {

    @Autowired
    GameService gameService;
    // query game info by game id
    @GetMapping("inner/getgameinfo/{gameId}")
    public Game getGameInfo(@PathVariable Integer gameId){
        Game game = gameService.getById(gameId);
        return game;
    }
}
