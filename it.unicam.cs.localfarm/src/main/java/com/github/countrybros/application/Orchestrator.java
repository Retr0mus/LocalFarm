package com.github.countrybros.application;

import com.github.countrybros.application.acceptancesubmission.ISubmissionService;
import com.github.countrybros.application.acceptancesubmission.SubmissionMapper;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.product.*;
import com.github.countrybros.application.user.*;
import com.github.countrybros.application.user.dto.PaymentMethod;
import com.github.countrybros.infrastructure.shopping.MockPaymentFactory;
import com.github.countrybros.model.acceptancesubmission.AddProductSubmission;
import com.github.countrybros.model.acceptancesubmission.RecogniseProductSubmission;
import com.github.countrybros.model.acceptancesubmission.Submission;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.product.ItemStatus;
import com.github.countrybros.model.user.*;
import com.github.countrybros.web.acceptancesubmission.request.AddProductSubmissionRequest;
import com.github.countrybros.model.product.Stock;
import com.github.countrybros.web.acceptancesubmission.request.RecogniseProductSubmissionRequest;
import com.github.countrybros.web.product.requests.AddCertificationRequest;
import com.github.countrybros.web.product.requests.AddItemRequest;
import com.github.countrybros.web.user.request.AddItemToCartRequest;
import org.springframework.beans.PropertyValues;
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
    private final IStockService stockService;
    private final ICertificationService certificationService;
    private final ISubmissionService submissionService;
    private final ICompanyService companyService;
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
        this.userService = userService;
        this.shoppingService = shoppingService;
        this.orderService = orderService;
        this.paymentService = paymentService;
    }


    /**
     * Generate a new product and the relative submission to accept/refuse it.
     *
     * @param request the request.
     */
    public void addItemRequest(AddItemRequest request) {

        ItemMapper director = new ItemMapper(companyService, certificationService);

        Item item = director.toDomain(request);
        itemService.addItem(item);

        AddProductSubmissionRequest requestToAdd = new AddProductSubmissionRequest();
        requestToAdd.setItemDetailsId(item.getId());
        requestToAdd.setType("addProduct");
        requestToAdd.setSenderId(request.senderId);


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
            submissionService.onAcceptance(submissionId);
            accept(submission);
        } else {
            submissionService.onRejection(submissionId);
            refuse(submission);
        }
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

            itemService.setStatus(ItemStatus.available, sub.getItemDetailsId());

        else if (submission instanceof RecogniseProductSubmission sub) {

            stockService.addQuantityToStock(sub.getItemId(), sub.getQta(), sub.getSenderId());
        }

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
            itemService.deleteItemDetails(sub.getItemDetailsId());
    }

    public void addQuantityToStock(RecogniseProductSubmissionRequest request) {

        if (request.getQta() <= 0)
            throw new ImpossibleRequestException("Quantity less or equal 0");

        Item item = itemService.getItem(request.getProductId());

        if (item == null)
            throw new ImpossibleRequestException("Item not found");

        if(item.getStatus() != ItemStatus.available)
            throw new ImpossibleRequestException("Item not available");

        Company company = companyService.getCompany(request.getProductId());

        if(company == null)
            throw new ImpossibleRequestException("Company not found");

        submissionService.addSubmission(SubmissionMapper.toDomain(request));
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
        PaymentMethod paymentMethod = f.createPaymentMethod();

        // Pay
        if(!paymentMethod.pay(order.getTotal()))
            throw new ImpossibleRequestException("Payment failed");

        orderService.setAsPaid(orderId);

        // Removing ordered items from the relative stocks
        for (OrderItem item : order.getItems())
            stockService.removeQuantityToStock(item.getItem().getId(), item.getQuantity(), item.getSeller().getId());
    }

    public List<Stock> getStocksByItem(int itemId) {
        return stockService.getStocksByItem(itemId);
    }
}