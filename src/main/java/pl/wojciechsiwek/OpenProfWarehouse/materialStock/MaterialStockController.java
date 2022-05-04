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
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.MaterialShape;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.MaterialShapeRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("materialstock")
public class MaterialStockController {

    private static final Logger logger = LoggerFactory.getLogger(MaterialStockController.class);
    private final MaterialStockRepository stockRepository;
    private final MaterialGradeRepository gradeRepository;
    private final MaterialShapeRepository shapeRepository;

    public MaterialStockController(MaterialStockRepository repository, MaterialGradeRepository gradeRepository, MaterialShapeRepository shapeRepository) {
        this.stockRepository = repository;
        this.gradeRepository = gradeRepository;
        this.shapeRepository = shapeRepository;
    }


    @GetMapping(path = "")
    String getStock(@ModelAttribute StockItem item, Model model) {
        List<StockItem> items = this.stockRepository.findAll();
        model.addAttribute("items", items);
        logger.info("showing all data");
        return "materialStock/materialStock";
    }

    @GetMapping("/new")
    public String newItem(@ModelAttribute @Valid StockItem item, BindingResult bindingResult, Model model) {
        List<MaterialShape> shapes = shapeRepository.findAll();
        List<MaterialGrade> materialGrades = gradeRepository.findAll();
        model.addAttribute("shapes", shapes);
        model.addAttribute("materials", materialGrades);
        model.addAttribute("item", item);

        if (!bindingResult.hasErrors()) {
            try {
                stockRepository.save(item);
                model.addAttribute("message-success", "Pomyslnie zapisano");
            } catch (Exception e) {
                model.addAttribute("info", "Nie można dodać materiału");
                return "materialStock/newItem";
            }

        } else {
            return "materialStock/newItem";
        }
        return "redirect:/materialstock";
    }

    @GetMapping(path = "/delete/{signature}")
    String deleteStockItem(@ModelAttribute StockItem item, Model model) {

        StockItem stockItem = stockRepository.findBySignatureEquals(item.getSignature());
        stockRepository.delete(stockItem);
        logger.info("deleting");

        return "redirect:/materialstock";
    }

    @GetMapping("/edit/{signature}")
    public String editStockItem(@PathVariable String signature, Model model) {
        List<MaterialShape> shapes = shapeRepository.findAll();
        List<MaterialGrade> materialGrades = gradeRepository.findAll();
        model.addAttribute("shapes", shapes);
        model.addAttribute("materials", materialGrades);
        StockItem stockItem;
        try {
            stockItem = stockRepository.findBySignatureEquals(signature);
            model.addAttribute("item", stockItem);
            model.addAttribute("message-success", "Pomyślnie zapisano zmiany");
        } catch (Exception e) {
            model.addAttribute("message-warning", "Nie udało się przejść do edycji materiału");
            return "redirect:/materialstock";
        }
        model.addAttribute("item", stockItem);
        return "materialStock/editItem";
    }


}