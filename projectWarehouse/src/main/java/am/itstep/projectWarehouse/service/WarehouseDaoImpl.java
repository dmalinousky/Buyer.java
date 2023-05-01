package am.itstep.projectWarehouse.service;

import am.itstep.projectWarehouse.model.Order;
import am.itstep.projectWarehouse.model.Warehouse;
import am.itstep.projectWarehouse.repository.WarehouseRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseDaoImpl implements WarehouseDaoService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public boolean createWarehouse(Warehouse warehouse) {
        if (warehouseRepository != null && warehouse != null) {
            warehouseRepository.save(warehouse);
            return true;
        }
        return false;
    }

    @Transactional
    public Warehouse findWarehouse(Long warehouseId) {
        return warehouseRepository.findByWarehouseId(warehouseId);
    }


    @Transactional
    public Warehouse findWarehouse(String registrationNumber) {
        return warehouseRepository.findByRegistrationNumber(registrationNumber);
    }


    @Transactional
    public boolean editWarehouse(Long warehouseId, Warehouse warehouse) {
        Session session = sessionFactory.openSession();
        if (warehouseId != null && session != null) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE Warehouse SET " +
                    "entityTitle = :entityTitle," +
                    "registrationNumber = :registrationNumber," +
                    "legalAddress = :legalAddress," +
                    "realAddress = :realAddress," +
                    "GpsCoordinates = :GpsCoordinates," +
                    "phoneNumber = :phoneNumber," +
                    "emailAddress = :emailAddress," +
                    "isTemperatureWH = :isTemperatureWH," +
                    "isCustomWH = :isCustomWH," +
                    "spaceFree = :spaceFree, " +
                    "balance = :balance, " +
                    "status = :status, " +
                    "login = :login, " +
                    "password = :password " +
                    "WHERE warehouseId = :warehouseId");
            query.setParameter("entityTitle", warehouse.getEntityTitle());
            query.setParameter("registrationNumber", warehouse.getRegistrationNumber());
            query.setParameter("legalAddress", warehouse.getLegalAddress());
            query.setParameter("realAddress", warehouse.getRealAddress());
            query.setParameter("GpsCoordinates", warehouse.getGpsCoordinates());
            query.setParameter("phoneNumber", warehouse.getPhoneNumber());
            query.setParameter("emailAddress", warehouse.getEmailAddress());
            query.setParameter("isTemperatureWH", warehouse.getIsTemperatureWH());
            query.setParameter("isCustomWH", warehouse.getIsCustomWH());
            query.setParameter("spaceFree", warehouse.getSpaceFree());
            query.setParameter("balance", warehouse.getBalance());
            query.setParameter("status", warehouse.getStatus());
            query.setParameter("login", warehouse.getLogin());
            query.setParameter("password", warehouse.getPassword());
            query.setParameter("warehouseId", warehouse.getWarehouseId());
            query.executeUpdate();
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }


    @Transactional
    public List<Warehouse> showAllWarehouses() {
        if (warehouseRepository != null) {
            return warehouseRepository.findAll();
        }
        return null;
    }


    @Transactional
    public boolean deleteWarehouse(Warehouse warehouse) {
        if (warehouse != null && warehouseRepository != null) {
            warehouseRepository.delete(warehouse);
            return true;
        }
        return false;
    }
}
