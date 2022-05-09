package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(Order order) throws Exception{
        try {
            orderRepository.save(order);
        } catch (Exception e){
            throw new RuntimeException("Saving order to DB failed");
        }
    }
}
