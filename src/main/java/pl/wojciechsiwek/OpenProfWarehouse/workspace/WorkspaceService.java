package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsService;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderetItemsExtendedLengthComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class WorkspaceService {

    private final MaterialStockRepository stockRepository;
    private final OrderedItemRepository orderedItemsRepository;
    private final OrderedItemsService orderedItemsService;

    public WorkspaceService(MaterialStockRepository stockRepository, OrderedItemRepository orderedItemsRepository, OrderedItemsService orderedItemsService) {
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

    Workspace startNesting(Workspace workspace) throws IllegalStateException {

        Comparator comparator = new OrderetItemsExtendedLengthComparator().reversed();
        workspace.getOrderedItemsExtendedList().sort(comparator);

        List<SingleProfileNested> list = new ArrayList<>();
        if (workspace.getStockItemList() == null || workspace.getOrderedItemsExtendedList() == null) {
            throw new IllegalStateException("Parts or profiles lists to nesting can not be null");
        }

        nest(workspace, list);
        workspace.setProfileNestedList(list);

        workspace.getOrderedItemsExtendedList().forEach(item -> {
            item.setNestedQty(item.getQty() - item.getToNestQty());
        });
        assignNewSignatures(workspace);
        return workspace;
    }

    private void nest(Workspace workspace, List<SingleProfileNested> list) {
        int i = 0;
        while (i < workspace.getStockItemList().size() && workspace.getOrderedItemsExtendedList().size() > 0) {
            int availableQtyForLoop = workspace.getStockItemList().get(i).getAvailableQty();
            while (availableQtyForLoop > 0 && workspace.getOrderedItemsExtendedList().size() > 0) {
                SingleProfileNested singleProfileNested = nestOnSingleStockItem(workspace.getStockItemList().get(i), workspace.getOrderedItemsExtendedList(), workspace);
                if (singleProfileNested.getItemsOnProfile().size() > 0) {
                    boolean isUnique = true;
                    for (SingleProfileNested profileFromList : list) {
                        List<OrderedItemsExtended> list1 = profileFromList.getItemsOnProfile();
                        List<OrderedItemsExtended> list2 = singleProfileNested.getItemsOnProfile();

                        if (profileFromList.getItemsOnProfile().equals(singleProfileNested.getItemsOnProfile()) && profileFromList.getStockItem().equals(singleProfileNested.getStockItem())) {
                            profileFromList.increaseRepetition();
                            isUnique = false;
                        }
                    }
                    if (isUnique && singleProfileNested.getItemsOnProfile().size() != 0) {
                        list.add(singleProfileNested);
                        workspace.getStockItemList().get(i).decreaseAvailableQty();
                    }
                }
                availableQtyForLoop--;
            }
            i++;
        }
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

        checkRemnantUsability(stockItem, workspace, availableLength);

        return new SingleProfileNested(stockItem, partsOnProfile);
    }

    private void checkRemnantUsability(StockItem stockItem, Workspace workspace, double availableLength) {
        if ((availableLength + 2 * workspace.getProfileMargin()) >= workspace.getMinRemnantLength()) {
            availableLength = availableLength + (2 * workspace.getProfileMargin());
            StockItem newRemnant = new StockItem(stockItem, availableLength);

            boolean isUniqueRemnant = true;
            for (StockItem remnant : workspace.getRemnantList()) {
                if (remnant.equalsExceptQuantites(newRemnant)) {
                    remnant.increaseBothQuantities();
                    isUniqueRemnant = false;
                }
            }
            if (isUniqueRemnant) workspace.addToRemnantList(newRemnant);

        }
    }

    private void assignNewSignatures(Workspace workspace) {
        List<StockItem> remnants = workspace.getRemnantList();
        for (StockItem remnant : remnants) {
            Integer i = 1;
            String currentSignature = remnant.getSignature();
            while (stockRepository.existsBySignatureEquals(currentSignature)) {
                String toVerifySignature = currentSignature;
                currentSignature = currentSignature + "-" + i.toString();
                for (StockItem remnant2 : remnants) {
                    while (remnant2.getSignature().equals(currentSignature)) {
                        i++;
                        currentSignature = toVerifySignature + "-" + i.toString();
                    }
                }
            }
            remnant.setSignature(currentSignature);
        }
    }

}
