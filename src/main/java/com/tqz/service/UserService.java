package com.tqz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tqz.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tian
 * @since 2019-12-28
 */
public interface UserService extends IService<User> {

    IPage<User> selectUserPage(Page<User> userPage);
}
