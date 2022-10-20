package com.testtask.service.impl;

import com.testtask.entity.dto.EmployeePostDto;
import com.testtask.entity.dto.EmployeeDto;
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
  public EmployeeDto findById(long id) {
    log.info("Employee is going to be found by id");
    return EmployeeMapper.INSTANCE.toDtoWithId(employeeRepo.getEmployeeById(id));
  }

  @Override
  public List<EmployeeDto> findByName(String name) {
    log.info("List of employees with the specific name is going to be obtained");
    return employeeRepo.getEmployeesByName(name).stream()
        .map(EmployeeMapper.INSTANCE::toDtoWithId)
        .collect(Collectors.toList());
  }

  @Override
  public List<EmployeeDto> findAll(int page, int amount) {
    log.info("List of employees is going to be obtained");
    return employeeRepo.getAllEmployees(amount, page * amount).stream()
        .map(EmployeeMapper.INSTANCE::toDtoWithId)
        .collect(Collectors.toList());
  }

  @Override
  public EmployeeDto create(EmployeePostDto employeePostDto) {
    log.info("Employee is going to be added");
    return EmployeeMapper.INSTANCE.toDtoWithId(
        employeeRepo.addEmployee(EmployeeMapper.INSTANCE.fromDto(employeePostDto)));
  }

  @Override
  public EmployeeDto update(EmployeeDto employeeDto) {
    log.info("Employee is going to be updated");
    return EmployeeMapper.INSTANCE.toDtoWithId(
        employeeRepo.updateEmployee(EmployeeMapper.INSTANCE.fromDtoWithId(employeeDto)));
  }

  @Override
  public void deleteById(long id) {
    log.info("Employee is going to be removed");
    employeeRepo.deleteEmployee(id);
  }
}
