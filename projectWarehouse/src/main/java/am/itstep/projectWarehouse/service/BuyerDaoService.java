package am.itstep.projectWarehouse.service;

import am.itstep.projectWarehouse.model.Buyer;
import am.itstep.projectWarehouse.model.Warehouse;

import java.util.ArrayList;

public interface BuyerDaoService {
    boolean createBuyer(Buyer buyer);

    Buyer findBuyer(String login, String password, String registrationNumber);

    boolean deleteBuyer(String login, String password, String registrationNumber);

    public Boolean editBuyer(Buyer buyer);

    ArrayList<Warehouse> showAllWarehouses();

}
