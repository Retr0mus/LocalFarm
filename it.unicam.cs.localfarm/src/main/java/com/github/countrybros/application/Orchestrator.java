package com.github.countrybros.application;

import com.github.countrybros.application.acceptancesubmission.ISubmissionService;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.product.ICertificationService;
import com.github.countrybros.application.product.IItemService;
import com.github.countrybros.application.product.ItemMapper;
import com.github.countrybros.application.user.*;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.user.Order;
import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;
import com.github.countrybros.web.acceptancesubmission.request.AddProductSubmissionRequest;
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
    private final ISubmissionService submissionService;
    private final IOrderService orderService;
    private final IShoppingService shoppingService;
    private final IPaymentService paymentService;
    private final IUserService userService;

    public Orchestrator(IItemService itemService, ICompanyService companyService,
                        ICertificationService certificationService,
                        ISubmissionService submissionService, IOrderService orderService, IShoppingService shoppingService, PaymentService paymentService, IUserService userService) {

        this.itemService = itemService;
        this.companyService = companyService;
        this.certificationService = certificationService;
        this.submissionService = submissionService;
        this.orderService = orderService;
        this.shoppingService = shoppingService;
        this.paymentService = paymentService;
        this.userService = userService;
    }


    /**
     * Generate a new product and the relative submission to accept/refuse it.
     *
     * @param request the request.
     */
    public void addItemRequest (AddItemRequest request) {

        ItemMapper director = new ItemMapper(companyService, certificationService);

        Item item = director.toDomain(request);
        itemService.addItem(item);

        AddProductSubmissionRequest requestToAdd = new AddProductSubmissionRequest();
        requestToAdd.setItemDetailsId(item.getId());
        requestToAdd.setType("addProduct");
        requestToAdd.setSenderId(request.senderId);

        submissionService.addAcceptanceSubmission(requestToAdd);
    }

    public void addCertification(AddCertificationRequest request) {

        certificationService.addCertification(request);
    }
    
    public void takeChargeOfSubmission(int userId, int submissionId) {

        if (!userService.userHasRole(userId, UserRole.CURATOR)) {
            throw new ImpossibleRequestException("Only curators can take charge of a submission");
        }
        submissionService.takeChargeOfSubmission(userId, submissionId);
    }

    public List<Order> getOrders(int userId) {
        return orderService.getOrders(userId);
    }

    public void addOrder(OrderRequest request) {
    }

    public void removeItemFromCart(int userId, int shoppingItemId) {
        shoppingService.removeItemFromCart(userId, shoppingItemId);
    }

    public void editQuantityOfItemInCart(int userId, int shoppingItemId, int qty) {
        shoppingService.editQuantityOfItemInCart(userId, shoppingItemId, qty);

    }

    public void cancelAndRefundOrder(RefundRequest request) {
        User user = userService.getUser(request.getUserId());

        Order order = orderService.getOrders(user.getUserId()).stream()
                .filter(o -> o.getOrderId() == request.getOrderId())
                .findFirst()
                .orElseThrow(() -> new NotFoundInRepositoryException(
                        "Order not found with ID " + request.getOrderId()));

        if (order.getCustomer().getUserId() == user.getUserId()) {
            throw new IllegalStateException("Order does not belong to the user");
        }

        boolean refunded = paymentService.refund(request.getEmail(),order.getTotal());

        if (!refunded){
            throw new IllegalStateException("Refund failed, order blocked");
        }

        orderService.cancelOrder(request);
    }
}
