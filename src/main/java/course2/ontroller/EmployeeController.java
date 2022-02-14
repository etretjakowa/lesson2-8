package course2.ontroller;


import course2.Employee;
import course2.servise.EmployeeServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collection;

@RestController
@RequestMapping("/employee")

public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, int salary) {
        if (!StringUtils.isAlpha(firstName) && !StringUtils.isAlpha(lastName)) {
            throw new RuntimeException();
        }

        employeeService.add(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), department, salary);


        return "Сотрудник " + firstName + " " + lastName + " успешно создан.";
    }


    @GetMapping("/remove")
    public String remove(@RequestParam String firstName, @RequestParam String lastName) {
        employeeService.remove(firstName, lastName);
        return "Сотрудник " + firstName + " " + lastName + " удален.";
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping("/all")
    public Collection<Employee> all() {
        return employeeService.getAll();
    }
}
