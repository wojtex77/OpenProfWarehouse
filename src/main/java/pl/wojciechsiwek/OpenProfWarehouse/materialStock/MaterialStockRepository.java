package pl.wojciechsiwek.OpenProfWarehouse.materialStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialStockRepository extends JpaRepository<StockItem, String> {

    StockItem findBySignatureEquals(String signature);

    boolean existsByProfileEquals(@NonNull String profile);

    boolean existsByMaterialEquals(@NonNull String material);

    boolean existsBySignatureEquals(String signature);






}
