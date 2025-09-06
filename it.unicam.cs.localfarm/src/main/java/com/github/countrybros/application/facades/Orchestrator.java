package com.github.countrybros.application.facades;

import com.github.countrybros.application.abstractions.IPaymentMethod;
import com.github.countrybros.application.errors.EventsNotFoundException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.factories.PaymentMethodFactory;
import com.github.countrybros.application.models.requests.item.AddStockRequest;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.application.models.requests.event.CreateInvitationRequest;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.application.services.event.IEventService;
import com.github.countrybros.application.services.event.IInvitationService;
import com.github.countrybros.application.services.order.IOrderService;
import com.github.countrybros.application.services.event.EventService;
import com.github.countrybros.application.services.event.IEventService;
import com.github.countrybros.application.services.event.IInvitationService;
import com.github.countrybros.application.services.order.IOrderService;
import com.github.countrybros.application.services.order.OrderService;
import com.github.countrybros.application.services.payment.IPaymentService;
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
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.Invitation;
import com.github.countrybros.model.company.CompanyStatus;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.model.event.TimeInterval;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.model.event.TimeInterval;
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
import com.github.countrybros.application.models.requests.item.AddItemRequest;
import com.github.countrybros.application.models.requests.user.AddItemToCartRequest;
import com.github.countrybros.application.models.requests.order.RefundRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

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
    private final IOrderService orderService;
    private final IPaymentService paymentService;
    private final IEventService eventService;
    private final IInvitationService invitationService;

    @Autowired
    public Orchestrator(IItemService itemService, IStockService stockService, ICompanyService companyService,
                        ICertificationService certificationService,
                        ISubmissionService submissionService, ICompanyService companyService1, IUserService userService, IShoppingService shoppingService, OrderService orderService, PaymentService paymentService, EventService eventService, IInvitationService invitationService) {

        this.itemService = itemService;
        this.stockService = stockService;
        this.certificationService = certificationService;
        this.submissionService = submissionService;
        this.companyService = companyService;
        this.orderService = orderService;
        this.shoppingService = shoppingService;
        this.paymentService = paymentService;
        this.userService = userService;
        this.eventService = eventService;
        this.invitationService = invitationService;
    }


    /**
     * Generate a new item and the relative submission to accept/refuse it.
     *
     * @param request the request.
     */
    public void addItemRequest (AddItemRequest request) {

        ItemMapper director = new ItemMapper(companyService, certificationService, itemService);
        SubmissionMapper subMapper = new SubmissionMapper(companyService, itemService, stockService);

        Item item = director.toDomain(request);
        itemService.addItem(item);

        AddProductSubmissionRequest requestToAdd = new AddProductSubmissionRequest();
        requestToAdd.setItemId(item.getId());
        requestToAdd.setType("addProduct");
        requestToAdd.setSenderId(request.producerId);

        submissionService.addSubmission(subMapper.toDomain(requestToAdd));
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

    public void takeChargeOfSubmission(int userId, int submissionId) {

        if (!userService.userHasRole(userId, UserRole.CURATOR)) {
            throw new ImpossibleRequestException("Only curators can take charge of a submission");
        }

        User user = userService.getUser(userId);
        submissionService.takeChargeOfSubmission(submissionId,user);
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
            itemService.setStatus(ItemStatus.available, sub.getItem().getId());

        else if (submission instanceof RecogniseProductSubmission sub) {
            stockService.addQuantityToStock(sub.getStock().getId(), sub.getQta(), sub.getSender().getId());
        }

    }

    public void removeItemFromCart(int userId, int shoppingItemId) {
        //check existences of user
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
            itemService.deleteItem(sub.getItem().getId());
    }

    public void addSubmissionQuantityToStock(RecogniseProductSubmissionRequest request) {


        Stock stock = stockService.getStock(request.getStockId());
        if(stock.getItem().getStatus() != ItemStatus.available)
            throw new ImpossibleRequestException("Item not available");
        if(stock.getSeller().getId() != request.getSenderId())
            throw new ImpossibleRequestException("Seller does not own the stock");

        companyService.getCompany(request.getSenderId());

        SubmissionMapper subMapper = new SubmissionMapper(companyService, itemService, stockService);

        submissionService.addSubmission(subMapper.toDomain(request));
    }

    public void cancelAndRefundOrder(RefundRequest request) {
        User user = userService.getUser(request.getUserId());

        Order order = orderService.getOrders(user.getUserId()).stream()
                .filter(o -> o.getOrderId() == request.getOrderId())
                .findFirst()
                .orElseThrow(() -> new NotFoundInRepositoryException(
                        "Order not found with ID " + request.getOrderId()));

        if (order.getCustomer().getUserId() != user.getUserId()) {
            throw new IllegalStateException("Order does not belong to the user");
        }

        boolean refunded = paymentService.refund(request.getEmail(),order.getTotal());

        if (!refunded){
            orderService.blockOrder(order.getOrderId());
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
        if(!paymentMethod.pay(order.getTotal(), "this.is@email.ofSystem"))
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

    /**
     * Pay all the orders of the last 30 days to the companies.
     */
    public void payMonthlyOrders() {

        List<Order> orders = orderService.getOrdersSince(LocalDate.now().minusDays(30));

        paymentService.paySellers(orders);
    }

    /**
     * Acceptance/rejection an invitation of a company on an @Event.
     *
     * @param invitationId invitation ID
     * @param accepted if the invitation is accepted or not
     */
    public void acceptInvitation(int invitationId, boolean accepted) {

        Invitation invitation = invitationService.getInvitation(invitationId);
        Event event = eventService.getEvent(invitation.getEvent().getId());
        Company company = companyService.getCompany(invitation.getReceiver().getId());

        if (accepted)
            eventService.confirmCompanyParticipation(event, company);

        invitationService.deleteInvitation(invitationId);
    }

    /**
     * Cancel the participation of a company to a specified event, if it is already participating of course
     *
     * @param companyId company ID
     * @param eventId EventId
     */
    public void cancelCompanyParticipation(int companyId, int eventId) {

        Company company = companyService.getCompany(companyId);
        Event event = eventService.getEvent(eventId);

        eventService.cancelCompanyParticipation(company, event);
    }

    public List<Event> getParticipations(int companyId) {

        return eventService.getParticipations(companyService.getCompany(companyId));
    }

    public void createStock(AddStockRequest request) {
        Company seller = companyService.getCompany(request.getSellerId());
        Item item = itemService.getItem(request.getItemId());

        if(item.getStatus() != ItemStatus.available)
            throw new ImpossibleRequestException("Item not available");

        if(seller.getStatus() == CompanyStatus.inactive)
            throw new ImpossibleRequestException("Seller's status is inactive");

        Stock stock = new Stock();
        stock.setSeller(seller);
        stock.setItemDetails(item);
        stock.setPrice(request.getPrice());

        stockService.add(stock);
    }

    public void disableCompany(int companyId, int adminId) {
        if(!userService.getUser(adminId).getRoles().contains(UserRole.ADMIN))
            throw new ImpossibleRequestException("You are not allowed to cancel this company");

        companyService.disableCompany(companyId);
        stockService.deleteAllCompanyStocks(companyId);
    }

    public void createEvent(CreateEventRequest request) {

        validateEventRequest(request);
        User organizer = userService.getUser(request.organizerId);
        eventService.createEvent(request,organizer);
        Event event = eventService.getLastCreatedEvent();

        for (Integer companyId : request.guestsId) {
            CreateInvitationRequest invitationRequest = new CreateInvitationRequest();
            invitationRequest.event = event;
            invitationRequest.expiration = java.time.LocalDate.now().plusDays(7);
            Company company = companyService.getCompany(companyId);

            invitationService.addInvitation(invitationRequest, company);
        }

    }

    private void validateEventRequest(CreateEventRequest request) {


        if (eventService.existsByName(request.name)) {
            throw new ImpossibleRequestException("An event with the same name already exists");
        }

        if (request.dates == null || request.dates.isEmpty()) {
            throw new ImpossibleRequestException("At least one date interval must be provided");
        }

        for (var interval : request.dates) {
            if (interval.getStartTime() == null || interval.getEnd() == null) {
                throw new ImpossibleRequestException("Start and end time must be provided for each interval");
            }
            if (!interval.getStartTime().isBefore(interval.getEnd())) {
                throw new ImpossibleRequestException("Start time must be before end time");
            }
        }

        if (request.location == null) {
            throw new ImpossibleRequestException("Location must be provided");
        }

        for (int i = 0; i < request.dates.size(); i++) {
            var first = request.dates.get(i);
            for (int j = i + 1; j < request.dates.size(); j++) {
                var second = request.dates.get(j);

                if (first.getStartTime().toLocalDate().equals(second.getStartTime().toLocalDate())) {
                    boolean overlap = !first.getEnd().isBefore(second.getStartTime()) &&
                            !second.getEnd().isBefore(first.getStartTime());

                    if (overlap) {
                        throw new ImpossibleRequestException(
                                "Overlapping time intervals are not allowed on the same day"
                        );
                    }
                }
            }
        }

        for (var interval : request.dates) {
            List<Event> existingEvents = eventService.getEventsByDate(interval.getStartTime().toLocalDate());

            for (Event existingEvent : existingEvents) {
                for (TimeInterval existingInterval : existingEvent.getDates()) {
                    boolean overlap = !interval.getEnd().isBefore(existingInterval.getStartTime()) &&
                            !existingInterval.getEnd().isBefore(interval.getStartTime());
                    if (overlap) {
                        throw new ImpossibleRequestException(
                                "New event interval overlaps with an existing event on the same day: " + existingEvent.getName()
                        );
                    }
                }
            }
        }

        // Check if the organizer exits
        companyService.getCompany(request.organizerId);
    }

    public void subscribeToEvent(int userId, int eventId) {
        Event event = eventService.getEvent(eventId);
        User user = userService.getUser(userId);

        if (event.getSubscribers().contains(user)) {
            throw new RequestAlreadySatisfiedException("User is already subscribed to this event");
        }

        if(event.getState() != EventState.currentlyPublic)
        {
            throw new ImpossibleRequestException("The event is not public or is ended");
        }

        eventService.subscribeToEvent(user,eventId);
    }

    public void unSubscribeToEvent(int userId, int eventId) {
        Event event = eventService.getEvent(eventId);
        User user = userService.getUser(userId);

        if (!event.getSubscribers().contains(user)) {
            throw new RequestAlreadySatisfiedException("The user "+user.getId()+" is not subscribed to this event "+event.getId());
        }

        if(event.getState() == EventState.completed)
        {
            throw new ImpossibleRequestException("You cannot cancel your registration because the event has ended.");
        }

        eventService.unSubscribeFromEvent(user, eventId);

    }

    public List<Event> getEventsByOrganizer(int organizerId) {
        User organizer = userService.getUser(organizerId);

        List<Event> events = eventService.getEventsByOrganizer(organizer);

        if (events == null || events.isEmpty()) {
            throw new EventsNotFoundException(
                    "No events found for organizer with id: " + organizerId
            );
        }

        return events;
    }

    public List<Event> getEventsSubscribedByUser(int userId) {
        // checks the existence of a user
        User user = userService.getUser(userId);

        return eventService.getEventsSubscribedByUser(userId);
    }
}