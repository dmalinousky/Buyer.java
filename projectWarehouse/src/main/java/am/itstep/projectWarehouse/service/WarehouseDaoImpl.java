package am.itstep.projectWarehouse.service;

import am.itstep.projectWarehouse.model.Warehouse;
import am.itstep.projectWarehouse.repository.WarehouseRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class WarehouseDaoImpl {
    @Autowired
    private WarehouseRepository repo;


    public void createWarehouse() throws InterruptedException {
        Warehouse test = new Warehouse(123l, "123", "123",
                "123", "123", "123",
                "123", "123", true, true,
                123d, 123d, "None",
                "123", "123", 1234l);
        if (repo != null) {
            System.out.println("repository != null");
        } else {
            System.out.println("repository = null");
        }
    }
}
