package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.materialStock.MaterialStockRepository;
import pl.wojciechsiwek.OpenProfWarehouse.parts.PartRepository;
import pl.wojciechsiwek.OpenProfWarehouse.parts.PartService;

import java.util.Optional;

@Service
public class MaterialGradeService {

    private MaterialGradeRepository gradeRepository;
    private MaterialStockRepository stockRepository;
    private PartRepository partRepository;
    private PartService partService;

    public MaterialGradeService(MaterialGradeRepository gradeRepository, MaterialStockRepository stockRepository, PartRepository partRepository, PartService partService) {
        this.gradeRepository = gradeRepository;
        this.stockRepository = stockRepository;
        this.partRepository = partRepository;
        this.partService = partService;
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

    void saveChange(MaterialGrade materialGrade) {
        gradeRepository.save(materialGrade);
        partService.recalculateWeight(materialGrade);
    }
}
