package course2;

import course2.exception.EmployeeIsNotFoundException;
import course2.servise.DepartmentServiseImpl;
import course2.servise.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static course2.EmployeeServiceImplTestConstans.*;
import static course2.EmployeeServiceImplTestConstans.SALARY_2;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiseImplTest {

    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentServiseImpl out;

    @BeforeEach
    void setUp() {
        Mockito.when(employeeServiceMock.getAll()).thenReturn(List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1),
                new Employee(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_1, SALARY_2)
        ));
    }

    @Test
    public void testMinSalary() {
        Employee actualResult = out.getEmployeeWithMinSalary(DEPARTMENT_1);
        Employee expectedResult = new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void testMinSalaryEmptyDepartment() {
        Assertions.assertThrowsExactly(EmployeeIsNotFoundException.class, () -> out.getEmployeeWithMinSalary(DEPARTMENT_2));
    }

    @Test
    public void testMaxSalary() {
        Employee actualResult = out.getEmployeeWithMaxSalary(DEPARTMENT_1);
        Employee expectedResult = new Employee(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_1, SALARY_2);
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    void testMaxSalaryEmptyDepartment() {
        Assertions.assertThrowsExactly(EmployeeIsNotFoundException.class, () -> out.getEmployeeWithMaxSalary(DEPARTMENT_2));
    }

    @Test
    void testGetEmployeesDepartment() {
        Collection<Employee> actual = out.getEmployeesFor(DEPARTMENT_1);
        List<Employee> expected = List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1),
                new Employee(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_1, SALARY_2)
        );

        assertEquals(actual.size(), expected.size());
        Assertions.assertTrue(expected.containsAll(actual));
    }

}
