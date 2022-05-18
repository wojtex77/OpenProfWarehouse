package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "wsrest")
public class WorkspaceRestController {
    private final WorkspaceService service;

    public WorkspaceRestController(WorkspaceService service) {
        this.service = service;
    }


    @PostMapping("/save")
    public String saveWS(){

        return "Zapisano";
    }


    @PostMapping("/nest")
    public String doNesting(){
        List<String> stockSignaturesToUse = new ArrayList<>();
        List<Integer> orderedItemsIds = new ArrayList<>();
        stockSignaturesToUse.add("1-22");
        stockSignaturesToUse.add("1-22-3");
        stockSignaturesToUse.add("5-22");
        orderedItemsIds.add(45);
        orderedItemsIds.add(46);
        orderedItemsIds.add(47);

        List<SingleProfileNested> nestingList = service.doNesting(stockSignaturesToUse,orderedItemsIds);

        return "Zapisano";
    }

}
