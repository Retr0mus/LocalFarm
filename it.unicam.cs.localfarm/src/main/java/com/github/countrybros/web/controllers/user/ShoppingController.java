package com.github.countrybros.web.controllers.user;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.mappers.ShoppingItemMapper;
import com.github.countrybros.application.services.user.IShoppingService;
import com.github.countrybros.model.order.Order;
import com.github.countrybros.application.models.requests.user.AddItemToCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private Orchestrator orchestrator;
    @Autowired
    private IShoppingService shoppingService;

    @GetMapping("/cart/get")
    public ResponseEntity<Object> getCart(@RequestParam int userId) {
        return new ResponseEntity<>(shoppingService.getCart(userId).getShoppingItems().stream().map(ShoppingItemMapper::toDto), HttpStatus.OK);
    }


    @PostMapping("/cart/add")
    public ResponseEntity<String> addItemToCart(@RequestBody AddItemToCartRequest request) {
        orchestrator.addItemToCart(request);
        return new ResponseEntity<>("Item added to cart", HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Object> checkout(@RequestParam int userId) {
        Order order = orchestrator.checkout(userId);
        return new ResponseEntity<>("Created order " + order.getOrderId() + " with price " + order.getTotal() + ", please proceed to pay it.", HttpStatus.CREATED);
    }

    @PutMapping("/cart/edit")
    public ResponseEntity<String> editQuantityOfItemInCart(@RequestParam int userId, @RequestParam int shoppingItemId, @RequestParam int qty) {
        try {
            orchestrator.editQuantityOfItemInCart(userId, shoppingItemId, qty);
            return new ResponseEntity<>("Item quantity updated", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundInRepositoryException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cart/remove")
    public ResponseEntity<String> removeItemFromCart(@RequestParam int userId, @RequestParam int shoppingItemId) {
        try {
            orchestrator.removeItemFromCart(userId, shoppingItemId);
            return new ResponseEntity<>("Item removed from cart", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundInRepositoryException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }


    }
}
