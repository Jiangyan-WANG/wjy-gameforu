package org.wjy.gameforu.service.impl;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.wjy.gameforu.client.game.GameFeignClient;
import org.wjy.gameforu.model.entity.Game;
import org.wjy.gameforu.model.search.GameEs;
import org.wjy.gameforu.repository.GameApiRepository;
import org.wjy.gameforu.service.GameApiService;

import java.util.ArrayList;
import java.util.List;


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

        String[] keywords = keyword.split(" ");
        // * query linked with bool
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for(int i = 0; i<keywords.length; i++){
            // matchQuery must match whole word, try fuzzy or wildcardQuery* ?
            boolQueryBuilder.must(QueryBuilders.fuzzyQuery("keyword", keywords[i]));
        }



        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        // highlight method
        String preTag = "<font color='#dd4b39'>";//google的色值
        String postTag = "</font>";

        nativeSearchQueryBuilder.withQuery(boolQueryBuilder) // add bool query
//                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC)) // if sort use this
                .withHighlightFields( // highlight
                        new HighlightBuilder.Field("keyword"))
                .withHighlightBuilder(
                        new HighlightBuilder().preTags(preTag).postTags(postTag))
                .withPageable(pageable); // paginaton
        suggestGame = gameApiRepository.search(nativeSearchQueryBuilder.build());
        // 构建查询条件
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        // 查询field
//        queryBuilder.withQuery(QueryBuilders.termQuery("keyword", keyword));
//        // 分页设置
//        queryBuilder.withPageable(pageable);
//        // 查询
//        suggestGame = gameApiRepository.search(queryBuilder.build());

        // TODO debug, why convert to Integer
//        suggestGame = gameApiRepository.findByKeywordLike(keyword.replaceAll(" ",""),pageable);
//        Page<GameEs> allGame = gameApiRepository.findAll(pageable);
        return suggestGame;
    }
}
