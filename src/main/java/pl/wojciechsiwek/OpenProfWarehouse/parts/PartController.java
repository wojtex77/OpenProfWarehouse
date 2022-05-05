package pl.wojciechsiwek.OpenProfWarehouse.parts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.Contrahent;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.ContrahentRepository;
import pl.wojciechsiwek.OpenProfWarehouse.materialGrades.MaterialGrade;
import pl.wojciechsiwek.OpenProfWarehouse.materialGrades.MaterialGradeRepository;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.MaterialShape;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.MaterialShapeRepository;

import java.util.List;

@Controller
@RequestMapping("parts")
public class PartController {

    private final MaterialGradeRepository gradeRepository;
    private final MaterialShapeRepository shapeRepository;
    private final ContrahentRepository contrahentRepository;

    public PartController(MaterialGradeRepository gradeRepository, MaterialShapeRepository shapeRepository, ContrahentRepository contrahentRepository) {
        this.gradeRepository = gradeRepository;
        this.shapeRepository = shapeRepository;
        this.contrahentRepository = contrahentRepository;
    }

    @GetMapping(path = "")
    public String showAllParts() {

        return "parts/all";
    }


    @GetMapping(path = "/new")
    public String showNewForm(Model model, @ModelAttribute("part") Part part) {
        if (part == null) {
            part = new Part();
        }
        List<MaterialGrade> materials = gradeRepository.findAll();
        List<MaterialShape> shapes = shapeRepository.findAll();
        List<Contrahent> contrahents = contrahentRepository.findAll();

        model.addAttribute("action", "new");
        model.addAttribute("part", part);
        model.addAttribute("contrahents", contrahents);
        model.addAttribute("shapes", shapes);
        model.addAttribute("materials", materials);
        return "parts/new";
    }
}
