package fr.miage.web.controller;

import fr.miage.Application;
import fr.miage.core.entity.Category;
import fr.miage.core.entity.Customer;
import fr.miage.core.service.CategoryService;
import fr.miage.core.service.CustomerService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CategoryService categoryService;

    // V0.6 #### Pour pouvoir afficher tous les clients d'une catégorie 
    // V0.6 #### par l'url. Par exemple l'url /customer/B permet d'afficher tous
    // V0.6 #### les clients de catégorie B.
    // V0.6 #### Remarque : le fonctionnement précédent reste d'actualité, 
    // V0.6 #### c'est-à-dire que /customer ou /customer/ affiche toujours la
    // V0.6 #### liste de tous les étudiants.
    
    // V0.6 #### Une solution est d'utiliser @PathVariable
    // V0.6 ####    - categoryname contient le nom d'une éventuelle catégorie
    // V0.6 ####    - categoryname n'est pas requis. En son absence, c'est la
    // V0.6 ####      liste complète qui doit s'afficher    
    // V0.6 #### et d'exploiter le template "customer/list" qui existe et dont
    // V0.6 #### le but est d'afficher une liste de clients (quelle que soit  
    // V0.6 #### cette liste. Il suffit donc de générer la liste à afficher.                   
    @GetMapping({"", "/{categoryname}"})
    public String findAll(@PathVariable(required = false, name = "categoryname") String categoryname, Model model) {
        // V0.6 ####  En cas d'absence de catégorie dans l'url, categoryname est null
        if (categoryname == null) {
            // V0.6 ####  C'est le signe pour afficher toute la liste.
            model.addAttribute("customers", customerService.findAll());
        } else {
            // V0.6 #### Sinon, on récupère le nom de la catégorie voulue
            // V0.6 #### par lequel on récupère la catégorie associée 
            // V0.6 #### Remarque : il y a une contrainte d'unicité sur le nom 
            // V0.6 #### dans la définition de la table.
            // V0.6 #### Grâce à la nouvelle requête findByName() ajoutée pour 
            // V0.6 #### l'occasion, on obtient 
            Category category = categoryService.findByName(categoryname);
            model.addAttribute("categoryname", categoryname);
            if (category == null) {
                // V0.6 #### Il est possible que la catégorie demandée n'existe
                // V0.6 #### pas. Dans ce cas, category vaut null et on envoie
                // V0.6 #### une liste vide.
                model.addAttribute("customers", null);
            } else {
                // V0.6 #### Si la catégorie existe, on envoie la liste des 
                // V0.6 #### clients de cette catégorie grâce à la nouvelle
                // V0.6 #### requête findByCategory.
                model.addAttribute("customers", customerService.findByCategory(category));
            }
        }
        return "customer/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        model.addAttribute("categories", categoryService.findAll());
        return "customer/edit";
    }

    @PostMapping("/create")
    public String created(@Valid Customer customer, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "customer/edit";
        }
        customerService.save(customer);

        return "redirect:/customer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return "redirect:/customer";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id).get());
        model.addAttribute("categories", categoryService.findAll());
        return "customer/edit";
    }

    @PostMapping("/edit")
    public String edited(@Valid Customer customer, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "customer/edit";
        }
        customerService.save(customer);
        return "redirect:/customer";
    }
}
