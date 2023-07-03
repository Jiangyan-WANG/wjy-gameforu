package org.wjy.gameforu.common.result;

import lombok.Getter;
/**
 * 统一返回结果状态信息类
 *
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    FAIL(201, "fail"),
    SERVICE_ERROR(2012, "service error"),
    DATA_ERROR(204, "data error"),
    ILLEGAL_REQUEST(205, "illegal request"),
    REPEAT_SUBMIT(206, "repeat submit"),

    LOGIN_AUTH(208, "need login"),
    PERMISSION(209, "no permission"),
    ;

    private Integer code;

    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}