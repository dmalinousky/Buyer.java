package am.itstep.projectWarehouse.repository;

import am.itstep.projectWarehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query("SELECT w FROM Warehouse w WHERE w.warehouseId= :warehouseId")
    Warehouse findByWarehouseId(@Param("warehouseId") Long warehouseId);

    @Query("SELECT w FROM Warehouse w WHERE w.registrationNumber= :registrationNumber")
    Warehouse findByRegistrationNumber(@Param("registrationNumber") String registrationNumber);
}
