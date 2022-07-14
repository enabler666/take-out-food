package com.enabler.takeFood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enabler.takeFood.entity.Employee;
import com.enabler.takeFood.mapper.EmployeeMapper;
import com.enabler.takeFood.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author Enabler
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{
}
