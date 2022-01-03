package tacos.web.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tacos.domain.Order;
import tacos.data.OrderRepository;
import tacos.domain.User;
import tacos.exceptions.ResourceNotFoundException;
import tacos.web.OrderProps;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/orders",
                consumes = "application/json")
@CrossOrigin(origins = "*")
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderProps orderProps;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderProps orderProps) {
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
    }

    @PostMapping
    public Order saveOrder(@RequestBody @Valid Order order, @AuthenticationPrincipal User user) {
        order.setUser(user);
        Order savedOrder = orderRepository.save(order);
        log.info("Order [id = {}] was saved", order.getId());
        return savedOrder;
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        if (optOrder.isPresent()) {
            return optOrder.get();
        }
        throw new ResourceNotFoundException("Order with id " + orderId + " was not found");
    }

    @GetMapping
    public List<Order> getOrdersForUser(@AuthenticationPrincipal User user) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        return orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable Long orderId,
                            @RequestBody Order patch) {

        Order order = findOrder(orderId);
        updateOrder(patch, order);

        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException ignored) {}
    }

    private void updateOrder(Order patch, Order order) {
        if (patch.getName() != null) {
            order.setName(patch.getName());
        }
        if (patch.getStreet() != null) {
            order.setStreet(patch.getStreet());
        }
        if (patch.getCity() != null) {
            order.setCity(patch.getCity());
        }
        if (patch.getState() != null) {
            order.setState(patch.getState());
        }
        if (patch.getZip() != null) {
            order.setZip(patch.getZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
    }

    private Order findOrder(Long orderId) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        if (optOrder.isEmpty()) {
            throw new ResourceNotFoundException("Order with id " + orderId + " was not found");
        }
        return optOrder.get();
    }
}
