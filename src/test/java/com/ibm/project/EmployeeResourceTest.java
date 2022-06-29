package com.ibm.project;

import com.ibm.project.dto.EmployeeDTO;
import com.ibm.project.model.Employee;
import com.ibm.project.repository.EmployeeRepository;
import com.ibm.project.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeResourceTest {

	@InjectMocks
    private EmployeeService employeeService;

	@Mock
    private EmployeeRepository employeeRepository;

	@Test
	public void getAll() {
        when(employeeRepository.findAll()).thenReturn(createEmployees());
        when(employeeService.getAllEmployees()).thenReturn(createEmployeeDTOs());
    }

	@Test
	public void getOne() {
	    Integer id = 1;
        when(employeeRepository.getOne(id)).thenReturn(createEmployee());
        when(employeeService.getEmployeeById(id)).thenReturn(createEmployeeDto());
    }

	@Test
	public void getOneWithNonExistEmp() {
	    Integer id = 1;
        when(employeeRepository.getOne(id)).thenReturn(null);
        when(employeeService.getEmployeeById(id)).thenReturn(new EmployeeDTO());
    }

	@Test
	public void insertEmployee() {
	    EmployeeDTO employeeDTO = createEmployeeDto();
	    Employee employee = convertDTOToModel(employeeDTO);
        when(employeeRepository.exists(employeeDTO.getId())).thenReturn(false);
        when(employeeRepository.saveAndFlush(any())).thenReturn(employee);
        when(employeeService.createEmployee(employeeDTO)).thenReturn(employeeDTO);
    }

	@Test
	public void updEmployee() {
	    EmployeeDTO employeeDTO = createEmployeeDto();
	    Employee employee = convertDTOToModel(employeeDTO);
        when(employeeRepository.exists(employeeDTO.getId())).thenReturn(true);
        when(employeeRepository.save((Employee) any())).thenReturn(employee);
        when(employeeService.updateEmployee(employeeDTO)).thenReturn(employeeDTO);
    }

    private Employee convertDTOToModel(EmployeeDTO employeeDTO) {
        Employee emp1 = createEmployee();
        emp1.setId(employeeDTO.getId());
        emp1.setName(employeeDTO.getName());
        emp1.setJob(employeeDTO.getJob());

        return emp1;
    }

    private Employee createEmployee() {
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setName("test");
        emp1.setJob("test");

        return emp1;
    }

    private EmployeeDTO createEmployeeDto() {
        EmployeeDTO emp1 = new EmployeeDTO();
        emp1.setId(1);
        emp1.setName("test");
        emp1.setJob("test");

        return emp1;
    }

    private List<Employee> createEmployees() {
	    List<Employee> list = new ArrayList<>();

	    Employee emp1 = new Employee();
	    emp1.setId(1);
	    emp1.setName("test");
	    emp1.setJob("test");

	    Employee emp2 = new Employee();
	    emp1.setId(2);
	    emp1.setName("test");
	    emp1.setJob("test");

	    Employee emp3 = new Employee();
	    emp1.setId(3);
	    emp1.setName("test");
	    emp1.setJob("test");

        list.add(emp1);
        list.add(emp2);
        list.add(emp3);

	    return list;
    }

    private List<EmployeeDTO> createEmployeeDTOs() {
	    List<EmployeeDTO> list = new ArrayList<>();

        EmployeeDTO emp1 = new EmployeeDTO();
	    emp1.setId(1);
	    emp1.setName("test");
	    emp1.setJob("test");

        EmployeeDTO emp2 = new EmployeeDTO();
	    emp1.setId(2);
	    emp1.setName("test");
	    emp1.setJob("test");

        EmployeeDTO emp3 = new EmployeeDTO();
	    emp1.setId(3);
	    emp1.setName("test");
	    emp1.setJob("test");

        list.add(emp1);
        list.add(emp2);
        list.add(emp3);

	    return list;
    }

}
