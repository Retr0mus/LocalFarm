package com.github.countrybros.web.product;

import com.github.countrybros.application.product.BundleDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BundleDetailsController {
    private final BundleDetailsService bundleDetailsService;

    @Autowired
    public BundleDetailsController(BundleDetailsService bundleDetailsService) {
        this.bundleDetailsService = bundleDetailsService;
    }
}
