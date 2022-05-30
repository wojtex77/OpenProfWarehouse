package pl.wojciechsiwek.OpenProfWarehouse.orderedItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderedItemsRepository extends JpaRepository<OrderedItems, Integer> {

    List<OrderedItems> findByOrderNumberEquals(String orderNumber);

    List<OrderedItems> findByIdInOrderByQtyDesc(Collection<Integer> ids);



}