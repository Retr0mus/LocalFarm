package com.github.countrybros.web.user;

import com.github.countrybros.application.user.IShoppingService;
import com.github.countrybros.model.user.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private IShoppingService shoppingService;

    @GetMapping("/cart")
    public Cart getCart(@RequestParam int userId) {
        return shoppingService.getCart(userId);
    }

    @PostMapping("/cart/add")
    public void addItemToCart(@RequestParam int userId,@RequestParam  int itemIdm,@RequestParam  int qty) {
         shoppingService.addItemToCart(userId, itemIdm, qty);
    }

    @PutMapping("/cart/edit")
    public void editQuantityOfItemInCart(@RequestParam int userId,@RequestParam int itemIdm,@RequestParam int qty) {
         shoppingService.editQuantityOfItemInCart(userId, itemIdm, qty);
    }

    @DeleteMapping("/cart/remove")
    public void removeItemFromCart(@RequestParam int userId,@RequestParam int itemIdm,@RequestParam int qty) {
         shoppingService.removeItemFromCart(userId, itemIdm, qty);
    }

    @PostMapping("/cart/excess")
    public Cart getExcessItems(@RequestBody Cart cart){
        return shoppingService.getExcessItems(cart);
    }
}
