package gamecore.com.gamecore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @GetMapping("/login")
    public String login(ModelMap m) 
    {
        m.put("view", "login/login");
    
        return "_t/frame";
    }

    @PostMapping("login")
    public String loginPost(@RequestParam String loginname, 
                            @RequestParam String password, 
                            ModelMap model) 
    {
        if(loginService.validateUser(loginname, password)) 
        {
            return "redirect:/";
        }
        else
        {
            model.put("error", "Usuario o contraseña incorrectos");
            return "login/login";
        }
    }
}
