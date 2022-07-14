package com.enabler.takeFood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enabler.takeFood.entity.ShoppingCart;
import com.enabler.takeFood.mapper.ShoppingCartMapper;
import com.enabler.takeFood.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @author Enabler
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
