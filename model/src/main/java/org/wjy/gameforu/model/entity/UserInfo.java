package org.wjy.gameforu.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    private String phonenumber;

    private String email;

    private LocalDateTime createTime;

    private String token;

}
