package com.github.countrybros.web.product;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.product.StockMapper;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/stock")
public class StockController {

    private final Orchestrator orchestrator;

    @Autowired
    public StockController(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @GetMapping("getByItem")
    public ResponseEntity<Object> getByItem(@PathParam("itemId") int itemId) {
        return new ResponseEntity<>(orchestrator.getStocksByItem(itemId).stream().map(StockMapper::toDto), HttpStatus.OK);
    }

    @GetMapping( "getBySeller")
    public ResponseEntity<Object> getItem(@PathParam("sellerId") int sellerId) {
        return new ResponseEntity<>(orchestrator.getStocksBySeller(sellerId).stream().map(StockMapper::toDto), HttpStatus.OK);
    }

    @PutMapping( "removeQuantity")
    public ResponseEntity<Object> removeItemQuantity(@PathParam("stockId") int stockId,
                                                     @PathParam("quantity") int quantity,
                                                     @PathParam("sellerId") int sellerId) {
        try {
            orchestrator.removeQuantityToStock(stockId, quantity, sellerId);
        }catch (ImpossibleRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Quantity successfully removed", HttpStatus.OK);
    }

    /*@PostMapping( "add")
    public ResponseEntity<Object> addItem(@RequestBody AddStockRequest request) {
        itemService.addItem(request);
        return new ResponseEntity<>("Item successfully added", HttpStatus.OK);
    }

    @PutMapping( "setPrice")
    public ResponseEntity<Object> setItemPrice(@PathParam("itemId") int itemId,
                                               @PathParam("price") Float price) {
        itemService.setPrice(itemId, price);
        return new ResponseEntity<>("Price successfully set", HttpStatus.OK);
    }

    */
}
