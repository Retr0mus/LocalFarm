package com.github.countrybros.web.controllers.item;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.mappers.ItemMapper;
import com.github.countrybros.application.models.requests.item.AddItemRequest;
import com.github.countrybros.application.services.item.IItemService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/item")
public class ItemController {

    private final Orchestrator orchestrator;
    private final IItemService itemService;

    @Autowired
    public ItemController(Orchestrator orchestrator, IItemService itemService) {
        this.orchestrator = orchestrator;
        this.itemService = itemService;
    }

    @PostMapping( "addRequest")
    public ResponseEntity<Object> addItemRequest(@Valid @RequestBody AddItemRequest request) {
        orchestrator.addItemRequest(request);
        return new ResponseEntity<>("Item creation request successfully generated", HttpStatus.OK);
    }

    @GetMapping( "get")
    public ResponseEntity<Object> getItemDetails(@PathParam("itemId") int itemId) {
        return new ResponseEntity<>(ItemMapper.toDto(itemService.getItem(itemId)), HttpStatus.OK);
    }

    @GetMapping("getAvailable")
    public ResponseEntity<Object> getAvailableItems() {
        return new ResponseEntity<>(itemService.getAvailableItems().stream().map(ItemMapper::toDto), HttpStatus.OK);
    }

}
