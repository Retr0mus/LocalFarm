package com.github.countrybros.web.user;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.user.IShoppingService;
import com.github.countrybros.model.user.Cart;
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
    @Autowired
    private Orchestrator orchestrator;

    @GetMapping("/cart")
    public ResponseEntity<Cart> getCart(@RequestParam int userId) {
        Cart cart = shoppingService.getCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

//    @PostMapping("/cart/add")
//    public ResponseEntity<String> addItemToCart(@RequestBody AddItemToCartRequest request) {
//        shoppingService.addItemToCart(request.userId, request.itemId, request.quantity);
//        return new ResponseEntity<>("Item added to cart", HttpStatus.OK);
//    }

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
        }catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundInRepositoryException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

//    @PostMapping("/cart/excess")
//    public ResponseEntity<Cart> getExcessItems(@RequestBody Cart cart) {
//        Cart excessCart = shoppingService.getExcessItems(cart);
//        return new ResponseEntity<>(excessCart, HttpStatus.OK);
//    }

//    @PostMapping("/checkout")
//    public ResponseEntity<Order> checkout(@RequestParam int userId, @RequestBody CheckoutRequest checkoutRequest) {
//        Order order = shoppingService.checkout(userId, checkoutRequest.paymentMethod, checkoutRequest.shippingAddress);
//        return new ResponseEntity<>(order, HttpStatus.CREATED);
//    }
}
