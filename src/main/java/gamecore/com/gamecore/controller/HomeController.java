package gamecore.com.gamecore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(ModelMap m){
        m.put("view", "/home/home");
        return "_t/frame";
    }
    @PostMapping("/")
    public String registro(ModelMap m) {
        m.put("view", "/home/home");
        return "_t/frame";
    }
    
}
