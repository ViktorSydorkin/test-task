package com.testtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.entity.Department;
import com.testtask.entity.Employee;
import com.testtask.entity.dto.EmployeeDto;
import com.testtask.entity.mappers.EmployeeMapper;
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
  private static final String MOCKED_URL = "/employees/";
  private static final String MOCKED_SEARCH_URL = "/employees/search/";
  private static final String MOCKED_NAME = "Emp";
  private static final String MOCKED_INVALID_NAME = "";
  private static final boolean MOCKED_ACTIVE = true;

  @Test
  void getEmployeeById() throws Exception {
    EmployeeDto employee =
        EmployeeDto.builder()
            .employeeId(MOCKED_ID1)
            .employeeName(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(MOCKED_ID1)
            .build();
    when(employeeService.findById(MOCKED_ID1)).thenReturn(employee);

    mockMvc
        .perform(get(MOCKED_URL + MOCKED_ID1))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.employeeId").value(MOCKED_ID1))
        .andExpect(jsonPath("$.department").value(MOCKED_ID1));
  }

  @Test
  void getEmployeeByName() throws Exception {
    EmployeeDto employee =
        EmployeeDto.builder()
            .employeeId(MOCKED_ID1)
            .employeeName(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(MOCKED_ID1)
            .build();
    when(employeeService.findByName(MOCKED_NAME)).thenReturn(List.of(employee));

    mockMvc
        .perform(get(MOCKED_SEARCH_URL).param("name", MOCKED_NAME))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].employeeId").value(MOCKED_ID1))
        .andExpect(jsonPath("$[0].employeeName").value(MOCKED_NAME))
        .andExpect(jsonPath("$[0].department").value(MOCKED_ID1));
  }

  @Test
  void getAllEmployees() throws Exception {
    EmployeeDto employee =
        EmployeeDto.builder()
            .employeeId(MOCKED_ID1)
            .employeeName(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(MOCKED_ID1)
            .build();
    EmployeeDto employee2 =
        EmployeeDto.builder()
            .employeeId(MOCKED_ID2)
            .employeeName(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(MOCKED_ID2)
            .build();
    when(employeeService.findAll(0, 2)).thenReturn(List.of(employee, employee2));
    mockMvc
        .perform(get(MOCKED_URL).param("page", "0").param("amount", "2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].employeeId").value(MOCKED_ID1))
        .andExpect(jsonPath("$[0].department").value(MOCKED_ID1))
        .andExpect(jsonPath("$[1].employeeId").value(MOCKED_ID2))
        .andExpect(jsonPath("$[1].department").value(MOCKED_ID2));
  }

  @Test
  void updateEmployee() throws Exception {
    EmployeeDto employee =
        EmployeeDto.builder()
            .employeeId(MOCKED_ID1)
            .employeeName(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(MOCKED_ID1)
            .build();
    when(employeeService.update(employee)).thenReturn(employee);

    mockMvc
        .perform(
            put(MOCKED_URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(employee)))
        .andDo(print())
        .andExpect(status().isOk());
    verify(employeeService).update(employee);
  }

  @Test
  void updateInvalidEmployee() throws Exception {
    EmployeeDto employee =
        EmployeeDto.builder()
            .employeeId(MOCKED_ID1)
            .employeeName(MOCKED_INVALID_NAME)
            .active(MOCKED_ACTIVE)
            .department(MOCKED_ID1)
            .build();
    when(employeeService.update(employee)).thenReturn(employee);

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
            .employeeId(MOCKED_ID1)
            .employeeName(MOCKED_NAME)
            .active(MOCKED_ACTIVE)
            .department(
                Department.builder()
                    .departmentId(MOCKED_ID1)
                    .departmentName(MOCKED_DEP_NAME)
                    .build())
            .build();
    when(employeeService.create(EmployeeMapper.INSTANCE.toPostDto(employee)))
        .thenReturn(EmployeeMapper.INSTANCE.toDto(employee));

    mockMvc
        .perform(
            post(MOCKED_URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(EmployeeMapper.INSTANCE.toPostDto(employee))))
        .andDo(print())
        .andExpect(status().isCreated());
    verify(employeeService).create(EmployeeMapper.INSTANCE.toPostDto(employee));
  }

  @Test
  void addInvalidEmployee() throws Exception {
    Employee employee =
        Employee.builder()
            .employeeId(MOCKED_ID1)
            .employeeName(MOCKED_INVALID_NAME)
            .active(MOCKED_ACTIVE)
            .department(
                Department.builder()
                    .departmentId(MOCKED_ID1)
                    .departmentName(MOCKED_DEP_NAME)
                    .build())
            .build();
    when(employeeService.create(EmployeeMapper.INSTANCE.toPostDto(employee)))
        .thenReturn(EmployeeMapper.INSTANCE.toDto(employee));

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
    doNothing().when(employeeService).deleteById(MOCKED_ID1);

    mockMvc.perform(delete(MOCKED_URL + MOCKED_ID1)).andDo(print()).andExpect(status().isOk());
    verify(employeeService).deleteById(MOCKED_ID1);
  }
}
