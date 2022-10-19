package com.testtask.controller;

import com.testtask.entity.dto.EmployeeDTO;
import com.testtask.entity.dto.EmployeeDTOWithId;
import com.testtask.service.inter.EmployeeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
  private final EmployeeService employeeService;

  @ApiOperation(
      value = "Get employee by its id",
      notes = "Get employee by its id that was given as a path variable",
      httpMethod = "GET",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParam(
      name = "id",
      value = "Id of the employee",
      dataType = "Long",
      paramType = "path")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public EmployeeDTOWithId getEmployeeById(@PathVariable long id) {
    return employeeService.getEmployeeById(id);
  }

  @ApiOperation(
      value = "Get employee by name",
      notes = "Get employee by its a specific regex that tha its name should start with",
      httpMethod = "GET",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParam(
      name = "name",
      value = "Name of the employee",
      dataType = "String",
      paramType = "query")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/search")
  public List<EmployeeDTOWithId> getEmployeeByName(@RequestParam("name") @NotNull String name) {
    return employeeService.getEmployeesByName(name);
  }

  @ApiOperation(
      value = "Get all employees",
      notes = "Get employees with pagination",
      httpMethod = "GET",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "page",
        value = "Number of the page",
        dataType = "Integer",
        example = "0",
        defaultValue = "0",
        paramType = "query"),
    @ApiImplicitParam(
        name = "amount",
        value = "Amount of users on the page",
        dataType = "Integer",
        example = "5",
        defaultValue = "5",
        paramType = "query")
  })
  @ResponseStatus(HttpStatus.OK)
  @GetMapping()
  public List<EmployeeDTOWithId> getAllEmployees(
      @RequestParam(defaultValue = "0") @PositiveOrZero int page,
      @RequestParam(defaultValue = "5") @Positive int amount) {
    return employeeService.getAllEmployees(page, amount);
  }

  @ApiOperation(
      value = "Update employee",
      notes = "Updates employee with the new given data",
      httpMethod = "PUT",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PutMapping()
  public EmployeeDTOWithId updateEmployee(@RequestBody @Valid EmployeeDTOWithId employeeDTOWithId) {
    return employeeService.updateEmployee(employeeDTOWithId);
  }

  @ApiOperation(
      value = "Save employee",
      notes = "Add employee to the DB",
      httpMethod = "POST",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  public EmployeeDTOWithId addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
    return employeeService.addEmployee(employeeDTO);
  }

  @ApiOperation(
      value = "Delete employee by its id",
      notes = "Delete employee by its id that was given as a path variable",
      httpMethod = "DELETE",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParam(
      name = "id",
      value = "Id of the employee",
      dataType = "Long",
      paramType = "path")
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable @Positive long id) {
    employeeService.deleteEmployee(id);
  }
}
