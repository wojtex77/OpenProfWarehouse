package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @GetMapping(path = "")
    public String showAllOrders(Model model){
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);

        return "orders/all";
    }




}
