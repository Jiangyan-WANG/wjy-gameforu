package org.wjy.gameforu.model.gameforu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "reply to comment")
@TableName("reply_comment")
public class ReplyComment {

    @TableId(type = IdType.AUTO)
    Integer rcid;

    @TableField("uid")
    Integer uid;

    @TableField("cid")
    Integer cid;

    @TableField("content")
    String content;

    @TableField("datetime")
    Date dateTime;
}
