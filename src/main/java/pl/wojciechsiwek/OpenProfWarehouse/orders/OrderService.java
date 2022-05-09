package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order) throws Exception {

        if (orderRepository.existsByOrderNumberEquals(order.getOrderNumber()))
            throw new DuplicatedOrderEntryException("Order number exists");
        orderRepository.save(order);

    }
}
