package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;
import pl.wojciechsiwek.OpenProfWarehouse.parts.PartRepository;

import java.util.Optional;

@Service
public class MaterialGradeService {

    private MaterialGradeRepository gradeRepository;
    private MaterialStockRepository stockRepository;
    private PartRepository partRepository;

    public MaterialGradeService(MaterialGradeRepository gradeRepository, MaterialStockRepository stockRepository, PartRepository partRepository) {
        this.gradeRepository = gradeRepository;
        this.stockRepository = stockRepository;
        this.partRepository = partRepository;
    }


    void deleteMaterialGrade(int id) throws GradeRemoveNotAllowedException {
        Optional<MaterialGrade> grade = gradeRepository.findById(id);
        MaterialGrade fromOptional = grade.orElse(null);
        if (stockRepository.existsByMaterialEquals(fromOptional.getFullName()) || partRepository.existsByMaterialEquals(fromOptional.getFullName()))
            throw new GradeRemoveNotAllowedException("Material grade in use, can not be deleted");
        else {
            gradeRepository.deleteById(id);
        }
    }

    void addPart(MaterialGrade materialGrade) throws DuplicatedGradeEntryException {
        if (gradeRepository.existsByFullNameEquals(materialGrade.getFullName())){
            throw new DuplicatedGradeEntryException();
        }
        gradeRepository.save(materialGrade);
    }
}
