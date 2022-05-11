package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderedItemsService itemsService;

    public OrderService(OrderRepository orderRepository, OrderedItemsRepository orderedItemsRepository, OrderedItemsService itemsService) {
        this.orderRepository = orderRepository;
        this.itemsService = itemsService;
    }

    public void addOrder(Order order, List<Integer> partIds, List<Integer> ammountOfParts) throws Exception {

        if (orderRepository.existsByOrderNumberEquals(order.getOrderNumber()))
            throw new DuplicatedOrderEntryException("Order number exists");
        Map<Integer, Integer> map = combinePartIdAndQtyLists(partIds, ammountOfParts);


        orderRepository.save(order);
        itemsService.saveItems(order.getOrderNumber(), map);
    }

    void removeFromDb(int id) throws Exception {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Removing order From db failed");
        }
    }

    void saveChanges(Order order, List<Integer> partIds, List<Integer> ammountOfParts, List<Integer> itemIds) throws Exception {

        try {
            orderRepository.save(order);
            itemsService.saveChanges(order.getOrderNumber(), partIds, ammountOfParts, itemIds);

        } catch (Exception e) {
            throw new Exception("Saving changes failed");
        }
    }

    Map<Integer, Integer> combinePartIdAndQtyLists(List<Integer> ids, List<Integer> qtys) {
        if (ids.size() != qtys.size())
            throw new IllegalArgumentException("Cannot combine lists with dissimilar sizes");
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        for (int i = 0; i < ids.size(); i++) {
            map.put(ids.get(i), qtys.get(i));
        }
        return map;
    }

}
