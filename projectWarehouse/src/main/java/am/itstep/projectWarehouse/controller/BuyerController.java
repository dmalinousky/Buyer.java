package am.itstep.projectWarehouse.controller;

import am.itstep.projectWarehouse.model.Buyer;
import am.itstep.projectWarehouse.service.BuyerDaoImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuyerController {
    @Autowired
    private BuyerDaoImpl buyerDaoImpl;


    @GetMapping ("/create_buyer")
    public String createBuyerControl(
            @RequestParam(name = "entityTitle", required = false) String entityTitle,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            @RequestParam(name = "legalAddress", required = false) String legalAddress,
            @RequestParam(name = "realAddress", required = false) String realAddress,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "emailAddress", required = false) String emailAddress,
            @RequestParam(name = "login", required = false) String login,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "passwordCheck", required = false) String passwordCheck,
            Model model
    ) {
        Boolean createMessage = null; // Message shows user if account has been created

        // Checking fields
        if (
                entityTitle != null && !entityTitle.equals("") &&
                registrationNumber != null && !registrationNumber.equals("") &&
                legalAddress != null && !legalAddress.equals("") &&
                realAddress != null && !realAddress.equals("") &&
                phoneNumber != null && !phoneNumber.equals("") &&
                emailAddress != null && !emailAddress.equals("") &&
                login != null && !login.equals("") &&
                password != null && !password.equals("") &&
                passwordCheck != null && !passwordCheck.equals("")
        ) {

            // In case of incorrect password confirmation
            if (!password.equals(passwordCheck)) {
                createMessage = false;
                model.addAttribute("createMessage", createMessage);
                return "create_buyer";

                // In case of correct password confirmation
            } else {
                // Creating Buyer object and putting provided data into it
                Buyer buyer = new Buyer();
                buyer.setEntityTitle(entityTitle);
                buyer.setRegistrationNumber(registrationNumber);
                buyer.setLegalAddress(legalAddress);
                buyer.setRealAddress(realAddress);
                buyer.setPhoneNumber(phoneNumber);
                buyer.setEmailAddress(emailAddress);
                buyer.setLogin(login);
                buyer.setPassword(password);
                buyer.setBalance(0d); // Balance is default for now
                buyer.setStatus("None"); // Status is default for now

                // Putting new buyer into DB. If everything is ok showing such message
                if (this.getBuyerDaoImpl().createBuyer(buyer)) {
                    createMessage = true;
                } else {
                    createMessage = false;
                }
            }
        }
        model.addAttribute("createMessage", createMessage);

        return "create_buyer";
    }


    @GetMapping ("/find_buyer")
    public String findBuyerControl() {
        return "find_buyer";
    }


    @PostMapping ("/find_buyer")
    public ModelAndView findBuyerControl(
            @RequestParam(name = "providedLogin", required = false) String login,
            @RequestParam(name = "providedPassword", required = false) String password,
            @RequestParam(name = "providedRegistrationNumber", required = false) String registrationNumber,
            ModelMap model
    ) {
        Boolean editMessage = null; // Shows user info if the account was found

        // Checking fields
        if (login != null && !login.equals("") &&
            password != null && !password.equals("") &&
            registrationNumber != null && !registrationNumber.equals("")
        ) {
            // Searching Buyer object and putting provided data into it
            Buyer buyer = buyerDaoImpl.findBuyer(login, password, registrationNumber);

            // If Buyer object is not null returning it
            if (buyer != null) {
                editMessage = false;
                model.addAttribute("buyer", buyer);
                model.addAttribute("editMessage", editMessage);
                return new ModelAndView("forward:/edit_buyer", model);
            } else {
                // In case Buyer object is null
                editMessage = false;
                model.addAttribute("editMessage", editMessage);
                return new ModelAndView("find_buyer", model);
            }
        }
        return new ModelAndView("find_buyer", model);
    }


    @PostMapping("/edit_buyer")
    public String editBuyerControl(Model model,
            @RequestParam(name = "entityTitle", required = false) String entityTitle,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            @RequestParam(name = "legalAddress", required = false) String legalAddress,
            @RequestParam(name = "realAddress", required = false) String realAddress,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "emailAddress", required = false) String emailAddress,
            @RequestParam(name = "login", required = false) String login,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "passwordCheck", required = false) String passwordCheck,
            @RequestParam(name = "buyerId", required = false) Long buyerId
    ) {
        Buyer buyer = null;

        Boolean editMessage = null; // Shows user info if the account was found
        if (
                entityTitle != null && !entityTitle.equals("") &&
                registrationNumber != null && !registrationNumber.equals("") &&
                legalAddress != null && !legalAddress.equals("") &&
                realAddress != null && !realAddress.equals("") &&
                phoneNumber != null && !phoneNumber.equals("") &&
                emailAddress != null && !emailAddress.equals("") &&
                login != null && !login.equals("") &&
                password != null && !password.equals("") &&
                passwordCheck != null && !passwordCheck.equals("") &&
                buyerId != null
        ) {
            // In case of incorrect password confirmation
            if (!password.equals(passwordCheck)) {
                editMessage = false;
                model.addAttribute("editMessage", editMessage);
                return "edit_warehouse";

                // In case of correct password confirmation
            } else {
                // Editing Buyer object and putting provided data into it
                buyer = new Buyer();
                buyer.setEntityTitle(entityTitle);
                buyer.setRegistrationNumber(registrationNumber);
                buyer.setLegalAddress(legalAddress);
                buyer.setRealAddress(realAddress);
                buyer.setPhoneNumber(phoneNumber);
                buyer.setEmailAddress(emailAddress);
                buyer.setLogin(login);
                buyer.setPassword(password);
                buyer.setBuyerId(buyerId);
                System.out.println(this.getBuyerDaoImpl().editBuyer(buyer));
                if (this.getBuyerDaoImpl().editBuyer(buyer)) {
                    editMessage = true;
                } else {
                    editMessage = false;
                }
            }
            model.addAttribute("buyer", buyer);
            model.addAttribute("editMessage", editMessage);
        }
        return "edit_buyer";
    }


    @GetMapping ("/delete_buyer")
    public String deleteBuyerControl() {
        return "delete_buyer";
    }


    @PostMapping ("/delete_buyer")
    public String deleteBuyerControl(
            @RequestParam(name = "login", required = false) String login,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            Model model
    ) {
        Boolean deleteMessage = null; // Shows deleting status

        // Checking fields
        if (
                login != null && !login.equals("") &&
                password != null && !password.equals("") &&
                registrationNumber != null && !registrationNumber.equals("")
        ) {
            // Getting Buyer registration number, login and password and deleting from DB + sending delete notification
            buyerDaoImpl.deleteBuyer(login, password, registrationNumber);
            deleteMessage = true;
        }
        model.addAttribute("deleteMessage", deleteMessage);

        return "delete_buyer";
    }


    @GetMapping ("/buyer_main_menu")
    public String BuyerMainMenuControl() {
        return "buyer_main_menu";
    }
}