package com.tqz.controller;


import com.tqz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import lombok.extern.slf4j.Slf4j;
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
@Api(value = "菜单接口。", tags = "菜单接口")
@RestController
@RequestMapping("/menu")
@ApiSort(value = 3)
//@ApiIgnore //swagger文档忽略该类，用在类上面对类生效，用在方法上面对方法生效
@Slf4j
public class MenuController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "测试第一个接口", position = 1, notes = "测试第一个接口。")
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        log.info("查询索引，测试成功了");
        return "测试第一个接口";
    }

    @ApiOperation(value = "测试第二个接口", position = 4, notes = "测试第二个接口。")
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
        return "测试第二个接口";
    }

    @ApiOperation(value = "测试第三个接口", position = 2, notes = "测试第三个接口。")
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String test3() {
        return "测试第三个接口";
    }

    @ApiOperation(value = "测试第四个接口", position = 3, notes = "测试第四个接口。")
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public String test4() {
        return "测试第四个接口";
    }

}
