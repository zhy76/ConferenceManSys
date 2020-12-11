package com.conference.util.result;

/**
 * @ClassName: ResultCode
 * @Description: That's enough.
 * @Author: Lance
 * @Date: 2020/12/6 18:51
 */
public enum ResultCode {
    SUCCESS(200, "成功"),
    ERROR(1, "错误"),
    FAIL(2, "失败"),
    DuplicateKeyException(101, "插入的唯一性字段数据已存在"),
    BindException(111, "参数校验失败"),
    NeedLogin(121, "该操作需要先登录"),
//    IllegalToken(122, "非法token"),
    IncorrectCredentialsException(131, "密码不正确"),
    UnknownAccountException(132, "此账号不存在"),
    AuthenticationException(134, "认证异常"),
    UnauthenticatedException(141, "用户没有登录"),
    IllegalArgumentException(151, "参数不合法");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 提示信息
     */
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
