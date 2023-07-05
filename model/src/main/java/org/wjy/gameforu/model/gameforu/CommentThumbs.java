package org.wjy.gameforu.model.gameforu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "comment thumbs")
@TableName("comment_thumbs")
public class CommentThumbs {

    @TableId(type = IdType.AUTO)
    Integer tcid;

    @TableField("uid")
    Integer uid;

    @TableField("cid")
    Integer cid;

    @ApiModelProperty("thumbs up [1-up 0-down]")
    @TableField("up")
    Boolean up;
}
