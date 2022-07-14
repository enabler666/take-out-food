package com.enabler.takeFood.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enabler.takeFood.common.CustomException;
import com.enabler.takeFood.entity.Category;
import com.enabler.takeFood.entity.Dish;
import com.enabler.takeFood.entity.Setmeal;
import com.enabler.takeFood.mapper.CategoryMapper;
import com.enabler.takeFood.service.CategoryService;
import com.enabler.takeFood.service.DishService;
import com.enabler.takeFood.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    /**
     * 通过id删除对应的分类，但是比自带的实现多了判断有无关联的东西
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        dishWrapper.eq(Dish::getCategoryId, id);
        int countDish = dishService.count(dishWrapper);

        if(countDish > 0){
            throw new CustomException("当前分类项目有关联的菜品");
        }

        LambdaQueryWrapper<Setmeal> setmealWrapper = new LambdaQueryWrapper<>();
        setmealWrapper.eq(Setmeal::getCategoryId, id);
        int countSetmeal = setmealService.count(setmealWrapper);
        if(countSetmeal > 0){
            throw new CustomException("当前分类项目有关联的套餐");
        }

        super.removeById(id);
    }
}
