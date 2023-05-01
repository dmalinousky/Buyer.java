package am.itstep.projectWarehouse.repository;

import am.itstep.projectWarehouse.model.Order;
import am.itstep.projectWarehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.orderId= :orderId")
    Order findByOderId(@Param("orderId") Long orderId);

    @Query("SELECT o FROM Order o WHERE o.warehouseId = :warehouseId")
    List<Order> findByWarehouseId(@Param("warehouseId") Long warehouseId);

    @Query("SELECT o FROM Order o WHERE o.buyerId = :buyerId")
    List<Order> findByBuyerId(@Param("buyerId") Long buyerId);
}
