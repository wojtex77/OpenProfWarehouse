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

    void removeFromDb(int id) throws Exception {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Removing order From db failed");
        }
    }
}
