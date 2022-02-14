package course2;

import course2.exception.EmployeeExistsException;
import course2.exception.EmployeeIsNotFoundException;
import course2.servise.EmployeeService;
import course2.servise.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static course2.EmployeeServiceImplTestConstans.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceImplTest {


    private EmployeeService out;

    @BeforeEach
    void setUp() {
        out = new EmployeeServiceImpl();
    }

    @Test
    public void testAdd() {
        Employee actualEmployee = out.add(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void testAddDuplicate() {
        out.add(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Assertions.assertThrowsExactly(EmployeeExistsException.class, () -> out.add(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1));
    }

    @Test
    void testRemoveAddedEmployee() {
        out.add(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Employee actualEmployee = out.remove(FIRST_NAME_1, LAST_NAME_1);
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void testRemoveNonExistentEmployee() {
        Assertions.assertThrowsExactly(EmployeeIsNotFoundException.class, () -> out.remove(FIRST_NAME_1, LAST_NAME_1));
    }


    @Test
    void testFindAddedEmployee() {
        out.add(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Employee actualEmployee = out.find(FIRST_NAME_1, LAST_NAME_1);
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        assertEquals(expectedEmployee, actualEmployee);
    }


    @Test
    void testFindNonExistentEmployee() {
        Assertions.assertThrowsExactly(EmployeeIsNotFoundException.class, () -> out.find(FIRST_NAME_1, LAST_NAME_1));
    }


    @Test
    void testGetAll() {
        out.add(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        out.add(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_2, SALARY_2);
        Collection<Employee> actual = out.getAll();
        List<Employee> expected = List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1),
                new Employee(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_2, SALARY_2)
        );

        assertEquals(actual.size(), expected.size());
        Assertions.assertTrue(expected.containsAll(actual));
    }
}
