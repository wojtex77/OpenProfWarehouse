package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping(path = "/add")
    ResponseEntity addMaterialGrade(@RequestBody MaterialGrade materialGrade) {

        materialGradeRepository.save(materialGrade);
        logger.info("material grade added");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "/getmaterials")
    ResponseEntity<List<MaterialGrade>> getAllMaterialsGrade() {
        return ResponseEntity.ok(materialGradeRepository.findAll());
    }

    @PostMapping("/new")
    public String newMaterialGrade(@ModelAttribute MaterialGrade materialGrade) {
        try {
            materialGradeRepository.save(materialGrade);

        }catch (Exception e){
            logger.warn("adding new material gone wrong");
            return "redirect:/materialgrades/all";
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


}
