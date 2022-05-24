package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;

import java.util.List;

public class SingleProfileNested {
    private StockItem stockItem;
    private List<OrderedItemsExtended> itemsOnProfile;

    SingleProfileNested(StockItem profileSignature, List itemsOnProfile) {
        this.stockItem = profileSignature;
        this.itemsOnProfile = itemsOnProfile;
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
}
