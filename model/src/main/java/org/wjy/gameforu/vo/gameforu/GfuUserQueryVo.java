package org.wjy.gameforu.vo.gameforu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * Gfu用户查询实体
 * </p>
 *
 * @author jy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "用户查询实体")
public class GfuUserQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    private String userName;

}
