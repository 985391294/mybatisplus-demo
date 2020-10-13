package com.tqz.util;

public enum CommonResultEmnu {
    OK(10000, "OK"), // 请求成功
    ERROR(50000, "ERROR"), // 操作失败
    INVALID_PARAMS(20000, "INVALID_PARAMS"), // 非法参数
    AUTHENTICATION_ERR(20001, "AUTHENTICATION_ERR"), // 身份校验错误
    TOKEN_ERR(20002, "TOKEN_ERR"), // token error
    CRYPTO_ERR(20003, "CRYPTO_ERR"), // 加密/解密错误
    DATA_EMPTY(20004, "EMPTY_ERR"), // 空数据
    SIGN_ERR(20005, "SIGN_ERR"), // 签名错误
    SERVER_ERR(10001, "SERVER_ERR");// 服务异常

    private Integer code;

    private String info;

    private CommonResultEmnu(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public static String getInfo(Integer code) {
        for (CommonResultEmnu item : CommonResultEmnu.values()) {
            if (code.equals(item.getCode())) {
                return item.getInfo();
            }
        }
        return "";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
