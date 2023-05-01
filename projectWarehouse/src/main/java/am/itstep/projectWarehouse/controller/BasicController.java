package am.itstep.projectWarehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
    @GetMapping("/main_page")
    public String chooseRoleControl() {
        return "main_page";
    }

    @GetMapping("/about_project")
    public String aboutProjectControl() {
        return "about_project";
    }
}
