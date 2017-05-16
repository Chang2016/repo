package org.strupp.test;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.strupp.controller.EmployeeController;
import org.strupp.model.Employee;
import org.strupp.representation.CarResource;
import org.strupp.representation.EmployeeResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EmployeeController.class })
@WebAppConfiguration
public class EmployeeControllerTest {
	private static final String BASE_URI = "http://localhost:8080/Spring_HibernateRest";
	
	@Test
    public void createNewAuthor() {
//        RestTemplate restTemplate = new RestTemplate();
//        
//        // creating new author
//        Employee emp = new Employee();
//        emp.setFirstName("Brian");
//        emp.setLastName("Goetz");
//        emp.setEmail("brian@goetz.de");
//        emp.setPhone("030-3087654");
//        ResponseEntity<Void> response = restTemplate.postForEntity(BASE_URI + "/authors", emp, Void.class);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        
//        // retrieving the newly created author details using the URI received in Location header
//        EmployeeResource author = restTemplate.getForObject(response.getHeaders().getLocation(), EmployeeResource.class);
//        assertEquals(emp.getLastName(), author.getLastName());
////        assertTrue(author.getId() > 0);
//        
//        // getting the author's books using the link with rel books
//        Link authorBooksLink = author.getLink("cars");
//        List<CarResource> authorBooks = restTemplate.getForObject(authorBooksLink.getHref(), List.class);
//        assertTrue(authorBooks.isEmpty());
    }
}
