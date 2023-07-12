package org.wjy.gameforu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.util.Streamable;
import org.wjy.gameforu.model.search.GameEs;

/**
 * generic: orm, id_type
 */
public interface GameApiRepository extends ElasticsearchRepository<GameEs,Integer> {

//    Streamable<GameEs> findByKeywordLike(String keyword);

    @Highlight(fields = {
            @HighlightField(name="keyword")
    }
//    ,
//            parameters = @HighlightParameters(
//                    preTags = "<strong><font style='color:red'>",
//                    postTags = "</font></strong>",
//                    fragmentSize = 500,
//                    numberOfFragments = 3
//            )
    )
    SearchHits<GameEs> findByKeywordLike(String keyword);

}
