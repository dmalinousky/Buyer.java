package am.itstep.projectWarehouse.controller;

import am.itstep.projectWarehouse.model.Order;
import am.itstep.projectWarehouse.service.OrderDaoImpl;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

//@RestController
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Controller
public class OrderController {
    @Autowired
    private OrderDaoImpl orderDaoImpl;


    @GetMapping(value = {"/create_order", "/create_order/{warehouseId}/{buyerId}/{totalSum}/{keepingDays}/{collisUnloaded}/{isUnloadingByWH}/{isLoadingByWH}/{platesNumberUnloading}/{platesNumberLoading}/{status}/{comment}"})
    public String createOrderControl(
            @PathVariable(name = "warehouseId", required = false) Long warehouseId,
            @PathVariable(name = "buyerId", required = false) Long buyerId,
            @PathVariable(name = "totalSum", required = false) Double totalSum,
            @PathVariable(name = "keepingDays", required = false) Integer keepingDays,
            @PathVariable(name = "collisUnloaded", required = false) String collisUnloaded,
            @PathVariable(name = "isUnloadingByWH", required = false) Boolean isUnloadingByWH,
            @PathVariable(name = "isLoadingByWH", required = false) Boolean isLoadingByWH,
            @PathVariable(name = "platesNumberUnloading", required = false) String platesNumberUnloading,
            @PathVariable(name = "platesNumberLoading", required = false) String platesNumberLoading,
            @PathVariable(name = "status", required = false) String status,
            @PathVariable(name = "comment", required = false) String comment,
            Model model
    )  {
        Boolean createMessage = null; // Message shows user if account has been created
        System.out.println(isLoadingByWH);
        if (
                warehouseId != null && !warehouseId.equals("") &&
                        buyerId != null && totalSum != null &&
                        keepingDays != null && collisUnloaded != null &&
                        isUnloadingByWH != null && isLoadingByWH != null &&
                        platesNumberUnloading != null && !platesNumberUnloading.equals("") &&
                        platesNumberLoading != null && !platesNumberLoading.equals("") &&
                        status != null && !status.equals("") && comment != null && !comment.equals("")
        ) {
            Order order = new Order();
            order.setWarehouseId(warehouseId);
            order.setBuyerId(buyerId);
            order.setTotalSum(totalSum);
            order.setKeepingDays(keepingDays);
            order.setCollisUnloaded(Integer.parseInt(collisUnloaded));
            order.setIsUnloadingByWH(isUnloadingByWH);
            if (isLoadingByWH) {
                order.setIsLoadingByWH(true);
            } else {
                order.setIsLoadingByWH(false);
            }
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
        return "create_order";
    }

    @GetMapping(value = {"/find_and_edit_order"})
    public String findOrderControl(
            @RequestParam(name = "searchOrderId", required = false) Long searchOrderId,
            Model model
    ) {
        Boolean editMessage = null;
        Order order = null;
        if (searchOrderId != null) {
            Optional<Order> optionalOrder = this.getOrderDaoImpl().findOrder(searchOrderId);
            if (optionalOrder.isPresent()) {
                order = optionalOrder.get();
                this.getOrderDaoImpl().editOrder(searchOrderId, order);
                editMessage = true;
            } else {
                editMessage = false;
            }
        }
        model.addAttribute("editMessage", editMessage);
        model.addAttribute("order", order);
        return "find_and_edit_order";
    }

    @PostMapping("/find_and_edit_order")
    public String editOrderControl(Order order, Boolean editMessage, Model model,
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
        if (order != null && order.getOrderId().equals(orderId)) {
            if (
                    warehouseId != null && !warehouseId.equals("") &&
                    buyerId != null && totalSum != null &&
                    keepingDays != null && collisUnloaded != null &&
                    isUnloadingByWH != null && isLoadingByWH != null &&
                    platesNumberUnloading != null && !platesNumberUnloading.equals("") &&
                    platesNumberLoading != null && !platesNumberLoading.equals("") &&
                    status != null && !status.equals("") && comment != null && !comment.equals("")
            ) {
                order.setWarehouseId(warehouseId);
                order.setBuyerId(buyerId);
                order.setTotalSum(totalSum);
                order.setKeepingDays(keepingDays);
                order.setCollisUnloaded(Integer.parseInt(collisUnloaded));
                order.setIsUnloadingByWH(isUnloadingByWH);
                order.setIsLoadingByWH(isLoadingByWH);
                order.setPlatesNumberUnloading(platesNumberUnloading);
                order.setPlatesNumberLoading(platesNumberLoading);
                order.setStatus(status);
                order.setComment(comment);

                this.getOrderDaoImpl().editOrder(orderId, order);

                model.addAttribute("editMessage", editMessage);
            }
        }
        return "find_and_edit_order";
    }


    @GetMapping("/delete_order")
    public String deleteOrderControl(
            @RequestParam(name = "deleteOrderId", required = false) Long deleteOrderId,
            Model model
    ) {
        Boolean deleteMessage = null;
        Order order = null;
        if (deleteOrderId != null) {
            Optional<Order> optionalOrder = this.getOrderDaoImpl().findOrder(deleteOrderId);
            if (optionalOrder.isPresent()) {
                order = optionalOrder.get();
                if (this.getOrderDaoImpl().deleteOrder(order)) {
                    deleteMessage = true;
                }
            } else {
                deleteMessage = false;
            }

        }
        model.addAttribute("deleteMessage", deleteMessage);

        return "delete_order";
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
