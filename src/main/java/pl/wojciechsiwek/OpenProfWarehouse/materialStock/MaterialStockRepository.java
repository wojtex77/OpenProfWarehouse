package pl.wojciechsiwek.OpenProfWarehouse.materialStock;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MaterialStockRepository extends JpaRepository<StockItem, String> {

    StockItem findBySignatureEquals(String signature);

    boolean existsByProfileEquals(@NonNull String profile);

    boolean existsByMaterialEquals(@NonNull String material);

    boolean existsBySignatureEquals(String signature);

    List<StockItem> findBySignatureInOrderByProfileLengthDesc(Collection<String> signatures);









}
