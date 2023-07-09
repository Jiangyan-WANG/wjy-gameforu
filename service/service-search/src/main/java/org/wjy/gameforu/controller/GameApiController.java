package org.wjy.gameforu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.service.GameApiService;

@RestController
@RequestMapping("api/search/game")
public class GameApiController {

    @Autowired
    GameApiService gameApiService;

    //add game
    @GetMapping("inner/addGame/{gameId}")
    public Result add(@PathVariable Integer gameId){
        gameApiService.addGame(gameId);
        return Result.ok(null);
    }

    //delete game
    @GetMapping("inner/removeGame/{gameId}")
    public Result remove(@PathVariable Integer gameId){
        gameApiService.removeGame(gameId);
        return Result.ok(null);
    }
}
