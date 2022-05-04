package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.MaterialShape;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.ShapeDeleteRemoveNotAllowedException;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;

import java.util.Optional;

@Service
public class MaterialGradeService {

    private MaterialGradeRepository gradeRepository;
    private MaterialStockRepository stockRepository;

    public MaterialGradeService(MaterialGradeRepository gradeRepository, MaterialStockRepository stockRepository) {
        this.gradeRepository = gradeRepository;
        this.stockRepository = stockRepository;
    }


    void deleteMaterialGrade(int id) throws GradeRemoveNotAllowedException {
        Optional<MaterialGrade> grade = gradeRepository.findById(id);
        MaterialGrade fromOptional = grade.orElse(null);
        if (stockRepository.existsByMaterialEquals(fromOptional.getFullName()))
            throw new GradeRemoveNotAllowedException("Material grade in use, can not be deleted");
        else {
            gradeRepository.deleteById(id);
        }
    }
}
