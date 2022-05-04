package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
    public String showAllContrahents(Model model) {
        List<Contrahent> contrahents = repository.findAll();
        model.addAttribute("contrahents", contrahents);
        return "contrahents/all";
    }


    @GetMapping(path = "/new")
    public String showNewContrahentForm(Model model, @ModelAttribute ("contrahent") Contrahent contrahent) {
        if (contrahent == null) {
            contrahent = new Contrahent();
        }
        model.addAttribute("contrahent", contrahent);
        return "contrahents/new";
    }

    @PostMapping(path = "/add")
    public RedirectView addNewContrahentToDb(@ModelAttribute Contrahent contrahent, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView();
        try {
            service.addContrahent(contrahent);
            attributes.addFlashAttribute("messageSuccess", "Pomyślnie dodano do bazy");
            redirectView.setUrl("/contrahents");
        } catch (DuplicatedEntryException e) {
            attributes.addFlashAttribute("messageWarning", "W bazie istnieje już użytkownik o podanym aliasie lub nazwie");
            attributes.addFlashAttribute("contrahent", contrahent);
            redirectView.setUrl("/contrahents/new");
        } catch (Exception e) {
            attributes.addFlashAttribute("messageWarning", "Nie dodano do bazy");
        }
        return redirectView;
    }


    @GetMapping(path = "/delete/{id}")
    public RedirectView removeContrahentFromDb(RedirectAttributes attributes, @PathVariable int id) {
        try {
            service.delete(id);
            attributes.addFlashAttribute("messageSuccess", "Pomyślnie usunięto");
        } catch (Exception e) {
            attributes.addFlashAttribute("messageWarning", "Nie udało się usunąć");
        }
        return new RedirectView("/contrahents");
    }
}