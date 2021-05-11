package com.atguigu.service.impl;

import com.atguigu.model.auto.Payment;
import com.atguigu.mapper.auto.PaymentMapper;
import com.atguigu.service.IPaymentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-30
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {

    @Override
    public void insertPayment() {
        Payment p = new Payment();
        p.setSerial("编号");
        baseMapper.insert(p);
    }

    @Override
    public void getPayment() {
        QueryWrapper<Payment> p = new QueryWrapper<>();
        List<Integer> ts = Arrays.asList(1, 2, 3);
        p.in("id",ts);
        List<Payment> list = baseMapper.selectList(p);
        list.forEach(System.out::println);
    }

    @Override
    public void getPaymentMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        List<Payment> list = baseMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    @Override
    public void getSelect() {
        LambdaQueryWrapper<Payment> q = new LambdaQueryWrapper();
        //q.likeLeft("serial",2).and(wq -> wq.lt("id",2)).or();
        q.eq(Payment::getSerial,"编号");

        List<Payment> list = baseMapper.selectList(q);

        list.forEach(System.out::println);
    }

    @Override
    public void selectAllSuper2(){
        QueryWrapper<Payment> q = new QueryWrapper();
        q.select(Payment.class,info -> !info.getColumn().equals("id"));
        List<Payment> list = baseMapper.selectList(q);
        list.forEach(System.out::println);
    }
}
