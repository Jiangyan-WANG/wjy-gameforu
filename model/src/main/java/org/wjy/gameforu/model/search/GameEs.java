package org.wjy.gameforu.model.search;

import io.github.classgraph.json.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "gamees", shards = 3, replicas = 1) //elasticsearch index = database
public class GameEs {

    @Id
    Integer id;

    @Field(type = FieldType.Text, analyzer = "ik_smart")
    String keyword;
}
