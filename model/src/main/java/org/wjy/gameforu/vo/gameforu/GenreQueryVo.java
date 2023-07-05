package org.wjy.gameforu.vo.gameforu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "game genre name for query")
public class GenreQueryVo {

    // version control
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value="game name")
    private String genrename;

}
