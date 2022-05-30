package pl.wojciechsiwek.OpenProfWarehouse.reservations;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItems;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsRepository;
import pl.wojciechsiwek.OpenProfWarehouse.workspace.SingleProfileNested;
import pl.wojciechsiwek.OpenProfWarehouse.workspace.Workspace;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final NestedOrderedItemRepository nestedOrderedItemRepository;
    private final OrderedItemsRepository orderedItemsRepository;

    public ReservationService(ReservationRepository reservationRepository, NestedOrderedItemRepository nestedOrderedItemRepository, OrderedItemsRepository orderedItemsRepository) {
        this.reservationRepository = reservationRepository;
        this.nestedOrderedItemRepository = nestedOrderedItemRepository;
        this.orderedItemsRepository = orderedItemsRepository;
    }

    public Workspace makeReservation(Workspace workspace) {
        List<SingleProfileNested> profileNestedList = workspace.getProfileNestedList();
        profileNestedList.forEach(singleProfileNested -> {
            String signature = singleProfileNested.getStockItem().getSignature();
            Integer repetition = singleProfileNested.getRepetition();
            Reservation reservation = new Reservation(signature,repetition);
            reservation = reservationRepository.save(reservation);

            List<OrderedItemsExtended> orderedItemsExtendedList = singleProfileNested.getItemsOnProfile();
            Reservation finalReservation = reservation;
            orderedItemsExtendedList.forEach(orderedItemsExtended -> {
                NestedOrderedItem nestedOrderedItem = new NestedOrderedItem(Math.toIntExact(finalReservation.getId()),orderedItemsExtended.getId(),orderedItemsExtended.getNestedQty());
                nestedOrderedItemRepository.save(nestedOrderedItem);
                OrderedItems orderedItems = new OrderedItems(orderedItemsExtended.getId(), orderedItemsExtended.getOrderNumber(),orderedItemsExtended.getPartId(), orderedItemsExtended.getQty());
                orderedItems.changeNestedQtyByValue(orderedItemsExtended.getNestedQty());
                orderedItemsRepository.save(orderedItems);
            });
        });

        return workspace;
    }

}
