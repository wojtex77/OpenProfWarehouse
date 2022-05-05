package pl.wojciechsiwek.OpenProfWarehouse.parts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.Contrahent;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.ContrahentRepository;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.DuplicatedEntryException;
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
    private final PartRepository partRepository;

    private final PartService service;

    public PartController(MaterialGradeRepository gradeRepository, MaterialShapeRepository shapeRepository, ContrahentRepository contrahentRepository, PartRepository partRepository, PartService service) {
        this.gradeRepository = gradeRepository;
        this.shapeRepository = shapeRepository;
        this.contrahentRepository = contrahentRepository;
        this.partRepository = partRepository;
        this.service = service;
    }

    @GetMapping(path = "")
    public String showAllParts(Model model) {
        List<Part> parts = partRepository.findAll();
        model.addAttribute("parts", parts);
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

    @PostMapping(path = "/add")
    public RedirectView addNewPartToDb(@ModelAttribute Part part, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView();
        try {
            service.addPart(part);
            attributes.addFlashAttribute("messageSuccess", "Pomyślnie dodano do bazy " + part.getPartName());
            redirectView.setUrl("/parts");
        } catch (DuplicatedEntryException e) {
            attributes.addFlashAttribute("messageWarning", "W bazie istnieje już część o podanej nazwie");
            attributes.addFlashAttribute("part", part);
            redirectView.setUrl("/parts/new");
        } catch (Exception e) {
            attributes.addFlashAttribute("messageWarning", "Nie dodano do bazy");
        }
        return redirectView;
    }
}