package com.github.countrybros.web.product;

import com.github.countrybros.application.product.TransformedProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransformedProductDetailsController {
    private final TransformedProductDetailsService transformedProductDetailsService;

    @Autowired
    public TransformedProductDetailsController(TransformedProductDetailsService transformedProductDetailsService) {
        this.transformedProductDetailsService = transformedProductDetailsService;
    }


}
