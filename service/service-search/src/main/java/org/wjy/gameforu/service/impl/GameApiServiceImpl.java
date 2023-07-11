package org.wjy.gameforu.service.impl;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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
        gameEs.setId(gameInfo.getId());
        gameEs.setKeyword(gameInfo.getGamename());

        gameApiRepository.save(gameEs);
    }

    @Override
    public void removeGame(Integer gameId) {
        gameApiRepository.deleteById(gameId);
    }

    @Override
    public Page<GameEs> getSuggestGame(String keyword, Pageable pageable) {
        Page<GameEs> suggestGame = null;
        // 构建查询条件
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        // 查询field
//        queryBuilder.withQuery(QueryBuilders.termQuery("keyword", keyword));
//        // 分页设置
//        queryBuilder.withPageable(pageable);
//        // 查询
//        suggestGame = gameApiRepository.search(queryBuilder.build());

        // TODO debug, why convert to Integer
        suggestGame = gameApiRepository.findByKeywordLike(keyword.replaceAll(" ",""),pageable);
//        Page<GameEs> allGame = gameApiRepository.findAll(pageable);
        return suggestGame;
    }
}
