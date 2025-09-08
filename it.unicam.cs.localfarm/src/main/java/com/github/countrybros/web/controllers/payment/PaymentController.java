package com.github.countrybros.web.controllers.payment;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.models.dtos.company.CompanyDto;
import com.github.countrybros.application.services.payment.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final IPaymentService paymentService;
    private final Orchestrator orchestrator;

    @Autowired
    public PaymentController(IPaymentService paymentService, Orchestrator orchestrator) {

        this.paymentService = paymentService;
        this.orchestrator = orchestrator;
    }

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
        List<CompanyDto> failedPayments = orchestrator.payMonthlyOrders();

        if (failedPayments == null)
            return new ResponseEntity<>("Payment failed for all companies", HttpStatus.EXPECTATION_FAILED);

        if (failedPayments.isEmpty())
            return new ResponseEntity<>("Sellers paid successfully", HttpStatus.OK);

        String failedPaymentList = failedPayments.stream().map(c -> c.email).collect(Collectors.joining("\n"));
        return new ResponseEntity<>("Payment failed for: " + failedPaymentList, HttpStatus.OK);
    }
}
