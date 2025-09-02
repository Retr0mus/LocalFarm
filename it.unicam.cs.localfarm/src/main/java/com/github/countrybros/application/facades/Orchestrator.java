package com.github.countrybros.application.facades;

import com.github.countrybros.application.abstractions.IPaymentMethod;
import com.github.countrybros.application.factories.PaymentMethodFactory;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.application.services.order.OrderService;
import com.github.countrybros.application.services.payment.PaymentService;
import com.github.countrybros.application.services.submission.ISubmissionService;
import com.github.countrybros.application.mappers.SubmissionMapper;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.services.item.ICertificationService;
import com.github.countrybros.application.services.item.IItemService;
import com.github.countrybros.application.services.stock.IStockService;
import com.github.countrybros.application.mappers.ItemMapper;
import com.github.countrybros.application.services.user.*;
import com.github.countrybros.infrastructure.services.shopping.MockPaymentFactory;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.order.Order;
import com.github.countrybros.model.order.OrderItem;
import com.github.countrybros.model.order.OrderStatus;
import com.github.countrybros.model.submission.AddProductSubmission;
import com.github.countrybros.model.submission.RecogniseProductSubmission;
import com.github.countrybros.model.submission.Submission;
import com.github.countrybros.model.item.Item;
import com.github.countrybros.model.item.ItemStatus;
import com.github.countrybros.model.user.*;
import com.github.countrybros.application.models.requests.submission.AddProductSubmissionRequest;
import com.github.countrybros.model.stock.Stock;
import com.github.countrybros.application.models.requests.submission.RecogniseProductSubmissionRequest;
import com.github.countrybros.application.models.requests.item.AddCertificationRequest;
import com.github.countrybros.application.models.requests.item.AddItemRequest;
import com.github.countrybros.application.models.requests.user.AddItemToCartRequest;
import com.github.countrybros.application.models.requests.order.RefundRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final IStockService stockService;
    private final ICertificationService certificationService;
    private final ISubmissionService submissionService;
    private final IUserService userService;
    private final IShoppingService shoppingService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    @Autowired
    public Orchestrator(IItemService itemService, IStockService stockService, ICompanyService companyService,
                        ICertificationService certificationService,
                        ISubmissionService submissionService, ICompanyService companyService1, IUserService userService, IShoppingService shoppingService, OrderService orderService, PaymentService paymentService) {

        this.itemService = itemService;
        this.stockService = stockService;
        this.certificationService = certificationService;
        this.submissionService = submissionService;
        this.companyService = companyService1;
        this.orderService = orderService;
        this.shoppingService = shoppingService;
        this.paymentService = paymentService;
        this.userService = userService;
    }


    /**
     * Generate a new item and the relative submission to accept/refuse it.
     *
     * @param request the request.
     */
    public void addItemRequest (AddItemRequest request) {

        ItemMapper director = new ItemMapper(companyService, certificationService, itemService);

        Item item = director.toDomain(request);
        itemService.addItem(item);

        AddProductSubmissionRequest requestToAdd = new AddProductSubmissionRequest();
        requestToAdd.setItemId(item.getId());
        requestToAdd.setType("addProduct");
        requestToAdd.setSenderId(request.producerId);



        submissionService.addSubmission(SubmissionMapper.toDomain(requestToAdd));
    }

    /**
     * Adds a new certification.
     *
     * @param request the request.
     */
    public void addCertification(AddCertificationRequest request) {

        certificationService.addCertification(request);
    }

    public List<Item> getAvailableItems() {
        return itemService.getAvailableItems();
    }

    public Item getItemDetails(int itemId) {
        return itemService.getItem(itemId);
    }

    public List<Stock> getStocksBySeller(int sellerId) {
        // This istruction is used to throw an eventual loading exception
        Company company = companyService.getCompany(sellerId);
        return stockService.getStocksBySeller(sellerId);
    }

    public void removeQuantityToStock(int stockId, int quantity, int sellerId) {
        stockService.removeQuantityToStock(stockId, quantity, sellerId);
    }

    /**
     * Retrives all the Submission that haven't been accepted.
     *
     * @return all the available submission.
     */
    public List<Submission> getAvailableSubmissions() {
        return submissionService.getAvailableAcceptanceSubmissions();
    }

    /**
     * Manage the aftermaths of accepting/rejecting a @Submission.
     *
     * @param submissionId the id of the submission.
     * @param accepted     states if the submission have to be accepted or refused.
     */
    public void acceptSubmission(int submissionId, boolean accepted) {

        Submission submission = submissionService
                .getSubmission(submissionId);

        if (accepted) {
            submissionService.onAcception(submissionId);
            accept(submission);
        } else {
            submissionService.onRejection(submissionId);
            refuse(submission);
        }
    }

    public void takeChargeOfSubmission(int userId, int submissionId) {

        if (!userService.userHasRole(userId, UserRole.CURATOR)) {
            throw new ImpossibleRequestException("Only curators can take charge of a submission");
        }
        submissionService.takeChargeOfSubmission(submissionId,userId);
    }

    public List<Order> getOrders(int userId) {
        userService.getUser(userId);
        return orderService.getOrders(userId);
    }


    public Cart getCart(int userId) {
        return shoppingService.getCart(userId);
    }

    /**
     * Logic behind the acceptance of a submission.
     *
     * @param submission the submission to accept
     */
    private void accept(Submission submission) {

        if (submission instanceof AddProductSubmission sub)
            itemService.setStatus(ItemStatus.available, sub.getItemId());

        else if (submission instanceof RecogniseProductSubmission sub) {
            stockService.addQuantityToStock(sub.getStockId(), sub.getQta(), sub.getSenderId());
        }

    }

    public void removeItemFromCart(int userId, int shoppingItemId) {
        userService.getUser(userId);
        shoppingService.removeItemFromCart(userId, shoppingItemId);
    }

    public void editQuantityOfItemInCart(int userId, int shoppingItemId, int qty) {
        userService.getUser(userId);
        shoppingService.editQuantityOfItemInCart(userId, shoppingItemId, qty);

    }


    /**
     * Logic behind the rejection of a submission.
     * <p>
     * Only the request to add a new Item will make some changes.
     *
     * @param submission the submission selected.
     */
    private void refuse(Submission submission) {

        if (submission instanceof AddProductSubmission sub)
            itemService.deleteItemDetails(sub.getItemId());
    }

    public void addSubmissionQuantityToStock(RecogniseProductSubmissionRequest request) {

        if (request.getQta() <= 0)
            throw new ImpossibleRequestException("Quantity less or equal 0");

        Item item = itemService.getItem(request.getProductId());
        if(item.getStatus() != ItemStatus.available)
            throw new ImpossibleRequestException("Item not available");

        Company company = companyService.getCompany(request.getSenderId());

        submissionService.addSubmission(SubmissionMapper.toDomain(request));
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

    public void addItemToCart(AddItemToCartRequest request) {

        Cart cart = shoppingService.getCart(request.userId);

        Stock stock = stockService.getStock(request.stockId);
        if (stock == null)
            throw new NotFoundInRepositoryException("Stock with ID " + request.stockId + " not found.");

        if (request.quantity > stock.getQty())
            throw new ImpossibleRequestException("The quantity required is more than the supplier can give.");

        shoppingService.addItemToCart(request.userId, stock, request.quantity);
    }

    public Order checkout(int userId){
        Order order = shoppingService.checkout(userId);

        if (order == null)
            throw new NotFoundInRepositoryException("Cannot proceed to checkout, check the content of the cart.");

        order.setCustomer(userService.getUser(userId));
        order.setAddress(order.getCustomer().getAddress());

        orderService.addOrder(order);

        return order;
    }


    public void paymentToMarketplace(int orderId) {

        Order order = orderService.getOrder(orderId);

        if(order == null)
            throw new NotFoundInRepositoryException("Order with ID " + orderId + " not found.");

        if(order.getOrderStatus() != OrderStatus.picking)
            throw new ImpossibleRequestException("Invalid order status");

        // Checks if it is possible to complete the payment of the order.
        for(OrderItem item : order.getItems()) {
            if(stockService.getStockByItemAndSeller(item.getItem(), item.getSeller()).getQty() < item.getQuantity())
                throw new ImpossibleRequestException("Quantity of item " + item.getItem().getName() +  " exceeds the supplier's stock");
        }

        PaymentMethodFactory f = new MockPaymentFactory();
        IPaymentMethod paymentMethod = f.createPaymentMethod();

        // Pay
        if(!paymentMethod.pay(order.getTotal()))
            throw new ImpossibleRequestException("Payment failed");

        orderService.setAsPaid(orderId);

        // Removing ordered items from the relative stocks
        for (OrderItem item : order.getItems())
            stockService.removeQuantityToStock(item.getItem().getId(), item.getQuantity(), item.getSeller().getId());
    }

    public List<Stock> getStocksByItem(int itemId) {
        Item item = itemService.getItem(itemId);

        if(item == null)
            throw new NotFoundInRepositoryException("Item not found");

        return stockService.getStocksByItem(itemId);
    }
}