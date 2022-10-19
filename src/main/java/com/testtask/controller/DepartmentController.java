package com.testtask.controller;

import com.testtask.entity.dto.DepartmentDTO;
import com.testtask.entity.dto.DepartmentDTOWithId;
import com.testtask.exception.ControllerException;
import com.testtask.exception.ServiceException;
import com.testtask.service.inter.DepartmentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
  private final DepartmentService departmentService;

  @ApiOperation(
      value = "Get department by its id",
      notes = "Get department by its id that was given as a path variable",
      httpMethod = "GET",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParam(
      name = "id",
      value = "Id of the department",
      dataType = "Long",
      paramType = "path")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public DepartmentDTOWithId getDepartment(@PathVariable @Positive long id) {
    return departmentService.getDepartmentById(id);
  }

  @ApiOperation(
      value = "Get all departments",
      notes = "Get all departments from the DB",
      httpMethod = "GET",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @GetMapping()
  public List<DepartmentDTOWithId> getAllDepartments() {
      return departmentService.getAllDepartments();
  }

  @ApiOperation(
      value = "Update department",
      notes = "Updates department with the new given data",
      httpMethod = "PUT",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PutMapping()
  public DepartmentDTOWithId updateDepartment(
      @RequestBody @Valid DepartmentDTOWithId departmentDTOWithId) {
      return departmentService.updateDepartment(departmentDTOWithId);
  }

  @ApiOperation(
      value = "Save department",
      notes = "Add department to the DB",
      httpMethod = "POST",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public DepartmentDTOWithId addDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
      return departmentService.addDepartment(departmentDTO);
  }

  @ApiOperation(
      value = "Delete department by its id",
      notes = "Delete department by its id that was given as a path variable",
      httpMethod = "DELETE")
  @ApiImplicitParam(
      name = "id",
      value = "Id of the department",
      dataType = "Long",
      paramType = "path")
  @DeleteMapping("/{id}")
  public void deleteDepartment(@PathVariable @Positive long id) {
      departmentService.deleteDepartment(id);
  }
}
