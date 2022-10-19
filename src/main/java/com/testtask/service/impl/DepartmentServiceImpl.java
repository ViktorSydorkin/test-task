package com.testtask.service.impl;

import com.testtask.entity.dto.DepartmentDTO;
import com.testtask.entity.dto.DepartmentDTOWithId;
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
  public DepartmentDTOWithId getDepartmentById(long id) {
    log.info("Department is going to be found by id");
    return DepartmentMapper.INSTANCE.toDtoWithId(departmentRepo.getDepartmentById(id));
  }

  @Override
  public List<DepartmentDTOWithId> getAllDepartments() {
    log.info("List of departments is going to be obtained");
    return departmentRepo.getAllDepartments().stream()
        .map(DepartmentMapper.INSTANCE::toDtoWithId)
        .collect(Collectors.toList());
  }

  @Override
  public DepartmentDTOWithId addDepartment(DepartmentDTO departmentDTO) {
    log.info("Department is going to be added");
    return DepartmentMapper.INSTANCE.toDtoWithId(
        departmentRepo.addDepartment(DepartmentMapper.INSTANCE.fromDto(departmentDTO)));
  }

  @Override
  public DepartmentDTOWithId updateDepartment(DepartmentDTOWithId departmentDTOWithId) {
    log.info("Department is going to be updated");
    return DepartmentMapper.INSTANCE.toDtoWithId(
        departmentRepo.updateDepartment(
            DepartmentMapper.INSTANCE.fromDtoWithId(departmentDTOWithId)));
  }

  @Override
  public void deleteDepartment(long id) {
    log.info("Department is going to be removed");
    departmentRepo.deleteDepartment(id);
  }
}
