package com.testtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.entity.Department;
import com.testtask.entity.Employee;
import com.testtask.service.inter.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
  @MockBean private EmployeeService employeeService;
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  /*DEPARTMENT*/
  private static final String MOCKED_DEP_NAME = "Dep";
  /*EMPLOYEE*/
  private static final long MOCKED_ID1 = 1L;
  private static final long MOCKED_ID2 = 2L;
  private static final String MOCKED_URL = "/employee/";
  private static final String MOCKED_SEARCH_URL = "/employee/search/";
  private static final String MOCKED_NAME = "Emp";
  private static final String MOCKED_INVALID_NAME = "";
  private static final boolean MOCKED_ACTIVE = true;

  @Test
  void getEmployeeById() throws Exception {
    Employee employee =
        Employee.builder()
            .employee_id(MOCKED_ID1)
            .employee_name(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(
                Department.builder()
                    .department_id(MOCKED_ID1)
                    .department_name(MOCKED_DEP_NAME)
                    .build())
            .build();
    when(employeeService.getEmployeeById(MOCKED_ID1)).thenReturn(employee);

    mockMvc
        .perform(get(MOCKED_URL + MOCKED_ID1))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.employee_id").value(MOCKED_ID1))
        .andExpect(jsonPath("$.department.department_id").value(MOCKED_ID1));
  }

  @Test
  void getEmployeeByName() throws Exception {
    Employee employee =
        Employee.builder()
            .employee_id(MOCKED_ID1)
            .employee_name(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(
                Department.builder()
                    .department_id(MOCKED_ID1)
                    .department_name(MOCKED_DEP_NAME)
                    .build())
            .build();
    when(employeeService.getEmployeesByName(MOCKED_NAME)).thenReturn(List.of(employee));

    mockMvc
        .perform(get(MOCKED_SEARCH_URL).param("name", MOCKED_NAME))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].employee_id").value(MOCKED_ID1))
        .andExpect(jsonPath("$[0].employee_name").value(MOCKED_NAME))
        .andExpect(jsonPath("$[0].department.department_id").value(MOCKED_ID1));
  }

  @Test
  void getAllEmployees() throws Exception {
    Employee employee =
        Employee.builder()
            .employee_id(MOCKED_ID1)
            .employee_name(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(
                Department.builder()
                    .department_id(MOCKED_ID1)
                    .department_name(MOCKED_DEP_NAME)
                    .build())
            .build();
    Employee employee2 =
        Employee.builder()
            .employee_id(MOCKED_ID2)
            .employee_name(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(
                Department.builder()
                    .department_id(MOCKED_ID2)
                    .department_name(MOCKED_DEP_NAME)
                    .build())
            .build();
    when(employeeService.getAllEmployees(0, 2)).thenReturn(List.of(employee, employee2));
    mockMvc
        .perform(get(MOCKED_URL).param("page", "0").param("amount", "2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].employee_id").value(MOCKED_ID1))
        .andExpect(jsonPath("$[0].department.department_id").value(MOCKED_ID1))
        .andExpect(jsonPath("$[1].employee_id").value(MOCKED_ID2))
        .andExpect(jsonPath("$[1].department.department_id").value(MOCKED_ID2));
  }

  @Test
  void updateEmployee() throws Exception {
    Employee employee =
        Employee.builder()
            .employee_id(MOCKED_ID1)
            .employee_name(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(
                Department.builder()
                    .department_id(MOCKED_ID1)
                    .department_name(MOCKED_DEP_NAME)
                    .build())
            .build();
    doNothing().when(employeeService).updateEmployee(employee);

    mockMvc
        .perform(
            put(MOCKED_URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(employee)))
        .andDo(print())
        .andExpect(status().isOk());
    verify(employeeService).updateEmployee(employee);
  }

  @Test
  void updateInvalidEmployee() throws Exception {
    Employee employee =
        Employee.builder()
            .employee_id(MOCKED_ID1)
            .employee_name(MOCKED_INVALID_NAME)
            .active(MOCKED_ACTIVE)
            .department(
                Department.builder()
                    .department_id(MOCKED_ID1)
                    .department_name(MOCKED_DEP_NAME)
                    .build())
            .build();
    doNothing().when(employeeService).updateEmployee(employee);

    mockMvc
        .perform(
            put(MOCKED_URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(employee)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void addEmployee() throws Exception {
    Employee employee =
        Employee.builder()
            .employee_id(MOCKED_ID1)
            .employee_name(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(
                Department.builder()
                    .department_id(MOCKED_ID1)
                    .department_name(MOCKED_DEP_NAME)
                    .build())
            .build();
    doNothing().when(employeeService).addEmployee(employee);

    mockMvc
        .perform(
            post(MOCKED_URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(employee)))
        .andDo(print())
        .andExpect(status().isCreated());
    verify(employeeService).addEmployee(employee);
  }

  @Test
  void addInvalidEmployee() throws Exception {
    Employee employee =
        Employee.builder()
            .employee_id(MOCKED_ID1)
            .employee_name(MOCKED_INVALID_NAME)
            .active(MOCKED_ACTIVE)
            .department(
                Department.builder()
                    .department_id(MOCKED_ID1)
                    .department_name(MOCKED_DEP_NAME)
                    .build())
            .build();
    doNothing().when(employeeService).addEmployee(employee);

    mockMvc
        .perform(
            post(MOCKED_URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(employee)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void deleteEmployee() throws Exception {
    doNothing().when(employeeService).deleteEmployee(MOCKED_ID1);

    mockMvc.perform(delete(MOCKED_URL + MOCKED_ID1)).andDo(print()).andExpect(status().isOk());
    verify(employeeService).deleteEmployee(MOCKED_ID1);
  }
}
