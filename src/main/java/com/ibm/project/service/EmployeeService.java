package com.ibm.project.service;

import com.ibm.project.dto.EmployeeDTO;
import com.ibm.project.model.Employee;
import com.ibm.project.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        LOGGER.info("[getAllEmployees()] method is called.");
        return employeeRepository.findAll()
                .stream()
                .map(e -> new EmployeeDTO(e.getId(), e.getName(), e.getJob()))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Integer id) {
        LOGGER.info("[getEmployeeById()] method is called. :: id={}", id);

        Employee employee = employeeRepository.getOne(id);
        if(employee == null) {
            LOGGER.error("[updateEmployee()] employee could not found. :: id={}", id);
            return new EmployeeDTO();
        }

        EmployeeDTO result = new EmployeeDTO(employee.getId(), employee.getName(), employee.getJob());
        LOGGER.info("[getEmployeeById()] employee info retrieved. :: employee={}", result);

        return result;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        LOGGER.info("[createEmployee()] method is called. :: employee={}", employeeDTO);

        boolean isExist = employeeRepository.exists(employeeDTO.getId());
        if(isExist) {
            LOGGER.error("[createEmployee()] employee is already exist. :: id={}", employeeDTO.getId());
            return new EmployeeDTO();
        }

        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setJob(employeeDTO.getJob());

        Employee emp = employeeRepository.saveAndFlush(employee);
        EmployeeDTO result = new EmployeeDTO(emp.getId(), emp.getName(), emp.getJob());
        LOGGER.info("[createEmployee()] employee info saved. :: employee={}", result);

        return result;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        LOGGER.info("[updateEmployee()] method is called. :: employee={}", employeeDTO);

        boolean isExist = employeeRepository.exists(employeeDTO.getId());
        if(!isExist) {
            LOGGER.error("[updateEmployee()] employee could not found. :: id={}", employeeDTO.getId());
            return new EmployeeDTO();
        }

        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setJob(employeeDTO.getJob());

        Employee emp = employeeRepository.save(employee);
        EmployeeDTO result = new EmployeeDTO(emp.getId(), emp.getName(), emp.getJob());
        LOGGER.info("[updateEmployee()] employee info updated. :: employee={}", result);

        return result;
    }
}

