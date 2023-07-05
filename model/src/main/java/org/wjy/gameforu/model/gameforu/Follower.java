package org.wjy.gameforu.model.gameforu;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "follower")
@TableName("follower")
public class Follower {

    @TableId(type = IdType.AUTO)
    Integer fid;

    @ApiModelProperty("user id")
    @TableField("uid")
    Integer uid;


    @ApiModelProperty("follower user id")
    @TableField("fuid")
    Integer fuid;

    @ApiModelProperty("follow time [default now]")
    @TableField("datetime")
    Date dateTime;
}
