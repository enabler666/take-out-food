package com.enabler.takeFood.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.enabler.takeFood.common.R;
import com.enabler.takeFood.entity.Employee;
import com.enabler.takeFood.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author Enabler
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request){
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, employee.getUsername());
        Employee employeeByName = employeeService.getOne(wrapper);
        if(employeeByName == null ){
            return R.error("没有该用户");
        }

        if(!employeeByName.getPassword().equals(password)){
            return R.error("密码错误");
        }

        if(employeeByName.getStatus() == 0){
            return R.error("用户已被禁用");
        }

        request.getSession().setAttribute("employee", employeeByName.getId());

        return R.success(employeeByName);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping()
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());

        Long creatorId = (Long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(creatorId);
//        employee.setUpdateUser(creatorId);

        employeeService.save(employee);
        return R.success("成功新增员工");
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page<Employee>> page(int page, int pageSize, String name) {

        Page<Employee> pageInfo = new Page<Employee>(page, pageSize);
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<Employee>();
        wrapper.like(StringUtils.isNotEmpty(name), Employee::getName,name);
        wrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo, wrapper);
        return R.success(pageInfo);
    }

    @PutMapping()
    public R<String> update(@RequestBody Employee employee, HttpServletRequest request) {
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        boolean b = employeeService.updateById(employee);
        return R.success("修改成功");
    }

    @GetMapping("/{id}")
    public R<Employee> geyById(@PathVariable Long id) {
        Employee byId = employeeService.getById(id);
        if(byId != null) {
            return R.success(byId);
        }
        return R.error("没有该id");
    }
}
