package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsService;

import java.util.ArrayList;
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

        workspace.sortOrderedItemsByLengthDecreasing();

        if (workspace.getStockItemList() == null || workspace.getOrderedItemsExtendedList() == null) {
            throw new IllegalStateException("Parts or profiles lists to nesting can not be null");
        }

        nest(workspace);


//        workspace.getOrderedItemsExtendedList().forEach(item -> {
//            item.setNestedQty(item.getQty() - item.getToNestQty());
//        });
//        assignNewSignatures(workspace);
        return workspace;
    }

    /*
        private void oldnest(Workspace workspace) {
            List<SingleProfileNested> list = new ArrayList<>();

            int i = 0;
            while (i < workspace.getStockItemList().size() && workspace.getOrderedItemsExtendedList().size() > 0) {
                int availableQtyForLoop = workspace.getStockItemList().get(i).getAvailableQty();
                while (availableQtyForLoop > 0 && workspace.getOrderedItemsExtendedList().size() > 0) {
                    SingleProfileNested singleProfileNested = nestOnSingleStockItem(workspace.getStockItemList().get(i), workspace.getOrderedItemsExtendedList(), workspace.getProfileMargin(), workspace.getPartDistance());

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
            workspace.setProfileNestedList(list);
        }
    */
    private void nest(Workspace workspace) {
        workspace.sortProfilesByLengthAscending();
        workspace.sortOrderedItemsByLengthDecreasing();

        workspace.getStockItemList().forEach(stockItem -> {
            while (stockItem.getAvailableQty() > 0) {
                SingleProfileNested profileNested = nestOnSingleStockItem(stockItem, workspace);
                if (profileNested != null) {
                    checkProfileNestedDuplication(workspace, profileNested);
                    stockItem.decreaseAvailableQty();
                }
            }
        });


    }

    private void checkProfileNestedDuplication(Workspace workspace, SingleProfileNested profileNested) {

        if (workspace.getProfileNestedList().size() == 0) {
            workspace.getProfileNestedList().add(profileNested);
        } else {

            int indexOfSameProfile = -1;

            for (SingleProfileNested singleProfileNested : workspace.getProfileNestedList()) {
                if (singleProfileNested.equalsExceptRepetition(profileNested))
                    indexOfSameProfile = workspace.getProfileNestedList().indexOf(singleProfileNested);
            }
            if (indexOfSameProfile >= 0) workspace.getProfileNestedList().get(indexOfSameProfile).increaseRepetition();
            else if (profileNested.getItemsOnProfile().size() > 0) workspace.getProfileNestedList().add(profileNested);
        }


    }

    private SingleProfileNested nestOnSingleStockItem(StockItem stockItem, Workspace workspace) {
        List<OrderedItemsExtended> itemsOnProfile = new ArrayList<>();

        final double[] tempAvailableLength = {stockItem.getProfileLength() - (2 * workspace.getProfileMargin())};

        workspace.getOrderedItemsExtendedList().forEach(orderedItemsExtended -> {
            OrderedItemsExtended itemForCurrentStockItem = new OrderedItemsExtended(orderedItemsExtended);
            while (tempAvailableLength[0] >= orderedItemsExtended.getProfileLength() && itemForCurrentStockItem.getToNestQty() > 0) {
                if (itemsOnProfile.contains(itemForCurrentStockItem)) itemForCurrentStockItem.increaseRepetition();
                else {
                    itemsOnProfile.add(itemForCurrentStockItem);
                    itemForCurrentStockItem.decreaseToNestQty();
                }
                tempAvailableLength[0] = tempAvailableLength[0] - workspace.getPartDistance() - itemForCurrentStockItem.getProfileLength();
            }
            orderedItemsExtended.setNestedQty(itemForCurrentStockItem.getNestedQty());
            orderedItemsExtended.setToNestQty(itemForCurrentStockItem.getToNestQty());
        });

        if (itemsOnProfile != null) {
            return new SingleProfileNested(stockItem, itemsOnProfile);
        } else return null;
    }


    /*
        private SingleProfileNested oldnestOnSingleStockItem(StockItem stockItem, List<OrderedItemsExtended> orderedItemsList, double profileMargin, double partDistance) {

            double availableLength = stockItem.getProfileLength() - (2 * profileMargin);
            float shortestPartLength = orderedItemsList.stream().min(Comparator.comparing(OrderedItemsExtended::getProfileLength)).get().getProfileLength();
            List<OrderedItemsExtended> fullyNestedItems = new ArrayList<>();

            List<OrderedItemsExtended> partsOnProfile = new ArrayList<>();

            for (int i = 0; i < orderedItemsList.size(); i++) { //Iterating each part on sigle profile up the profile is fully used

                int qty = 0; // counter how many orderedItems can be nested on profile
                while ((availableLength >= orderedItemsList.get(i).getProfileLength()) && orderedItemsList.get(i).getToNestQty() > 0) { //petla sprawdzająca ile tej samej części zmieści się na profilu
                    if (orderedItemsList.get(i).getProfileLength() <= stockItem.getProfileLength()) {
                        qty++;
                        availableLength = availableLength - orderedItemsList.get(i).getProfileLength() - partDistance;
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
    */
    private void checkRemnantUsability(StockItem stockItem, Workspace workspace, double availableLength, double minRemnantLength) {
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
