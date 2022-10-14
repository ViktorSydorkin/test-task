package com.testtask.service.impl;

import com.testtask.eception.RepositoryException;
import com.testtask.eception.ServiceException;
import com.testtask.entity.Department;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

  @Mock DepartmentRepo departmentRepo;

  @InjectMocks DepartmentServiceImpl departmentService;

  private static final long MOCKED_ID1 = 1L;
  private static final long MOCKED_ID2 = 2L;

  @Test
  void getDepartmentById() {
    Department department = Department.builder().department_id(MOCKED_ID1).build();
    when(departmentRepo.getDepartmentById(MOCKED_ID1)).thenReturn(department);

    assertEquals(department, departmentService.getDepartmentById(MOCKED_ID1));
  }

  @Test
  void getDepartmentByIdThrow() {
    doThrow(RepositoryException.class).when(departmentRepo).getDepartmentById(MOCKED_ID1);

    assertThrows(ServiceException.class, () -> departmentService.getDepartmentById(MOCKED_ID1));
  }

  @Test
  void getAllDepartments() {
    Department department = Department.builder().department_id(MOCKED_ID1).build();
    Department department2 = Department.builder().department_id(MOCKED_ID2).build();
    List<Department> departmentList = List.of(department, department2);

    when(departmentRepo.getAllDepartments()).thenReturn(departmentList);
    assertThat(departmentService.getAllDepartments(), containsInAnyOrder(department, department2));
  }

  @Test
  void getAllDepartmentsThrow() {
    doThrow(RepositoryException.class).when(departmentRepo).getAllDepartments();

    assertThrows(ServiceException.class, () -> departmentService.getAllDepartments());
  }

  @Test
  void addDepartment() {
    Department department = Department.builder().department_id(MOCKED_ID1).build();

    departmentService.addDepartment(department);
    verify(departmentRepo, times(1)).addDepartment(department);
  }

  @Test
  void addDepartmentThrow() {
    Department department = Department.builder().department_id(MOCKED_ID1).build();
    doThrow(RepositoryException.class).when(departmentRepo).addDepartment(department);

    assertThrows(ServiceException.class, () -> departmentService.addDepartment(department));
  }

  @Test
  void updateDepartment() {
    Department department = Department.builder().department_id(MOCKED_ID1).build();

    departmentService.updateDepartment(department);
    verify(departmentRepo, times(1)).updateDepartment(department);
  }

  @Test
  void updateDepartmentThrow() {
    Department department = Department.builder().department_id(MOCKED_ID1).build();
    doThrow(RepositoryException.class).when(departmentRepo).updateDepartment(department);

    assertThrows(ServiceException.class, () -> departmentService.updateDepartment(department));
  }

  @Test
  void deleteDepartment() {
    departmentService.deleteDepartment(MOCKED_ID1);
    verify(departmentRepo, times(1)).deleteDepartment(MOCKED_ID1);
  }

  @Test
  void deleteDepartmentThrow() {
    doThrow(RepositoryException.class).when(departmentRepo).deleteDepartment(MOCKED_ID1);

    assertThrows(ServiceException.class, () -> departmentService.deleteDepartment(MOCKED_ID1));
  }
}
