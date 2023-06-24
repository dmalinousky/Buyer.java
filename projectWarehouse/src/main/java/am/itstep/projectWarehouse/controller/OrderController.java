package am.itstep.projectWarehouse.controller;

import am.itstep.projectWarehouse.model.Buyer;
import am.itstep.projectWarehouse.model.Order;
import am.itstep.projectWarehouse.model.Warehouse;
import am.itstep.projectWarehouse.service.OrderDaoImpl;
import jakarta.servlet.ServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

//@RestController
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Controller
public class OrderController {
    @Autowired
    private OrderDaoImpl orderDaoImpl;


    @GetMapping("/order_create")
    public String createOrderControl() {
        return "order_create";
    }


    @PostMapping(value = {"/order_create"})
    public String createOrderControl(
            @RequestParam(name = "warehouseId", required = false) Long warehouseId,
            @RequestParam(name = "buyerId", required = false) Long buyerId,
            @RequestParam(name = "totalSum", required = false) Double totalSum,
            @RequestParam(name = "keepingDays", required = false) Integer keepingDays,
            @RequestParam(name = "collisUnloaded", required = false) String collisUnloaded,
            @RequestParam(name = "isUnloadingByWH", required = false) Boolean isUnloadingByWH,
            @RequestParam(name = "isLoadingByWH", required = false) Boolean isLoadingByWH,
            @RequestParam(name = "platesNumberUnloading", required = false) String platesNumberUnloading,
            @RequestParam(name = "platesNumberLoading", required = false) String platesNumberLoading,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "comment", required = false) String comment,
            Model model
    ) {
        Boolean createMessage = null; // Message shows user if account has been created

        // Checking fields
        if (
                warehouseId != null && buyerId != null && totalSum != null &&
                keepingDays != null && collisUnloaded != null &&
                platesNumberUnloading != null && !platesNumberUnloading.equals("") &&
                platesNumberLoading != null && !platesNumberLoading.equals("") &&
                status != null && !status.equals("")
        ) {

            // Creating orders using provided info
            Order order = new Order();
            order.setOrderId(order.orderIdGenerator());
            order.setWarehouseId(warehouseId);
            order.setBuyerId(buyerId);
            order.setTotalSum(totalSum);
            order.setKeepingDays(keepingDays);
            order.setCollisUnloaded(Integer.parseInt(collisUnloaded));
            order.setIsUnloadingByWH(isUnloadingByWH != null);
            order.setIsLoadingByWH(isLoadingByWH != null);
            order.setPlatesNumberUnloading(platesNumberUnloading);
            order.setPlatesNumberLoading(platesNumberLoading);
            order.setStatus(status);
            order.setComment(comment);
            if (orderDaoImpl.createOrder(order)) {
                createMessage = true;
            } else {
                createMessage = false;
            }
        }
        model.addAttribute("createMessage", createMessage);
        return "order_create";
    }


    @GetMapping(value = {"/order_find"})
    public String findOrderControl() {
        return "order_find";
    }


    @PostMapping("/order_find")
    public ModelAndView findOrderControl(
            @RequestParam(name = "searchOrderId", required = false) Long searchOrderId,
            ModelMap model
    ) {
        Boolean findMessage = null;

        if (searchOrderId != null) {
            Order orderToFind = this.getOrderDaoImpl().findOrder(searchOrderId);
            if (orderToFind != null) {
                findMessage = true;
                model.addAttribute("findMessage", findMessage);
                model.addAttribute("order", orderToFind);

                return new ModelAndView("forward:/order_edit", model);
            } else {
                findMessage = false;
                model.addAttribute("findMessage", findMessage);

                return new ModelAndView("order_find");
            }
        }
        model.addAttribute("findMessage", findMessage);

        return new ModelAndView("order_find", model);
    }


    @GetMapping(value = {"/order_edit"})
    public String editOrderControl() {
        return "order_edit";
    }


    @PostMapping("/order_edit")
    public String editOrderControl(
           ModelMap model,
           @RequestParam(name = "orderId", required = false) Long orderId,
           @RequestParam(name = "warehouseId", required = false) Long warehouseId,
           @RequestParam(name = "buyerId", required = false) Long buyerId,
           @RequestParam(name = "totalSum", required = false) Double totalSum,
           @RequestParam(name = "keepingDays", required = false) Integer keepingDays,
           @RequestParam(name = "collisUnloaded", required = false) String collisUnloaded,
           @RequestParam(name = "isUnloadingByWH", required = false) Boolean isUnloadingByWH,
           @RequestParam(name = "isLoadingByWH", required = false) Boolean isLoadingByWH,
           @RequestParam(name = "platesNumberUnloading", required = false) String platesNumberUnloading,
           @RequestParam(name = "platesNumberLoading", required = false) String platesNumberLoading,
           @RequestParam(name = "status", required = false) String status,
           @RequestParam(name = "comment", required = false) String comment) {

        Boolean editMessage = null;

        Order order = this.getOrderDaoImpl().findOrder(orderId);

        if (order != null) {
            if (
                    warehouseId != null && buyerId != null && totalSum != null &&
                    keepingDays != null && collisUnloaded != null &&
                    platesNumberUnloading != null && !platesNumberUnloading.equals("") &&
                    platesNumberLoading != null && !platesNumberLoading.equals("") &&
                    status != null && !status.equals("") && comment != null && !comment.equals("")
            ) {
                order.setWarehouseId(warehouseId);
                order.setBuyerId(buyerId);
                order.setTotalSum(totalSum);
                order.setKeepingDays(keepingDays);
                order.setCollisUnloaded(Integer.parseInt(collisUnloaded));
                order.setIsUnloadingByWH(isUnloadingByWH != null);
                order.setIsLoadingByWH(isLoadingByWH != null);
                order.setPlatesNumberUnloading(platesNumberUnloading);
                order.setPlatesNumberLoading(platesNumberLoading);
                order.setStatus(status);
                order.setComment(comment);

                this.getOrderDaoImpl().editOrder(order.getOrderId(), order);

                editMessage = true;

                model.addAttribute("editMessage", editMessage);
            }
        }
        return "order_edit";
    }


    @GetMapping("/order_delete")
    public String deleteOrderControl() {
        return "order_delete";
    }

    @PostMapping("/order_delete")
    public String deleteOrderControl(
            @RequestParam(name = "deleteOrderId", required = false) Long deleteOrderId,
            Model model
    ) {
        Boolean deleteMessage = null;
        Order order = null;
        if (deleteOrderId != null) {
            Order optionalOrder = this.getOrderDaoImpl().findOrder(deleteOrderId);
            if (optionalOrder != null) {
                order = optionalOrder;
                if (this.getOrderDaoImpl().deleteOrder(order)) {
                    deleteMessage = true;
                }
            } else {
                deleteMessage = false;
            }
        }
        model.addAttribute("deleteMessage", deleteMessage);

        return "order_delete";
    }

    @GetMapping("/warehouse_orders_show")
    public String showWarehouseOrdersControl(
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            @RequestParam(name = "warehouseId", required = false) Long warehouseId,
            Model model
    ) {
        if (
                registrationNumber != null && !registrationNumber.equals("") &&
                warehouseId != null
        ) {
            model.addAttribute("orders", orderDaoImpl.showAllOrders(warehouseId, new Warehouse()));
        }
        return "warehouse_orders_show";
    }


    @GetMapping("/buyer_orders_show")
    public String showBuyerOrdersControl(
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            @RequestParam(name = "buyerId", required = false) Long buyerId,
            Model model
    ) {
        if (
                registrationNumber != null && !registrationNumber.equals("") &&
                buyerId != null
        ) {
            model.addAttribute("orders", orderDaoImpl.showAllOrders(buyerId, new Buyer()));
        }
        return "buyer_orders_show";
    }


    public JSONObject requestParamsToJSON(ServletRequest req) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        Map<String,String[]> params = req.getParameterMap();
        for (Map.Entry<String,String[]> entry : params.entrySet()) {
            String v[] = entry.getValue();
            Object o = (v.length == 1) ? v[0] : v;
            jsonObj.put(entry.getKey(), o);
        }
        return jsonObj;
    }
}
