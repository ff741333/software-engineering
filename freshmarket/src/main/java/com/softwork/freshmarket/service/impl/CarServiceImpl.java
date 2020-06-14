package com.softwork.freshmarket.service.impl;

import com.softwork.freshmarket.entity.Car;
import com.softwork.freshmarket.mapper.CarMapper;
import com.softwork.freshmarket.service.ICarService;
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
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

}
