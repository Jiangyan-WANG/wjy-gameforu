package org.wjy.gameforu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("login data")
public class LoginDataVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "user name")
    private String username;

    @ApiModelProperty(value = "user password")
    private String password;
}
