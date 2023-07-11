package org.wjy.gameforu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer appid;

    private String gamename;

    private String developer;

    private String publisher;

    private Integer positive;

    private Integer negative;

    private Integer userscore;

    private String owners;

    @TableField("initialPrice")
    private Integer initialprice;

    private Integer ccu;

    private String description;

    private String logourl;

    private LocalDateTime addTime;


}
