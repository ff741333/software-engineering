package com.softwork.freshmarket.service.impl;

import com.softwork.freshmarket.entity.Customer;
import com.softwork.freshmarket.mapper.CustomerMapper;
import com.softwork.freshmarket.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
