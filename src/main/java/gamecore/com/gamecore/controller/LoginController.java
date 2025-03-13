package gamecore.com.gamecore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    
    @GetMapping("login")
    public String login(
            ModelMap m) {
        m.put("view", "login/login");
        return "_t/frame";
    }

    @PostMapping("login")
    public String loginPost(
    ) {
        return "redirect:/";
    }
}
