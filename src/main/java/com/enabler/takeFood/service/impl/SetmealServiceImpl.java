package com.enabler.takeFood.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enabler.takeFood.common.CustomException;
import com.enabler.takeFood.dto.SetmealDto;
import com.enabler.takeFood.entity.Setmeal;
import com.enabler.takeFood.entity.SetmealDish;
import com.enabler.takeFood.mapper.SetmealMapper;
import com.enabler.takeFood.service.SetmealDishService;
import com.enabler.takeFood.service.SetmealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Enabler
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    private SetmealDishService setmealDishService;

    @Transactional(rollbackFor = {})
    @Override
    public void saveAll(SetmealDto setmealDto) {
        this.save(setmealDto);

        Long id = setmealDto.getId();
        List<SetmealDish> dishes = setmealDto.getSetmealDishes().stream().map((item) -> {
            item.setSetmealId(id);
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(dishes);
    }

    /**
     * 同时删除套餐合关联的菜品
     * @param ids
     */
    @Transactional(rollbackFor = {})
    @Override
    public void removeWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Setmeal::getId, ids);
        wrapper.eq(Setmeal::getStatus, 1);
        int count = this.count(wrapper);
        if(count > 0){
            throw new CustomException("套餐正在售卖中");
        }
        this.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(wrapper1);
    }
}
