package com.github.countrybros.web.user;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.user.IPaymentService;
import com.github.countrybros.model.user.FakePayment;
import com.github.countrybros.web.user.request.RefundRequest;
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
    @Autowired
    private Orchestrator orchestrator;

    /**
     * Payment is fake!!!
     * @param amount
     * @return
     */
    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestParam float amount) {

        boolean success = paymentService.paymentToMarketplace(new FakePayment(), amount);
        if (success) {
            return new ResponseEntity<>("Payment completed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pay_sellers")
    public ResponseEntity<String> paySellers() {
        paymentService.paySellers();
        return new ResponseEntity<>("Sellers paid successfully", HttpStatus.OK);
    }


}

