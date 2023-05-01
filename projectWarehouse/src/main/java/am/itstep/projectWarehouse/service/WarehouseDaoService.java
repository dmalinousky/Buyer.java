package am.itstep.projectWarehouse.service;

import am.itstep.projectWarehouse.model.Warehouse;

public interface WarehouseDaoService {
    boolean createWarehouse(Warehouse warehouse);

    boolean editWarehouse(Long warehouseId, Warehouse warehouse);

    boolean deleteWarehouse(Warehouse warehouse);
}
