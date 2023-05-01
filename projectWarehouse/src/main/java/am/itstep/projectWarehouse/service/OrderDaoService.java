package am.itstep.projectWarehouse.service;

import am.itstep.projectWarehouse.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDaoService {
    boolean createOrder(Order order);

    Order findOrder(Long orderId);

    boolean editOrder(Long orderId, Order order);

    boolean deleteOrder(Order order);
}
