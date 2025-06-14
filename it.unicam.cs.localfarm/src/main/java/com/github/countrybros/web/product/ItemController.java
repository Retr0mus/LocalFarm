package com.github.countrybros.web.product;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.product.IItemDetailsService;
import com.github.countrybros.application.product.IItemService;
import com.github.countrybros.application.product.ItemService;
import com.github.countrybros.application.user.CompanyService;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.product.ItemDetails;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.web.product.requests.AddItemRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "item")
public class ItemController {

    private final IItemService itemService;
    private final ICompanyService companyService;
    private final IItemDetailsService itemDetailsService;

    @Autowired
    public ItemController(ItemService itemService, CompanyService companyService,
                          IItemDetailsService itemDetailsService) {

        this.itemService = itemService;
        this.companyService = companyService;
        this.itemDetailsService = itemDetailsService;
    }

    @PostMapping(value = "add")
    public ResponseEntity<Object> addItem(@RequestBody AddItemRequest request) {

        itemService.addItem(request);
        return new ResponseEntity<>("Item successfully added", HttpStatus.OK);
    }

    @PutMapping(value = "addQuantity")
    public ResponseEntity<Object> addItemQuantity(@PathParam("itemId") int itemId,
                                                  @PathParam("quantity") int quantity) {
        try {
            itemService.addQuantityToItem(itemId, quantity);
        }catch (ImpossibleRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Quantity successfully added", HttpStatus.OK);
    }

    @PutMapping(value = "removeQuantity")
    public ResponseEntity<Object> removeItemQuantity(@PathParam("itemId") int itemId,
                                                     @PathParam("quantity") int quantity) {
        try {
            itemService.removeQuantityToItem(itemId, quantity);
        }catch (ImpossibleRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Quantity successfully removed", HttpStatus.OK);
    }

    @PutMapping(value = "setPrice")
    public ResponseEntity<Object> setItemPrice(@PathParam("itemId") int itemId,
                                               @PathParam("price") Float price) {
        itemService.setPrice(itemId, price);
        return new ResponseEntity<>("Price successfully set", HttpStatus.OK);
    }

    @GetMapping(value = "getBySeller")
    public ResponseEntity<Object> getItem(@PathParam("sellerId") int sellerId) {

        return new ResponseEntity<>(itemService.getItemsBySeller(sellerId), HttpStatus.OK);
    }
}
