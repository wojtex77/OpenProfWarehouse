package pl.wojciechsiwek.OpenProfWarehouse.parts;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.DuplicatedEntryException;
import pl.wojciechsiwek.OpenProfWarehouse.materialGrades.MaterialGrade;
import pl.wojciechsiwek.OpenProfWarehouse.materialGrades.MaterialGradeRepository;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.MaterialShape;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapes.MaterialShapeRepository;

@Service
public class PartService {
    private final PartRepository partRepository;
    private final MaterialGradeRepository gradeRepository;
    private final MaterialShapeRepository shapeRepository;

    public PartService(PartRepository partRepository, MaterialGradeRepository gradeRepository, MaterialShapeRepository shapeRepository) {
        this.partRepository = partRepository;
        this.gradeRepository = gradeRepository;
        this.shapeRepository = shapeRepository;
    }

    void addPart(Part part) throws DuplicatedEntryException {
        if (partRepository.existsByPartNameEquals(part.getPartName())) {
            throw new DuplicatedEntryException("Part with such name exists");
        }
        partRepository.save(calculateWeight(part));
    }

    private Part calculateWeight(Part part) {
        MaterialGrade grade = gradeRepository.findByFullNameEquals(part.getMaterial());
        MaterialShape shape = shapeRepository.findByNameEquals(part.getProfile());
        part.setWeight(grade.getDensity() * shape.getArea() * part.getProfileLength() / 100000);
        return part;
    }

    void saveChange(Part part) {
        part.setId(partRepository.findByPartNameEquals(part.getPartName()).getId());
        partRepository.save(calculateWeight(part));
    }
}
