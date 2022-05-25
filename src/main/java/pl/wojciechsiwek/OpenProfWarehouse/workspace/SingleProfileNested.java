package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;

import java.util.List;

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
        if (!(o instanceof SingleProfileNested)) return false;

        SingleProfileNested that = (SingleProfileNested) o;

        if (repetition != that.repetition) return false;
        if (stockItem != null ? !stockItem.equals(that.stockItem) : that.stockItem != null) return false;
        return itemsOnProfile != null ? itemsOnProfile.equals(that.itemsOnProfile) : that.itemsOnProfile == null;
    }

    @Override
    public int hashCode() {
        int result = stockItem != null ? stockItem.hashCode() : 0;
        result = 31 * result + (itemsOnProfile != null ? itemsOnProfile.hashCode() : 0);
        result = 31 * result + repetition;
        return result;
    }
}
