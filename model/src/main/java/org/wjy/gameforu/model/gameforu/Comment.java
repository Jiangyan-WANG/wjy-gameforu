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
@ApiModel(description = "comment to game")
@TableName("comment")
public class Comment {

    @TableId(type = IdType.AUTO)
    Integer cid;

    @ApiModelProperty("user id")
    @TableField("uid")
    Integer uid;

    @ApiModelProperty("game id")
    @TableField("gid")
    Integer gid;

    @TableField("content")
    String content;

    @ApiModelProperty("add date [default now]")
    @TableField("datetime")
    Date dateTime;
}
