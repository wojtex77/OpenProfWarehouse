package pl.wojciechsiwek.OpenProfWarehouse.materialStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialStockRepository extends JpaRepository<StockItem, String> {

    StockItem findBySignatureEquals(String signature);

    boolean deleteBySignatureContainsIgnoreCase(@Nullable String signature);







}
