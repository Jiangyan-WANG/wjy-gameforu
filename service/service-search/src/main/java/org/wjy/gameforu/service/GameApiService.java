package org.wjy.gameforu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wjy.gameforu.model.search.GameEs;

import java.util.List;

public interface GameApiService {
    void addGame(Integer gameId);

    void removeGame(Integer gameId);

    /**
     * query suggest Games when user search
     * by elasticsearch
     * @param keyword
     * @return
     */
    Page<GameEs> getSuggestGame(String keyword, Pageable pageable);
}
