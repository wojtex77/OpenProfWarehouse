package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("materialgrades")
public class MaterialGradesController {

    private static final Logger logger = LoggerFactory.getLogger(MaterialGradesController.class);
    private final MaterialGradeRepository materialGradeRepository;


    public MaterialGradesController(MaterialGradeRepository materialGradeRepository) {
        this.materialGradeRepository = materialGradeRepository;
    }

    @GetMapping(path = "/all")
    String getAllMaterialGrades(@ModelAttribute MaterialGrade materialGrade, Model model) {
        Iterable<MaterialGrade> materialGrades = materialGradeRepository.findAll();
        model.addAttribute("materialGrades", materialGrades);
        model.addAttribute("materialGrade", materialGrade);
        return "allMaterialGrades";
    }

    @GetMapping("/new")
    public String newMaterialGrade(@ModelAttribute @Valid MaterialGrade materialGrade, BindingResult bindingResult, Model model) {
        /*try {
            if (!bindingResult.hasErrors()) {
                materialGradeRepository.save(materialGrade);
            }
        } catch (Exception e) {
            logger.warn("adding new material gone wrong");
        } finally {
            Iterable<MaterialGrade> materialGrades = materialGradeRepository.findAll();
            model.addAttribute("materialGrades", materialGrades);
            return "allMaterialGrades";
        }

*/
        if (!bindingResult.hasErrors()) {
            try {
                materialGradeRepository.save(materialGrade);
            }catch (Exception e){
                model.addAttribute("info", "Nie można dodać materiału, być może krótka nazwa już istnieje");
                return "newMaterialGrade";
            }

        } else {
            return "newMaterialGrade";
        }
        return "redirect:/materialgrades/all";
    }

    @GetMapping("/delete/{id}")
    public String newMaterialGrade(@ModelAttribute MaterialGrade materialGrade, @PathVariable int id) {
        try {
            materialGradeRepository.deleteById(id);
        } catch (Exception e) {
            logger.warn("something gone wrong");
            return "redirect:/materialgrades/all";
        }
        return "redirect:/materialgrades/all";
    }

    @GetMapping("/edit/{id}")
    public String newMaterialGrade(@PathVariable int id, Model model) {
        try {
            Optional<MaterialGrade> materialGrade = materialGradeRepository.findById(id);
            model.addAttribute("materialGrade", materialGrade);
        } catch (Exception e) {
            logger.warn("something gone wrong");
            return "redirect:/materialgrades/all";
        }
        return "editMaterialGrade";
    }


}