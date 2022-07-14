package com.enabler.takeFood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.enabler.takeFood.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Enabler
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
