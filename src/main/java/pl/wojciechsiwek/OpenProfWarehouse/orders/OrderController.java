package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.Contrahent;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.ContrahentRepository;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ContrahentRepository contrahentRepository;
    private final OrderService service;

    public OrderController(OrderRepository orderRepository, ContrahentRepository contrahentRepository, OrderService service) {
        this.orderRepository = orderRepository;
        this.contrahentRepository = contrahentRepository;
        this.service = service;
    }


    @GetMapping(path = "")
    public String showAllOrders(Model model){
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders/all";
    }

    @GetMapping(path = "/new")
    public String newOrderView(Model model){
        List<Contrahent> contrahents = contrahentRepository.findAll();
        model.addAttribute("contrahents", contrahents);
        model.addAttribute("order", new Order());
        model.addAttribute("action", "new");
        return  "orders/new";
    }

    @PostMapping(path = "/add")
    public RedirectView saveOrderToDb(@ModelAttribute Order order, RedirectAttributes attributes){
        RedirectView view = new RedirectView("/orders");

        try {
            service.saveOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("messageWarning", "Nie udało się zapisać zlecenia");
            attributes.addFlashAttribute("order", order);
            view.setUrl("orders/new");
        }


        return view;
    }




}
