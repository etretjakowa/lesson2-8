package course2.servise;


import course2.Employee;
import course2.exception.EmployeeExistsException;
import course2.exception.EmployeeIsNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;


    public EmployeeServiceImpl() {
        employees = new LinkedHashMap<>();
    }

    private boolean contains(String key) {
        return employees.containsKey(key);
    }

    @Override
    public Employee add(String firstName, String lastName, int department, int salary) {
        Employee newEmployee = new Employee(firstName, lastName, department, salary);
        return add(newEmployee);
    }


    @Override
    public Employee add(Employee employee) {
        String key = getKey(employee);

        if (contains(key)) {
            throw new EmployeeExistsException();
        }
        employees.put(key, employee);
        return employee;
    }


    @Override
    public Employee remove(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        return remove(newEmployee);
    }


    @Override
    public Employee remove(Employee employee) {
        Employee deletedValue = employees.remove(getKey(employee));
        if (deletedValue == null) {
            throw new EmployeeIsNotFoundException();
        }
        return deletedValue;
    }


    @Override
    public Employee find(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        Employee employee = employees.get(key);
        if (employee == null) {
            throw new EmployeeIsNotFoundException();
        }
        return employee;
    }


    @Override
    public Collection<Employee> getAll() {
        return Set.copyOf(employees.values());
    }

    private String getKey(Employee employee) {
        return getKey(employee.getFirstName(), employee.getLastName());
    }

    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }


}