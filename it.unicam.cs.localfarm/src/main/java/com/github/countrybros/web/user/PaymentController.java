package com.github.countrybros.web.user;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.user.IPaymentService;
import com.github.countrybros.infrastructure.shopping.MockPayment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private Orchestrator orchestrator;

    /**
     * Payment is fake!!!
     * @return if the order was paid
     */
    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestParam int orderId) {

        try {
            orchestrator.paymentToMarketplace(orderId);
            return new ResponseEntity<>("Payment completed successfully", HttpStatus.OK);
        } catch (ImpossibleRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/pay_sellers")
    public ResponseEntity<String> paySellers() {
        paymentService.paySellers();
        return new ResponseEntity<>("Sellers paid successfully", HttpStatus.OK);
    }


}
