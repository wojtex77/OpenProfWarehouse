package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;

import java.util.List;
import java.util.Objects;

public class SingleProfileNested {
    private StockItem stockItem;
    private List<OrderedItemsExtended> itemsOnProfile;
    private int repetition;

    SingleProfileNested(StockItem stockItem, List itemsOnProfile) {
        this.stockItem = stockItem;
        this.itemsOnProfile = itemsOnProfile;
        this.repetition = 1;
    }

    public int getRepetition() {
        return repetition;
    }

    void increaseRepetition() {
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

    public boolean equalsExceptRepetition(SingleProfileNested o) {
        if (this == o) return true;
        if (this.stockItem.getSignature() != o.stockItem.getSignature()) return false;
        if (this.itemsOnProfile.size() != o.itemsOnProfile.size()) return false;
        if (this.itemsOnProfile.size() != 0) {
            for (int i = 0; i < this.itemsOnProfile.size(); i++) {
                if (this.itemsOnProfile.get(i).getId() != o.itemsOnProfile.get(i).getId()) return false;
                else {
                    if (this.itemsOnProfile.get(i).getRepetition() != o.itemsOnProfile.get(i).getRepetition()) return false;
                }
            }
        }
        return true;
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
