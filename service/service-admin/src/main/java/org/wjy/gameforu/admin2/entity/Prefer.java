package org.wjy.gameforu.admin2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Prefer implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private Integer genid;

    private LocalDateTime preferTime;


}
