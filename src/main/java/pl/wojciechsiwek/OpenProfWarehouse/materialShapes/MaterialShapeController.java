package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("materialshapes")
public class MaterialShapeController {

    private final MaterialShapeRepository repository;
    private final MaterialShapeService service;

    public MaterialShapeController(MaterialShapeRepository repository, MaterialShapeService service) {
        this.repository = repository;
        this.service = service;
    }


    @GetMapping(path = "/all")
    String getAllShapes(Model model) {
        List<MaterialShape> allShapes = repository.findAll();
        model.addAttribute("shapes", allShapes);
        return "materialShapes/allMaterialShapes";
    }

    @GetMapping(path = "/delete/{id}")
    String deleteShape(@PathVariable int id, Model model) {
        repository.deleteById(id);
        return "redirect:/materialshapes/all";
    }


    @GetMapping("/edit/{id}")
    public String newMaterialShape(@PathVariable int id, Model model) {
        Shape shape;
        try {
            Optional<MaterialShape> materialShape = repository.findById(id);
            shape = service.returnProperType(materialShape);
        } catch (Exception e) {
            return "redirect:/materialshapes/all";
        }
        model.addAttribute("shape", shape);
        String templateToReturn = service.getTemplate(shape);
        return templateToReturn;
    }


    /*
     * Beginning of part of code responsible for SPECIAL shapes
     *
     * */
    @GetMapping(path = "/newSpecial")
    String showNewSpecialForm(Model model) {
        model.addAttribute("shape", new MaterialShape());
        return "materialShapes/newSpecial";
    }


    @PostMapping(path = "/addSpecial")
    String addSpecialShape(@ModelAttribute MaterialShape materialShape, Model model) {
        repository.save(materialShape);
        return "redirect:/materialshapes/all";
    }
    //End of special shape code


    /*
     * Beginning of part of code responsible for RECTANGULAR shapes
     *
     * */
    @GetMapping(path = "/newRectangular")
    String showNewRectangularForm(Model model) {
        model.addAttribute("shape", new RectangularTube(null, null, null));
        return "materialShapes/newRectangular";
    }

    @PostMapping(path = "/addRectangular")
    String addRectangularShape(@ModelAttribute RectangularTube tube, Model model) {

        repository.save(service.convertToMaterialShape(tube));
        return "redirect:/materialshapes/all";
    }
    //End of RECTANGULAR shape code




    /*
     * Beginning of part of code responsible for CIRCULAR shapes
     *
     * */
    @GetMapping(path = "/newCircular")
    String showNewCircularForm(Model model) {
        model.addAttribute("shape", new CircularTube(null, null));
        return "materialShapes/newCircular";
    }

    @PostMapping(path = "/addCircular")
    String addCircularShape(@ModelAttribute CircularTube tube, Model model) {

        repository.save(service.convertToMaterialShape(tube));
        return "redirect:/materialshapes/all";
    }
    //End of CIRCULAR shape code



    /*
     * Beginning of part of code responsible for RECTANGULAR_BAR shapes
     *
     * */
    @GetMapping(path = "/newRectangularBar")
    String showNewRectangularBarForm(Model model) {
        model.addAttribute("shape", new RectangularBar(null, null));
        return "materialShapes/newRectangularBar";
    }

    @PostMapping(path = "/addRectangularBar")
    String addRectangularBarShape(@ModelAttribute RectangularBar bar, Model model) {

        repository.save(service.convertToMaterialShape(bar));
        return "redirect:/materialshapes/all";
    }
    //End of RECTANGULAR_BAR shape code





    /*
     * Beginning of part of code responsible for CIRCULAR_BAR shapes
     *
     * */
    @GetMapping(path = "/newCircularBar")
    String showNewCircularBarForm(Model model) {
        model.addAttribute("shape", new CircularBar(null));
        return "materialShapes/newCircularBar";
    }

    @PostMapping(path = "/addCircularBar")
    String addCircularBarShape(@ModelAttribute CircularBar bar, Model model) {

        repository.save(service.convertToMaterialShape(bar));
        return "redirect:/materialshapes/all";
    }
    //End of CIRCULAR_BAR shape code


}
