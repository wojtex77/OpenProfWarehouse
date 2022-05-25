package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;

import java.util.List;
import java.util.Objects;

public class SingleProfileNested {
    private StockItem stockItem;
    private List<OrderedItemsExtended> itemsOnProfile;
    private int repetition;

    SingleProfileNested(StockItem profileSignature, List itemsOnProfile) {
        this.stockItem = profileSignature;
        this.itemsOnProfile = itemsOnProfile;
        this.repetition = 1;
    }

    public int getRepetition() {
        return repetition;
    }

    void increaseRepetition(){
        repetition++;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }

    public List getItemsOnProfile() {
        return itemsOnProfile;
    }

    public void setItemsOnProfile(List itemsOnProfile) {
        this.itemsOnProfile = itemsOnProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleProfileNested that = (SingleProfileNested) o;
        return repetition == that.repetition && Objects.equals(stockItem, that.stockItem) && Objects.equals(itemsOnProfile, that.itemsOnProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockItem, itemsOnProfile, repetition);
    }

    @Override
    public String toString() {
        return "SingleProfileNested{" +
                "stockItem=" + stockItem +
                ", itemsOnProfile=" + itemsOnProfile +
                ", repetition=" + repetition +
                '}';
    }
}
