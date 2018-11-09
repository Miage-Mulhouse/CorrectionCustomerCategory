package fr.miage.core.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.miage.core.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import fr.miage.config.SpringCoreConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringCoreConfigTest.class })
public class CustomerRepositoryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRepositoryTest.class);

	private static final String NAME = "Fnac";

	@Autowired
	CustomerRepository customerRepository;

	@Before
	public void initObjects() {
		if (customerRepository.findByName(NAME) == null) {
			customerRepository.save(new Customer(NAME));
		}
	}

	@Test
	public void findByName_success() {
		Customer entity = (Customer) customerRepository.findByName(NAME);
		assertNotNull(entity);
		assertEquals(entity.getName(), NAME);

		LOGGER.info(">>>>> customerRepository findByName_success OK");
	}

	@Test
	public void findByName_notFound() {
		Customer entity = (Customer) customerRepository.findByName("CUSTOMER");
		assertNull(entity);

		LOGGER.info(">>>>> customerRepository findByLastName_notFound OK");
	}

}