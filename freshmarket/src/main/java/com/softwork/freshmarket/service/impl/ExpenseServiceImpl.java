package com.softwork.freshmarket.service.impl;

import com.softwork.freshmarket.entity.Expense;
import com.softwork.freshmarket.mapper.ExpenseMapper;
import com.softwork.freshmarket.service.IExpenseService;
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
public class ExpenseServiceImpl extends ServiceImpl<ExpenseMapper, Expense> implements IExpenseService {

}
