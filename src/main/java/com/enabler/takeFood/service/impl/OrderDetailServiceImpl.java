package com.enabler.takeFood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enabler.takeFood.entity.OrderDetail;
import com.enabler.takeFood.mapper.OrderDetailMapper;
import com.enabler.takeFood.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author Enabler
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
