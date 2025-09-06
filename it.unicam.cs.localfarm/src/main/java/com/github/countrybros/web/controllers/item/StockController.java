package com.github.countrybros.web.controllers.item;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.services.stock.IStockService;
import com.github.countrybros.application.mappers.StockMapper;
import com.github.countrybros.application.services.company.CompanyService;
import com.github.countrybros.application.models.requests.item.AddStockRequest;
import com.github.countrybros.model.stock.Stock;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/stock")
public class StockController {

    private final Orchestrator orchestrator;
    private final IStockService stockService;

    @Autowired
    public StockController(Orchestrator orchestrator, IStockService stockService) {
        this.orchestrator = orchestrator;
        this.stockService = stockService;
    }

    @GetMapping("get")
    public ResponseEntity<Object> getStock(@PathParam( "id" ) Integer id) {
        return new ResponseEntity<>(StockMapper.toDto(stockService.getStock(id)), HttpStatus.OK);
    }

    @PostMapping( "add")
    public ResponseEntity<Object> add(@Valid @RequestBody AddStockRequest request) {
        orchestrator.createStock(request);
        return new ResponseEntity<>("Stock successfully created", HttpStatus.OK);
    }

    @PutMapping("modifyPrice")
    public ResponseEntity<Object> modifyPrice(@PathParam("stockId") int stockId, @PathParam("sellerId") int sellerId, @PathParam("newPrice") float newPrice ) {
        stockService.setPrice(stockId, sellerId, newPrice);
        return new ResponseEntity<>("Price of the stock modified accordingly", HttpStatus.OK);
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

    /*
    @PutMapping( "setPrice")
    public ResponseEntity<Object> setItemPrice(@PathParam("itemId") int itemId,
                                               @PathParam("price") Float price) {
        stockService.setPrice(itemId, price);
        return new ResponseEntity<>("Price successfully set", HttpStatus.OK);
    }*/

}
