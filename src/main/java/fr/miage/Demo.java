package fr.miage;

import fr.miage.core.entity.Category;
import fr.miage.core.entity.Customer;
import fr.miage.core.service.CategoryService;
import fr.miage.core.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Demo implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {

        // Customers
        Customer c1 = customerService.findByName("Fnac");
        if (c1 == null) {
            c1 = new Customer("Fnac");
            customerService.save(c1);
            LOGGER.info("Fnac created");
        }
        Customer c2 = customerService.findByName("DECATHLON");
        if (c2 == null) {
            c2 = new Customer("DECATHLON");
            customerService.save(c2);
            LOGGER.info("DECATHLON created");
        }
    
        
        createCategory("A");
        createCategory("B");
        createCategory("Z");
    }

    private void createCategory(String name) {
        
        Category c = categoryService.findByName(name);
        if (c == null) {
            c = new Category(name);
            categoryService.save(c);
            LOGGER.info(name + " created");
        }
    }
}