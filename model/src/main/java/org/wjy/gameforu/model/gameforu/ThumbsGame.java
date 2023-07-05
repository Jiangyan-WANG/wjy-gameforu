package org.wjy.gameforu.model.gameforu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "thumbs up on games")
@TableName("thumbs_game")
public class ThumbsGame {

    @TableId(type = IdType.AUTO)
    Integer tgid;

    @TableField("uid")
    Integer uid;

    @TableField("gid")
    Integer gid;

    @TableField("up")
    Boolean up;
}
