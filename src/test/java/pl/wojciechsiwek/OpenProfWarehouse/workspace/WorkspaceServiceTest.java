package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.StockItem;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsExtended;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class WorkspaceServiceTest {

    @Test
    void startNesting() {
//        given

        StockItem stockItem1 = new StockItem("1-22", "PO-8", "1.4301", 2000, 10, 10, "");
        StockItem stockItem2 = new StockItem("2-22", "PO-8", "1.4301", 850, 50, 20, "");
        List<StockItem> stockItemList = new ArrayList<>();
        stockItemList.add(stockItem1);
        stockItemList.add(stockItem2);

        OrderedItemsExtended itemExtended1 = new OrderedItemsExtended(1, 1, "AAA", 3, 3, 0, "PO-8", 850, "1.4301", "1/22", "", "", 1);
        OrderedItemsExtended itemExtended2 = new OrderedItemsExtended(2, 2, "BBB", 2, 2, 0, "PO-8", 223, "1.4301", "1/22", "", "", 2);
        List<OrderedItemsExtended> itemsExtendedList = new ArrayList<>();
        itemsExtendedList.add(itemExtended1);
        itemsExtendedList.add(itemExtended2);

        Workspace workspace = new Workspace(stockItemList, itemsExtendedList);
        workspace.setMinRemnantLength(500);
        workspace.setPartDistance(3);
        workspace.setProfileMargin(30);

        MaterialStockRepository stockRepository = Mockito.mock(MaterialStockRepository.class);
        OrderedItemRepository orderedItemsRepository = Mockito.mock(OrderedItemRepository.class);
        OrderedItemsService orderedItemsService = Mockito.mock(OrderedItemsService.class);

        WorkspaceService service = new WorkspaceService(stockRepository, orderedItemsRepository, orderedItemsService);


//        when
        Workspace workspaceAfterNesting = service.startNesting(workspace);


//        then
        assertThat(workspaceAfterNesting.getProfileNestedList().size()).isEqualTo(2);
        assertThat(workspaceAfterNesting.getOrderedItemsExtendedList().get(0).getNestedQty()).isEqualTo(3);
        assertThat(workspaceAfterNesting.getOrderedItemsExtendedList().get(1).getNestedQty()).isEqualTo(2);

    }
}