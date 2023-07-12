package org.wjy.gameforu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.wjy.gameforu.model.search.GameEs;

/**
 * generic: orm, id_type
 */

public interface GameApiRepository extends ElasticsearchRepository<GameEs,Integer> {
    Page<GameEs> findByKeywordLike(String keyword, Pageable pageable);
}
