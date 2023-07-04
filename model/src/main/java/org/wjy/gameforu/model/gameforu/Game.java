package org.wjy.gameforu.model.gameforu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "game")
@TableName(value="game",autoResultMap = true)
public class Game {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "game id") //swagger: desc for attribute
    @TableId(type = IdType.AUTO) //mp: primary key and type (AUTO auto_increment)
    private Integer gid;

    @ApiModelProperty(value = "appid in steamdb, not null")
    @TableField("appid")
    private String appid;

    @TableField("name")
    private String name;

    @TableField("developer")
    private String developer;

    @TableField("publisher")
    private String publisher;

    @TableField("positive")
    private Integer positive;

    @TableField("negative")
    private Integer negative;

    @TableField("userscore")
    private Integer userscore;

    @TableField("owners")
    private String owners;

    @TableField("initialPrice")
    private Integer initialPrice;

    @ApiModelProperty(value = "concurrent users")
    @TableField("ccu")
    private Integer ccu;

    @TableField("description")
    private String description;
}
