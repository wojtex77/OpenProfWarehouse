package pl.wojciechsiwek.OpenProfWarehouse.materialStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wojciechsiwek.OpenProfWarehouse.materialGrades.MaterialGrade;

@Repository
public interface MaterialStockRepository extends JpaRepository<StockItem, String> {


}
