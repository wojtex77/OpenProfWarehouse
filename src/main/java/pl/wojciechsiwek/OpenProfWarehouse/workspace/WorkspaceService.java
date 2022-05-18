package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsService;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderetItemsExtendedLengthComparator;

import java.util.*;

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


    List<SingleProfileNested> doNesting(List<String> stockSignaturesToUse, List<Integer> orderedItemsIds) throws IllegalStateException {

        List<StockItem> stockToUse = stockRepository.findBySignatureInOrderByProfileLengthDesc(stockSignaturesToUse);
        List<OrderedItemsExtended> orderedItemsList = orderedItemsService.extendListOfOrderedItems(orderedItemsRepository.findByIdInOrderByQtyDesc(orderedItemsIds));
        Comparator comparator = new OrderetItemsExtendedLengthComparator().reversed();
        orderedItemsList.sort(comparator);

        List<SingleProfileNested> list = new ArrayList<>();
        if (stockToUse == null || orderedItemsList == null) {
            throw new IllegalStateException("Parts or profiles lists to nesting can not be null");
        }
        int i = 0;

        while (i < stockSignaturesToUse.size() && orderedItemsList.size() > 0) {
            list.add(nestOnSingleStockItem(stockToUse.get(i), orderedItemsList));
            i++;
        }
        return list;
    }

    private SingleProfileNested nestOnSingleStockItem(StockItem stockItem, List<OrderedItemsExtended> orderedItemsList) {
        double availableLength = stockItem.getProfileLength();
        float shortestPartLength = orderedItemsList.stream().min(Comparator.comparing(OrderedItemsExtended::getProfileLength)).get().getProfileLength();
        List<OrderedItemsExtended> fullyNestedItems = new ArrayList<>();

        Map partsOnProfile = new HashMap<Integer, Integer>();

        for (int i = 0; i < orderedItemsList.size(); i++) { //Druga pętla iterująca po każdej części w liście aż do momentu gdy już nic się nie zmieści.
            int qty = 0; // licznik ile sztuk zamówionej częsci zmieści się na profilu
            while ((availableLength >= orderedItemsList.get(i).getProfileLength()) && orderedItemsList.get(i).getQty() > 0) { //petla sprawdzająca ile tej samej części zmieści się na profilu
                if (orderedItemsList.get(i).getProfileLength() <= stockItem.getProfileLength()) {
                    qty++;
                    availableLength = availableLength - orderedItemsList.get(i).getProfileLength();
                    orderedItemsList.get(i).decreaseQty();
                }
            }
            if (qty > 0) partsOnProfile.put(orderedItemsList.get(i).getId(), qty);
            if (orderedItemsList.get(i).getQty() == 0) fullyNestedItems.add(orderedItemsList.get(i));
            if (availableLength < shortestPartLength) break;
        }
        if (fullyNestedItems != null) {
            for (int i = 0; i < fullyNestedItems.size(); i++) {
                orderedItemsList.remove(fullyNestedItems.get(i));
            }
        }
        return new SingleProfileNested(stockItem.getSignature(), partsOnProfile);
    }
}
