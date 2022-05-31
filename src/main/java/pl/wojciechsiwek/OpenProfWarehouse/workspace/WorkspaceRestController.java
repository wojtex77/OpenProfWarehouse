package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemRepository;
import pl.wojciechsiwek.OpenProfWarehouse.orderedItems.OrderedItemsService;
import pl.wojciechsiwek.OpenProfWarehouse.reservations.ReservationService;

import java.util.List;

@RestController
@RequestMapping(path = "wsrest")
public class WorkspaceRestController {
    private final WorkspaceService service;
    private final MaterialStockRepository materialStockRepository;
    private final OrderedItemsService orderedItemsService;
    private final OrderedItemRepository orderedItemsRepository;
    private final ReservationService reservationService;

    public WorkspaceRestController(WorkspaceService service, MaterialStockRepository materialStockRepository, OrderedItemsService orderedItemsService, OrderedItemRepository orderedItemsRepository, ReservationService reservationService) {
        this.service = service;
        this.materialStockRepository = materialStockRepository;
        this.orderedItemsService = orderedItemsService;
        this.orderedItemsRepository = orderedItemsRepository;
        this.reservationService = reservationService;
    }


    @PostMapping("/makeReservation")
    public Workspace saveWS(@RequestParam(value = "stockItemsSignatures[]") List<String> stockSignaturesToUse, @RequestParam(value = "orderedItemsIds[]") List<Integer> orderedItemsIds, @RequestParam(value = "profileMargin") Float profileMargin, @RequestParam(value = "partDistance") Float partDistance, @RequestParam(value = "minRemnantLength") Float minRemnantLength) {

        return reservationService.makeReservation(this.doNesting(stockSignaturesToUse,orderedItemsIds,profileMargin,partDistance,minRemnantLength));

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
