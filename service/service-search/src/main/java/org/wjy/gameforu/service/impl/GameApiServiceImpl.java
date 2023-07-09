package org.wjy.gameforu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.client.game.GameFeignClient;
import org.wjy.gameforu.model.entity.Game;
import org.wjy.gameforu.model.search.GameEs;
import org.wjy.gameforu.repository.GameApiRepository;
import org.wjy.gameforu.service.GameApiService;

@Service
public class GameApiServiceImpl implements GameApiService {

    @Autowired
    GameApiRepository gameApiRepository;

    @Autowired
    GameFeignClient gameFeignClient;

    @Override
    public void addGame(Integer gameId) {
        Game gameInfo = gameFeignClient.getGameInfo(gameId);
        if(gameInfo==null) return;
        GameEs gameEs = new GameEs();
        gameEs.setKeyword(gameInfo.getGamename());

        gameApiRepository.save(gameEs);
    }

    @Override
    public void removeGame(Integer gameId) {
        gameApiRepository.deleteById(gameId);
    }
}
