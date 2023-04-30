package am.itstep.projectWarehouse.service;

import am.itstep.projectWarehouse.model.Buyer;
import am.itstep.projectWarehouse.model.Warehouse;

import java.util.ArrayList;

public interface BuyerDaoService {
    boolean createBuyer(Buyer buyer);

    Buyer editBuyer(String login, String password);

    boolean deleteBuyer(String registrationNumber);

    ArrayList<Warehouse> showAllWarehouses();

}
