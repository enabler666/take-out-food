package com.enabler.takeFood.dto;

import com.enabler.takeFood.entity.OrderDetail;
import com.enabler.takeFood.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @author Enabler
 */
@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
