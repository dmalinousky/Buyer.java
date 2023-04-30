package am.itstep.projectWarehouse.service;

import am.itstep.projectWarehouse.model.Warehouse;

public interface WarehouseDaoService {
    boolean createWarehouse(Warehouse warehouse);

    Warehouse editWarehouse(String login, String password);

    boolean deleteWarehouse(String registrationNumber);
}
