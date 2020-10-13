package com.tqz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tqz.entity.User;
import com.tqz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tian
 * @since 2019-12-28
 */
@Api(value = "用户接口。", tags = "用户接口")
@RestController
@ApiSort(value = 2)
@RequestMapping("/user")
@Slf4j
public class UserController {

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

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String error(){
        try {
            log.info("异常接口执行了，可以使用索引了。。。");
            int i = 1 / 0;
        }catch (Exception e){
            e.printStackTrace();
            return "服务异常！！！";
        }
        return "成功了！！！";
    }

    @ApiOperation(value = "查询所有", notes = "查询所有1")
    @RequestMapping(value = "selectList", method = RequestMethod.GET)
    public IPage<User> selectList(Integer pageNumber, Integer pageSize, HttpServletRequest request) {
        log.info("查询所有的接口执行了，es要使用索引了。。。");
//        List<User> list = userService.list();
        User user = new User();
        user.setAge(12).setEmail("123456@qq.com").setName("zhangsan");
//        userService.save(user);
        System.out.println("URI:" + request.getRequestURI());
        System.out.println("URL:" + request.getRequestURL());

        /*
        // 无条件翻页查询
        IPage<T> page(IPage<T> page);
        // 翻页查询
        IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);
        // 无条件翻页查询
        IPage<Map<String, Object>> pageMaps(IPage<T> page);
        // 翻页查询
        IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper);
        */
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        Page<User> page = new Page<User>(pageNumber, pageSize)/*.setSearchCount(true)*/;
        IPage<User> iPage = userService.selectUserPage(page);
        return iPage;
    }

    public static void main(String[] args) {
        int i = 5;

        if(i > 5) {
            System.out.println("大一1");
        } else if (i > 2) {
            System.out.println("大于2");
        } else if (i > 3) {
            System.out.println("大于3");
        }
    }
}
