package com.github.countrybros.web.product;

import com.github.countrybros.application.product.ItemDetailsService;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.model.product.ItemDetailsStatus;
import com.github.countrybros.web.product.requests.AddItemDetailsRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for management of ItemDetails
 */
@RestController
@RequestMapping( "/itemDetails")
public class ItemDetailsController {

    private final ItemDetailsService itemDetailsService;
    private final ICompanyService companyService;

    @Autowired
    public ItemDetailsController(ItemDetailsService itemDetailsService, ICompanyService companyService) {

        this.itemDetailsService = itemDetailsService;
        this.companyService = companyService;
    }

    @PostMapping( "addRequest")
    public ResponseEntity<Object> RequestItemDetails(@RequestBody AddItemDetailsRequest request) {

        itemDetailsService.requestToAddItemDetails(request);
        return new ResponseEntity<>("ItemDetails creation request successfully created", HttpStatus.OK);
    }

    @DeleteMapping( "delete")
    public ResponseEntity<Object> deleteItemDetails(@PathParam("itemDetailsId") int itemDetailsId) {

        itemDetailsService.deleteItemDetails(itemDetailsId);
        return new ResponseEntity<>("ItemDetails successfully deleted", HttpStatus.OK);
    }

    @GetMapping( "get")
    public ResponseEntity<Object> getItemDetails(@PathParam("itemDetailsId") int itemDetailsId) {

        return new ResponseEntity<>(itemDetailsService.getItemDetails(itemDetailsId), HttpStatus.OK);
    }

    @PutMapping( "acceptChanges")
    public ResponseEntity<Object> acceptChanges(@PathParam("submissionId") int submissionId) {
        itemDetailsService.acceptChanges(submissionId);
        return new ResponseEntity<>("Item successfully updated", HttpStatus.OK);
    }

    @PutMapping( "changeStatus")
    public ResponseEntity<Object> changeStatus(@PathParam("status")ItemDetailsStatus status,
                                               @PathParam("itemDetailsId") int itemDetailsId) {
        itemDetailsService.setStatus(status, itemDetailsId);

        return new ResponseEntity<>("Item status successfully updated", HttpStatus.OK);
    }
}
