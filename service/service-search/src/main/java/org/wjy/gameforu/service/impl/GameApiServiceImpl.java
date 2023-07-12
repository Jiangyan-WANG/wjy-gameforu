package org.wjy.gameforu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wjy.gameforu.client.game.GameFeignClient;
import org.wjy.gameforu.model.entity.Game;
import org.wjy.gameforu.model.search.GameEs;
import org.wjy.gameforu.repository.GameApiRepository;
import org.wjy.gameforu.service.GameApiService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        gameEs.setId(gameInfo.getId().toString());
        gameEs.setKeyword(gameInfo.getGamename());

        gameApiRepository.save(gameEs);
    }

    @Override
    public void removeGame(Integer gameId) {
        gameApiRepository.deleteById(gameId);
    }

//    @Override
//    public Page<GameEs> getSuggestGame2(String keyword, Pageable pageable) {
//        Page<GameEs> suggestGame = null;
//
//        String[] keywords = keyword.split(" ");
//        // * query linked with bool
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        for(int i = 0; i<keywords.length; i++){
//            // matchQuery must match whole word, try fuzzy or wildcardQuery* ?
//            boolQueryBuilder.must(QueryBuilders.fuzzyQuery("keyword", keywords[i]));
//        }
//
//
//
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//
//        // highlight method
//        String preTag = "<font color='#dd4b39'>";//google的色值
//        String postTag = "</font>";
//
//        nativeSearchQueryBuilder.withQuery(boolQueryBuilder) // add bool query
////                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC)) // if sort use this
//                .withHighlightFields( // highlight
//                        new HighlightBuilder.Field("keyword"))
//                .withHighlightBuilder(
//                        new HighlightBuilder().preTags(preTag).postTags(postTag))
//                .withPageable(pageable); // paginaton
//        suggestGame = gameApiRepository.search(nativeSearchQueryBuilder.build());
//        // 构建查询条件
////        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
////        // 查询field
////        queryBuilder.withQuery(QueryBuilders.termQuery("keyword", keyword));
////        // 分页设置
////        queryBuilder.withPageable(pageable);
////        // 查询
////        suggestGame = gameApiRepository.search(queryBuilder.build());
//
//        // TODO debug, why convert to Integer
////        suggestGame = gameApiRepository.findByKeywordLike(keyword.replaceAll(" ",""),pageable);
////        Page<GameEs> allGame = gameApiRepository.findAll(pageable);
//        return suggestGame;
//    }

//    @Override
//    public Page<GameEs> getSuggestGame(String keyword, Pageable pageable){
//        Streamable<GameEs> res = null;
//        String[] keywords=keyword.trim().split(" ");
//        for (int i=0;i<keywords.length;i++) {
//            if(i==0){
//                res = gameApiRepository.findByKeywordLike(keywords[i]);
//            }else {
//                res = res.and(gameApiRepository.findByKeywordLike(keywords[i]));
//            }
//        }
////        Page<GameEs> pageModel = res.and(gameApiRepository.findAll(pageable));
//        List<GameEs> gameEsList = new ArrayList<>();
////      distinct remove duplicated, need equals method
//        gameEsList = res.stream().distinct().collect(Collectors.toList());
//
//        Page<GameEs> gameEs = new PageImpl<>(gameEsList, pageable, 10);
//        return gameEs;
//    }

    @Override
    public Page<SearchHit<GameEs>> getSuggestGame(String keyword, Pageable pageable){
        List<SearchHits<GameEs>> res = new ArrayList<>();
        String[] keywords=keyword.trim().split(" ");
        for (int i=0;i < keywords.length;i++) {
            if(!StringUtils.isEmpty(keywords[i])){

                res.add(gameApiRepository.findByKeywordLike(keywords[i]));

            }
        }
//        Page<GameEs> pageModel = res.and(gameApiRepository.findAll(pageable));
//        List<GameEs> gameEsList = new ArrayList<>();
//      distinct remove duplicated, need equals method
        Streamable<SearchHit<GameEs>> streamRes = Streamable.of();
        for (SearchHits<GameEs> re : res) {
            for (SearchHit<GameEs> gameEsSearchHit : re) {
                streamRes = streamRes.and(gameEsSearchHit);
            }
        }

//        List<SearchHit<GameEs>> gameEsList = streamRes.stream().distinct().collect(Collectors.toList());
        // new method: remove duplicated
        List<SearchHit<GameEs>> gameEsList = streamRes.stream().filter(distinctByKey(SearchHit::getId)).collect(Collectors.toList());

        Page<SearchHit<GameEs>> gameEs = new PageImpl<>(gameEsList, pageable, 10);
        return gameEs;
    }

    /**
     * remove duplicated
     * @param keyExtractor
     * @param <T>
     * @return
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
