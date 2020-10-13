package com.tqz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tqz.dao.UserMapper;
import com.tqz.entity.User;
import com.tqz.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tian
 * @since 2019-12-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     */
    @Override
    public boolean saveBatch(Collection<User> entityList) {
        return false;
    }

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<User> entityList) {
        return false;
    }

    /**
     * 根据 UpdateWrapper 条件，更新记录 需要设置sqlset
     *
     * @param updateWrapper 实体对象封装操作类 {@link UpdateWrapper}
     */
    @Override
    public boolean update(Wrapper<User> updateWrapper) {
        // 要修改的值
        User user = new User();
        user.setAge(88);
        user.setName("张三");
        int update = userMapper.update(user, update());
        return false;
    }

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     */
    @Override
    public boolean updateBatchById(Collection<User> entityList) {
        return false;
    }

    /**
     * 根据 Wrapper，查询一条记录 <br/>
     * <p>结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")</p>
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public User getOne(Wrapper<User> queryWrapper) {
        return null;
    }

    /**
     * 查询总记录数
     *
     * @see Wrappers#emptyWrapper()
     */
    @Override
    public int count() {
        return 0;
    }

    /**
     * 查询所有
     *
     * @see Wrappers#emptyWrapper()
     */
    @Override
    public List<User> list() {
        return userMapper.selectList(null);
    }

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     * @see Wrappers#emptyWrapper()
     */
    @Override
    public <E extends IPage<User>> E page(E page) {
        return null;
    }

    /**
     * 查询所有列表
     *
     * @see Wrappers#emptyWrapper()
     */
    @Override
    public List<Map<String, Object>> listMaps() {
        return null;
    }

    /**
     * 查询全部记录
     */
    @Override
    public List<Object> listObjs() {
        return null;
    }

    /**
     * 查询全部记录
     *
     * @param mapper 转换函数
     */
    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return null;
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public List<Object> listObjs(Wrapper<User> queryWrapper) {
        return null;
    }

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     * @see Wrappers#emptyWrapper()
     */
    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return null;
    }

    /**
     * 链式查询 普通
     *
     * @return QueryWrapper 的包装类
     */
    @Override
    public QueryChainWrapper<User> query() {
        return null;
    }

    /**
     * 链式查询 lambda 式
     * <p>注意：不支持 Kotlin </p>
     *
     * @return LambdaQueryWrapper 的包装类
     */
    @Override
    public LambdaQueryChainWrapper<User> lambdaQuery() {
        return null;
    }

    /**
     * 链式更改 普通
     *
     * @return UpdateWrapper 的包装类
     */
    @Override
    public UpdateChainWrapper<User> update() {
        return null;
    }

    /**
     * 链式更改 lambda 式
     * <p>注意：不支持 Kotlin </p>
     *
     * @return LambdaUpdateWrapper 的包装类
     */
    @Override
    public LambdaUpdateChainWrapper<User> lambdaUpdate() {
        return null;
    }

    /**
     * <p>
     * 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
     * 此次修改主要是减少了此项业务代码的代码量（存在性验证之后的saveOrUpdate操作）
     * </p>
     *
     * @param entity        实体对象
     * @param updateWrapper
     */
    @Override
    public boolean saveOrUpdate(User entity, Wrapper<User> updateWrapper) {
        return false;
    }

    @Override
    public IPage<User> selectUserPage(Page<User> userPage) {
        return userMapper.selectPage(userPage, null);
    }
}
