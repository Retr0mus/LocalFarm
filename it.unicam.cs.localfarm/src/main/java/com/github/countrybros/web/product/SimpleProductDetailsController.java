package com.github.countrybros.web.product;

import com.github.countrybros.application.product.SimpleProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleProductDetailsController {
    private final SimpleProductDetailsService simpleProductDetailsService;

    @Autowired
    public SimpleProductDetailsController(SimpleProductDetailsService simpleProductDetailsService) {
        this.simpleProductDetailsService = simpleProductDetailsService;
    }
}
