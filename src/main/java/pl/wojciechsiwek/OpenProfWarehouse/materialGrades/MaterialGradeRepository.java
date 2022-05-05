package pl.wojciechsiwek.OpenProfWarehouse.materialGrades;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialGradeRepository extends JpaRepository<MaterialGrade, Integer> {
    MaterialGrade findByFullNameEquals(String fullName);




}
