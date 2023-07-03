package org.wjy.gameforu.common.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wjy.gameforu.common.result.Result;

/**
 * by AOP
 * global excetion handler
 * to make sure unified result to user
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Global Exception Handler
     * ResponseBody represent json response body
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null);
    }

    /**
     * handler for self-defined exception
     * @param e
     * @return
     */
    @ExceptionHandler(GameForUException.class)
    @ResponseBody
    public Result error(GameForUException e){
        e.printStackTrace();
        return Result.fail(null);
    }
}
