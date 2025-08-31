package com.github.countrybros.web.product;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.product.ItemMapper;
import com.github.countrybros.web.product.requests.AddItemRequest;
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

    @Autowired
    public ItemController(Orchestrator orchestrator) {

        this.orchestrator = orchestrator;
    }

    @PostMapping( "addRequest")
    public ResponseEntity<Object> addItemRequest(@Valid @RequestBody AddItemRequest request) {

        orchestrator.addItemRequest(request);
        return new ResponseEntity<>("ItemDetails creation request successfully created", HttpStatus.OK);
    }

//    @DeleteMapping( "delete")
//    public ResponseEntity<Object> deleteItemDetails(@PathParam("itemDetailsId") int itemDetailsId) {
//
//        itemDetailsService.deleteItemDetails(itemDetailsId);
//        return new ResponseEntity<>("ItemDetails successfully deleted", HttpStatus.OK);
//    }

    @GetMapping( "get")
    public ResponseEntity<Object> getItemDetails(@PathParam("itemId") int itemId) {
        return new ResponseEntity<>(ItemMapper.toDto(orchestrator.getItemDetails(itemId)), HttpStatus.OK);
    }
//    @GetMapping( "get")
//    public ResponseEntity<Object> getItemDetails(@PathParam("itemDetailsId") int itemDetailsId) {
//
//        return new ResponseEntity<>(itemDetailsService.getItemDetails(itemDetailsId), HttpStatus.OK);
//    }

//    @PutMapping( "acceptChanges")
//    public ResponseEntity<Object> acceptChanges(@PathParam("submissionId") int submissionId) {
//        itemDetailsService.acceptChanges(submissionId);
//        return new ResponseEntity<>("Item successfully updated", HttpStatus.OK);
//    }

//    @PutMapping( "changeStatus")
//    public ResponseEntity<Object> changeStatus(@PathParam("status") ItemStatus status,
//                                               @PathParam("itemDetailsId") int itemDetailsId) {
//        itemDetailsService.setStatus(status, itemDetailsId);
//
//        return new ResponseEntity<>("Item status successfully updated", HttpStatus.OK);
//    }
}
