package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.stereotype.Component;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsService;

import java.util.List;

@Component
public class Workspace {
    private List<SingleProfileNested> nestingList;
    private List<StockItem> stockToUse;
    private List<OrderedItemsExtended> orderedItemsList;
    private MaterialStockRepository stockRepository;
    private OrderedItemsService orderedItemsService;
    private OrderedItemsRepository orderedItemsRepository;

    public Workspace(MaterialStockRepository stockRepository,
                     OrderedItemsService orderedItemsService,
                     OrderedItemsRepository orderedItemsRepository,
                     List<String> stockSignaturesToUse,
                     List<Integer> orderedItemsIds) {
        this.stockRepository = stockRepository;
        this.orderedItemsService = orderedItemsService;
        this.orderedItemsRepository = orderedItemsRepository;
        this.stockToUse = this.stockRepository.findBySignatureInOrderByProfileLengthDesc(stockSignaturesToUse);
        this.orderedItemsList = this.orderedItemsService.extendListOfOrderedItems(this.orderedItemsRepository.findByIdInOrderByQtyDesc(orderedItemsIds));

    }

    void setNestingList(List<SingleProfileNested> nestingList) {
        this.nestingList = nestingList;
    }
}
