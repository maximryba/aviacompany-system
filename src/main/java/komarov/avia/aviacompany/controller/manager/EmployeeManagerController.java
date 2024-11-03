package komarov.avia.aviacompany.controller.manager;

import komarov.avia.aviacompany.entity.Employee;
import komarov.avia.aviacompany.service.EmployeeService;
import komarov.avia.aviacompany.service.FlightsService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/employee")
public class EmployeeManagerController {

    private final EmployeeService employeeService;
    
    private final FlightsService flightsService;

    @GetMapping("/all")
    public String allEmployees(Model model) {
    	List<String> positions = List.of("Пилот", "Стюардесса", "Инженер");
    	model.addAttribute("positions", positions);
        model.addAttribute("employees", employeeService.findAll());
        return "manager/employee";
    }
    
    @GetMapping("/assign")
    public String assignEmployee(Model model) {
    	model.addAttribute("employees", this.employeeService.findAll());
    	model.addAttribute("flights", this.flightsService.findAll());
    	return "manager/employee-flights";
    }
    
    @PostMapping("assign/{id}")
    public String assignEmployeePost(@ModelAttribute Employee employee, @PathVariable int id) {
    	this.employeeService.assign(employee, id);
    	return "redirect:/manager/employee/assign";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        this.employeeService.save(employee);
        return "redirect:/manager/employee/all";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, @ModelAttribute Employee employee) {
        this.employeeService.update(employee, id);
        return "redirect:/manager/employee/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        this.employeeService.delete(id);
        return "redirect:/manager/employee/all";
    }

}
