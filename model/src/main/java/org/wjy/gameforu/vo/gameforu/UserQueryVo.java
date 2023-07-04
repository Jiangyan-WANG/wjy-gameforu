package org.wjy.gameforu.vo.gameforu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * user view object
 * </p>
 *
 * @author jy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "user search entity")
public class UserQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "username")
    private String userName;

}
