package fr.miage.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import fr.miage.core.entity.Customer;
import fr.miage.core.repository.CustomerRepository;
import fr.miage.core.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceTest.class);

    private static final String NAME = "Fnac";

    @InjectMocks
    CustomerService service = new CustomerServiceImpl();

    @Mock
    CustomerRepository mockCustomerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll_success() {

        // Mock du repository



        LOGGER.info(">>>>> CustomerService findAll_success OK");
    }

}
