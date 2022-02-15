package course2.servise;

import course2.Employee;
import course2.exception.EmployeeIsNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Service
public class DepartmentServiseImpl implements DepartmentServise {

    private final EmployeeService employeeService;

    public DepartmentServiseImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMinSalary(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.isInDepartment(department))
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeIsNotFoundException("Работник для отдела " + department + "не найден"));
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.isInDepartment(department))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeIsNotFoundException("Работник для отдела " + department + "не найден"));
    }

    @Override
    public Collection<Employee> getEmployeesFor(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.isInDepartment(department))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}