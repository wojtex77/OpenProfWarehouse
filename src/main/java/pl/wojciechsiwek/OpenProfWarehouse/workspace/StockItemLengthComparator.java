package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;

import java.util.Comparator;

public class StockItemLengthComparator implements Comparator<StockItem> {
    @Override
    public int compare(StockItem o1, StockItem o2) {
        return Double.compare(o1.getProfileLength(), o2.getProfileLength());
    }
}
