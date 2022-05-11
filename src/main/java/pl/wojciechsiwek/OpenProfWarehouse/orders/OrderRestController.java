package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<Part> getPartsFromDb(@RequestBody MultiValueMap<String, String> formData){

        List<Part> parts = partRepository.findByContrahentEquals(formData.getFirst("contrahent"));
        return parts;
    }


    @PostMapping("/getPartFromDb")
    public Part getPartFromDb(@RequestBody MultiValueMap<String, String> formData){

        Part part = partRepository.findById(Integer.parseInt(formData.getFirst("partId"))).get();
        return part;
    }

}
