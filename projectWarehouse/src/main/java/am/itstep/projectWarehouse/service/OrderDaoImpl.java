package am.itstep.projectWarehouse.service;

import am.itstep.projectWarehouse.model.Order;
import am.itstep.projectWarehouse.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDaoImpl implements OrderDaoService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public boolean createOrder(Order order) {
        if (orderRepository != null && order != null) {
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Order> findOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Transactional
    public boolean editOrder(Long orderId, Order order) {
        Session session = sessionFactory.openSession();
        if (orderId != null && session != null) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE Order SET " +
                    "buyerId = :buyerId," +
                    "totalSum = :totalSum," +
                    "keepingDays = :keepingDays," +
                    "collisUnloaded = :collisUnloaded," +
                    "isUnloadingByWH = :isUnloadingByWH," +
                    "isLoadingByWH = :isUnloadingByWH," +
                    "platesNumberUnloading = :platesNumberUnloading," +
                    "platesNumberLoading = :platesNumberLoading," +
                    "status = :status," +
                    "comment = :comment " +
                    "WHERE orderId = :orderId");
            query.setParameter("buyerId", order.getBuyerId());
            query.setParameter("totalSum", order.getTotalSum());
            query.setParameter("keepingDays", order.getKeepingDays());
            query.setParameter("collisUnloaded", order.getCollisUnloaded());
            query.setParameter("isUnloadingByWH", order.getIsUnloadingByWH());
            query.setParameter("isUnloadingByWH", order.getIsLoadingByWH());
            query.setParameter("platesNumberUnloading", order.getPlatesNumberUnloading());
            query.setParameter("platesNumberLoading", order.getPlatesNumberLoading());
            query.setParameter("status", order.getStatus());
            query.setParameter("comment", order.getComment());
            query.setParameter("orderId", String.valueOf(orderId));
            query.executeUpdate();
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteOrder(Order order) {
        if (order != null && orderRepository != null) {
            orderRepository.delete(order);
            return true;
        }
        return false;
    }
}
