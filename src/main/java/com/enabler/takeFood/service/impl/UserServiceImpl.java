package com.enabler.takeFood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enabler.takeFood.entity.User;
import com.enabler.takeFood.mapper.UserMapper;
import com.enabler.takeFood.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Enabler
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
