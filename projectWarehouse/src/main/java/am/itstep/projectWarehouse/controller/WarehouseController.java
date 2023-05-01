package am.itstep.projectWarehouse.controller;

import am.itstep.projectWarehouse.model.Warehouse;
import am.itstep.projectWarehouse.service.WarehouseDaoImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WarehouseController {
    @Autowired
    private WarehouseDaoImpl warehouseDaoImpl;

    @GetMapping(value = {"/create_warehouse", "/create_warehouse/{entityTitle}/{registrationNumber}/{legalAddress}/{realAddress}/{GpsCoordinates}/{phoneNumber}/{emailAddress}/{spaceFree}/{login}/{password}/{passwordCheck}/{isTemperatureWH}/{isCustomWH}"})
    public String createWarehouseControl(
            @PathVariable(name = "entityTitle", required = false) String entityTitle,
            @PathVariable(name = "registrationNumber", required = false) String registrationNumber,
            @PathVariable(name = "legalAddress", required = false) String legalAddress,
            @PathVariable(name = "realAddress", required = false) String realAddress,
            @PathVariable(name = "GpsCoordinates", required = false) String GpsCoordinates,
            @PathVariable(name = "phoneNumber", required = false) String phoneNumber,
            @PathVariable(name = "emailAddress", required = false) String emailAddress,
            @PathVariable(name = "isTemperatureWH", required = false) Boolean isTemperatureWH,
            @PathVariable(name = "isCustomWH", required = false) Boolean isCustomWH,
            @PathVariable(name = "spaceFree", required = false) Double spaceFree,
            @PathVariable(name = "login", required = false) String login,
            @PathVariable(name = "password", required = false) String password,
            @PathVariable(name = "passwordCheck", required = false) String passwordCheck,
            Model model
    ) {
        Boolean createMessage = null; // Message shows user if account has been created
        if (
                entityTitle != null && !entityTitle.equals("") &&
                registrationNumber != null && !registrationNumber.equals("") &&
                legalAddress != null && !legalAddress.equals("") &&
                realAddress != null && !realAddress.equals("") &&
                GpsCoordinates != null && !GpsCoordinates.equals("") &&
                phoneNumber != null && !phoneNumber.equals("") &&
                emailAddress != null && !emailAddress.equals("") &&
                isTemperatureWH != null && isCustomWH != null &&
                spaceFree != null &&
                login != null && !login.equals("") &&
                password != null && !password.equals("") &&
                passwordCheck != null && !passwordCheck.equals("")
        ) {
            // In case of incorrect password confirmation
            if (!password.equals(passwordCheck)) {
                createMessage = false;
                model.addAttribute("createMessage", createMessage);
                return "create_warehouse";

                // In case of correct password confirmation
            } else {
                // Creating Warehouse object and putting provided data into it
                Warehouse warehouse = new Warehouse();
                warehouse.setEntityTitle(entityTitle);
                warehouse.setRegistrationNumber(registrationNumber);
                warehouse.setLegalAddress(legalAddress);
                warehouse.setRealAddress(realAddress);
                warehouse.setGpsCoordinates(GpsCoordinates);
                warehouse.setPhoneNumber(phoneNumber);
                warehouse.setEmailAddress(emailAddress);
                warehouse.setIsTemperatureWH(isTemperatureWH);
                warehouse.setIsCustomWH(isCustomWH);
                warehouse.setSpaceFree(spaceFree);
                warehouse.setBalance(0d); // Balance is default for now
                warehouse.setStatus("None"); // Status is default for now
                warehouse.setLogin(login);
                warehouse.setPassword(password);
                warehouse.setWarehouseId(warehouse.warehouseIdGenerator());
                // Putting new Warehouse into database. If everything is ok showing such message
                if (this.getWarehouseDaoImpl().createWarehouse(warehouse)) {
                    createMessage = true;
                } else {
                    createMessage = false;
                }
            }
        }
        model.addAttribute("createMessage", createMessage);

        return "create_warehouse";
    }


    @GetMapping(value = {"/find_and_edit_warehouse"})
    public String findWarehouseControl(
            @RequestParam(name = "providedRegistrationNumber", required = false) String registrationNumber,
            Model model
    ) {
        System.out.println(registrationNumber);
        Boolean editMessage = null;
        Warehouse warehouse = null;
        if (registrationNumber != null) {
            Warehouse optionalWarehouse = this.getWarehouseDaoImpl().findWarehouse(registrationNumber);
            if (optionalWarehouse != null) {
                warehouse = optionalWarehouse;
                this.getWarehouseDaoImpl().editWarehouse(warehouse.getWarehouseId(), warehouse);
                editMessage = true;
            } else {
                editMessage = false;
            }
        }
        model.addAttribute("editMessage", editMessage);
        model.addAttribute("warehouse", warehouse);
        return "find_and_edit_warehouse";
    }

    @PostMapping("/find_and_edit_warehouse")
    public String editWarehouseControl(Warehouse warehouse, Boolean editMessage, Model model,
           @RequestParam(name = "entityTitle", required = false) String entityTitle,
           @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
           @RequestParam(name = "legalAddress", required = false) String legalAddress,
           @RequestParam(name = "realAddress", required = false) String realAddress,
           @RequestParam(name = "GpsCoordinates", required = false) String GpsCoordinates,
           @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
           @RequestParam(name = "emailAddress", required = false) String emailAddress,
           @RequestParam(name = "isTemperatureWH", required = false) Boolean isTemperatureWH,
           @RequestParam(name = "isCustomWH", required = false) Boolean isCustomWH,
           @RequestParam(name = "spaceFree", required = false) Double spaceFree,
           @RequestParam(name = "balance", required = false) Double balance,
           @RequestParam(name = "status", required = false) String status,
           @RequestParam(name = "login", required = false) String login,
           @RequestParam(name = "password", required = false) String password,
           @RequestParam(name = "warehouseId", required = false) Long warehouseId)
    {
        if (warehouse != null && warehouse.getWarehouseId().equals(warehouseId)) {
            if (
                    entityTitle != null && !entityTitle.equals("") &&
                    registrationNumber != null && !registrationNumber.equals("") &&
                    legalAddress != null && !legalAddress.equals("") &&
                    realAddress != null && !realAddress.equals("") &&
                    GpsCoordinates != null && !GpsCoordinates.equals("") &&
                    phoneNumber != null && !phoneNumber.equals("") &&
                    emailAddress != null && !emailAddress.equals("") &&
                    isTemperatureWH != null && isCustomWH != null &&
                    spaceFree != null && balance != null &&
                    status != null && !status.equals("") &&
                    login != null && !login.equals("") &&
                    password != null && !password.equals("")
            ) {
                warehouse.setEntityTitle(entityTitle);
                warehouse.setRegistrationNumber(registrationNumber);
                warehouse.setLegalAddress(legalAddress);
                warehouse.setRealAddress(realAddress);
                warehouse.setGpsCoordinates(GpsCoordinates);
                warehouse.setPhoneNumber(phoneNumber);
                warehouse.setEmailAddress(emailAddress);
                warehouse.setIsTemperatureWH(isTemperatureWH);
                warehouse.setIsCustomWH(isCustomWH);
                warehouse.setLogin(login);
                warehouse.setPassword(password);
                this.getWarehouseDaoImpl().editWarehouse(warehouseId, warehouse);

                model.addAttribute("editMessage", editMessage);
            }
        }
        return "find_and_edit_warehouse";
    }


    @GetMapping("/delete_warehouse")
    public String deleteWarehouseControl(
            @RequestParam(name = "login", required = false) String login,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            Model model
    ) {
        Boolean deleteMessage = null;
        Warehouse warehouse = null;
        if (
                login != null && !login.equals("") &&
                password != null && !password.equals("") &&
                registrationNumber != null && !registrationNumber.equals("")
        ) {
            Warehouse optionalWarehouse = this.getWarehouseDaoImpl().findWarehouse(registrationNumber);
            if (optionalWarehouse != null) {
                warehouse = optionalWarehouse;
                if (this.getWarehouseDaoImpl().deleteWarehouse(warehouse)) {
                    deleteMessage = true;
                }
            } else {
                deleteMessage = false;
            }
        }
        model.addAttribute("deleteMessage", deleteMessage);

        return "delete_warehouse";
    }


    @GetMapping ("/warehouse_main_menu")
    public String warehouseMainMenuControl() {
        return "warehouse_main_menu";
    }


    @GetMapping ("/show_warehouses")
    public String showWarehousesControl(Model model) {
        model.addAttribute("warehouses", warehouseDaoImpl.showAllWarehouses());
        return "show_warehouses";
    }

    @GetMapping ("/warehouse_details")
    public String showWarehouseControl() {
        return "warehouse_details";
    }
}