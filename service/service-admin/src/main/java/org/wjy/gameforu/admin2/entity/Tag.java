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
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String tagname;

    private Integer uid;

    private Integer gid;

    private LocalDateTime tagTime;


}
