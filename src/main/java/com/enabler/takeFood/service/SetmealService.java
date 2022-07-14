package com.enabler.takeFood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.enabler.takeFood.dto.SetmealDto;
import com.enabler.takeFood.entity.Setmeal;

import java.util.List;

/**
 * @author Enabler
 */
public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增菜单，保存其关联关系
     * @param setmealDto
     */
    public void saveAll(SetmealDto setmealDto);

    /**
     * 同时删除套餐合关联的菜品
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
