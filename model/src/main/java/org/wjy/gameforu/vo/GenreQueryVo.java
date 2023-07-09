package org.wjy.gameforu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "game genre name for query")
public class GenreQueryVo {

    // version control
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value="genre name")
    private String genrename;

}
