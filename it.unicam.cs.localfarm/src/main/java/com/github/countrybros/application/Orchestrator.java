package com.github.countrybros.application;

import com.github.countrybros.application.acceptancesubmission.IAcceptanceSubmissionService;
import com.github.countrybros.application.product.ICertificationService;
import com.github.countrybros.application.product.IItemService;
import com.github.countrybros.application.product.ItemMapper;
import com.github.countrybros.application.product.ItemBuilderFactory;
import com.github.countrybros.application.user.*;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.user.Order;
import com.github.countrybros.web.acceptancesubmission.request.AddProductAcceptanceSubmissionRequest;
import com.github.countrybros.web.product.requests.AddCertificationRequest;
import com.github.countrybros.web.product.requests.AddItemRequest;
import com.github.countrybros.web.user.request.OrderRequest;
import com.github.countrybros.web.user.request.RefundRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Facade that represents alla the use cases of the system.
 * <p>
 * Controllers use this class to request use cases,
 * and this manager contacts the services needed to obtain the requested feature.
 */
@Service
public class Orchestrator {

    private final IItemService itemService;
    private final ICompanyService companyService;
    private final ICertificationService certificationService;
    private final IAcceptanceSubmissionService acceptanceSubmissionService;
    private final IOrderService orderService;
    private final IShoppingService shoppingService;
    private final IPaymentService paymentService;

    public Orchestrator(IItemService itemService, ICompanyService companyService,
                        ICertificationService certificationService,
                        IAcceptanceSubmissionService acceptanceSubmissionService, IOrderService orderService, IShoppingService shoppingService, PaymentService paymentService) {

        this.itemService = itemService;
        this.companyService = companyService;
        this.certificationService = certificationService;
        this.acceptanceSubmissionService = acceptanceSubmissionService;
        this.orderService = orderService;
        this.shoppingService = shoppingService;
        this.paymentService = paymentService;
    }


    /**
     * Generate a new product and the relative submission to accept/refuse it.
     *
     * @param request the request.
     */
    public void addItemRequest (AddItemRequest request) {

        ItemBuilderFactory factory = new ItemBuilderFactory();
        ItemMapper director = new ItemMapper(companyService, certificationService);

        Item item = director.toDomain(request);
        itemService.addItem(item);

        AddProductAcceptanceSubmissionRequest requestToAdd = new AddProductAcceptanceSubmissionRequest();
        requestToAdd.setItemDetailsId(item.getId());
        requestToAdd.setType("addProduct");
        requestToAdd.setSenderId(request.senderId);

        acceptanceSubmissionService.addAcceptanceSubmission(requestToAdd);
    }

    public void addCertification(AddCertificationRequest request) {

        certificationService.addCertification(request);
    }
    
    public void takeChargeOfSubmission(int userId, int submissionId) {
        acceptanceSubmissionService.takeChargeOfSubmission(userId, submissionId);
    }

    public List<Order> getOrders(int userId) {
        return orderService.getOrders(userId);
    }

    public void addOrder(OrderRequest request) {
    }

    public void removeItemFromCart(int userId, int itemId) {
        shoppingService.removeItemFromCart(userId, itemId);
    }

    public void editQuantityOfItemInCart(int userId, int itemId, int qty) {
        shoppingService.editQuantityOfItemInCart(userId, itemId, qty);

    }

    public void cancelAndRefundOrder(RefundRequest request) {
        boolean refunded = paymentService.refund(request);

        if(refunded) {
            orderService.cancelOrder(request);
            System.out.println("Order cancelled & refunded");
        }
        else {
            System.out.println("Failed to refund order");
        }
    }
}
