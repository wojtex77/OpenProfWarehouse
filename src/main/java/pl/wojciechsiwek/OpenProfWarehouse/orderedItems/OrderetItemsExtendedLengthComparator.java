package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import java.util.Comparator;

public class OrderetItemsExtendedLengthComparator implements Comparator<OrderedItemsExtended> {
    @Override
    public int compare(OrderedItemsExtended o1, OrderedItemsExtended o2) {
        return Double.compare(o1.getProfileLength(), o2.getProfileLength());
    }
}
