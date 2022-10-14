package com.testtask.service.impl;

import com.testtask.eception.RepositoryException;
import com.testtask.eception.ServiceException;
import com.testtask.entity.Employee;
import com.testtask.repository.inter.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepo employeeRepo;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    private static final long MOCKED_ID1 = 1L;
    private static final long MOCKED_ID2 = 2L;
    private static final String MOCKED_NAME = "N";

    @Test
    void getEmployeeById() {
        Employee employee = Employee.builder().employee_id(MOCKED_ID1).build();
        when(employeeRepo.getEmployeeById(MOCKED_ID1)).thenReturn(employee);

        assertEquals(employee, employeeService.getEmployeeById(MOCKED_ID1));
    }

    @Test
    void getEmployeeByIdThrow() {
            doThrow(RepositoryException.class).when(employeeRepo).getEmployeeById(MOCKED_ID1);

            assertThrows(ServiceException.class, ()-> employeeService.getEmployeeById(MOCKED_ID1));
    }

    @Test
    void getEmployeesByName() {
        Employee employee = Employee.builder().employee_id(MOCKED_ID1).employee_name(MOCKED_NAME).build();
        when(employeeRepo.getEmployeesByName(MOCKED_NAME)).thenReturn(List.of(employee));

        assertEquals(List.of(employee), employeeService.getEmployeesByName(MOCKED_NAME));
    }

    @Test
    void getEmployeesByNameThrow() {
        doThrow(RepositoryException.class).when(employeeRepo).getEmployeesByName(MOCKED_NAME);

        assertThrows(ServiceException.class, ()-> employeeService.getEmployeesByName(MOCKED_NAME));
    }

    @Test
    void getAllEmployees() {
        Employee employee = Employee.builder().employee_id(MOCKED_ID1).build();
        Employee employee2 = Employee.builder().employee_id(MOCKED_ID2).build();
        List<Employee> employees = List.of(employee, employee2);
        when(employeeRepo.getAllEmployees(2, 0)).thenReturn(employees);

        assertThat(employeeService.getAllEmployees(0, 2), containsInAnyOrder(employee, employee2));
    }

    @Test
    void getAllEmployeesThrow() {
        doThrow(RepositoryException.class).when(employeeRepo).getAllEmployees(2, 0);

        assertThrows(ServiceException.class, ()-> employeeService.getAllEmployees(0, 2));
    }

    @Test
    void addEmployee() {
        Employee employee = Employee.builder().employee_id(MOCKED_ID1).build();

        employeeService.addEmployee(employee);
        verify(employeeRepo, times(1)).addEmployee(employee);

    }

    @Test
    void addEmployeeThrow() {
        Employee employee = Employee.builder().employee_id(MOCKED_ID1).build();
        doThrow(RepositoryException.class).when(employeeRepo).addEmployee(employee);

        assertThrows(ServiceException.class, ()-> employeeService.addEmployee(employee));
    }

    @Test
    void updateEmployee() {
        Employee employee = Employee.builder().employee_id(MOCKED_ID1).build();

        employeeService.updateEmployee(employee);
        verify(employeeRepo, times(1)).updateEmployee(employee);
    }

    @Test
    void updateEmployeeThrow() {
        Employee employee = Employee.builder().employee_id(MOCKED_ID1).build();
        doThrow(RepositoryException.class).when(employeeRepo).updateEmployee(employee);

        assertThrows(ServiceException.class, ()-> employeeService.updateEmployee(employee));
    }

    @Test
    void deleteEmployee() {
        employeeService.deleteEmployee(MOCKED_ID1);
        verify(employeeRepo, times(1)).deleteEmployee(MOCKED_ID1);
    }

    @Test
    void deleteEmployeeThrow() {
        doThrow(RepositoryException.class).when(employeeRepo).deleteEmployee(MOCKED_ID1);

        assertThrows(ServiceException.class, ()-> employeeService.deleteEmployee(MOCKED_ID1));
    }
}