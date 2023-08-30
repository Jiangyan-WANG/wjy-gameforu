package org.wjy.gameforu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class UserGameRating implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private Integer gid;

    private Double rating;

    private LocalDateTime ratingTime;


}
