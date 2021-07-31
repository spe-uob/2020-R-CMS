package com.leon.dynamiccolumn.handler;

import com.leon.dynamiccolumn.eexception.ConflictTitleException;
import com.leon.dynamiccolumn.eexception.UnExistColumnException;
import com.leon.dynamiccolumn.eexception.UnExistTabelException;
import com.leon.dynamiccolumn.response.R;
import com.leon.dynamiccolumn.response.ResultCodeEnum;
import com.leon.dynamiccolumn.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * -------- 通用异常处理方法 --------
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        log.error(ExceptionUtil.getMessage(e));
        return R.error();    // 通用异常结果
    }

    @ExceptionHandler(UnExistTabelException.class)
    @ResponseBody
    public R error(UnExistTabelException e) {
        log.error(ExceptionUtil.getMessage(e));
        return R.setResult(ResultCodeEnum.UNEXIST_TABLE);
    }

    @ExceptionHandler(UnExistColumnException.class)
    @ResponseBody
    public R error(UnExistColumnException e) {
        log.error(ExceptionUtil.getMessage(e));
        return R.setResult(ResultCodeEnum.UNEXIST_COLUMN);
    }

    @ExceptionHandler(ConflictTitleException.class)
    @ResponseBody
    public R error(ConflictTitleException e) {
        log.error(ExceptionUtil.getMessage(e));
        return R.setResult(ResultCodeEnum.CONFLICT_TITLE);
    }

    /**
     * -------- 指定异常处理方法 --------
     **/
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public R error(NullPointerException e) {
        log.error(ExceptionUtil.getMessage(e));
        return R.setResult(ResultCodeEnum.NULL_POINT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R error(MethodArgumentNotValidException e) {
        log.error(ExceptionUtil.getMessage(e));
        return R.setResult(ResultCodeEnum.PARAM_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public R error(IndexOutOfBoundsException e) {
        log.error(ExceptionUtil.getMessage(e));
        return R.setResult(ResultCodeEnum.HTTP_CLIENT_ERROR);
    }
}
