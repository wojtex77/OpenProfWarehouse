package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.parts.Part;
import pl.wojciechsiwek.OpenProfWarehouse.parts.PartRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class OrderedItemsService {
    private final OrderedItemsRepository orderedItemsRepository;
    private final PartRepository partRepository;

    public OrderedItemsService(OrderedItemsRepository repository, PartRepository partRepository) {
        this.orderedItemsRepository = repository;
        this.partRepository = partRepository;
    }

    public void saveItems(String orderNumber, Map<Integer, Integer> map) {
        List<OrderedItems> items = new LinkedList<>();
        map.forEach((partId, qty) -> {
            items.add(new OrderedItems(orderNumber, partId, qty));
        });
        orderedItemsRepository.saveAll(items);
    }

    public List<OrderedItemsExtended> getOrderedItemsExtendedByOrder(String orderNumber) {
        List<OrderedItemsExtended> itemsExtended = new ArrayList<>();
        List<OrderedItems> simpleItems = orderedItemsRepository.findByOrderNumberEquals(orderNumber);
        simpleItems.forEach(simpleItem -> {
            Part part = partRepository.findById(simpleItem.getPartId()).get();
            itemsExtended.add(new OrderedItemsExtended(simpleItem.getId(), part.getId(), part.getPartName(), simpleItem.getQty(), part.getProfile(), part.getProfileLength(), part.getMaterial()));
        });
        return itemsExtended;
    }

    public void saveChanges(String orderNumber, List<Integer> partIds, List<Integer> ammountOfParts, List<Integer> itemIds, List<Integer> idsToDelete) {

        List<OrderedItems> items = new LinkedList<OrderedItems>();
        for (int i = 0; i < partIds.size(); i++) {
            items.add(new OrderedItems(itemIds.get(i), orderNumber, partIds.get(i), ammountOfParts.get(i)));
        }
        if (items.size() <= 1) orderedItemsRepository.save(items.get(0));
        else orderedItemsRepository.saveAll(items);

        if (idsToDelete.size() > 1) {
            idsToDelete.remove(0);
            orderedItemsRepository.deleteAllById(idsToDelete);
        }
    }

    public List<OrderedItemsExtended> getAllOrderedItems() {
        List<OrderedItemsExtended> itemsExtended = new ArrayList<>();
        List<OrderedItems> simpleItems = orderedItemsRepository.findAll();
        simpleItems.forEach(simpleItem -> {
            Part part = partRepository.findById(simpleItem.getPartId()).get();
            itemsExtended.add(new OrderedItemsExtended(
                    simpleItem.getId(), part.getId(), part.getPartName(), simpleItem.getQty(),
                    part.getProfile(), part.getProfileLength(), part.getMaterial(), simpleItem.getOrderNumber(), part.getArticle(), part.getDrawing(), part.getWeight()
            ));
        });
        return itemsExtended;
    }


    public OrderedItemsExtended getOrderedItem(int id) {
        OrderedItems simpleItem = orderedItemsRepository.findById(id).get();
        Part part = partRepository.findById(simpleItem.getPartId()).get();
        return new OrderedItemsExtended(
                simpleItem.getId(), part.getId(), part.getPartName(), simpleItem.getQty(),
                part.getProfile(), part.getProfileLength(), part.getMaterial(),
                simpleItem.getOrderNumber(), part.getArticle(), part.getDrawing(),
                part.getWeight());
    }
}
