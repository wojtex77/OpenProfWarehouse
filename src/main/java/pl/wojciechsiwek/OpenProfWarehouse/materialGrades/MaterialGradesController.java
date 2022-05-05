package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.Contrahent;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.MaterialShape;
import pl.wojciechsiwek.OpenProfWarehouse.parts.Part;

import java.util.List;

@Controller
@RequestMapping("materialgrades")
public class MaterialGradesController {

    private static final Logger logger = LoggerFactory.getLogger(MaterialGradesController.class);
    private final MaterialGradeRepository materialGradeRepository;
    private final MaterialGradeService materialGradeService;


    public MaterialGradesController(MaterialGradeRepository materialGradeRepository, MaterialGradeService materialGradeService) {
        this.materialGradeRepository = materialGradeRepository;
        this.materialGradeService = materialGradeService;
    }

    @GetMapping(path = "/all")
    String getAllMaterialGrades(@ModelAttribute MaterialGrade materialGrade, Model model) {
        Iterable<MaterialGrade> materialGrades = materialGradeRepository.findAll();
        model.addAttribute("materialGrades", materialGrades);
        model.addAttribute("materialGrade", materialGrade);
        return "materialGrades/allMaterialGrades";
    }


    @GetMapping(path = "/new")
    public String showNewGradeForm(Model model, @ModelAttribute("materialGrade") MaterialGrade materialGrade) {
        if (materialGrade == null) {
            materialGrade = new MaterialGrade();
        }
        model.addAttribute("action", "new");
        model.addAttribute("materialGrade", materialGrade);
        return "materialGrades/newMaterialGrade";
    }


    @PostMapping(path = "/add")
    public RedirectView addNewGradeToDb(@ModelAttribute MaterialGrade materialGrade, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView();
        try {
            materialGradeService.addPart(materialGrade);
            attributes.addFlashAttribute("messageSuccess", "Pomyślnie dodano do bazy " + materialGrade.getFullName());
            redirectView.setUrl("/materialgrades/all");
        } catch (DuplicatedGradeEntryException e) {
            attributes.addFlashAttribute("messageWarning", "W bazie istnieje już materiał o podanej nazwie");
            attributes.addFlashAttribute("materialGrades", materialGrade);
            redirectView.setUrl("/materialgrades/new");
        } catch (Exception e) {
            attributes.addFlashAttribute("messageWarning", "Nie dodano do bazy");
            redirectView.setUrl("/materialgrades/all");
        }
        return redirectView;
    }


    @GetMapping("/delete/{id}")
    public RedirectView newMaterialGrade(RedirectAttributes attributes, @PathVariable int id) {
        try {
            materialGradeService.deleteMaterialGrade(id);
            attributes.addFlashAttribute("messageSuccess", "Pomyślnie usunięto.");
        } catch (GradeRemoveNotAllowedException e) {
            logger.warn("material grade in use, con not be deleted");
            attributes.addFlashAttribute("messageWarning", "Gatunek w użyciu, nie można usunąć.");
        } catch (Exception e) {
            logger.warn("something gone wrong");
            attributes.addFlashAttribute("messageWarning", "Nie udało się usunąć materiału");
        } finally {
            return new RedirectView("/materialgrades/all");
        }
    }
//
//    @GetMapping("/edit/{id}")
//    public String newMaterialGrade(@PathVariable int id, Model model) {
//        try {
//            Optional<MaterialGrade> materialGrade = materialGradeRepository.findById(id);
//            model.addAttribute("materialGrade", materialGrade);
//        } catch (Exception e) {
//            logger.warn("something gone wrong");
//            return "redirect:/materialgrades/all";
//        }
//        return "materialGrades/editMaterialGrade";
//    }
//


    @GetMapping(path = "/edit/{id}")
    public String showEditGradeForm(Model model, @PathVariable int id) {
        MaterialGrade grade = materialGradeRepository.findById(id).get();

        model.addAttribute("action", "edit");
        model.addAttribute("materialGrade", grade);
        return "materialGrades/editMaterialGrade";
    }

    @PostMapping(path = "/saveChanges")
    public RedirectView saveChangeToDb(@ModelAttribute MaterialGrade materialGrade, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView();
        try {
            materialGradeService.saveChange(materialGrade);
            attributes.addFlashAttribute("messageSuccess", "Pomyślnie edytowano " + materialGrade.getFullName());
            redirectView.setUrl("/materialgrades/all");
        } catch (Exception e) {
            attributes.addFlashAttribute("messageWarning", "Nie dodano do bazy");
            redirectView.setUrl("/materialgrades/edit/" + materialGrade.getId());
        }
        return redirectView;
    }


}
