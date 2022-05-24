package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.stereotype.Component;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;

import java.util.List;

@Component
public class Workspace {


    private List<SingleProfileNested> profileNestedList;
    private List<OrderedItemsExtended> orderedItemsExtendedList;
    private List<StockItem> stockItemList;

    Workspace(List<StockItem> stockItemList, List<OrderedItemsExtended> orderedItemsExtendedList) {
        this.orderedItemsExtendedList = orderedItemsExtendedList;
        this.stockItemList = stockItemList;
    }

    public List<SingleProfileNested> getProfileNestedList() {
        return profileNestedList;
    }

    void setProfileNestedList(List<SingleProfileNested> profileNestedList) {
        this.profileNestedList = profileNestedList;
    }

    public List<OrderedItemsExtended> getOrderedItemsExtendedList() {
        return orderedItemsExtendedList;
    }

    void setOrderedItemsExtendedList(List<OrderedItemsExtended> orderedItemsExtendedList) {
        this.orderedItemsExtendedList = orderedItemsExtendedList;
    }

    public List<StockItem> getStockItemList() {
        return stockItemList;
    }

    void setStockItemList(List<StockItem> stockItemList) {
        this.stockItemList = stockItemList;
    }
}
