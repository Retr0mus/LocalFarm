package com.github.countrybros.web;

import com.github.countrybros.application.user.IPaymentMethod;
import com.github.countrybros.application.user.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/buy")
    public boolean buy(int userId, IPaymentMethod paymentMethod, float amout) {
        return paymentService.buy(userId,paymentMethod,amout);
    }

    @PostMapping("/pay_sellers")
    public void paySeller(@RequestParam("since") Date dataSince) {
        paymentService.paySellers(dataSince);
    }
}
