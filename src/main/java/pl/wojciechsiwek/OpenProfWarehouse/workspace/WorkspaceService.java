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

    List<StockItem> getStockToUseBySignatures(List<String> stockSignaturesToUse) {
        return stockRepository.findBySignatureInOrderByProfileLengthDesc(stockSignaturesToUse);
    }

    List<OrderedItemsExtended> getOrderedItemsExtendedByIds(List<Integer> orderedItemsIds) {
        return orderedItemsService.extendListOfOrderedItems(orderedItemsRepository.findByIdInOrderByQtyDesc(orderedItemsIds));
    }

    Workspace doNesting(Workspace workspace) throws IllegalStateException {

        Comparator comparator = new OrderetItemsExtendedLengthComparator().reversed();
        workspace.getOrderedItemsExtendedList().sort(comparator);

        List<SingleProfileNested> list = new ArrayList<>();
        if (workspace.getStockItemList() == null || workspace.getOrderedItemsExtendedList() == null) {
            throw new IllegalStateException("Parts or profiles lists to nesting can not be null");
        }
        int i = 0;

        while (i < workspace.getStockItemList().size() && workspace.getOrderedItemsExtendedList().size() > 0) {
            while (workspace.getStockItemList().get(i).getAvailableQty() > 0 && workspace.getOrderedItemsExtendedList().size() > 0) {
                SingleProfileNested singleProfileNested = nestOnSingleStockItem(workspace.getStockItemList().get(i), workspace.getOrderedItemsExtendedList(), workspace);
                if (singleProfileNested != null) {
                    boolean isUnique = true;
                    for (SingleProfileNested profileFromList : list) {
                        if (profileFromList.getItemsOnProfile().equals(singleProfileNested.getItemsOnProfile()) && profileFromList.getStockItem().equals(singleProfileNested.getStockItem())) {
                            profileFromList.increaseRepetition();
                            isUnique = false;
                        }
                    }
                    if (isUnique) list.add(singleProfileNested);
                    workspace.getStockItemList().get(i).decreaseAvailableQty();
                }
            }
            i++;
        }
        workspace.setProfileNestedList(list);

        workspace.getOrderedItemsExtendedList().forEach(item -> {
            item.setNestedQty(item.getQty() - item.getToNestQty());
        });

        return workspace;
    }

    private SingleProfileNested nestOnSingleStockItem(StockItem stockItem, List<OrderedItemsExtended> orderedItemsList, Workspace workspace) {

        double availableLength = stockItem.getProfileLength() - (2 * workspace.getProfileMargin());
        float shortestPartLength = orderedItemsList.stream().min(Comparator.comparing(OrderedItemsExtended::getProfileLength)).get().getProfileLength();
        List<OrderedItemsExtended> fullyNestedItems = new ArrayList<>();

        List<OrderedItemsExtended> partsOnProfile = new ArrayList<>();

        for (int i = 0; i < orderedItemsList.size(); i++) { //Iterating each part on sigle profile up the profile is fully used

            int qty = 0; // counter how many orderedItems can be nested on profile
            while ((availableLength >= orderedItemsList.get(i).getProfileLength()) && orderedItemsList.get(i).getToNestQty() > 0) { //petla sprawdzająca ile tej samej części zmieści się na profilu
                if (orderedItemsList.get(i).getProfileLength() <= stockItem.getProfileLength()) {
                    qty++;
                    availableLength = availableLength - orderedItemsList.get(i).getProfileLength() - workspace.getPartDistance();
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
        return new SingleProfileNested(stockItem, partsOnProfile);
    }
}
