package org.wjy.gameforu.common.exception;

import lombok.Data;
import org.wjy.gameforu.common.result.ResultCodeEnum;

@Data
public class GameForUException extends RuntimeException {
    // exception code
    private Integer code;

    /**
     * constructor by message and code
     * @param message
     * @param code
     */
    public GameForUException(String message, Integer code){
        super(message);
        this.setCode(code);
    }

    public GameForUException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.setCode(resultCodeEnum.getCode());
    }


}
