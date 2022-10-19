package com.testtask.service.impl;

import com.testtask.entity.Department;
import com.testtask.entity.Employee;
import com.testtask.entity.dto.EmployeeDTO;
import com.testtask.entity.mappers.EmployeeMapper;
import com.testtask.exception.RepositoryException;
import com.testtask.exception.ServiceException;
import com.testtask.repository.inter.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

  @Mock EmployeeRepo employeeRepo;

  @InjectMocks EmployeeServiceImpl employeeService;

  private static final long MOCKED_ID1 = 1L;
  private static final long MOCKED_ID2 = 2L;
  private static final String MOCKED_NAME = "N";

  @Test
  void getEmployeeById() {
    Employee employee = Employee.builder().employeeId(MOCKED_ID1).department(Department.builder().departmentId(MOCKED_ID1).build()).build();
    when(employeeRepo.getEmployeeById(MOCKED_ID1)).thenReturn(employee);

    assertEquals(
        EmployeeMapper.INSTANCE.toDtoWithId(employee), employeeService.getEmployeeById(MOCKED_ID1));
  }

  @Test
  void getEmployeesByName() {
    Employee employee = Employee.builder().employeeId(MOCKED_ID1).department(Department.builder().departmentId(MOCKED_ID1).build()).build();
    when(employeeRepo.getEmployeesByName(MOCKED_NAME)).thenReturn(List.of(employee));

    assertEquals(
        Stream.of(employee).map(EmployeeMapper.INSTANCE::toDtoWithId).collect(Collectors.toList()),
        employeeService.getEmployeesByName(MOCKED_NAME));
  }

  @Test
  void getAllEmployees() {
    Employee employee = Employee.builder().department(Department.builder().departmentId(MOCKED_ID1).build()).employeeId(MOCKED_ID1).build();
    Employee employee2 = Employee.builder().department(Department.builder().departmentId(MOCKED_ID1).build()).employeeId(MOCKED_ID2).build();
    List<Employee> employees = List.of(employee, employee2);
    when(employeeRepo.getAllEmployees(2, 0)).thenReturn(employees);

    assertThat(
        employeeService.getAllEmployees(0, 2),
        containsInAnyOrder(
            EmployeeMapper.INSTANCE.toDtoWithId(employee),
            EmployeeMapper.INSTANCE.toDtoWithId(employee2)));
  }

  @Test
  void addEmployee() {
    EmployeeDTO employee = EmployeeDTO.builder().department(MOCKED_ID1).build();

    employeeService.addEmployee(employee);
    verify(employeeRepo, times(1)).addEmployee( EmployeeMapper.INSTANCE.fromDto(employee));
  }

  @Test
  void updateEmployee() {
    Employee employee = Employee.builder().department(Department.builder().departmentId(MOCKED_ID1).build()).employeeId(MOCKED_ID1).build();

    employeeService.updateEmployee(EmployeeMapper.INSTANCE.toDtoWithId(employee));
    verify(employeeRepo, times(1)).updateEmployee(employee);
  }

  @Test
  void deleteEmployee() {
    employeeService.deleteEmployee(MOCKED_ID1);
    verify(employeeRepo, times(1)).deleteEmployee(MOCKED_ID1);
  }
}
