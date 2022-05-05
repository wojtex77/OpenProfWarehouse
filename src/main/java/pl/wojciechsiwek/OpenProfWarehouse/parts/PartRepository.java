package pl.wojciechsiwek.OpenProfWarehouse.parts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {
    boolean existsByPartNameEquals(String partName);

    Part findByPartNameEquals(String partName);

    boolean existsByMaterialEquals(String material);

    boolean existsByProfileEquals(String profile);

    boolean existsByContrahentEquals(String contrahent);
}
