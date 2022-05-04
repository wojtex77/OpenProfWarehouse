package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("contrahents")
public class ContrahentController {
    private static final Logger logger = LoggerFactory.getLogger(ContrahentController.class);
    private final ContrahentRepository repository;
    private final ContrahentService service;

    public ContrahentController(ContrahentRepository repository, ContrahentService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(path = "")
    public String showAllContrahents(Model model){
        List<Contrahent> contrahents = repository.findAll();
        model.addAttribute("contrahents", contrahents);
        return "contrahents/all";
    }



    @GetMapping(path = "/new")
    public String showNewContrahentForm(Model model){
        Contrahent contrahent = new Contrahent();
        model.addAttribute("contrahent", contrahent);
        return "contrahents/new";
    }

    @PostMapping(path = "/add")
    public RedirectView addNewContrahentToDb(@ModelAttribute Contrahent contrahent, RedirectAttributes attributes){
        try {
            service.addContrahent(contrahent);
            attributes.addFlashAttribute("messageSuccess", "Pomyślnie dodano do bazy");
        } catch (Exception e){
            attributes.addFlashAttribute("messageWarning", "Nie dodano do bazy");
        }
        return new RedirectView("/contrahents");
    }
}
