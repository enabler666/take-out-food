package com.enabler.takeFood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.enabler.takeFood.entity.Category;

/**
 * @author Enabler
 */
public interface CategoryService extends IService<Category> {
    /**
     * 通过id删除对应的分类，但是比自带的实现多了判断有无关联的东西
     */
    public void remove(Long id);
}
