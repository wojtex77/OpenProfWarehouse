package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.Contrahent;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.ContrahentRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsService;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ContrahentRepository contrahentRepository;
    private final OrderService service;
    private final OrderedItemsService orderedItemsService;

    public OrderController(OrderRepository orderRepository, ContrahentRepository contrahentRepository, OrderService service, OrderedItemsService orderedItemsService) {
        this.orderRepository = orderRepository;
        this.contrahentRepository = contrahentRepository;
        this.service = service;
        this.orderedItemsService = orderedItemsService;
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
    public RedirectView saveToDb(@ModelAttribute Order order, @RequestParam("partId") List<Integer> partIds,
                                 @RequestParam("ammountOfPart") List<Integer> ammountOfParts, RedirectAttributes attributes) {
        RedirectView view = new RedirectView();

        try {
            service.addOrder(order, partIds, ammountOfParts);
            attributes.addFlashAttribute("messageSuccess", "Pomyslnie dodano zlecenie " + order.getOrderNumber());
            int id = orderRepository.findByOrderNumberEquals(order.getOrderNumber()).getId();
            view.setUrl("/orders/edit/" + id);
        } catch (DuplicatedOrderEntryException e) {
            attributes.addFlashAttribute("messageWarning", "Podany numer zlecenia ju?? istnieje");
            attributes.addFlashAttribute("order", order);
            view.setUrl("/orders/new");
        } catch (Exception e) {
            attributes.addFlashAttribute("messageWarning", "Nie uda??o si?? zapisa?? zlecenia");
            attributes.addFlashAttribute("order", order);
            view.setUrl("/orders/new");
        }
        return view;
    }

    @GetMapping(path = "/delete/{id}")
    public RedirectView deleteOrder(Model model, @PathVariable("id") int id) {
        RedirectView view = new RedirectView("/orders");
        try {
            service.removeFromDb(id);
            model.addAttribute("messageSuccess", "Usunieto zlecenie");
        } catch (Exception e) {
            model.addAttribute("messageWarning", "Usuwanie zlecenia nie powiod??o si??");
        }
        return view;
    }


    @GetMapping(path = "/edit/{id}")
    public String editOrderView(Model model, @PathVariable("id") int id) {
        Order order = orderRepository.findById(id).get();
        List<Contrahent> contrahents = contrahentRepository.findAll();
        List<OrderedItemsExtended> items = orderedItemsService.getOrderedItemsExtendedByOrder(order.getOrderNumber());

        model.addAttribute("contrahents", contrahents);
        model.addAttribute("orderedItems", items);
        model.addAttribute("order", order);
        model.addAttribute("action", "edit");
        return "orders/edit";
    }

    @PostMapping(path = "/saveChanges")
    public RedirectView saveChangesToDb(@ModelAttribute Order order,
                                        @RequestParam("partId") List<Integer> partIds,
                                        @RequestParam("ammountOfPart") List<Integer> ammountOfParts,
                                        @RequestParam("itemId") List<Integer> itemIds,
                                        @RequestParam("idsToDelete") List<Integer> idsToDelete,
                                        RedirectAttributes attributes) {
        RedirectView view = new RedirectView();
        try {
            service.saveChanges(order, partIds, ammountOfParts, itemIds, idsToDelete);
            attributes.addFlashAttribute("messageSuccess", "Pomyslnie edytowano zlecenie " + order.getOrderNumber());
            int id = orderRepository.findByOrderNumberEquals(order.getOrderNumber()).getId();
            view.setUrl("/orders/edit/" + id);
        } catch (Exception e) {
            attributes.addFlashAttribute("messageWarning", "Nie uda??o si?? zapisa?? zmian");
            attributes.addFlashAttribute("order", order);
            view.setUrl("/orders/edit/" + order.getId());
        }
        return view;
    }


}
