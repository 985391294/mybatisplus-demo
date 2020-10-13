package com.tqz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tqz.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tian
 * @since 2019-12-28
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectList();
}
