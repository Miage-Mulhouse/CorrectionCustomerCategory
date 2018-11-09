package fr.miage.web.controller;

import fr.miage.core.entity.Category;
import fr.miage.core.service.CategoryService;
import javax.validation.Valid;
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
// #### V0.1 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html

// #### V0.1 Indicates that an annotated class is a "Controller" 
// #### V0.1 (e.g. a web controller).
// #### V0.1 This annotation serves as a specialization of @Component, allowing 
// #### V0.1 for implementation classes to be autodetected through classpath 
// #### V0.1 scanning. It is typically used in combination with annotated 
// #### V0.1 handler methods based on the RequestMapping annotation.
@RequestMapping("/category")
// #### V0.1 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html
// #### V0.1 Toutes les méthodes de cette classe annotées @RequestMapping ou
// #### V0.1 @GetMapping ou @PostMapping seront mappées sur une url basée sur
// #### V0.1 /category

public class CategoryController {

// #### V0.1 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html
// #### V0.1 Injection de dépendance (en l'occurrence CategoryServiceImpl)
    @Autowired
    private CategoryService categoryService;

// #### V0.1 La méthode findAll 
// #### V0.1 Comportement exécuté dans le cas d'une requête HTTP/GET sur
// #### V0.1 /category ou  /category/ 
    @GetMapping({"", "/"})
// #### V0.1 @GetMapping({"","/"}) aurait pu être remplacé par 
// #### V0.1 @RequestMapping(method = RequestMethod.GET), une affaire de goût.

// #### V0.1 Spring utilise l'injection de dépendance pour exécuter un 
// #### V0.1 comportement défini par cette méthode.
// #### V0.1 Ce comportement est plutôt une "interprétation" de cette méthode.
// #### V0.1 Par exemple, la chaîne qu'elle retrouve ne sera pas dans la
// #### V0.1 réponse HTTP (sauf si on utilise @ResponseBody). Cette chaîne doit
// #### V0.1 correspondre au nom d'un template html qui se trouve dans le dossier
// #### V0.1 src/main/resources/templates
// #### V0.1 Il s'agit du fichier src/main/resources/templates/category/list.html
// #### V0.1 Par ailleurs, l'emploi du paramètre Model model permet d'envoyer
// #### V0.1 des données au template (à la vue). En l'occurrence, il s'agit de
// #### V0.1 la liste de toutes les catégories enregistrées dans la base de 
// #### V0.1 données qui est envoyée à la vue pour être affichée.    
// #### V0.1 Le retour et les paramètres de cette fonction pilote le comportement 
// #### V0.1 de Spring. 
// #### V0.1 Pour en savoir plus : 
// #### V0.1  - https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-arguments  
// #### V0.1  - https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-return-types
    public String findAll(Model model) {
        // #### V0.1 Le but de l'URL /category est d'afficher la liste des 
        // #### V0.1 catégories existantes. La méthode categoryService.findAll()
        // #### V0.1 interroge la BDD pour retourner cette liste qui est envoyée
        // #### V0.1 au template src/main/resources/templates/category/list.html
        // #### V0.1 par l'intermédiaire du modèle dans lequel il est identifié
        // #### V0.1 par la clé "categories".
        model.addAttribute("categories", categoryService.findAll());
        return "category/list";
    }

// #### V0.1 La méthode create demande le formulaire de création d'une nouvelle
// #### V0.1 catégorie. Ce formulaire présente des champs à remplir par 
// #### V0.1 l'utilisation. Sa validation provoque le lancement d'une requête
// #### V0.1 HTTP/POST (méthode created un peu plus loin).
// #### V0.1 Ce comportement est exécuté dans le cas d'une requête HTTP/GET sur
// #### V0.1 /category/create
    @GetMapping("/create")
    public String create(Model model) {
// #### V0.1 Une categorie (vide) est créée. Il est rare d'utiliser new en
// #### V0.1 Spring, sauf dans les cas des entités (comme c'est le cas ici)
        Category category = new Category();
// #### V0.1 Elle est envoyée à la vue par l'entremise du modèle (model)
// #### V0.1 Elle sera renseignée dans le formulaire.
        model.addAttribute("category", category);
// #### V0.1 Le template src/main/resources/templates/category/edit.html
        return "category/edit";
    }

// #### V0.1 La méthode created enregistre la categorie saisie dans le  
// #### V0.1 formulaire (si ses contraintes sont respectées) 
// #### V0.1 Ce comportement est exécuté dans le cas d'une requête HTTP/POST sur
// #### V0.1 /category/create
    @PostMapping("/create")

// #### V0.1 L'entité Category (@Entity) possède un champs name qui doit 
// #### V0.1 respecter une contrainte. 
// #### V0.1 L'annotation @Valid demande à Spring (ou plutôt Hibernate Validator)
// #### V0.1 de vérifier le respect des contraintes.
// #### V0.1 L'annotation @Valid demande à Spring (ou plutôt Hibernate Validator)
// #### V0.1 Le paramètre BindingResult permet de vérifier les éventuels erreurs.
    public String created(@Valid Category category, BindingResult br) {
        if (br.hasErrors()) {
// #### V0.1 En cas d'erreur, Le template 
// #### V0.1 src/main/resources/templates/category/edit.html est invoqué
            return "category/edit";
        }
// #### V0.1 Si tout s'est bien passé, la catégorie est enregistrée.
        categoryService.save(category);

// #### V0.1 "redirect:" indique à Spring de faire une redirection, en d'autres
// #### V0.1 termes, ça revient à une requête HTTP/GET sur /category
        return "redirect:/category";
    }

// #### V0.1 La méthode delete permet de retirer une catégorie de la base de
// #### V0.1 données par son identifiant.
// #### V0.1 Ce comportement est exécuté dans le cas d'une requête HTTP/GET sur
// #### V0.1 /category/delete/{id} où est un identifiant entier (long).
    @GetMapping("/delete/{id}")
// #### V0.1 Une autre façon de passer des arguments. Elle est précisée grâce à
// #### V0.1 @PathVariable("id") où "id" correspond à {id}
    public String delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return "redirect:/category";
    }

// #### V0.1 Les deux méthodes suivantes, edit et edited, servent à modifier une
// #### V0.1 catégorie. La première (HTTP/GET) invoque le formulaire, la seconde
// #### V0.1 (HTTP/POST) réalise la modification s'il n'y a pas d'erreur.
    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("category", categoryService.findById(id).get());
        return "category/edit";
    }

    @PostMapping("/edit")
    public String edited(@Valid Category category, BindingResult br) {
        if (br.hasErrors()) {
            return "category/edit";
        }
        
// #### V0.1 (HTTP/POST) save() peut être utilisé soit
// #### V0.1    - pour la création en l'absence d'identifiant (dans ce cas, 
// #### V0.1      il calculé automatiquement)
// #### V0.1    - pour la modification avec un identifiant.
        categoryService.save(category);
        return "redirect:/category";
    }
}
