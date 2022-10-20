package com.testtask.controller;

import com.testtask.entity.dto.DepartmentPostDto;
import com.testtask.entity.dto.DepartmentDto;
import com.testtask.service.inter.DepartmentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
  public DepartmentDto findById(@PathVariable @Positive long id) {
    return departmentService.findById(id);
  }

  @ApiOperation(
      value = "Get all departments",
      notes = "Get all departments from the DB",
      httpMethod = "GET",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<DepartmentDto> findAll() {
      return departmentService.findAll();
  }

  @ApiOperation(
      value = "Update department",
      notes = "Updates department with the new given data",
      httpMethod = "PUT",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PutMapping
  public DepartmentDto update(
      @RequestBody @Valid DepartmentDto departmentDto) {
      return departmentService.update(departmentDto);
  }

  @ApiOperation(
      value = "Save department",
      notes = "Add department to the DB",
      httpMethod = "POST",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public DepartmentDto create(@RequestBody @Valid DepartmentPostDto departmentPostDto) {
      return departmentService.create(departmentPostDto);
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
  public void deleteById(@PathVariable @Positive long id) {
      departmentService.deleteById(id);
  }
}
