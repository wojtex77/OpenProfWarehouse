package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
