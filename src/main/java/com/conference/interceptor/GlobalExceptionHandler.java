package com.conference.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ResponseBody
//    @ExceptionHandler
//    public Result handleException(Exception e, HttpServletResponse response){
//        String msg = e.getMessage();
////        if (msg == null || msg.equals("")) {
////            msg = "服务器出错";
////        }
////        JSONObject jsonObject = new JSONObject();
////        jsonObject.put("message", msg);
////        return jsonObject;
//            return Result.success();
//    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e, HttpServletResponse response) {
        System.out.println(e);

        if (e instanceof BindException) {                   //参数校验错误
            BindException be = (BindException) e;
            FieldError error = be.getFieldError();
            String message = error.getDefaultMessage();
            Result result = new Result(ResultCode.BindException);
            result.setMessage(message);
            return result;
        } else if (e instanceof MethodArgumentNotValidException) {        //参数校验错误
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) e;
            String message = manve.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            Result result = new Result(ResultCode.BindException);
            result.setMessage(message);
            return result;
        } else if (e instanceof DuplicateKeyException) {                            //唯一键插入重复数据
            return new Result(ResultCode.DuplicateKeyException);
        } else if (e instanceof IncorrectCredentialsException) {                    //密码不正确
            return new Result(ResultCode.IncorrectCredentialsException);
        } else if (e instanceof UnknownAccountException) {                          //此账号不存在
            return new Result(ResultCode.UnknownAccountException);
        } else if (e instanceof AuthenticationException) {                          //认证异常
            return new Result(ResultCode.AuthenticationException);
        } else if (e instanceof IllegalArgumentException) {                         //参数不合法
            Result result = new Result(ResultCode.IllegalArgumentException);
            result.setMessage(e.getMessage());
            return result;
        } else if (e instanceof UnauthenticatedException) {                         //用户没有登录
            return new Result(ResultCode.UnauthenticatedException);
        } else {
            return Result.error();                                                  //其它异常
        }
    }
}
