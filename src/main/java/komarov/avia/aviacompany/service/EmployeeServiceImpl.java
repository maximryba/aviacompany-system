package komarov.avia.aviacompany.service;

import komarov.avia.aviacompany.entity.Employee;
import komarov.avia.aviacompany.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return this.employeeRepository.findByName(name);
    }

    @Transactional
    @Override
    public int save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void update(Employee employee, int id) {
        this.employeeRepository.update(employee, id);
    }

    @Transactional
    @Override
    public void delete(int id) {
        this.employeeRepository.delete(id);
    }
    
    @Transactional
    @Override
    public void assign(Employee employee, int id) {
    	this.employeeRepository.assign(employee, id);
    }
}
