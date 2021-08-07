package com.spe.cms.response;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class R {

    private Boolean success;

    private Integer code;

    private String message;

//    private Map<String, Object> data = new HashMap<>();
    private Object data = new HashMap<>();

    // Constructor private
    private R() {
    }

    // General return success
    public static R ok() {
        R r = new R();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    // General return failed, unknown error
    public static R error() {
        R r = new R();
        r.setSuccess(ResultCodeEnum.UNKNOWN_ERROR.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
        return r;
    }

    // Sets the result with the parameter as the result enumeration
    public static R setResult(ResultCodeEnum result) {
        R r = new R();
        r.setSuccess(result.getSuccess());
        r.setCode(result.getCode());
        r.setMessage(result.getMessage());
        return r;
    }

    /**
     * ------------Using chained programming, return the class itself-----------
     **/

    // Customize the returned data
    public R data(Object data) {
        this.setData(data);
        return this;
    }

    public R data(String key,Object value){
        Object data = this.getData();
        if (ObjectUtil.isNotNull(data)){
            if (data instanceof Map){
                ((Map<String, Object>) data).put(key,value);
                return this;
            }
        }
        HashMap<String, Object> mapData = new HashMap<String, Object>() {
            {
                put(key, value);
            }
        };
        return this.setData(mapData);
    }

    // Customize status information
    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    // Custom status code
    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    // Custom return results
    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
