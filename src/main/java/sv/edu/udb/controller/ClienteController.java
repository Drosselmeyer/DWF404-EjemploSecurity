package sv.edu.udb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    @GetMapping("/cliente/home")
    public String clienteHome() {
        return "cliente/home";
    }
}
