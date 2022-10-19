package com.testtask.service.impl;

import com.testtask.entity.dto.EmployeeDTO;
import com.testtask.entity.dto.EmployeeDTOWithId;
import com.testtask.entity.mappers.EmployeeMapper;
import com.testtask.repository.inter.EmployeeRepo;
import com.testtask.service.inter.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepo employeeRepo;

  @Override
  public EmployeeDTOWithId getEmployeeById(long id) {
    log.info("Employee is going to be found by id");
    return EmployeeMapper.INSTANCE.toDtoWithId(employeeRepo.getEmployeeById(id));
  }

  @Override
  public List<EmployeeDTOWithId> getEmployeesByName(String name) {
    log.info("List of employees with the specific name is going to be obtained");
    return employeeRepo.getEmployeesByName(name).stream()
        .map(EmployeeMapper.INSTANCE::toDtoWithId)
        .collect(Collectors.toList());
  }

  @Override
  public List<EmployeeDTOWithId> getAllEmployees(int page, int amount) {
    log.info("List of employees is going to be obtained");
    return employeeRepo.getAllEmployees(amount, page * amount).stream()
        .map(EmployeeMapper.INSTANCE::toDtoWithId)
        .collect(Collectors.toList());
  }

  @Override
  public EmployeeDTOWithId addEmployee(EmployeeDTO employeeDTO) {
    log.info("Employee is going to be added");
    return EmployeeMapper.INSTANCE.toDtoWithId(
        employeeRepo.addEmployee(EmployeeMapper.INSTANCE.fromDto(employeeDTO)));
  }

  @Override
  public EmployeeDTOWithId updateEmployee(EmployeeDTOWithId employeeDTOWithId) {
    log.info("Employee is going to be updated");
    return EmployeeMapper.INSTANCE.toDtoWithId(
        employeeRepo.updateEmployee(EmployeeMapper.INSTANCE.fromDtoWithId(employeeDTOWithId)));
  }

  @Override
  public void deleteEmployee(long id) {
    log.info("Employee is going to be removed");
    employeeRepo.deleteEmployee(id);
  }
}
