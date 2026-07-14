package com.sunil.demo.Dependencyinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    //field injection
    // @Autowired // for connecting two classes of IOC
    PaymentService paymentService;

    //Constructor injection // for connecting two classes of IOC

    OrderService(PaymentService paymentService){
        this.paymentService = paymentService;
    }


    //Setter injection // for connecting two classes of IO
    //@Autowired
    public void setPaymentService(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    public void placeOrder(){
        paymentService.payment();
        System.out.println("Order placed");
    }
}
