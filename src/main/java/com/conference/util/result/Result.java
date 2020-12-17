package com.conference.util.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/14 21:58
 * @sno 6109118015
 */

public class Result {
    private int code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result(ResultCode.SUCCESS);
    }

    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    /**
     * @param singleDataKey 唯一的数据的key
     * @param singleDataValue 唯一的数据的value
     * @return 返回封装好的 Result
     */
    public static Result success(String singleDataKey, Object singleDataValue) {
        Map<String, Object> data = new HashMap<>();
        data.put(singleDataKey, singleDataValue);
        return success(data);
    }

    public static Result error() {
        return new Result(ResultCode.ERROR);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public static Result error(Object data) {
        return new Result(ResultCode.ERROR, data);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
