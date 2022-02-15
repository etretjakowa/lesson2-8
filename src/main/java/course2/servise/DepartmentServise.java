package course2.servise;

import course2.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentServise {
    Employee getEmployeeWithMinSalary(int department);

    Employee getEmployeeWithMaxSalary(int department);

    Collection<Employee> getEmployeesFor(int department);

    Map<Integer, List<Employee>> getAllEmployeesByDepartment();
}
