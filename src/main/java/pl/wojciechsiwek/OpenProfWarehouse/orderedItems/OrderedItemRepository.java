package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {

    List<OrderedItem> findByOrderNumberEquals(String orderNumber);

    List<OrderedItem> findByIdInOrderByQtyDesc(Collection<Integer> ids);



}