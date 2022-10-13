package com.testtask.service.impl;

import com.testtask.eception.RepositoryException;
import com.testtask.eception.ServiceException;
import com.testtask.entity.Department;
import com.testtask.repository.inter.DepartmentRepo;
import com.testtask.service.inter.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
  private final DepartmentRepo departmentRepo;

  @Override
  public Department getDepartmentById(long id) {
    try {
      log.info("Department is going to be found by id");
      return departmentRepo.getDepartmentById(id);
    } catch (RepositoryException e) {
      log.error("Service has caught an exception and throws his own { }", e);
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<Department> getAllDepartments() {
    try {
      log.info("List of departments is going to be obtained");
      return departmentRepo.getAllDepartments();
    } catch (RepositoryException e) {
      log.error("Service has caught an exception and throws his own { }", e);
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public void addDepartment(Department department) {
    try {
      log.info("Department is going to be added");
      departmentRepo.addDepartment(department);
    } catch (RepositoryException e) {
      log.error("Service has caught an exception and throws his own { }", e);
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public void updateDepartment(Department department) {
    try {
      log.info("Department is going to be updated");
      departmentRepo.updateDepartment(department);
    } catch (RepositoryException e) {
      log.error("Service has caught an exception and throws his own { }", e);
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public void deleteDepartment(long id) {
    try {
      log.info("Department is going to be removed");
      departmentRepo.deleteDepartment(id);
    } catch (RepositoryException e) {
      log.error("Service has caught an exception and throws his own { }", e);
      throw new ServiceException(e.getMessage());
    }
  }
}
