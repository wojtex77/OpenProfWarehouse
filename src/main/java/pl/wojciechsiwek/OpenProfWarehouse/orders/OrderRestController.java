package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojciechsiwek.OpenProfWarehouse.parts.Part;
import pl.wojciechsiwek.OpenProfWarehouse.parts.PartRepository;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderRestController {

    private final OrderRepository orderRepository;
    private final PartRepository partRepository;

    public OrderRestController(OrderRepository orderRepository, PartRepository partRepository) {
        this.orderRepository = orderRepository;
        this.partRepository = partRepository;
    }

    @PostMapping("/getPartsFromDb")
    public List<Part> getPartsFromDb(){
        List<Part> parts = partRepository.findAll();
        return parts;
    }
}
