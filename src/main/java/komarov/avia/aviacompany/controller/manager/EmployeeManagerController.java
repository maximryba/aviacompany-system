package komarov.avia.aviacompany.controller.manager;

import komarov.avia.aviacompany.entity.Employee;
import komarov.avia.aviacompany.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/employee")
public class EmployeeManagerController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public String allEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "manager/employee";
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

    @PostMapping("/dedlete/{id}")
    public String dedleteEmployee(@PathVariable int id) {
        this.employeeService.delete(id);
        return "redirect:/manager/employee/all";
    }

}
