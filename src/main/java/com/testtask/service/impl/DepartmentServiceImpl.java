package com.testtask.service.impl;

import com.testtask.entity.dto.DepartmentDto;
import com.testtask.entity.dto.DepartmentPostDto;
import com.testtask.entity.mappers.DepartmentMapper;
import com.testtask.repository.inter.DepartmentRepo;
import com.testtask.service.inter.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
  private final DepartmentRepo departmentRepo;

  @Override
  public DepartmentDto findById(long id) {
    log.info("Department is going to be found by id");
    return DepartmentMapper.INSTANCE.toDto(departmentRepo.getDepartmentById(id));
  }

  @Override
  public List<DepartmentDto> findAll() {
    log.info("List of departments is going to be obtained");
    return departmentRepo.getAllDepartments().stream()
        .map(DepartmentMapper.INSTANCE::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public DepartmentDto create(DepartmentPostDto departmentPostDto) {
    log.info("Department is going to be added");
    return DepartmentMapper.INSTANCE.toDto(
        departmentRepo.addDepartment(DepartmentMapper.INSTANCE.fromPostDto(departmentPostDto)));
  }

  @Override
  public DepartmentDto update(DepartmentDto departmentDto) {
    log.info("Department is going to be updated");
    return DepartmentMapper.INSTANCE.toDto(
        departmentRepo.updateDepartment(
            DepartmentMapper.INSTANCE.fromDto(departmentDto)));
  }

  @Override
  public void deleteById(long id) {
    log.info("Department is going to be removed");
    departmentRepo.deleteDepartment(id);
  }
}
