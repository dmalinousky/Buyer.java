package am.itstep.projectWarehouse.repository;

import am.itstep.projectWarehouse.model.Order;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
