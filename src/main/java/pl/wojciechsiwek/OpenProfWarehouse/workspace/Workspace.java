package pl.wojciechsiwek.OpenProfWarehouse.workspace;


import org.springframework.stereotype.Component;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderetItemsExtendedLengthComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
public class Workspace {


    private List<SingleProfileNested> profileNestedList;
    private List<OrderedItemsExtended> orderedItemsExtendedList;
    private List<StockItem> stockItemList;
    private List<StockItem> remnantList;
    private double partDistance;
    private double profileMargin;
    private double minRemnantLength;

    Workspace(List<StockItem> stockItemList, List<OrderedItemsExtended> orderedItemsExtendedList) {
        this.orderedItemsExtendedList = orderedItemsExtendedList;
        this.stockItemList = stockItemList;
        this.remnantList = new ArrayList<>();
        this.profileNestedList = new ArrayList<>();
    }

    void addToRemnantList(StockItem remnant){
        this.remnantList.add(remnant);
    }

    public List<StockItem> getRemnantList() {
        return remnantList;
    }

    void setRemnantList(List<StockItem> remnantList) {
        this.remnantList = remnantList;
    }

    public double getMinRemnantLength() {
        return minRemnantLength;
    }

    void setMinRemnantLength(double minRemnantLength) {
        this.minRemnantLength = minRemnantLength;
    }

    void setPartDistance(double partDistance) {
        this.partDistance = partDistance;
    }

    void setProfileMargin(double profileMargin) {
        this.profileMargin = profileMargin;
    }

    public double getPartDistance() {
        return partDistance;
    }

    public double getProfileMargin() {
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

    void sortOrderedItemsByLengthDecreasing(){
        Comparator comparator = new OrderetItemsExtendedLengthComparator().reversed();
        this.orderedItemsExtendedList.sort(comparator);
    }


    void sortProfilesByLengthAscending() {
        Comparator comparator = new StockItemLengthComparator();
        this.getStockItemList().sort(comparator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return Double.compare(workspace.partDistance, partDistance) == 0 && Double.compare(workspace.profileMargin, profileMargin) == 0 && Double.compare(workspace.minRemnantLength, minRemnantLength) == 0 && Objects.equals(profileNestedList, workspace.profileNestedList) && Objects.equals(orderedItemsExtendedList, workspace.orderedItemsExtendedList) && Objects.equals(stockItemList, workspace.stockItemList) && Objects.equals(remnantList, workspace.remnantList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileNestedList, orderedItemsExtendedList, stockItemList, remnantList, partDistance, profileMargin, minRemnantLength);
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "profileNestedList=" + profileNestedList +
                ", orderedItemsExtendedList=" + orderedItemsExtendedList +
                ", stockItemList=" + stockItemList +
                ", remnantList=" + remnantList +
                ", partDistance=" + partDistance +
                ", profileMargin=" + profileMargin +
                ", minRemnantLength=" + minRemnantLength +
                '}';
    }
}
