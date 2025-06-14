package com.github.countrybros.web.user;

import com.github.countrybros.application.user.IPaymentMethod;
import com.github.countrybros.application.user.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/buy")
    public ResponseEntity<String> buy(
            @RequestParam int userId,
            @RequestBody IPaymentMethod paymentMethod,
            @RequestParam float amount) {

        boolean success = paymentService.buy(userId, paymentMethod, amount);
        if (success) {
            return new ResponseEntity<>("Payment completed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pay_sellers")
    public ResponseEntity<String> paySeller(@RequestParam("since") Date dateSince) {
        paymentService.paySellers(dateSince);
        return new ResponseEntity<>("Sellers paid successfully", HttpStatus.OK);
    }
}
