package com.tqz.generator;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tqz.entity.User;
import com.tqz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * author: tian
 * date: 2019-12-28 12:39
 * desc:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {
    
    @Autowired
    private UserService userService;

    /**
     * author: tian
     * date: 2019-12-28 14:13
     * desc: 查询所有
     **/
    @Test
    public void testSelectList(){
        List<User> listUser = userService.list();
        for(User user : listUser){
            System.out.println(user);
        }
    }

    /**
     * author: tian
     * date: 2019-12-28 14:21
     * desc: 返回结果为json格式的键值对
     **/
    @Test
    public void testSelectListMap(){
        List<Map<String, Object>> maps = userService.listMaps();
        for(Map<String,Object> map : maps){
            System.out.println(map);
        }
    }

    /**
     * author: tian
     * date: 2019-12-28 14:22
     * desc: 分页查询
     **/
    @Test
    public void testSelectPage(){
        Page page = new Page(1,2);
        IPage<User> userIPage = userService.page(page);
        System.out.println(userIPage);
    }

    /**
     * author: tian
     * date: 2019-12-28 14:35
     * desc: 修改
     **/
    @Test
    public void update(){
        // 要修改的值
        User user = new User();
        user.setAge(88);
        user.setName("张三");
        // 修改的条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<User>();
        userUpdateWrapper
                .eq("name","Jone")
                .eq("age",19);
        userService.update(userUpdateWrapper);
    }
}
