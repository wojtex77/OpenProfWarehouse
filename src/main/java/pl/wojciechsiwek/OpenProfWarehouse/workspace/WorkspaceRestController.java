package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "wsrest")
public class WorkspaceRestController {

    @PostMapping("/save")
    public String saveWS(){

        return "Zapisano";
    }
}
