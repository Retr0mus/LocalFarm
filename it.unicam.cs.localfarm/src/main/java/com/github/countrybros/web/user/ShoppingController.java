package com.github.countrybros.web.user;

import com.github.countrybros.application.user.IShoppingService;
import com.github.countrybros.model.user.Cart;
import com.github.countrybros.model.user.Order;
import com.github.countrybros.web.user.request.AddItemToCartRequest;
import com.github.countrybros.web.user.request.CheckoutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private IShoppingService shoppingService;

    @GetMapping("/cart")
    public ResponseEntity<Cart> getCart(@RequestParam int userId) {
        Cart cart = shoppingService.getCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<String> addItemToCart(@RequestBody AddItemToCartRequest request) {
        shoppingService.addItemToCart(request.userId, request.itemId, request.quantity);
        return new ResponseEntity<>("Item added to cart", HttpStatus.OK);
    }

    @PutMapping("/cart/edit")
    public ResponseEntity<String> editQuantityOfItemInCart(@RequestParam int userId, @RequestParam int itemId, @RequestParam int qty) {
        shoppingService.editQuantityOfItemInCart(userId, itemId, qty);
        return new ResponseEntity<>("Item quantity updated", HttpStatus.OK);
    }

    @DeleteMapping("/cart/remove")
    public ResponseEntity<String> removeItemFromCart(@RequestParam int userId, @RequestParam int itemId, @RequestParam int qty) {
        shoppingService.removeItemFromCart(userId, itemId, qty);
        return new ResponseEntity<>("Item removed from cart", HttpStatus.OK);
    }

    @PostMapping("/cart/excess")
    public ResponseEntity<Cart> getExcessItems(@RequestBody Cart cart) {
        Cart excessCart = shoppingService.getExcessItems(cart);
        return new ResponseEntity<>(excessCart, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@RequestParam int userId, @RequestBody CheckoutRequest checkoutRequest) {
        Order order = shoppingService.checkout(userId, checkoutRequest.paymentMethod, checkoutRequest.shippingAddress);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
