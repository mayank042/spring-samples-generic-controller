package com.sample.springcrudgeneric.employee;

import com.sample.springcrudgeneric.crud.CrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends CrudServiceImpl<Employee, String> {

    public EmployeeService(EmployeeRepository repository) {
        super(repository);
    }
}
