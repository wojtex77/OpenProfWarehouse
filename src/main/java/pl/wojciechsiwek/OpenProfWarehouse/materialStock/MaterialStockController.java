package pl.wojciechsiwek.OpenProfWarehouse.materialStock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wojciechsiwek.OpenProfWarehouse.materialGrades.MaterialGrade;
import pl.wojciechsiwek.OpenProfWarehouse.materialGrades.MaterialGradeRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("materialstock")
public class MaterialStockController {

    private static final Logger logger = LoggerFactory.getLogger(MaterialStockController.class);



    @GetMapping(path = "")
    String getStock(/*@ModelAttribute MaterialStock materialStock, Model model*/) {
        return "materialStock/materialStock";
    }

}
