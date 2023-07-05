package org.wjy.gameforu.model.gameforu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "game genre")
@TableName("genre")
public class Genre {


    private static final Long serialVersionUID=1L;

    @TableId(type = IdType.AUTO)
    private Integer genid;

    @TableField("name")
    private String genreName;
}
