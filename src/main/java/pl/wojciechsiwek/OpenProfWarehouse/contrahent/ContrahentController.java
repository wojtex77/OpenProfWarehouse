package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String showNewContrahentForm(Model model, @ModelAttribute("contrahent") Contrahent contrahent) {
        if (contrahent == null) {
            contrahent = new Contrahent();
        }
        model.addAttribute("action", "new");
        model.addAttribute("contrahent", contrahent);
        return "contrahents/new";
    }


    @GetMapping(path = "/edit/{id}")
    public String showEditContrahentForm(Model model, @ModelAttribute("contrahent") Contrahent contrahent, @PathVariable int id) {
        contrahent = repository.getById(id);
        model.addAttribute("action", "edit");
        model.addAttribute("contrahent", contrahent);
        return "contrahents/edit";
    }

    @PostMapping(path = "/saveChanges")
    public RedirectView saveEditedContrahentToDb(@ModelAttribute Contrahent contrahent, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView();
        try {
            attributes.addFlashAttribute("contrahent", contrahent);
            service.addContrahent(contrahent);
            attributes.addFlashAttribute("messageSuccess", "Pomy??lnie edytowano");
            redirectView.setUrl("/contrahents");
        } catch (DuplicatedEntryException e) {
            attributes.addFlashAttribute("messageWarning", "W bazie istnieje ju?? u??ytkownik o podanym aliasie lub nazwie");
            redirectView.setUrl("/contrahents/edit/" + contrahent.getId());
        } catch (Exception e) {
            attributes.addFlashAttribute("messageWarning", "Nie uda??o si?? zapisa?? zmian do bazy");
        }
        return redirectView;
    }

    @PostMapping(path = "/add")
    public RedirectView addNewContrahentToDb(@ModelAttribute Contrahent contrahent, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView();
        try {
            service.addContrahent(contrahent);
            attributes.addFlashAttribute("messageSuccess", "Pomy??lnie dodano do bazy");
            redirectView.setUrl("/contrahents");
        } catch (DuplicatedEntryException e) {
            attributes.addFlashAttribute("messageWarning", "W bazie istnieje ju?? u??ytkownik o podanym aliasie lub nazwie");
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
            attributes.addFlashAttribute("messageSuccess", "Pomy??lnie usuni??to");
        } catch (ContrahentRemoveNotAllowedException e) {
            attributes.addFlashAttribute("messageWarning", "Nie mo??na usun????, kontrahent w u??yciu");
        } catch (Exception e) {
            attributes.addFlashAttribute("messageWarning", "Nie uda??o si?? usun????");
        }
        return new RedirectView("/contrahents");
    }
}
