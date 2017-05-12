package org.strupp.representation.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import org.strupp.controller.EmployeeController;
import org.strupp.model.Employee;
import org.strupp.representation.EmployeeResource;

@Component
public class EmployeeResourceAssembler extends ResourceAssemblerSupport<Employee, EmployeeResource> {
	
	public EmployeeResourceAssembler() {
		super(EmployeeController.class, EmployeeResource.class);
	}
	
	@Override
	public EmployeeResource toResource(Employee employee) {
		EmployeeResource resource = createResourceWithId(employee.getId(), employee);
		employee.getCars().forEach(c -> {
			resource.add(linkTo(methodOn(EmployeeController.class).getEmployee(c.getId())).withRel("car"));
		});
		return resource;
	}

	@Override
	protected EmployeeResource instantiateResource(Employee entity) {
		return new EmployeeResource(entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPhone());
	}

}
