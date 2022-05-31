package pl.wojciechsiwek.OpenProfWarehouse.reservations;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemRepository;
import pl.wojciechsiwek.OpenProfWarehouse.workspace.SingleProfileNested;
import pl.wojciechsiwek.OpenProfWarehouse.workspace.Workspace;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final NestedOrderedItemRepository nestedOrderedItemRepository;
    private final OrderedItemRepository orderedItemsRepository;

    public ReservationService(ReservationRepository reservationRepository, NestedOrderedItemRepository nestedOrderedItemRepository, OrderedItemRepository orderedItemsRepository) {
        this.reservationRepository = reservationRepository;
        this.nestedOrderedItemRepository = nestedOrderedItemRepository;
        this.orderedItemsRepository = orderedItemsRepository;
    }

    public Workspace makeReservation(Workspace workspace) {
        List<SingleProfileNested> profileNestedList = workspace.getProfileNestedList();
        profileNestedList.forEach(singleProfileNested -> {
            String signature = singleProfileNested.getStockItem().getSignature();
            Integer repetition = singleProfileNested.getRepetition();
            StockItemReservation stockItemReservation = new StockItemReservation(signature,repetition);
            stockItemReservation = reservationRepository.save(stockItemReservation);

            List<OrderedItemsExtended> orderedItemExtendedList = singleProfileNested.getItemsOnProfile();
            StockItemReservation finalStockItemReservation = stockItemReservation;
            orderedItemExtendedList.forEach(orderedItemsExtended -> {
                NestedOrderedItem nestedOrderedItem = new NestedOrderedItem(Math.toIntExact(finalStockItemReservation.getId()),orderedItemsExtended.getId(),orderedItemsExtended.getNestedQty());
                nestedOrderedItemRepository.save(nestedOrderedItem);
                OrderedItem orderedItemFromDB = orderedItemsRepository.getById(orderedItemsExtended.getId());
                orderedItemFromDB.changeNestedQtyByValue(orderedItemsExtended.getNestedQty()*finalStockItemReservation.getRepetition());
                orderedItemsRepository.save(orderedItemFromDB);
            });
        });

        workspace.getOrderedItemsExtendedList().forEach(orderedItemsExtended -> {
            OrderedItem item = orderedItemsRepository.getById(orderedItemsExtended.getId());
            item.recalculateToNestQty();
            orderedItemsRepository.save(item);
        });

        return workspace;
    }

}
