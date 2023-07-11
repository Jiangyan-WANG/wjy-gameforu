package org.wjy.gameforu.model.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="game search suggest")
public class GameHintVo {
    private static final Long serialVersionUID = 1L;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @ApiModelProperty(value = "search keyword")
    String keyword;
}
