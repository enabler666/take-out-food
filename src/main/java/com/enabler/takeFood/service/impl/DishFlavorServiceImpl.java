package com.enabler.takeFood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enabler.takeFood.entity.DishFlavor;
import com.enabler.takeFood.mapper.DishFlavorMapper;
import com.enabler.takeFood.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @author Enabler
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
