package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsService;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderetItemsExtendedLengthComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class WorkspaceService {

    private final MaterialStockRepository stockRepository;
    private final OrderedItemsRepository orderedItemsRepository;
    private final OrderedItemsService orderedItemsService;

    public WorkspaceService(MaterialStockRepository stockRepository, OrderedItemsRepository orderedItemsRepository, OrderedItemsService orderedItemsService) {
        this.stockRepository = stockRepository;
        this.orderedItemsRepository = orderedItemsRepository;
        this.orderedItemsService = orderedItemsService;
    }

    List<StockItem> getStockToUse(List<String> stockSignaturesToUse) {
        return stockRepository.findBySignatureInOrderByProfileLengthDesc(stockSignaturesToUse);
    }

    List<OrderedItemsExtended> getOrderedItemsExtended(List<Integer> orderedItemsIds) {
        return orderedItemsService.extendListOfOrderedItems(orderedItemsRepository.findByIdInOrderByQtyDesc(orderedItemsIds));
    }

    List<SingleProfileNested> doNesting(List<String> stockSignaturesToUse, List<Integer> orderedItemsIds) throws IllegalStateException {

        List<StockItem> stockToUse = getStockToUse(stockSignaturesToUse);
        List<OrderedItemsExtended> orderedItemsList = getOrderedItemsExtended(orderedItemsIds);

        Comparator comparator = new OrderetItemsExtendedLengthComparator().reversed();
        orderedItemsList.sort(comparator);

        List<SingleProfileNested> list = new ArrayList<>();
        if (stockToUse == null || orderedItemsList == null) {
            throw new IllegalStateException("Parts or profiles lists to nesting can not be null");
        }
        int i = 0;

        while (i < stockSignaturesToUse.size() && orderedItemsList.size() > 0) {
            while (stockToUse.get(i).getAvailableQty() > 0 && orderedItemsList.size() > 0) {
                SingleProfileNested singleProfileNested = nestOnSingleStockItem(stockToUse.get(i), orderedItemsList);
                if (singleProfileNested != null) {
                    list.add(singleProfileNested);
                    stockToUse.get(i).decreaseAvailableQty();
                }
            }
            i++;
        }
        return list;
    }

    private SingleProfileNested nestOnSingleStockItem(StockItem stockItem, List<OrderedItemsExtended> orderedItemsList) {
        double availableLength = stockItem.getProfileLength();
        float shortestPartLength = orderedItemsList.stream().min(Comparator.comparing(OrderedItemsExtended::getProfileLength)).get().getProfileLength();
        List<OrderedItemsExtended> fullyNestedItems = new ArrayList<>();

        List<OrderedItemsExtended> partsOnProfile = new ArrayList<>();

        for (int i = 0; i < orderedItemsList.size(); i++) { //Iterating each part on sigle profile up the profile is fully used

            int qty = 0; // counter how many orderedItems can be nested on profile
            while ((availableLength >= orderedItemsList.get(i).getProfileLength()) && orderedItemsList.get(i).getToNestQty() > 0) { //petla sprawdzająca ile tej samej części zmieści się na profilu
                if (orderedItemsList.get(i).getProfileLength() <= stockItem.getProfileLength()) {
                    qty++;
                    availableLength = availableLength - orderedItemsList.get(i).getProfileLength();
                    orderedItemsList.get(i).nestItem();
                }
            }
            if (qty > 0) {
                partsOnProfile.add(new OrderedItemsExtended(orderedItemsList.get(i)));
                orderedItemsList.get(i).setNestedQty(0);
            }
            if (orderedItemsList.get(i).getToNestQty() == 0) fullyNestedItems.add(orderedItemsList.get(i));
            if (availableLength < shortestPartLength) break;
        }
        if (fullyNestedItems != null) { //remove nested item from orderedItems List
            for (int i = 0; i < fullyNestedItems.size(); i++) {
                orderedItemsList.remove(fullyNestedItems.get(i));
            }
        }
        return new SingleProfileNested(stockItem.getSignature(), partsOnProfile);
    }
}
