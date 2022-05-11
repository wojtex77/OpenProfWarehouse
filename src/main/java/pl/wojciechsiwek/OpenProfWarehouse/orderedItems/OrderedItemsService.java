package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class OrderedItemsService {
    private final OrderedItemsRepository repository;

    public OrderedItemsService(OrderedItemsRepository repository) {
        this.repository = repository;
    }

    public void saveItems(String orderNumber, Map<Integer, Integer> map) {
        List<OrderedItems> items = new LinkedList<OrderedItems>();
        map.forEach((partId, qty) -> {
            items.add(new OrderedItems(orderNumber,partId,qty));
        });
        repository.saveAll(items);
    }
}
