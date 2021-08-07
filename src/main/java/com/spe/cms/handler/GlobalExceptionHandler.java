package com.spe.cms.handler;

import com.spe.cms.eexception.ConflictTitleException;
import com.spe.cms.eexception.UnExistColumnException;
import com.spe.cms.eexception.UnExistTabelException;
import com.spe.cms.response.R;
import com.spe.cms.response.ResultCodeEnum;
import com.spe.cms.util.ExceptionUtil;
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
     * -------- Common exception handling methods --------
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        log.error(ExceptionUtil.getMessage(e));
        return R.error();    // General anomaly result
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
     * -------- Specifies the exception handling method --------
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
