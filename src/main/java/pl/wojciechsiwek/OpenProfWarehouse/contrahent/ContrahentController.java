package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("contrahents")
public class ContrahentController {
    private static final Logger logger = LoggerFactory.getLogger(ContrahentController.class);
    private final ContrahentRepository repository;

    public ContrahentController(ContrahentRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "")
    public String showAllContrahents(Model model){
        List<Contrahent> contrahents = repository.findAll();
        model.addAttribute("contrahents", contrahents);
        return "contrahents/all";
    }
}
