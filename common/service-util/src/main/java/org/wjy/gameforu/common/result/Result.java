package org.wjy.gameforu.common.result;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    private Result(){}

    public static<T> Result<T> build(T data, ResultCodeEnum resultCodeEnum){
        Result<T> res = new Result<>();
        if(data!=null){
            res.setData(data);
        }
        res.setCode(resultCodeEnum.getCode());
        res.setMessage(resultCodeEnum.getMessage());
        return res;
    }

    //    返回成功
    public static<T> Result<T> ok(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }
    //    返回失败
    public static<T> Result<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }
}
