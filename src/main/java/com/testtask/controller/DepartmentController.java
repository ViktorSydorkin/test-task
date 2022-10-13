package com.testtask.controller;

import com.testtask.eception.ControllerException;
import com.testtask.eception.ServiceException;
import com.testtask.entity.Department;
import com.testtask.service.inter.DepartmentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {
  private final DepartmentService departmentService;

  @ApiOperation(
      value = "Get department by its id",
      notes = "Get department by its id that was given as a path variable")
  @ApiImplicitParam(
      name = "id",
      value = "Id of the department",
      dataType = "Long",
      paramType = "path")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public Department getDepartment(@PathVariable long id) {
    return departmentService.getDepartmentById(id);
  }
  @ApiOperation(value = "Get all departments", notes = "Get all departments from the DB")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping()
  public List<Department> getAllDepartments() {
    try {
      return departmentService.getAllDepartments();
    } catch (ServiceException s) {
      throw new ControllerException(s.getMessage());
    }
  }

  @ApiOperation(value = "Update department", notes = "Updates department with the new given data")
  @ApiImplicitParam(
          name = "department",
          value = "Department to be updated",
          dataType = "Department",
          paramType = "request body")
  @ResponseStatus(HttpStatus.OK)
  @PutMapping()
  public void updateDepartment(@RequestBody @Valid Department department) {
    try {
      departmentService.updateDepartment(department);
    } catch (ServiceException s) {
      throw new ControllerException(s.getMessage());
    }
  }

  @ApiOperation(value = "Save department", notes = "Add department to the DB")
  @ApiImplicitParam(
          name = "department",
          value = "Department to be updated",
          dataType = "Department",
          paramType = "request body")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void addDepartment(@RequestBody @Valid Department department) {
    try {
      departmentService.addDepartment(department);
    } catch (ServiceException s) {
      throw new ControllerException(s.getMessage());
    }
  }

  @ApiOperation(
          value = "Delete department by its id",
          notes = "Delete department by its id that was given as a path variable")
  @ApiImplicitParam(
          name = "id",
          value = "Id of the department",
          dataType = "Long",
          paramType = "path")
  @DeleteMapping("/{id}")
  public void deleteDepartment(@PathVariable long id) {
    try {
      departmentService.deleteDepartment(id);
    } catch (ServiceException s) {
      throw new ControllerException(s.getMessage());
    }
  }
}
