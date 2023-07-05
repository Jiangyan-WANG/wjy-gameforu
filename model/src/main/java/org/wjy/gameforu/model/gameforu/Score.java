package org.wjy.gameforu.model.gameforu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "score on the games")
@TableName("score")
public class Score {

    @TableId(type = IdType.AUTO)
    Integer sid;

    @TableField("uid")
    Integer uid;

    @TableField("gid")
    Integer gid;

    @TableField("score")
    Integer score;
}
