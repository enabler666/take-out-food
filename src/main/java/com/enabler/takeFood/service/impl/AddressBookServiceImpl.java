package com.enabler.takeFood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enabler.takeFood.entity.AddressBook;
import com.enabler.takeFood.mapper.AddressBookMapper;
import com.enabler.takeFood.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @author Enabler
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
