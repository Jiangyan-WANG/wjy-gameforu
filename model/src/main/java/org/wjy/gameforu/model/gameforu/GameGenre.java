package org.wjy.gameforu.model.gameforu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "game and genre")
@TableName("game_genre")
public class GameGenre {

    @TableId(type= IdType.AUTO)
    Integer ggenid;

    @TableField("gid")
    Integer gid;

    @TableField("genid")
    Integer genid;
}
