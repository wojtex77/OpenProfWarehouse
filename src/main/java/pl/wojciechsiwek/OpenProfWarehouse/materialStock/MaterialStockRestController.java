package pl.wojciechsiwek.OpenProfWarehouse.materialStock;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "stock")
public class MaterialStockRestController {

    private final MaterialStockRepository repository;

    public MaterialStockRestController(MaterialStockRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/getstock")
    public List<StockItem> getStock() {
        List<StockItem> profiles = repository.findAll();
        return profiles;
    }

    @PostMapping(path = "/getsignature")
    public StockItem getSignature(@RequestBody MultiValueMap<String, String> data) {
        StockItem profile = repository.findBySignatureEquals(data.getFirst("signature"));
        return profile;
    }
}
