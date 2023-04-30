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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

                // Putting new buyer into database. If everything is ok showing such message
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


    @GetMapping ("/edit_buyer")
    public String editBuyerControl(
            @RequestParam(name = "providedLogin", required = false) String login,
            @RequestParam(name = "providedPassword", required = false) String password,
            Model model
    ) {
        Buyer buyer = null;
        Boolean editMessage = null; // Shows user info if the account was found
        if (login != null && !login.equals("") && password != null && !password.equals("")) {
            // Finding Buyer object and putting provided data into it
            buyer = buyerDaoImpl.editBuyer(login, password);
            if (buyer != null) {
                editMessage = true;
            } else {
                editMessage = false;
            }
            model.addAttribute("buyer", buyer);
            model.addAttribute("editMessage", editMessage);

            return "edit_buyer";
        }
        model.addAttribute("buyer", buyer);
        model.addAttribute("editMessage", editMessage);

        return "edit_buyer";
    }


    @GetMapping ("/delete_buyer")
    public String deleteBuyerControl(
            @RequestParam(name = "login", required = false) String login,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            Model model
    ) {
        System.out.println(login + "" + password + "" + registrationNumber);
        Boolean deleteMessage = false;

        if (
                login != null && !login.equals("") &&
                password != null && !password.equals("") &&
                registrationNumber != null && !registrationNumber.equals("")
        ) {
            // Getting Buyer registration number and deleting from DB + sending delete notification
            buyerDaoImpl.deleteBuyer(registrationNumber);
            deleteMessage = true;
        }
        model.addAttribute("deleteMessage", deleteMessage);

        return "delete_buyer";
    }

    @GetMapping ("/buyer_main_menu")
    public String createBuyerControl() {

        return "buyer_main_menu";
    }

    @GetMapping ("/about_project")
    public String aboutProjectControl() {
        return "about_project";
    }
}