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
public class MaterialGradesController {

    private static final Logger logger = LoggerFactory.getLogger(MaterialGradesController.class);
    private final MaterialGradeRepository materialGradeRepository;


    public MaterialGradesController(MaterialGradeRepository materialGradeRepository) {
        this.materialGradeRepository = materialGradeRepository;
    }

    @GetMapping(path = "/allmaterialgrades")
    String getAllMaterialGrades(Model model) {
        Iterable<MaterialGrade> materialGrades = materialGradeRepository.findAll();
        model.addAttribute("materialGrades", materialGrades);
        return "allMaterialGrades";
    }

    @PutMapping(path = "/addmaterialgrade")
    ResponseEntity addMaterialGrade(@RequestBody MaterialGrade materialGrade) {

        materialGradeRepository.save(materialGrade);
        logger.info("material grade added");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getmaterials")
    ResponseEntity<List<MaterialGrade>> getAllMaterialsGrade(){
        return ResponseEntity.ok(materialGradeRepository.findAll());
    }

}
