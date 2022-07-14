package com.enabler.takeFood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.enabler.takeFood.dto.DishDto;
import com.enabler.takeFood.entity.Dish;

/**
 * @author Enabler
 */
public interface DishService extends IService<Dish> {

    public void saveAll(DishDto dishDto);

    public DishDto getAll(Long id);

    public void updateAll(DishDto dishDto);
}
