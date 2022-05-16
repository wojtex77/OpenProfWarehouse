package pl.wojciechsiwek.OpenProfWarehouse.workspace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ws")
public class WorkspaceController {


    @GetMapping("/new")
    public String showNewWorkspace(){

        return "workspace/new";
    }
}
