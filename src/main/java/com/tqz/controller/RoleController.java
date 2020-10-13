package com.tqz.controller;


import com.tqz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tian
 * @since 2019-12-28
 */
@Api(value = "角色接口。", tags = "角色接口")
@ApiSort(value = 1)
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "测试第一个接口", notes = "测试第一个接口。")
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        return "测试第一个接口";
    }

    @ApiOperation(value = "测试第二个接口", notes = "测试第二个接口。")
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
        return "测试第二个接口";
    }

    @ApiOperation(value = "测试第三个接口", notes = "测试第三个接口。")
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String test3() {
        return "测试第三个接口";
    }

    @ApiOperation(value = "测试第四个接口", notes = "测试第四个接口。")
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public String test4() {
        return "测试第四个接口";
    }
}
