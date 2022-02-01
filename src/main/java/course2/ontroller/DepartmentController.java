package course2.ontroller;

import course2.Employee;
import course2.servise.DepartmentServise;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentServise departmentServise;

    public DepartmentController(DepartmentServise departmentServise) {
        this.departmentServise = departmentServise;
    }

    @GetMapping("/min-salary")
    Employee getEmployeeWithMinSalary(@RequestParam(name = "departmentId") int department) {
        return departmentServise.getEmployeeWithMinSalary(department);
    }

    @GetMapping("/max-salary")
    Employee getEmployeeWithMaxSalary(@RequestParam(name = "departmentId") int department) {
        return departmentServise.getEmployeeWithMaxSalary(department);
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    Collection<Employee> getEmployeesFor(@RequestParam(name = "departmentId") int department) {
        return departmentServise.getEmployeesFor(department);
    }

    @GetMapping("/all")
    Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return departmentServise.getAllEmployeesByDepartment();
    }
}
