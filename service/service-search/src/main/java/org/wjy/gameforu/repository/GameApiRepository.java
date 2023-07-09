package org.wjy.gameforu.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.wjy.gameforu.model.search.GameEs;

/**
 * generic: orm, id_type
 */
public interface GameApiRepository extends ElasticsearchRepository<GameEs,Integer> {
}
