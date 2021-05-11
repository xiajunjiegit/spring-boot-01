package com.atguigu.controller;


import com.atguigu.model.auto.Payment;
import com.atguigu.service.IPaymentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-30
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private IPaymentService paymentService;

    @PostMapping("/insertPayment")
    public void insertPayment() {
        paymentService.insertPayment();
    }

    @GetMapping("/getPayment")
    public void getPayment(){
        paymentService.getPayment();
    }

    @GetMapping("/getPaymentMap")
    public void getPaymentMap(){
        paymentService.getPaymentMap();

    }
    @GetMapping("/getSelect")
    public void getSelect(){
        paymentService.getSelect();
    }

    @GetMapping("/selectAllSuper2")
    public void selectAllSuper2(){
        paymentService.selectAllSuper2();
    }

}
