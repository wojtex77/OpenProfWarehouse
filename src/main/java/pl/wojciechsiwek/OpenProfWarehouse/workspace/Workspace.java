package pl.wojciechsiwek.OpenProfWarehouse.workspace;


import org.springframework.stereotype.Component;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;

import java.util.List;
import java.util.Objects;

@Component
public class Workspace {


    private List<SingleProfileNested> profileNestedList;
    private List<OrderedItemsExtended> orderedItemsExtendedList;
    private List<StockItem> stockItemList;
    private double partDistance;
    private double profileMargin;

    Workspace(List<StockItem> stockItemList, List<OrderedItemsExtended> orderedItemsExtendedList) {
        this.orderedItemsExtendedList = orderedItemsExtendedList;
        this.stockItemList = stockItemList;
    }

    void setPartDistance(double partDistance) {
        this.partDistance = partDistance;
    }

    void setProfileMargin(double profileMargin) {
        this.profileMargin = profileMargin;
    }

    double getPartDistance() {
        return partDistance;
    }

    double getProfileMargin() {
        return profileMargin;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return Double.compare(workspace.partDistance, partDistance) == 0 && Double.compare(workspace.profileMargin, profileMargin) == 0 && Objects.equals(profileNestedList, workspace.profileNestedList) && Objects.equals(orderedItemsExtendedList, workspace.orderedItemsExtendedList) && Objects.equals(stockItemList, workspace.stockItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileNestedList, orderedItemsExtendedList, stockItemList, partDistance, profileMargin);
    }

    @Override
    public String toString() {
        return "Workspace{" + "profileNestedList=" + profileNestedList + ", orderedItemsExtendedList=" + orderedItemsExtendedList + ", stockItemList=" + stockItemList + ", partDistance=" + partDistance + ", profileMargin=" + profileMargin + '}';
    }
}
