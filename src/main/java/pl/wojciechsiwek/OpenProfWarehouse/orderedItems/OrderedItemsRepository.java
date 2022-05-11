package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemsRepository extends JpaRepository<OrderedItems, Integer> {
}