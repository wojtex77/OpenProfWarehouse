package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojciechsiwek.OpenProfWarehouse.parts.Part;

import java.util.List;

@RestController
@RequestMapping("items")
public class OrderedItemsRestController {

    private final OrderedItemsService orderedItemsService;

    public OrderedItemsRestController(OrderedItemsService orderedItemsService) {
        this.orderedItemsService = orderedItemsService;
    }

    @PostMapping(path = "getAllParts")
    public List<OrderedItemsExtended> getAllOrderedItems(){
        return orderedItemsService.getAllOrderedItems();
    }

    @PostMapping("/getItemFromDb")
    public OrderedItemsExtended getOrderedItemFromDb(@RequestBody MultiValueMap<String, String> formData){

        OrderedItemsExtended orderedItemsExtended = orderedItemsService.getOrderedItem(Integer.parseInt(formData.getFirst("partId")));
        return orderedItemsExtended;
    }
}
