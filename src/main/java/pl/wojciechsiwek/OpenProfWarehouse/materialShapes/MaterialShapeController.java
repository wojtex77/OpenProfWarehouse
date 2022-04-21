package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("materialshapes")
public class MaterialShapeController {

    @GetMapping(path = "/all")
    String getAllShapes(){
        return "allMaterialShapes";
    }

}
