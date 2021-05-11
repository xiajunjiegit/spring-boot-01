package com.atguigu.service;

import com.atguigu.model.auto.Payment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-30
 */
public interface IPaymentService extends IService<Payment> {

    public void insertPayment();
    public void getPayment();
    public void getPaymentMap();
    public void getSelect();
    public void selectAllSuper2();

}
