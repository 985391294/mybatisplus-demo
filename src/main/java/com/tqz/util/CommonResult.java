package com.tqz.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tian
 * @Date: 2020/9/18 16:17
 * @Desc: 公共返回对象
 */
@Data
public class CommonResult<T> {

    /**
     * 请求成功返回码
     */
    public static final Integer CODE_OK = 10000;

    @ApiModelProperty("接口返回信息")
    private String info = CommonResultEmnu.OK.getInfo();

    @ApiModelProperty("接口返回信息 code ")
    private Integer code = CommonResultEmnu.OK.getCode();

    @ApiModelProperty("接口返回信息描述 ")
    private String description = "";

    @ApiModelProperty("接口返回值 ")
    private T data;

    public CommonResult() {
    }

    public CommonResult(T data) {
        this.data = data;
    }

    public CommonResult(CommonResultEmnu resultEmnu) {
        this.code = resultEmnu.getCode();
        this.info = resultEmnu.getInfo();
    }

    public CommonResult(CommonResultEmnu resultEmnu, String description) {
        this.code = resultEmnu.getCode();
        this.info = resultEmnu.getInfo();
        this.description = description;
    }
}
