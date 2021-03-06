package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsService;

import java.util.List;

@RestController
@RequestMapping(path = "wsrest")
public class WorkspaceRestController {
    private final WorkspaceService service;
    private final MaterialStockRepository materialStockRepository;
    private final OrderedItemsService orderedItemsService;
    private final OrderedItemsRepository orderedItemsRepository;

    public WorkspaceRestController(WorkspaceService service, MaterialStockRepository materialStockRepository, OrderedItemsService orderedItemsService, OrderedItemsRepository orderedItemsRepository) {
        this.service = service;
        this.materialStockRepository = materialStockRepository;
        this.orderedItemsService = orderedItemsService;
        this.orderedItemsRepository = orderedItemsRepository;
    }


    @PostMapping("/save")
    public String saveWS() {

        return "Zapisano";
    }


    @PostMapping("/nest")
    public Workspace doNesting(@RequestParam(value = "stockItemsSignatures[]") List<String> stockSignaturesToUse, @RequestParam(value = "orderedItemsIds[]") List<Integer> orderedItemsIds, @RequestParam(value = "profileMargin") Float profileMargin, @RequestParam(value = "partDistance") Float partDistance, @RequestParam(value = "minRemnantLength") Float minRemnantLength) {

        Workspace workspace = new Workspace(service.getStockToUseBySignatures(stockSignaturesToUse), service.getOrderedItemsExtendedByIds(orderedItemsIds));
        workspace.setPartDistance(partDistance);
        workspace.setProfileMargin(profileMargin);
        workspace.setMinRemnantLength(minRemnantLength);

        return service.startNesting(workspace);
    }

}
