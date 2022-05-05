package pl.wojciechsiwek.OpenProfWarehouse.materialStock;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialGrades.MaterialGrade;
import pl.wojciechsiwek.OpenProfWarehouse.materialGrades.MaterialGradeRepository;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.MaterialShape;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.MaterialShapeRepository;

@Service
public class MaterialStockService {
    private final MaterialStockRepository stockRepository;
    private final MaterialGradeRepository gradeRepository;
    private final MaterialShapeRepository shapeRepository;

    public MaterialStockService(MaterialStockRepository stockRepository, MaterialGradeRepository materialGradeRepository, MaterialShapeRepository materialShapeRepository) {
        this.stockRepository = stockRepository;
        this.gradeRepository = materialGradeRepository;
        this.shapeRepository = materialShapeRepository;
    }

    public void addItem(StockItem item) throws DuplicateSignatureException {
        if (stockRepository.existsBySignatureEquals(item.getSignature())){
            throw new DuplicateSignatureException("Podany certyfikat istnieje w bazie");
        }
        item.setAvailableQty(item.getQty());

        stockRepository.save(calculateWeight(item));
    }

    public void saveChanges(StockItem item) {
        stockRepository.save(calculateWeight(item));
    }

    private StockItem calculateWeight(StockItem item) {
        MaterialGrade grade = gradeRepository.findByFullNameEquals(item.getMaterial());
        MaterialShape shape = shapeRepository.findByNameEquals(item.getProfile());

        item.setSingleWeight(item.getProfileLength()*grade.getDensity()*shape.getArea()/1000000);
        item.setWholeWeight(item.getSingleWeight()* item.getQty());
        return item;
    }
}
