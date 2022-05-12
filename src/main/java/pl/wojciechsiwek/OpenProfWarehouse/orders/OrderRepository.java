package pl.wojciechsiwek.OpenProfWarehouse.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    boolean existsByContrahentEquals(String contrahent);

    boolean existsByOrderNumberEquals(String orderNumber);

    Order findByOrderNumberEquals(String orderNumber);





}
