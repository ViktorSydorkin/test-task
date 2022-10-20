package com.testtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.entity.Department;
import com.testtask.entity.dto.DepartmentDto;
import com.testtask.entity.mappers.DepartmentMapper;
import com.testtask.service.inter.DepartmentService;
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

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
  @MockBean private DepartmentService departmentService;
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  private static final long MOCKED_ID1 = 1L;
  private static final long MOCKED_ID2 = 2L;
  private static final String MOCKED_NAME = "Name";
  private static final String MOCKED_INVALID_NAME = "";
  private static final String MOCKED_URL = "/departments/";

  @Test
  void getDepartment() throws Exception {
    DepartmentDto department =
        DepartmentDto.builder().departmentId(MOCKED_ID1).departmentName(MOCKED_NAME).build();
    when(departmentService.findById(MOCKED_ID1)).thenReturn(department);

    mockMvc
        .perform(get(MOCKED_URL + MOCKED_ID1))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.departmentId").value(MOCKED_ID1));
  }

  @Test
  void getAllDepartments() throws Exception {
    DepartmentDto department =
        DepartmentDto.builder().departmentId(MOCKED_ID1).departmentName(MOCKED_NAME).build();
    DepartmentDto department2 =
        DepartmentDto.builder().departmentId(MOCKED_ID2).departmentName(MOCKED_NAME).build();
    when(departmentService.findAll()).thenReturn(List.of(department, department2));
    mockMvc
        .perform(get(MOCKED_URL))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].departmentId").value(MOCKED_ID1))
        .andExpect(jsonPath("$[1].departmentId").value(MOCKED_ID2));
  }

  @Test
  void updateDepartment() throws Exception {
    DepartmentDto department =
        DepartmentDto.builder().departmentId(MOCKED_ID1).departmentName(MOCKED_NAME).build();
    when(departmentService.update(department)).thenReturn(department);
    mockMvc
        .perform(
            put(MOCKED_URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(department)))
        .andDo(print())
        .andExpect(status().isOk());
    verify(departmentService).update(department);
  }

  @Test
  void updateInvalidDepartment() throws Exception {
    DepartmentDto department =
        DepartmentDto.builder()
            .departmentId(MOCKED_ID1)
            .departmentName(MOCKED_INVALID_NAME)
            .build();
    when(departmentService.update(department)).thenReturn(department);
    mockMvc
        .perform(
            put(MOCKED_URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(department)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void addDepartment() throws Exception {
    Department department =
        Department.builder().departmentId(MOCKED_ID1).departmentName(MOCKED_NAME).build();
    when(departmentService.create(DepartmentMapper.INSTANCE.toPostDto(department)))
        .thenReturn(DepartmentMapper.INSTANCE.toDto(department));

    mockMvc
        .perform(
            post(MOCKED_URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(department)))
        .andDo(print())
        .andExpect(status().isCreated());
    verify(departmentService).create(DepartmentMapper.INSTANCE.toPostDto(department));
  }

  @Test
  void addInvalidDepartment() throws Exception {
    Department department =
        Department.builder().departmentId(MOCKED_ID1).departmentName(MOCKED_INVALID_NAME).build();
    when(departmentService.create(DepartmentMapper.INSTANCE.toPostDto(department)))
        .thenReturn(DepartmentMapper.INSTANCE.toDto(department));

    mockMvc
        .perform(
            post(MOCKED_URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(department)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void deleteDepartment() throws Exception {
    doNothing().when(departmentService).deleteById(MOCKED_ID1);

    mockMvc.perform(delete(MOCKED_URL + MOCKED_ID1)).andDo(print()).andExpect(status().isOk());
    verify(departmentService).deleteById(MOCKED_ID1);
  }
}
