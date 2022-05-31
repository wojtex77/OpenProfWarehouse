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
    private final OrderedItemRepository orderedItemsRepository;
    private final PartRepository partRepository;

    public OrderedItemsService(OrderedItemRepository repository, PartRepository partRepository) {
        this.orderedItemsRepository = repository;
        this.partRepository = partRepository;
    }

    public void saveItems(String orderNumber, Map<Integer, Integer> map) {
        List<OrderedItem> items = new LinkedList<>();
        map.forEach((partId, qty) -> {
            items.add(new OrderedItem(orderNumber, partId, qty));
        });
        orderedItemsRepository.saveAll(items);
    }

    public List<OrderedItemsExtended> getOrderedItemsExtendedByOrder(String orderNumber) {
        List<OrderedItemsExtended> itemsExtended = new ArrayList<>();
        List<OrderedItem> simpleItems = orderedItemsRepository.findByOrderNumberEquals(orderNumber);
        simpleItems.forEach(simpleItem -> {
            Part part = partRepository.findById(simpleItem.getPartId()).get();
            itemsExtended.add(new OrderedItemsExtended(simpleItem.getId(), part.getId(), part.getPartName(), simpleItem.getQty(), simpleItem.getToNestQty(), simpleItem.getNestedQty(), part.getProfile(), part.getProfileLength(), part.getMaterial(), simpleItem.getOrderNumber(), part.getArticle(), part.getDrawing(), part.getWeight()));
        });
        return itemsExtended;
    }

    public void saveChanges(String orderNumber, List<Integer> partIds, List<Integer> ammountOfParts, List<Integer> itemIds, List<Integer> idsToDelete) {

        List<OrderedItem> itemsToSave = new LinkedList<OrderedItem>();
        for (int i = 0; i < partIds.size(); i++) {
            OrderedItem itemFromDB = null;
            if (itemIds.get(i) != null){
                int u= 32;
                itemFromDB = orderedItemsRepository.findById(itemIds.get(i)).get();
            }


            if (itemFromDB == null) {
                itemsToSave.add(new OrderedItem(itemIds.get(i), orderNumber, partIds.get(i), ammountOfParts.get(i)));
            } else {
                itemFromDB.setQty(ammountOfParts.get(i));
                itemFromDB.setToNestQty(ammountOfParts.get(i) - itemFromDB.getNestedQty());
                itemsToSave.add(itemFromDB);
            }
        }
        if (itemsToSave.size() <= 1) orderedItemsRepository.save(itemsToSave.get(0));
        else orderedItemsRepository.saveAll(itemsToSave);

        if (idsToDelete.size() > 1) {
            idsToDelete.remove(0);
            orderedItemsRepository.deleteAllById(idsToDelete);
        }
    }

    public List<OrderedItemsExtended> getAllOrderedItems() {
        List<OrderedItemsExtended> itemsExtended = new ArrayList<>();
        List<OrderedItem> simpleItems = orderedItemsRepository.findAll();
        simpleItems.forEach(simpleItem -> {
            Part part = partRepository.findById(simpleItem.getPartId()).get();
            itemsExtended.add(new OrderedItemsExtended(simpleItem.getId(), part.getId(), part.getPartName(), simpleItem.getQty(), simpleItem.getToNestQty(), simpleItem.getNestedQty(), part.getProfile(), part.getProfileLength(), part.getMaterial(), simpleItem.getOrderNumber(), part.getArticle(), part.getDrawing(), part.getWeight()));
        });
        return itemsExtended;
    }


    public OrderedItemsExtended getOrderedItem(int id) {
        OrderedItem simpleItem = orderedItemsRepository.findById(id).get();
        return extendOrderedItem(simpleItem);
    }

    public OrderedItemsExtended extendOrderedItem(OrderedItem simpleItem) {
        Part part = partRepository.findById(simpleItem.getPartId()).get();
        return new OrderedItemsExtended(simpleItem.getId(), part.getId(), part.getPartName(), simpleItem.getQty(), simpleItem.getToNestQty(), simpleItem.getNestedQty(), part.getProfile(), part.getProfileLength(), part.getMaterial(), simpleItem.getOrderNumber(), part.getArticle(), part.getDrawing(), part.getWeight());

    }

    public List<OrderedItemsExtended> extendListOfOrderedItems(List<OrderedItem> orderedItems) {
        List<OrderedItemsExtended> extendedItems = new ArrayList<>();
        orderedItems.forEach(item -> {
            extendedItems.add(extendOrderedItem(item));
        });
        return extendedItems;
    }

}
