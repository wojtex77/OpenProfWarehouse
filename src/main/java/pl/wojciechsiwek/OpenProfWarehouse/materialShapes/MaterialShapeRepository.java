package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialShapeRepository extends JpaRepository<MaterialShape, Integer> {
}
