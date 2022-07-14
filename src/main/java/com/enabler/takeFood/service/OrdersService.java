package com.enabler.takeFood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.enabler.takeFood.entity.Orders;

/**
 * @author Enabler
 */
public interface OrdersService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     * @return
     */
    public void submit(Orders orders);
}
