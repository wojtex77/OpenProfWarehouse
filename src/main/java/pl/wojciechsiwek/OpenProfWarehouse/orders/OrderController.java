package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String showAllOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders/all";
    }

    @GetMapping(path = "/new")
    public String newOrderView(Model model, @ModelAttribute("order") Order order) {

        if (order == null) order = new Order();

        List<Contrahent> contrahents = contrahentRepository.findAll();
        model.addAttribute("contrahents", contrahents);
        model.addAttribute("order", order);
        model.addAttribute("action", "new");
        return "orders/new";
    }

    @PostMapping(path = "/add")
    public RedirectView saveOrderToDb(@ModelAttribute Order order, RedirectAttributes attributes) {
        RedirectView view = new RedirectView();

        try {
            service.addOrder(order);
            attributes.addFlashAttribute("messageSuccess", "Pomyslnie dodano zlecenie " + order.getOrderNumber());
            view.setUrl("/orders");
        } catch (DuplicatedOrderEntryException e) {
            attributes.addFlashAttribute("messageWarning", "Podany numer zlecenia już istnieje");
            attributes.addFlashAttribute("order", order);
            view.setUrl("/orders/new");
        } catch (Exception e) {
            attributes.addFlashAttribute("messageWarning", "Nie udało się zapisać zlecenia");
            attributes.addFlashAttribute("order", order);
            view.setUrl("/orders/new");
        }
        return view;
    }


}
