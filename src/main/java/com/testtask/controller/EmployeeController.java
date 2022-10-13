package com.testtask.controller;

import com.testtask.eception.ControllerException;
import com.testtask.eception.ServiceException;
import com.testtask.entity.Employee;
import com.testtask.service.inter.EmployeeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
  private final EmployeeService employeeService;

  @ApiOperation(
      value = "Get employee by its id",
      notes = "Get employee by its id that was given as a path variable")
  @ApiImplicitParam(name = "id", value = "Id of the employee", dataType = "Long", paramType = "path")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public Employee getEmployeeById(@PathVariable long id) {
    try {
      return employeeService.getEmployeeById(id);
    } catch (ServiceException e) {
      throw new ControllerException(e.getMessage());
    }
  }

  @ApiOperation(
      value = "Get employee by name",
      notes = "Get employee by its a specific regex that tha its name should start with")
  @ApiImplicitParam(
      name = "name",
      value = "Name of the employee",
      dataType = "String",
      paramType = "request param")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/search")
  public List<Employee> getEmployeeByName(@RequestParam("name") String name) {
    try {
      return employeeService.getEmployeesByName(name);
    } catch (ServiceException e) {
      throw new ControllerException(e.getMessage());
    }
  }

  @ApiOperation(value = "Get all employees", notes = "Get employees with pagination")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "page",
        value = "Number of the page",
        dataType = "Integer",
        paramType = "request param"),
    @ApiImplicitParam(
        name = "amount",
        value = "Amount of users on the page",
        dataType = "Integer",
        paramType = "request param")
  })
  @ResponseStatus(HttpStatus.OK)
  @GetMapping()
  public List<Employee> getAllEmployees(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int amount) {
    try {
      return employeeService.getAllEmployees(page, amount);
    } catch (ServiceException e) {
      throw new ControllerException(e.getMessage());
    }
  }

  @ApiOperation(value = "Update employee", notes = "Updates employee with the new given data")
  @ApiImplicitParam(
      name = "employee",
      value = "Employee to be updated",
      dataType = "Employee",
      paramType = "request body")
  @ResponseStatus(HttpStatus.OK)
  @PutMapping()
  public void updateEmployee(@RequestBody @Valid Employee employee) {
    try {
      employeeService.updateEmployee(employee);
    } catch (ServiceException e) {
      throw new ControllerException(e.getMessage());
    }
  }

  @ApiOperation(value = "Save employee", notes = "Add employee to the DB")
  @ApiImplicitParam(
      name = "employee",
      value = "Employee to be added to the DB",
      dataType = "Employee",
      paramType = "request body")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  public void addEmployee(@RequestBody @Valid Employee employee) {
    try {
      employeeService.addEmployee(employee);
    } catch (ServiceException e) {
      throw new ControllerException(e.getMessage());
    }
  }

  @ApiOperation(
      value = "Delete employee by its id",
      notes = "Delete employee by its id that was given as a path variable")
  @ApiImplicitParam(
      name = "id",
      value = "Id of the employee",
      dataType = "Long",
      paramType = "path")
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable long id) {
    try {
      employeeService.deleteEmployee(id);
    } catch (ServiceException e) {
      throw new ControllerException(e.getMessage());
    }
  }
}
