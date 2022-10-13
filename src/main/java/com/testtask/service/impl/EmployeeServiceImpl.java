package com.testtask.service.impl;

import com.testtask.eception.RepositoryException;
import com.testtask.eception.ServiceException;
import com.testtask.entity.Employee;
import com.testtask.repository.inter.EmployeeRepo;
import com.testtask.service.inter.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Override
    public Employee getEmployeeById(long id) {
        try {
            log.info("Employee is going to be found by id");
            return employeeRepo.getEmployeeById(id);
        } catch (RepositoryException e) {
            log.error("Service has caught an exception and throws his own { }", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        try {
            log.info("List of employees with the specific name is going to be obtained");
            return employeeRepo.getEmployeesByName(name);
        } catch (RepositoryException e) {
            log.error("Service has caught an exception and throws his own { }", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees(int page, int amount) {
        try {
            log.info("List of employees is going to be obtained");
            return employeeRepo.getAllEmployees(amount, page * amount);
        } catch (RepositoryException e) {
            log.error("Service has caught an exception and throws his own { }", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        try {
            log.info("Employee is going to be added");
            employeeRepo.addEmployee(employee);
        } catch (RepositoryException e) {
            log.error("Service has caught an exception and throws his own { }", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try {
            log.info("Employee is going to be updated");
            employeeRepo.updateEmployee(employee);
        } catch (RepositoryException e) {
            log.error("Service has caught an exception and throws his own { }", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(long id) {
        try {
            log.info("Employee is going to be removed");
            employeeRepo.deleteEmployee(id);
        } catch (RepositoryException e) {
            log.error("Service has caught an exception and throws his own { }", e);
            throw new ServiceException(e.getMessage());
        }
    }
}
