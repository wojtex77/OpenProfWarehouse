package pl.wojciechsiwek.OpenProfWarehouse.reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NestedOrderedItemRepository extends JpaRepository<NestedOrderedItem, Integer> {
}