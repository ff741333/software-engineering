package com.softwork.freshmarket.service.impl;

import com.softwork.freshmarket.entity.User;
import com.softwork.freshmarket.mapper.UserMapper;
import com.softwork.freshmarket.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
