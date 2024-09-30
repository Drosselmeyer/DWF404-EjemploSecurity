package sv.edu.udb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VendedorController {

    @GetMapping("/vendedor/home")
    public String vendedorHome() {
        return "vendedor/home";
    }
}
