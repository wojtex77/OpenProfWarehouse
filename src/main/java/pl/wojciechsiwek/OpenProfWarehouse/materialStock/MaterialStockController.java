package pl.wojciechsiwek.OpenProfWarehouse.materialStock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("materialstock")
public class MaterialStockController {

    private static final Logger logger = LoggerFactory.getLogger(MaterialStockController.class);
    private final MaterialStockRepository repository;

    public MaterialStockController(MaterialStockRepository repository) {
        this.repository = repository;
    }


    @GetMapping(path = "")
    String getStock(@ModelAttribute StockItem item, Model model) {
        List<StockItem> items = this.repository.findAll();
        model.addAttribute("items", items);
        logger.info("showing all data");
        return "materialStock/materialStock";
    }

    @GetMapping(path = "/add")
    String addTestData(Model model) {

        StockItem item = new StockItem("2/22","PO-20.0","test",6000,10,5,"powierzony");
        repository.save(item);

        return "materialStock/materialStock";
    }

}
