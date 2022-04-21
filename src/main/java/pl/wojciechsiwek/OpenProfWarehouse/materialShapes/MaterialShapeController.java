package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wojciechsiwek.OpenProfWarehouse.materialGrades.MaterialGrade;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.SpecialShape;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("materialshapes")
public class MaterialShapeController {

    private final MaterialShapeRepository repository;

    public MaterialShapeController(MaterialShapeRepository repository) {
        this.repository = repository;
    }


    @GetMapping(path = "/all")
    String getAllShapes(Model model){
        List<MaterialShape> allShapes = repository.findAll();
        model.addAttribute("shapes", allShapes);
        return "materialShapes/allMaterialShapes";
    }

    @GetMapping(path = "/newSpecial")
    String showNewSpecialForm(Model model){
        model.addAttribute("specialShape", new MaterialShape());
        return "materialShapes/newSpecial";
    }
    @PostMapping(path = "/addSpecial")
    String addSpecialShape(@ModelAttribute MaterialShape materialShape, Model model){
        repository.save(materialShape);
        return "redirect:/materialshapes/all";
    }
    @GetMapping(path = "/delete/{id}")
    String showNewSpecialForm(@PathVariable int id, Model model){
        repository.deleteById(id);
        return "redirect:/materialshapes/all";
    }



}
