package org.wjy.gameforu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * game view object, which constraint the front-end params
 * </p>
 *
 * @author jy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "game search entity")
public class ThumbsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "thumbs [1:up 0:down]")
    private Integer thumbs;

}
