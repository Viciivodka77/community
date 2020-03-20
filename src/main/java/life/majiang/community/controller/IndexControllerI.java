package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControllerI {

    @GetMapping({"/","index"})
    public String index(){
        return "index";
    }
}
