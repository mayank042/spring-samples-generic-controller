package com.sample.springcrudgeneric.employee;

import com.sample.springcrudgeneric.crud.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController extends CrudController<Employee, String> {

    public EmployeeController(EmployeeService service) {
        super(service);
    }
}
