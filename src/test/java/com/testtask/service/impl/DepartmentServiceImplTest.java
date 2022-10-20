package com.testtask.service.impl;

import com.testtask.entity.Department;
import com.testtask.entity.mappers.DepartmentMapper;
import com.testtask.repository.inter.DepartmentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

  @Mock DepartmentRepo departmentRepo;

  @InjectMocks DepartmentServiceImpl departmentService;

  private static final long MOCKED_ID1 = 1L;
  private static final long MOCKED_ID2 = 2L;
  private static final String MOCKED_NAME = "Name";

  @Test
  void getDepartmentById() {
    Department department = Department.builder().departmentId(MOCKED_ID1).build();
    when(departmentRepo.getDepartmentById(MOCKED_ID1)).thenReturn(department);

    assertEquals(
        DepartmentMapper.INSTANCE.toDto(department),
        departmentService.findById(MOCKED_ID1));
  }

  @Test
  void getAllDepartments() {
    Department department = Department.builder().departmentId(MOCKED_ID1).build();
    Department department2 = Department.builder().departmentId(MOCKED_ID2).build();
    List<Department> departmentList = List.of(department, department2);

    when(departmentRepo.getAllDepartments()).thenReturn(departmentList);
    assertThat(
        departmentService.findAll(),
        containsInAnyOrder(
            DepartmentMapper.INSTANCE.toDto(department),
            DepartmentMapper.INSTANCE.toDto(department2)));
  }

  @Test
  void addDepartment() {
    Department department = Department.builder().departmentName(MOCKED_NAME).build();
    when(departmentRepo.addDepartment(department)).thenReturn(department);

    departmentService.create(DepartmentMapper.INSTANCE.toPostDto(department));
    verify(departmentRepo, times(1)).addDepartment(department);
  }

  @Test
  void updateDepartment() {
    Department department = Department.builder().departmentId(MOCKED_ID1).build();
    when(departmentRepo.updateDepartment(department)).thenReturn(department);

    departmentService.update(DepartmentMapper.INSTANCE.toDto(department));
    verify(departmentRepo, times(1)).updateDepartment(department);
  }

  @Test
  void deleteDepartment() {
    departmentService.deleteById(MOCKED_ID1);
    verify(departmentRepo, times(1)).deleteDepartment(MOCKED_ID1);
  }
}
