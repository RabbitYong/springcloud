package com.rbty.springcloud.controller;

import com.rbty.springcloud.entities.CommonResult;
import com.rbty.springcloud.entities.Payment;
import com.rbty.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService service;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payments")
    public CommonResult create(@RequestBody Payment payment){
        int result = service.create(payment);
        log.info("create result:" + result);

        if (result > 0){
            return new CommonResult(200,"插入成功"+serverPort,result);
        }else{
            return new CommonResult(444,"插入失败",null);
        }
    }

    @GetMapping("/payments/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        Payment payment = service.getPaymentById(id);
        log.info("get result:" + payment);

        return new CommonResult<Payment>(200,"查询成功"+serverPort,payment);
    }
}
