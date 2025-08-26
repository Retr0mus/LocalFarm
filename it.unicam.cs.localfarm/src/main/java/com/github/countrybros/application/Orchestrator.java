package com.github.countrybros.application;

import com.github.countrybros.application.acceptancesubmission.ISubmissionService;
import com.github.countrybros.application.acceptancesubmission.SubmissionMapper;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.product.*;
import com.github.countrybros.application.user.*;
import com.github.countrybros.application.user.dto.IPaymentMethod;
import com.github.countrybros.application.user.dto.PaymentMethod;
import com.github.countrybros.infrastructure.shopping.MockPaymentFactory;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.product.Stock;
import com.github.countrybros.model.user.*;
import com.github.countrybros.web.acceptancesubmission.request.RecogniseProductSubmissionRequest;
import com.github.countrybros.web.product.requests.AddCertificationRequest;
import com.github.countrybros.web.product.requests.AddItemRequest;
import com.github.countrybros.web.user.request.AddItemToCartRequest;
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

    }

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

    public void addQuantityToStock(RecogniseProductSubmissionRequest submission) {

        if (submission.getQta() <= 0)
            throw new ImpossibleRequestException("Quantity less or equal 0");

        if (itemService.getItem(submission.getProductId()) == null)
            throw new ImpossibleRequestException("Item not found");

        submissionService.addSubmission(SubmissionMapper.toDoamin(submission));
    }

    public List<Stock> getStocksByItem(int itemId) {
        return stockService.getStocksByItem(itemId);
    }

    public Cart getCart(int userId) {
        return shoppingService.getCart(userId);
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
            throw new NotFoundInRepositoryException("Cannot proceed to checkout");

        order.setCustomer(userService.getUser(userId));
        order.setAddress(order.getCustomer().getAddress());

        orderService.addOrder(order);

        return order;
    }

    public boolean paymentToMarketplace(int orderId) {

        Order order = orderService.getOrder(orderId);

        if(order.getOrderStatus() != OrderStatus.picking)
            return false;

        PaymentMethodFactory f = new MockPaymentFactory();
        PaymentMethod paymentMethod = f.createPaymentMethod();

        if(!paymentMethod.pay(order.getTotal()))
            return false;

        orderService.setAsPaid(orderId);

        // Removing ordered items from the relative stocks
        for (OrderItem item : order.getItems())
            stockService.removeQuantityToStock(item.getItem().getId(), item.getQuantity(), item.getSeller().getId());

        return true;
    }
}